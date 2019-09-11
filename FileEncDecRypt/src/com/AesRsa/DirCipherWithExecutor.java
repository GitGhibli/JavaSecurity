package com.AesRsa;

import javax.crypto.Cipher;
import java.io.File;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class DirCipherWithExecutor extends AbstractDirCipher {

    private final int numberOfThreads;

    public DirCipherWithExecutor(int numberOfThreads, String algorithm, String secretKey) {
        super(algorithm, secretKey);
        this.numberOfThreads = numberOfThreads;
    }

    @Override
    public void Encrypt(String input, String output) {
        var executorService = Executors.newFixedThreadPool(numberOfThreads);

        var dataDirectory = new File(input);

        for (var file : dataDirectory.listFiles()) {
            executorService.execute(() -> this.fileCipher.TransformFile(file, output + "\\" + file.getName(), Cipher.ENCRYPT_MODE));
        }

        executorService.shutdown();

        try {
            if(!executorService.awaitTermination(100, TimeUnit.SECONDS)){
                System.out.println("encryption time-outed");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void Decrypt(String input, String output) {
        var executorService = Executors.newFixedThreadPool(numberOfThreads);

        var dataDirectory = new File(input);

        for (var file : dataDirectory.listFiles()) {
            executorService.execute(() -> this.fileCipher.TransformFile(file, output + "\\" + file.getName(), Cipher.DECRYPT_MODE));
        }

        executorService.shutdown();

        try {
            if(!executorService.awaitTermination(100, TimeUnit.SECONDS)){
                System.out.println("decryption time-outed");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
