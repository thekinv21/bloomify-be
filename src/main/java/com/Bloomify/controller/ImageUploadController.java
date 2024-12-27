package com.Bloomify.controller;


import com.Bloomify.response.CustomApiResponse;
import com.Bloomify.service.ImageUploadService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/image-upload")
@RequiredArgsConstructor
@Tag(name = "Image Uploader S3")

public class ImageUploadController {

    private final ImageUploadService imageUploadService;

    @Operation(summary = "Upload Image for S3 Bucket", operationId = "uploadImage")
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<CustomApiResponse> uploadImage(@RequestPart("image") MultipartFile imageFile){
        return CustomApiResponse.builder().data(imageUploadService.uploadImage(imageFile)).build();
    }


    @Operation(summary = "Upload Multiple Images for S3 Bucket", operationId = "uploadImages")
    @PostMapping(value = "/multiple", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<CustomApiResponse> uploadImages(@RequestPart("images") List<MultipartFile> imageFiles) {
        return CustomApiResponse.builder().data(imageUploadService.uploadMultipleImage(imageFiles)).build();
    }

}
