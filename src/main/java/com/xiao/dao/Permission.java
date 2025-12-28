package com.xiao.dao;

import com.baomidou.mybatisplus.annotation.IdType;
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
@TableName("permission")
public class Permission {
    /**
     * 主键ID
     */
    @Schema(description = "主键ID")
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 权限名称
     */
    @Schema(description = "权限名称")
    private String permissionName;

    /**
     * 权限内容
     */
    @Schema(description = "权限内容")
    private String content;

    /**
     * 权限类别：url-1，other-2
     */
    @Schema(description = "权限类别：url-1，other-2")
    private Integer type;

    /**
     * 备注
     */
    @Schema(description = "备注")
    private String remark;

    /**
     * 插入时间
     */
    @Schema(description = "插入时间")
    private Date createDate;

    /**
     * 更新时间
     */
    @Schema(description = "更新时间")
    private Date updateDate;

    /**
     * 创建人
     */
    @Schema(description = "创建人")
    private Long createUserId;

    /**
     * 修改人
     */
    @Schema(description = "修改人")
    private Long updateUserId;
}
