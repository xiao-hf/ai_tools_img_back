package com.xiao.controller;

import com.xiao.common.AjaxResult;
import com.xiao.dao.AppImg;
import com.xiao.http.req.AppImgSaveReq;
import com.xiao.service.AppImgService;
import com.xiao.service.FileService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.annotation.Resource;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.validation.annotation.Validated;

@RestController
@RequestMapping("/app-img")
@Validated
public class AppImgController {

    @Resource
    private AppImgService appImgService;

    @Resource
    private FileService fileService;

    @Operation(summary = "上传文件并保存小程序图片（一个 appId 一条）")
    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public AjaxResult<AppImg> upload(@RequestParam @NotBlank(message = "appId不能为空") String appId,
                                     @RequestParam(required = false) String name,
                                     @RequestParam("file") MultipartFile file) {
        String url = fileService.uploadImage(file);
        AppImgSaveReq req = new AppImgSaveReq();
        req.setAppId(appId);
        req.setName(name);
        req.setWxImg(url);
        return AjaxResult.success(appImgService.save(req));
    }

    @Operation(summary = "查询小程序图片")
    @GetMapping("/{appId}")
    public AjaxResult<AppImg> getByAppId(@PathVariable String appId) {
        return AjaxResult.success(appImgService.getByAppId(appId));
    }

    @Operation(summary = "删除小程序图片")
    @DeleteMapping("/{appId}")
    public AjaxResult<String> delete(@PathVariable String appId) {
        appImgService.deleteByAppId(appId);
        return AjaxResult.success("删除成功");
    }
}
