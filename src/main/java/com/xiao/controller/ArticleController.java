package com.xiao.controller;

import com.xiao.common.AjaxResult;
import com.xiao.common.dto.ArticleDto;
import com.alibaba.fastjson.JSON;
import com.xiao.http.req.ArticleCreateReq;
import com.xiao.http.req.ArticleUpdateReq;
import com.xiao.service.ArticleService;
import com.xiao.service.FileService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@RestController
@RequestMapping("/article")
@Validated
public class ArticleController {

    @Resource
    private ArticleService articleService;

    @Resource
    private FileService fileService;

    @Operation(summary = "新增文章（正文 + 图片文件列表上传）")
    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public AjaxResult<ArticleDto> createWithFiles(@RequestParam @NotBlank(message = "appId不能为空") String appId,
                                                  @RequestParam @NotBlank(message = "content不能为空") String content,
                                                  @RequestParam @NotBlank(message = "locations不能为空") String locations,
                                                  @RequestParam("files") MultipartFile[] files) {
        ArticleCreateReq req = new ArticleCreateReq();
        req.setAppId(appId);
        req.setContent(content);
        req.setLocations(parseLocations(locations));
        req.setImgs(uploadFiles(files));
        return AjaxResult.success(articleService.create(req));
    }

    @Operation(summary = "更新文章")
    @PutMapping("/{id}")
    public AjaxResult<ArticleDto> update(@PathVariable Long id, @RequestBody @Valid ArticleUpdateReq req) {
        return AjaxResult.success(articleService.update(id, req));
    }

    @Operation(summary = "查看文章详情")
    @GetMapping("/{id}")
    public AjaxResult<ArticleDto> detail(@PathVariable Long id) {
        return AjaxResult.success(articleService.detail(id));
    }

    @Operation(summary = "按 appId 查询文章列表（不传 appId 返回全部）")
    @GetMapping("/list")
    public AjaxResult<List<ArticleDto>> list(@RequestParam(required = false) String appId) {
        return AjaxResult.success(articleService.listByAppId(appId));
    }

    @Operation(summary = "删除文章")
    @DeleteMapping("/{id}")
    public AjaxResult<String> delete(@PathVariable Long id) {
        articleService.delete(id);
        return AjaxResult.success("删除成功");
    }

    private java.util.List<String> uploadFiles(MultipartFile[] files) {
        java.util.List<String> urls = new java.util.ArrayList<>();
        if (files != null) {
            for (MultipartFile file : files) {
                if (file != null && !file.isEmpty()) {
                    urls.add(fileService.uploadImage(file));
                }
            }
        }
        return urls;
    }

    private java.util.List<Integer> parseLocations(String locations) {
        if (!StringUtils.hasText(locations)) {
            return java.util.Collections.emptyList();
        }
        try {
            return JSON.parseArray(locations, Integer.class);
        } catch (Exception ignore) {
            String[] parts = locations.split(",");
            java.util.List<Integer> list = new java.util.ArrayList<>();
            for (String part : parts) {
                if (StringUtils.hasText(part)) {
                    list.add(Integer.parseInt(part.trim()));
                }
            }
            return list;
        }
    }
}
