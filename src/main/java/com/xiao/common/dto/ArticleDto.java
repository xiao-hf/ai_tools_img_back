package com.xiao.common.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ArticleDto {

    @Schema(description = "文章 id")
    private Long id;

    @Schema(description = "所属 appId")
    private String appId;

    @Schema(description = "正文")
    private String content;

    @Schema(description = "标题")
    private String title;

    @Schema(description = "图片地址列表")
    private List<String> imgs;

    @Schema(description = "对应插入位置列表")
    private List<Integer> locations;

    @Schema(description = "创建时间")
    private Date createTime;

    @Schema(description = "标题图片")
    private String titleImg;
}
