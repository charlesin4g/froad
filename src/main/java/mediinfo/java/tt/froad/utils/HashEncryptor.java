package mediinfo.java.tt.froad.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashEncryptor {

    public static String encrypt(String input) {
        try {
            // 创建MessageDigest实例并指定SHA-256算法
            MessageDigest digest = MessageDigest.getInstance("SHA-256");

            // 计算哈希值（返回字节数组）
            byte[] hashBytes = digest.digest(input.getBytes());

            // 将字节数组转换为十六进制字符串
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                // 将每个字节转换为两位十六进制数（高位补0）
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA-256 algorithm not available", e);
        }
    }
}