package com.AesRsa;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;

public class FileCipherImpl implements FileCipher {
    private final String algorithm;

    public FileCipherImpl(String algorithm) {
        this.algorithm = algorithm;
    }

    public void TransformFile(SecretKeySpec secretKey, String inputFile, String outputFile, int mode) {
        try (var fileInput = new FileInputStream(inputFile);
             var fileOutput = new FileOutputStream(outputFile)) {
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
