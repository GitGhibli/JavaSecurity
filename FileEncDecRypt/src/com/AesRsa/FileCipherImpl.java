package com.AesRsa;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Arrays;

public class FileCipherImpl implements FileCipher {
    private final String algorithm;
    private final SecretKeySpec secretKey;

    public FileCipherImpl(String algorithm, String secretKey) {
        this.algorithm = algorithm;
        this.secretKey = new SecretKeySpec(secretKey.getBytes(), algorithm);
    }

    @Override
    public void Encrypt(String inputDir, String outputDir){
        TransformDir(inputDir, outputDir, Cipher.ENCRYPT_MODE);
    }

    @Override
    public void EncryptParallel(String inputDir, String outputDir) {
        TransformDirParallel(inputDir, outputDir, Cipher.ENCRYPT_MODE);
    }

    @Override
    public void Decrypt(String inputDir, String outputDir){
        TransformDir(inputDir, outputDir, Cipher.DECRYPT_MODE);
    }

    private void TransformDirParallel(String inputDir, String outputDir, int mode){
        var dataDirectory = new File(inputDir);
        Arrays.stream(dataDirectory.listFiles()).parallel().forEach((x) -> this.TransformFile(x, outputDir + "\\" + x.getName(), mode));
    }

    private void TransformDir(String inputDir, String outputDir, int mode){
        var dataDirectory = new File(inputDir);
        for (var file : dataDirectory.listFiles()) {
            if (file.isFile()) {
                var outputPath = outputDir + "\\" + file.getName();
                TransformFile(file, outputPath, mode);
            }
        }
    }

    private void TransformFile(File inputFile, String outputPath, int mode) {
        try (var fileInput = new FileInputStream(inputFile);
             var fileOutput = new FileOutputStream(outputPath)) {
            var cipher = Cipher.getInstance(algorithm);
            cipher.init(mode, secretKey);

            byte[] output = cipher.doFinal(fileInput.readAllBytes());

            fileOutput.write(output);
            fileOutput.flush();
        } catch (GeneralSecurityException |
                IOException e) {
            System.out.println("Transform failed");
        }
    }
}
