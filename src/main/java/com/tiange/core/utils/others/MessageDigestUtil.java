package com.tiange.core.utils.others;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MessageDigestUtil {
    /**
     * 使用 sha-256 算法计算摘要信息
     *
     * @param content 内容
     * @return 摘要
     */
    public static String getSHA256Digest(String content) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("sha-256");
            messageDigest.update(content.getBytes("utf-8"));
            return bytesToHexString(messageDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将字节数组转换成16进制字符串
     *
     * @param bytes 即将转换的数据
     * @return 16进制字符串
     */
    private static String bytesToHexString(byte[] bytes) {
        StringBuffer sb = new StringBuffer(bytes.length);
        String temp = null;
        for (int i = 0; i < bytes.length; i++) {
            temp = Integer.toHexString(0xFF & bytes[i]);
            if (temp.length() < 2) {
                sb.append(0);
            }
            sb.append(temp);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(MessageDigestUtil.getSHA256Digest("test"));
    }
}
