package com.xiao.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {

    /**
     * Upload image to MinIO and return the accessible url.
     */
    String uploadImage(MultipartFile file);

    /**
     * Delete object by full url if it belongs to configured bucket.
     */
    void deleteByUrl(String url);
}
