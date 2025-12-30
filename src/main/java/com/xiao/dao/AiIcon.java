package com.xiao.dao;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema
@Data
@TableName(value = "ai_icon")
public class AiIcon {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    @Schema(description = "主键")
    private Long id;

    /**
     * 名字
     */
    @TableField(value = "`name`")
    @Schema(description = "名字")
    private String name;

    /**
     * 图片
     */
    @TableField(value = "img")
    @Schema(description = "图片")
    private String img;
}