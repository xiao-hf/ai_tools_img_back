package com.xiao.dao;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Date;
import lombok.Data;

/**
 * 权限信息表
 */
@Schema(description = "权限信息表")
@Data
@TableName(value = "permission")
public class Permission {
    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    @Schema(description = "主键ID")
    private Long id;

    /**
     * 权限名称
     */
    @TableField(value = "permission_name")
    @Schema(description = "权限名称")
    private String permissionName;

    /**
     * 权限内容
     */
    @TableField(value = "content")
    @Schema(description = "权限内容")
    private String content;

    /**
     * 权限类别，url-1，other-2
     */
    @TableField(value = "`type`")
    @Schema(description = "权限类别，url-1，other-2")
    private Integer type;

    /**
     * 备注
     */
    @TableField(value = "remark")
    @Schema(description = "备注")
    private String remark;

    /**
     * 插入时间
     */
    @TableField(value = "create_date")
    @Schema(description = "插入时间")
    private Date createDate;

    /**
     * 更新时间
     */
    @TableField(value = "update_date")
    @Schema(description = "更新时间")
    private Date updateDate;

    /**
     * 创建人
     */
    @TableField(value = "create_user_id")
    @Schema(description = "创建人")
    private Long createUserId;

    /**
     * 修改人
     */
    @TableField(value = "update_user_id")
    @Schema(description = "修改人")
    private Long updateUserId;
}