package com.xiao.controller;

import com.xiao.common.AjaxResult;
import com.xiao.service.FileService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/file")
public class FileController {

    @Resource
    private FileService fileService;

    @Operation(summary = "上传图片到 MinIO")
    @PostMapping("/upload")
    public AjaxResult<String> upload(@RequestPart("file") MultipartFile file) {
        return AjaxResult.success(fileService.uploadImage(file));
    }
}
