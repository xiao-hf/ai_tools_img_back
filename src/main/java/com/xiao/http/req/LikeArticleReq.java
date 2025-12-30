package com.xiao.http.req;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LikeArticleReq {

    @NotNull(message = "userId不能为空")
    @Schema(description = "用户ID")
    private Long userId;

    @NotNull(message = "articleId不能为空")
    @Schema(description = "文章ID")
    private Long articleId;
}
