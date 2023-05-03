package com.enigma.service;

import com.enigma.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;

@Service
public class CourseUploadServiceImpl implements CourseUploadService {
    private FileRepository fileRepository;

    public CourseUploadServiceImpl(@Autowired FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    @Override
    public String uploadMaterial(MultipartFile multipartFile) {
        return fileRepository.store(multipartFile);
    }

    @Override
    public Resource downloadMaterial(String filename) {
        return fileRepository.load(filename);
    }
}
