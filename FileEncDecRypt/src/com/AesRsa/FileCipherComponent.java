package com.AesRsa;

import java.io.File;

public interface FileCipherComponent {
    void TransformFile(File input, String output, int mode);
}
