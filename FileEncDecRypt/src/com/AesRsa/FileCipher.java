package com.AesRsa;

public interface FileCipher {

    void Encrypt(String inputDir, String outputDir);

    void EncryptParallel(String inputDir, String outputDir);

    void Decrypt(String inputDir, String outputDir);
}
