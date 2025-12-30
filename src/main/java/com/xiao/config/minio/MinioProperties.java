package com.xiao.config.minio;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "minio")
public class MinioProperties {

    /**
     * MinIO endpoint, e.g. http://127.0.0.1:9000
     */
    private String endpoint;

    private String accessKey;

    private String secretKey;

    /**
     * Default bucket name for uploads.
     */
    private String bucket;
}
