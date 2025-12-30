package com.xiao.dao;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Date;
import lombok.Data;

@Schema
@Data
@TableName(value = "like_article")
public class LikeArticle {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    @Schema(description = "主键")
    private Long id;

    /**
     * 文章id
     */
    @TableField(value = "article_id")
    @Schema(description = "文章id")
    private Long articleId;

    /**
     * 用户id
     */
    @TableField(value = "user_id")
    @Schema(description = "用户id")
    private Long userId;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    @Schema(description = "创建时间")
    private Date createTime;

    /**
     * 卡片图片
     */
    @TableField(value = "title_img")
    @Schema(description = "卡片图片")
    private String titleImg;
}