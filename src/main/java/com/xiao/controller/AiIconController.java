package com.xiao.controller;

import com.xiao.common.AjaxResult;
import com.xiao.dao.AiIcon;
import com.xiao.http.req.AiIconUpdateReq;
import com.xiao.service.AiIconService;
import com.xiao.service.FileService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.MediaType;

import java.util.List;

@RestController
@RequestMapping("/ai-icon")
@Validated
public class AiIconController {

    @Resource
    private AiIconService aiIconService;

    @Resource
    private FileService fileService;

    @Operation(summary = "新增 AI 图标（表单+文件）")
    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public AjaxResult<AiIcon> createByUpload(@RequestParam @NotBlank(message = "名称不能为空") String name,
                                             @RequestPart("file") MultipartFile file) {
        String url = fileService.uploadImage(file);
        return AjaxResult.success(aiIconService.create(name, url));
    }

    @Operation(summary = "更新 AI 图标（JSON）")
    @PutMapping(value = "/{id}")
    public AjaxResult<AiIcon> update(@PathVariable Long id, @RequestBody @Valid AiIconUpdateReq req) {
        return AjaxResult.success(aiIconService.update(id, req));
    }

    @Operation(summary = "更新 AI 图标（表单+文件，可同时改名）")
    @PutMapping(value = "/{id}/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public AjaxResult<AiIcon> updateByUpload(@PathVariable Long id,
                                             @RequestParam(required = false) String name,
                                             @RequestPart(value = "file", required = false) MultipartFile file) {
        String url = file != null ? fileService.uploadImage(file) : null;
        AiIconUpdateReq req = new AiIconUpdateReq();
        req.setName(name);
        req.setImg(url);
        return AjaxResult.success(aiIconService.update(id, req));
    }

    @Operation(summary = "获取 AI 图标详情")
    @GetMapping("/{id}")
    public AjaxResult<AiIcon> detail(@PathVariable Long id) {
        return AjaxResult.success(aiIconService.detail(id));
    }

    @Operation(summary = "按名称前缀递减匹配，返回首个图片 URL")
    @GetMapping("/search")
    public AjaxResult<String> search(@RequestParam @NotBlank(message = "name 不能为空") String name) {
        return AjaxResult.success(aiIconService.findImgByCascadePrefix(name));
    }

    @Operation(summary = "查询 AI 图标列表（按名称模糊查询）")
    @GetMapping("/list")
    public AjaxResult<List<AiIcon>> list(@RequestParam(required = false) String name) {
        return AjaxResult.success(aiIconService.list(name));
    }

    @Operation(summary = "删除 AI 图标")
    @DeleteMapping("/{id}")
    public AjaxResult<String> delete(@PathVariable Long id) {
        aiIconService.delete(id);
        return AjaxResult.success("删除成功");
    }
}
