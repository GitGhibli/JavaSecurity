package com.md5sha;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;

class HashException extends Exception {
    HashException(String message, Throwable err) {
        super(message,  err);
    }
}

public class Main {
    private static final String MD5 = "MD5";
    private static final String SHA1 = "SHA-1";
    private static final String SHA256 = "SHA-256";

    private static String byteArrayToHexString(byte[] byteArray){
            StringBuffer stringBuffer = new StringBuffer();
            for (int i = 0; i < byteArray.length; i++) {
                stringBuffer.append(Integer.toString((byteArray[i] & 0xff) + 0x100, 16)
                        .substring(1));
            }
            return stringBuffer.toString();
        }

    private static String getHash(String message, String algorithm) throws HashException {
        try {
            MessageDigest digest = MessageDigest.getInstance(algorithm);
            byte[] byteArray = digest.digest(message.getBytes("UTF-8"));
            return byteArrayToHexString(byteArray);
        } catch (GeneralSecurityException | IOException e) {
            throw new HashException("Hashing string failed", e);
        }
    }

    public static void main(String[] args) {
        String message = args[0];

        try {
            System.out.println(getHash(message, SHA256));
        } catch (HashException e) {
            System.out.print(e);
        }
    }
}
