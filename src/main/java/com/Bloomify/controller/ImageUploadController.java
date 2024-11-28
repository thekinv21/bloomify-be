package com.Bloomify.controller;


import com.Bloomify.service.ImageUploadService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/image-upload")
@RequiredArgsConstructor
@Tag(name = "Image Uploader S3")

public class ImageUploadController {

    private final ImageUploadService imageUploadService;

    @Operation(summary = "Upload Image for S3 Bucket", operationId = "uploadImage")
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String uploadImage(@RequestPart("image") MultipartFile imageFile){
        return imageUploadService.uploadImage(imageFile);
    }

}
