package com.xiao.dao;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Date;
import lombok.Data;

/**
 * 角色权限关联表
 */
@Schema(description = "角色权限关联表")
@Data
@TableName("role_permission")
public class RolePermission {
    /**
    * 主键ID
    */
    @Schema(description="主键ID")
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
    * 角色ID
    */
    @Schema(description="角色ID")
    private Long roleId;

    /**
    * 权限ID
    */
    @Schema(description="权限ID")
    private Long permissionId;

    /**
    * 插入时间
    */
    @Schema(description="插入时间")
    private Date createDate;

    /**
    * 更新时间
    */
    @Schema(description="更新时间")
    private Date updateDate;

    /**
    * 创建人
    */
    @Schema(description="创建人")
    private Long createUserId;

    /**
    * 修改人
    */
    @Schema(description="修改人")
    private Long updateUserId;
}
