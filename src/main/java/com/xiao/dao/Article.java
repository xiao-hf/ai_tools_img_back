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
@TableName(value = "article")
public class Article {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    @Schema(description = "主键")
    private Long id;

    /**
     * 图片url列表
     */
    @TableField(value = "imgs")
    @Schema(description = "图片url列表")
    private String imgs;

    /**
     * 图片位置列表
     */
    @TableField(value = "locations")
    @Schema(description = "图片位置列表")
    private String locations;

    /**
     * 文章文字
     */
    @TableField(value = "content")
    @Schema(description = "文章文字")
    private String content;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    @Schema(description = "创建时间")
    private Date createTime;

    /**
     * 标题
     */
    @TableField(value = "title")
    @Schema(description = "标题")
    private String title;

    /**
     * 标题图片
     */
    @TableField(value = "title_img")
    @Schema(description = "标题图片")
    private String titleImg;
}