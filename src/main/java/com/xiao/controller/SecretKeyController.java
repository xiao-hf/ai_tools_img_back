package com.xiao.controller;

import com.xiao.common.AjaxResult;
import com.xiao.service.SecretKeyService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.annotation.Resource;
import jakarta.validation.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/secret")
@Validated
public class SecretKeyController {

    @Resource
    private SecretKeyService secretKeyService;

    @Operation(summary = "校验凭证，返回 true/false")
    @GetMapping("/verify")
    public AjaxResult<Boolean> verify(@RequestParam @NotBlank(message = "credential不能为空") String credential) {
        return AjaxResult.success(secretKeyService.verify(credential));
    }
}
