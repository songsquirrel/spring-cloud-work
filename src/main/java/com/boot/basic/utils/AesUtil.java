package com.boot.basic.utils;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;

public class AesUtil {


    /**
     * AES解密
     *
     * @param encryptedData 加密后的字符串（Base64编码）
     * @param secretKey     解密密钥（长度必须为16/24/32字节，对应AES-128/192/256）
     * @return 解密后的原始字符串
     * @throws Exception 解密过程中的异常（密钥错误、格式不匹配等）
     */
    private static String dec(String encryptedData, String secretKey) throws Exception {
        // 验证密钥长度
        byte[] keyBytes = secretKey.getBytes(StandardCharsets.UTF_8);
        System.out.println(Arrays.toString(keyBytes));
        System.out.println(keyBytes.length);
        if (keyBytes.length != 16 && keyBytes.length != 24 && keyBytes.length != 32) {
            throw new IllegalArgumentException("密钥长度必须为16/24/32字节");
        }

        // 初始化密钥
        SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "AES");

        // 初始化加密器（使用完整的模式字符串）
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, keySpec);

        // 4. 解密（先Base64解码，再AES解密）
        byte[] encryptedBytes = Base64.getDecoder().decode(encryptedData);
        byte[] decryptedBytes = cipher.doFinal(encryptedBytes);

        return new String(decryptedBytes, StandardCharsets.UTF_8);
    }

    /**
     * 简化版解密（默认使用AES/ECB/PKCS5Padding模式，适合大多数前端加密场景）
     *
     * @param encryptedStr 加密后的字符串（Base64编码）
     * @param secretKey     解密密钥（长度必须为16/24/32字节）
     * @return 解密后的原始字符串
     * @throws Exception 解密异常
     */
    public static String decrypt(String encryptedStr, String secretKey) throws Exception {
        return dec(encryptedStr, secretKey);
    }
}
