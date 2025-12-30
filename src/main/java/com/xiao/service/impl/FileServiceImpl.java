package com.xiao.service.impl;

import com.xiao.config.minio.MinioProperties;
import com.xiao.exception.BusinessException;
import com.xiao.service.FileService;
import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.RemoveObjectArgs;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.time.LocalDate;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {

    private static final Logger log = LoggerFactory.getLogger(FileServiceImpl.class);

    @Resource
    private MinioClient minioClient;

    @Resource
    private MinioProperties properties;

    @PostConstruct
    public void initBucket() {
        String bucket = properties.getBucket();
        if (!StringUtils.hasText(bucket)) {
            throw new BusinessException("MinIO bucket is empty");
        }
        try {
            boolean exists = minioClient.bucketExists(
                BucketExistsArgs.builder().bucket(bucket).build()
            );
            if (!exists) {
                minioClient.makeBucket(
                    MakeBucketArgs.builder().bucket(bucket).build()
                );
            }
        } catch (Exception e) {
            log.error("Init MinIO bucket failed", e);
            throw new BusinessException("初始化存储桶失败", 500, e);
        }
    }

    @Override
    public String uploadImage(MultipartFile file) {
        if (file == null || file.isEmpty()) {
            throw new BusinessException("上传文件为空");
        }
        String objectName = buildObjectName(file.getOriginalFilename());
        try (InputStream inputStream = file.getInputStream()) {
            minioClient.putObject(
                PutObjectArgs.builder()
                    .bucket(properties.getBucket())
                    .object(objectName)
                    .stream(inputStream, file.getSize(), -1)
                    .contentType(file.getContentType())
                    .build()
            );
            return buildFileUrl(objectName);
        } catch (Exception e) {
            log.error("Upload file to MinIO failed", e);
            throw new BusinessException("上传失败，请稍后再试", 500, e);
        }
    }

    @Override
    public void deleteByUrl(String url) {
        String objectName = extractObjectName(url);
        if (!StringUtils.hasText(objectName)) {
            return;
        }
        try {
            minioClient.removeObject(
                RemoveObjectArgs.builder()
                    .bucket(properties.getBucket())
                    .object(objectName)
                    .build()
            );
        } catch (Exception e) {
            log.warn("Delete object from MinIO failed, url={}", url, e);
        }
    }

    private String buildObjectName(String originalFilename) {
        String ext = "";
        if (StringUtils.hasText(originalFilename) && originalFilename.contains(".")) {
            ext = originalFilename.substring(originalFilename.lastIndexOf("."));
        }
        return "uploads/" + LocalDate.now() + "/" + UUID.randomUUID() + ext;
    }

    private String buildFileUrl(String objectName) {
        String endpoint = properties.getEndpoint();
        String bucket = properties.getBucket();
        if (endpoint.endsWith("/")) {
            endpoint = endpoint.substring(0, endpoint.length() - 1);
        }
        return endpoint + "/" + bucket + "/" + objectName;
    }

    private String extractObjectName(String url) {
        if (!StringUtils.hasText(url)) {
            return null;
        }
        String endpoint = properties.getEndpoint();
        String bucket = properties.getBucket();
        if (endpoint.endsWith("/")) {
            endpoint = endpoint.substring(0, endpoint.length() - 1);
        }
        String prefix = endpoint + "/" + bucket + "/";
        if (!url.startsWith(prefix)) {
            return null;
        }
        return url.substring(prefix.length());
    }
}
