package com.Bloomify.service;

import org.springframework.web.multipart.MultipartFile;

public interface ImageUploadService {

    String uploadImage(MultipartFile imageFile);
}
