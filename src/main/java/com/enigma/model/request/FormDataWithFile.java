package com.enigma.model.request;

import org.springframework.web.multipart.MultipartFile;

public class FormDataWithFile {
    private MultipartFile file;

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
}
