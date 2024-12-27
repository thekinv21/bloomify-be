package com.Bloomify.service;

import com.Bloomify.dto.ImageDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ImageUploadService {

    ImageDto uploadImage(MultipartFile imageFile);
    List<ImageDto> uploadMultipleImage(List<MultipartFile> imageFiles);
}
