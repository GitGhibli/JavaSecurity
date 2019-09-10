package com.AesRsa;

import javax.crypto.spec.SecretKeySpec;

public interface FileCipher
{
    void TransformFile(SecretKeySpec secretKey, String inputFile, String outputFile, int mode);
}
