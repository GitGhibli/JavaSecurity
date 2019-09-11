package com.AesRsa;

import java.io.File;
import java.util.Arrays;

import static com.AesRsa.Algorithms.AES;

public class Main {

    public static void main(String[] args) {
        String secretKey = args[0];

        var inputDir = "data\\OriginalDirectory";
        var encryptedDirName = "data\\EncryptedDirectory";
        var decryptedDirName = "data\\DecryptedDirectory";

        var start = System.nanoTime();
        CleanDirectories(encryptedDirName, decryptedDirName);
        var end = System.nanoTime();
        var duration = end - start;
        System.out.println("Directories cleaned in: " + duration/1000000);

        DirCipherWithExecutor executorParallelDirCipher = new DirCipherWithExecutor(10, AES, secretKey);
        System.out.println("Executor parallel Encryption about to start");
        start = System.nanoTime();
        executorParallelDirCipher.Encrypt(inputDir, encryptedDirName);
        System.out.println("Executor parallel Decryption about to start");
        executorParallelDirCipher.Decrypt(encryptedDirName, decryptedDirName);
        end = System.nanoTime();
        duration = end - start;
        System.out.println("Both executor parallel En/Deccryption ended in: " + (duration/1000000) + " milliseconds");
    }

    private static void CleanDirectories(String encryptedDirName, String decryptedDirName) {
        var encryptedDir = new File(encryptedDirName);
        Arrays.stream(encryptedDir.listFiles()).parallel().forEach((x) -> x.delete());

        var decryptedDir = new File(decryptedDirName);
        Arrays.stream(decryptedDir.listFiles()).parallel().forEach((x) -> x.delete());
    }
}
