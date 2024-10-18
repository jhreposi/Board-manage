package com.example.board.service;

import com.example.board.model.Article;
import com.example.board.model.FileVo;
import com.example.board.repository.FileMapper;
import com.example.board.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class FileService {
    FileMapper fileMapper;
    ArticleService articleService;
    final String[] ALLOWED_EXTENSION = {"jpg", "gif", "png", "zip"};
    final int MAX_FILE_COUNT = 5;

    @Value("${spring.servlet.multipart.location}")
    String dir;

    public FileService(FileMapper fileMapper, ArticleService articleService) {
        this.fileMapper = fileMapper;
        this.articleService = articleService;
    }

    /**
     * article은 생성되고 파일 생성 오류시 일관성 유지
     * @param article 생성할 Article
     * @param multipartFiles 업로드할 files
     * @return 생성된 articleId
     */
    @Transactional
    public int createArticleWithFiles(Article article, MultipartFile[] multipartFiles) throws IOException {
        int createdArticleId = articleService.createArticle(article);
        List<FileVo> uploadedFiles = uploadFiles(multipartFiles);
        createFileList(uploadedFiles, createdArticleId);

        return createdArticleId;
    }

    public List<FileVo> uploadFiles(MultipartFile[] multipartFiles) throws IOException {
        if (multipartFiles.length > MAX_FILE_COUNT) {
            throw new RemoteException("파일은 최대 5개 업로드 가능합니다");
        }

        List<FileVo> files = new ArrayList<>();

        for (MultipartFile file : multipartFiles) {
            if (StringUtil.isNullOrEmpty(file.getOriginalFilename())) {
                return null;
            }

            String fileExtension = getFileExtension(file);
            String uuidName = UUID.randomUUID() + "_" + file.getOriginalFilename();

            //파일 업로드
            File uploadFile = new File(dir + uuidName);
            file.transferTo(uploadFile);

            //파일 정보 객체 생성
            FileVo fileVo = FileVo.builder()
                    .filePath(dir)
                    .filename(uuidName)
                    .originalName(file.getOriginalFilename())
                    .fileSize(file.getSize())
                    .extension(fileExtension)
                    .build();

            //저장 리스트 추가
            files.add(fileVo);
        };
        return files;
    }

    //게시글 파일 정보 생성
    public void createFileList(List<FileVo> files, int articleId) {
        if (files != null) {
            files.forEach(fileVo -> {
                fileVo.setArticleId(articleId);
                fileMapper.insertFile(fileVo);
            });
        }
    }

    private String getFileExtension(MultipartFile file) {
        int extensionStartIndex = file.getOriginalFilename().lastIndexOf(".");
        if (extensionStartIndex == -1) {
            throw new RuntimeException("파일 확장자 추출 오류");
        }
        // extensionStartIndex . +1 부터 확장자 명 추출
        String fileExtension = file.getOriginalFilename().substring(extensionStartIndex + 1);

        for (String extension : ALLOWED_EXTENSION) {
            if (extension.equals(fileExtension)) {
                return fileExtension;
            }
        }
        throw new RuntimeException("허용 되지 않는 확장자");
    }

    public List<FileVo> getFilesByArticleId(int articleId) {
        return fileMapper.selectFilesByArticleId(articleId);
    }

}
