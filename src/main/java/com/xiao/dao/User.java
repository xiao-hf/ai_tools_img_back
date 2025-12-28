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
@TableName("user")
public class User {
    @Schema(description = "")
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 登录用户名
     */
    @Schema(description = "登录用户名")
    private String username;

    /**
     * 密码
     */
    @Schema(description = "密码")
    private String password;

    /**
     * 警号
     */
    @Schema(description = "警号")
    private String policeId;

    /**
     * 真实姓名
     */
    @Schema(description = "真实姓名")
    private String realName;

    /**
     * 所属部门单位ID
     */
    @Schema(description = "所属部门单位ID")
    private Long unitId;

    /**
     * 职位
     */
    @Schema(description = "职位")
    private String position;

    /**
     * 身份证号
     */
    @Schema(description = "身份证号")
    private String idCard;

    /**
     * 性别：0-女，1-男
     */
    @Schema(description = "性别：0-女，1-男")
    private Integer gender;

    /**
     * 联系电话
     */
    @Schema(description = "联系电话")
    private String phone;

    /**
     * 电子邮箱
     */
    @Schema(description = "电子邮箱")
    private String email;

    /**
     * 头像URL
     */
    @Schema(description = "头像URL")
    private String avatar;

    /**
     * 是否启用
     */
    @Schema(description = "是否启用")
    private Boolean enable;

    /**
     * 创建时间
     */
    @Schema(description = "创建时间")
    private Date createTime;

    /**
     * 更新时间
     */
    @Schema(description = "更新时间")
    private Date updateTime;

    /**
     * 最后登录时间
     */
    @Schema(description = "最后登录时间")
    private Date lastLoginTime;

    /**
     * 是否删除
     */
    @Schema(description = "是否删除")
    @TableField("is_deleted")
    private Boolean isDeleted;

    /**
     * 登录成功token
     */
    @Schema(description = "登录成功token")
    private String token;
}
