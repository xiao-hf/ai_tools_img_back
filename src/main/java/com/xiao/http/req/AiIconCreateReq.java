package com.xiao.http.req;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * AI 图标新增请求
 */
@Data
public class AiIconCreateReq {

    @Schema(description = "名称")
    @NotBlank(message = "名称不能为空")
    private String name;

    @Schema(description = "图片地址")
    @NotBlank(message = "图片地址不能为空")
    private String img;
}
