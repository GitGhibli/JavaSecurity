package com.AesRsa;

import javax.crypto.Cipher;
import java.io.File;
import java.util.Arrays;

public class DirCipherWithStreams extends AbstractDirCipher {

    public DirCipherWithStreams(String algorithm, String secretKey) {
        super(algorithm, secretKey);
    }

    @Override
    public void Encrypt(String input, String output){
        TransformDirParallel(input, output, Cipher.ENCRYPT_MODE);
    }

    @Override
    public void Decrypt(String input, String output){
        TransformDirParallel(input, output, Cipher.DECRYPT_MODE);
    }

    private void TransformDirParallel(String input, String output, int mode){
        var dataDirectory = new File(input);
        Arrays.stream(dataDirectory.listFiles()).parallel().forEach((x) -> this.fileCipher.TransformFile(x, output + "\\" + x.getName(), mode));
    }
}
