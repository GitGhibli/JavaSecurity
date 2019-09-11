package com.AesRsa;

public abstract class AbstractDirCipher {
    protected FileCipherComponent fileCipher;

    protected AbstractDirCipher(String algorithm, String secretKey){
        this.fileCipher = new FileCipherImpl(algorithm, secretKey);
    }

    public abstract void Encrypt(String input, String output);

    public abstract void Decrypt(String input, String output);
}
