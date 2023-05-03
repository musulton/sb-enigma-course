package com.enigma.repository;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FileRepository {
    String store(MultipartFile file);

    Resource load(String filename);
}
