package com.example.board.util;

import com.example.board.dto.SearchRequest;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Setter
@Getter
public class Page<T> {
    private List<T> articles;
    private int currentPage;
    private int totalCount;
    private int itemPerPage;
    private int lastPage;

    private Criteria criteria;
    private PageGroup pageGroup;

    public void setPage(SearchRequest searchRequest, int totalCount) {
        currentPage = searchRequest.getCurrentPage();
        itemPerPage = searchRequest.getPerPage();
        this.totalCount = totalCount;
        paging();
    }

    private void paging() {
        lastPage = totalCount / itemPerPage + (totalCount % itemPerPage > 0 ? 1 : 0);
        //db조회를 위한 범위 설정
        criteria = new Criteria();
        //뷰 페이지 페이지 번호 네비게이션 설정
        pageGroup = new PageGroup(currentPage, lastPage);
    }

    @Getter
    public class Criteria {
        private final int limit;
        private final int offset;

        private Criteria() {
            limit = itemPerPage;
            offset = calOffset();
        }
        private int calOffset() {
            return (currentPage -1) * itemPerPage;
        }
    }

    @Getter
    public class PageGroup {
        private int currentPage;
        private int lastPage;

        private int startNum;
        private int endNum;
        private int pageSize;

        private PageGroup() {
            pageSize = 5;
        }

        private PageGroup(int currentPage, int lastPage) {
            this();
            this.currentPage = currentPage;
            this.lastPage = lastPage;
            this.startNum = calStartNum();
            this.endNum = calEndNum();
        }

        private PageGroup(int currentPage, int lastPage, int pageSize) {
            this.currentPage = currentPage;
            this.lastPage = lastPage;
            this.pageSize = pageSize;
            this.startNum = calStartNum();
            this.endNum = calEndNum();
        }

        private int calStartNum() {
            return ((currentPage - 1) / pageSize) * pageSize + 1;
        }

        private int calEndNum() {
            return Math.min(startNum + pageSize - 1, lastPage);
        }
    }
}
