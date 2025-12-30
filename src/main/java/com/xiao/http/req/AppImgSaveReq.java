package com.xiao.http.req;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AppImgSaveReq {

    @Schema(description = "小程序 appId")
    @NotBlank(message = "appId 不能为空")
    private String appId;

    @Schema(description = "小程序名称")
    private String name;

    @Schema(description = "小程序图片 URL")
    @NotBlank(message = "图片地址不能为空")
    private String wxImg;
}
