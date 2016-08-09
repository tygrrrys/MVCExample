package com.epam.bench.model;

import org.springframework.web.multipart.MultipartFile;

/**
 * Created by root on 08.08.16.
 */
public class FileBucket {

    MultipartFile file;

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
}
