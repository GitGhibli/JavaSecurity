package com.AesRsa;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;

public class FileCipherImpl implements FileCipher, FileCipherComponent {
    private final String algorithm;
    private final SecretKeySpec secretKey;

    public FileCipherImpl(String algorithm, String secretKey) {
        this.algorithm = algorithm;
        this.secretKey = new SecretKeySpec(secretKey.getBytes(), algorithm);
    }

    @Override
    public void Encrypt(String input, String output) {
        this.TransformFile(new File(input), output, Cipher.ENCRYPT_MODE);
    }

    @Override
    public void Decrypt(String input, String output) {
        this.TransformFile(new File(input), output, Cipher.DECRYPT_MODE);
    }

    @Override
    public void TransformFile(File input, String output, int mode) {
        try (var fileInput = new FileInputStream(input);
             var fileOutput = new FileOutputStream(output)) {
            var cipher = Cipher.getInstance(algorithm);
            cipher.init(mode, secretKey);

            byte[] byteOutput = cipher.doFinal(fileInput.readAllBytes());

            fileOutput.write(byteOutput);
            fileOutput.flush();
        } catch (GeneralSecurityException |
                IOException e) {
            System.out.println("Transform failed");
        }
    }
}
