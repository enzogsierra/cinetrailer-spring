package com.example.cinetrailer.service;

import java.nio.file.Path;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;


public interface StorageService 
{
    void init();
    String storeFile(MultipartFile file);
    Path loadFile(String filename);
    Resource loadAsResource(String filename);
    void deleteFile(String filename);
}
