package com.AesRsa;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class Main {
    private static String AES = "AES";
    private static String DES = "DES";
    private static String RSA = "RSA";

    private static FileCipher fileCipher = new FileCipherImpl(AES);

    public static void main(String[] args) {
        String inputFile = "data\\OriginalMessage.bmp";
        String encryptedFile = "data\\EncryptedFile";
        String outputFile = "data\\DecryptedFile.bmp";

        var secretKey = new SecretKeySpec(args[0].getBytes(), "AES");
        EncryptFile(secretKey, inputFile, encryptedFile);
        DecryptFile(secretKey, encryptedFile, outputFile);
    }

    private static void EncryptFile(SecretKeySpec secretKey, String inputFile, String outputFile) {
        fileCipher.TransformFile(secretKey,  inputFile, outputFile, Cipher.ENCRYPT_MODE);
    }

    private static void DecryptFile(SecretKeySpec secretKey, String inputFile, String outputFile) {
        fileCipher.TransformFile(secretKey, inputFile, outputFile, Cipher.DECRYPT_MODE);
    }


}
