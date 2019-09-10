package com.AesRsa;

import static com.AesRsa.Algorithms.AES;

public class Main {

    public static void main(String[] args) {
        String secretKey = args[0];

        var inputDir = "data\\OriginalDirectory";
        var encryptedDir = "data\\EncryptedDirectory";
        var decryptedDir = "data\\DecryptedDirectory";

        FileCipher fileCipher = new FileCipherImpl(AES, secretKey);

        System.out.println("Regular Encryption about to start");
        var start = System.nanoTime();
        fileCipher.Encrypt(inputDir, encryptedDir);
        var end = System.nanoTime();
        long duration = end - start;
        System.out.println("Regular Encryption ended in: " + (duration/1000000) + " milliseconds");

        System.out.println("Parallel Encryption about to start");
        start = System.nanoTime();
        fileCipher.EncryptParallel(inputDir, encryptedDir);
        end = System.nanoTime();
        duration = end - start;
        System.out.println("Parallel Encryption ended in: " + (duration/1000000) + " milliseconds");
    }
}
