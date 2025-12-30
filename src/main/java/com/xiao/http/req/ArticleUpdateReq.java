package com.xiao.http.req;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;

@Data
public class ArticleUpdateReq {

    @Schema(description = "所属 appId，如不修改可不传")
    private String appId;

    @Schema(description = "正文内容")
    @NotBlank(message = "文章内容不能为空")
    private String content;

    @Schema(description = "图片地址列表，和 locations 对应")
    private List<String> imgs;

    @Schema(description = "图片插入位置列表，和 imgs 对应")
    private List<Integer> locations;
}
