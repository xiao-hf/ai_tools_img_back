package com.xiao.http.req;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class ArticleCreateReq {

    @Schema(description = "所属 appId")
    @NotBlank(message = "appId 不能为空")
    private String appId;

    @Schema(description = "正文内容")
    @NotBlank(message = "文章内容不能为空")
    private String content;

    @Schema(description = "标题")
    @NotBlank(message = "标题不能为空")
    private String title;

    @Schema(description = "图片地址列表，和 locations 对应")
    @NotNull(message = "图片列表不能为空")
    private List<String> imgs;

    @Schema(description = "图片插入位置列表，和 imgs 对应")
    @NotNull(message = "图片位置不能为空")
    private List<Integer> locations;
}
