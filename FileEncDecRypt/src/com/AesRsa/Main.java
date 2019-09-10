package com.AesRsa;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;

public class Main {

    public static void main(String[] args) {
        var secretKey = new SecretKeySpec(args[0].getBytes(), "AES");

        try {
            var cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);

            try(var fileInput = new FileInputStream("OriginalMessage.bmp")){
                byte[] output = cipher.doFinal(fileInput.readAllBytes());

                try(var fileOutput = new FileOutputStream("EncryptedFile")){
                    fileOutput.write(output);
                    fileOutput.flush();
                }
            };

        } catch (GeneralSecurityException | IOException e){
            System.out.println("Crypting failed");
            System.out.print(e.getMessage());
        }

        try {
            var cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);

            try(var fileInput = new FileInputStream("EncryptedFile")){
                byte[] output = cipher.doFinal(fileInput.readAllBytes());

                try(var fileOutput = new FileOutputStream("DecryptedFile.bmp")){
                    fileOutput.write(output);
                    fileOutput.flush();
                }
            };

        } catch (GeneralSecurityException | IOException e){
            System.out.println("Decrypting failed");
            System.out.print(e.getMessage());
        }
    }
}
