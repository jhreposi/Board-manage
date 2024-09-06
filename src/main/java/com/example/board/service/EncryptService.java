package com.example.board.service;

import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import java.nio.charset.StandardCharsets;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PublicKey;
import java.util.Base64;

@Service
public class EncryptService {
    private final KeyPair keyPair;
    private static final String BEGIN = "-----BEGIN PUBLIC KEY-----\n";
    private static final String END = "\n-----END PUBLIC KEY-----";
    private static final String ALGORITHM_TYPE = "RSA";

    /**
     * RSA 방식으로 KeyPairGenerator를 이용하여
     * 2048 길이의 키 쌍 생성
     * @throws Exception
     */
    public EncryptService() throws Exception {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance(ALGORITHM_TYPE);
        keyGen.initialize(2048);
        this.keyPair = keyGen.generateKeyPair();
    }

    public PublicKey getPublicKey() {
        return keyPair.getPublic();
    }

    /**
     * @param publicKey 서버 시작시 생성된 공개키
     * @return 공개키 객체를 pem 형식으로 반환
     */
    public String publicKeyToPem(PublicKey publicKey) {
        String base64Encoded = Base64.getMimeEncoder(64, "\n".getBytes()).encodeToString(publicKey.getEncoded());
        return BEGIN + base64Encoded + END;
    }

    /**
     * Base64로 인코딩된 암호화한 데이터 encryptedData를 디코딩
     * 복호화 객체 생성
     * @param encryptedData 암호화된 문자열
     * @return 복호화 후 바이트 배열 문자열로 변환
     * @throws Exception
     */
    public String decrypt(String encryptedData) throws Exception {
        byte[] encryptedBytes = Base64.getDecoder().decode(encryptedData);

        Cipher cipher = Cipher.getInstance(ALGORITHM_TYPE);
        cipher.init(Cipher.DECRYPT_MODE, keyPair.getPrivate());

        byte[] decryptedBytes = cipher.doFinal(encryptedBytes);

        return new String(decryptedBytes, StandardCharsets.UTF_8);
    }
}
