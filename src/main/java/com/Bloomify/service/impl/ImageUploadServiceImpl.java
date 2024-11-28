package com.Bloomify.service.impl;

import com.Bloomify.exception.CustomException;
import com.Bloomify.service.ImageUploadService;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.util.UUID;

@Service
public class ImageUploadServiceImpl implements ImageUploadService {

    @Value("${AmazonS3.S3_BUCKET_NAME}")
    private String s3BucketName;

    @Value("${AmazonS3.S3_REGION}")
    private String s3Region;

    @Autowired
    private AmazonS3 s3Client;

    @Transactional
    @Override
    public String uploadImage(final MultipartFile multipartFile) {
        String fileName = generateUniqueFileName(multipartFile.getOriginalFilename());

        try {
            uploadFileToS3Bucket(s3BucketName, multipartFile, fileName);
        } catch (final AmazonServiceException | IOException ex) {
            throw new CustomException(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return String.format("https://s3.%s.amazonaws.com/%s/%s", s3Region, s3BucketName, fileName);
    }

    private void uploadFileToS3Bucket(
            final String bucketName,
            final MultipartFile file,
            final String fileName
    ) throws IOException {
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(file.getSize());
        metadata.setContentType(file.getContentType());

        s3Client.putObject(bucketName, fileName, file.getInputStream(), metadata);
    }

    private String generateUniqueFileName(String originalFileName) {
        String uniqueId = UUID.randomUUID().toString();
        return uniqueId + "_" + originalFileName;
    }
}
