package com.xiao.http.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * AI 图标更新请求
 */
@Data
public class AiIconUpdateReq {

    @Schema(description = "名称")
    private String name;

    @Schema(description = "图片地址")
    private String img;
}
