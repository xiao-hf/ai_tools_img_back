package com.xiao.dao;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Date;
import lombok.Data;

/**
 * 角色权限关系表
 */
@Schema(description = "角色权限关系表")
@Data
@TableName(value = "role_permission")
public class RolePermission {
    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    @Schema(description = "主键ID")
    private Long id;

    /**
     * 角色ID
     */
    @TableField(value = "role_id")
    @Schema(description = "角色ID")
    private Long roleId;

    /**
     * 权限ID
     */
    @TableField(value = "permission_id")
    @Schema(description = "权限ID")
    private Long permissionId;

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