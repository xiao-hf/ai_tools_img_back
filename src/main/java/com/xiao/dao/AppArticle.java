package com.xiao.dao;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema
@Data
@TableName(value = "app_article")
public class AppArticle {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    @Schema(description = "主键")
    private Long id;

    /**
     * app_id
     */
    @TableField(value = "app_id")
    @Schema(description = "app_id")
    private String appId;

    /**
     * 文章id
     */
    @TableField(value = "article_id")
    @Schema(description = "文章id")
    private Long articleId;
}