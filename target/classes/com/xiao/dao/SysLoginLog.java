package com.xiao.dao;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Date;
import lombok.Data;

/**
 * 系统登录日志
 */
@Schema(description = "系统登录日志")
@Data
@TableName(value = "sys_login_log")
public class SysLoginLog {
    /**
     * 日志ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    @Schema(description = "日志ID")
    private Long id;

    /**
     * 用户ID
     */
    @TableField(value = "user_id")
    @Schema(description = "用户ID")
    private Long userId;

    /**
     * 用户名
     */
    @TableField(value = "username")
    @Schema(description = "用户名")
    private String username;

    /**
     * 登录IP
     */
    @TableField(value = "ip")
    @Schema(description = "登录IP")
    private String ip;

    /**
     * 浏览器类型
     */
    @TableField(value = "browser")
    @Schema(description = "浏览器类型")
    private String browser;

    /**
     * 操作系统
     */
    @TableField(value = "os")
    @Schema(description = "操作系统")
    private String os;

    /**
     * 设备类型(PC/Mobile)
     */
    @TableField(value = "device_type")
    @Schema(description = "设备类型(PC/Mobile)")
    private String deviceType;

    /**
     * 登录状态（0失败 1成功）
     */
    @TableField(value = "`status`")
    @Schema(description = "登录状态（0失败 1成功）")
    private Integer status;

    /**
     * 提示消息
     */
    @TableField(value = "msg")
    @Schema(description = "提示消息")
    private String msg;

    /**
     * 登录时间
     */
    @TableField(value = "login_time")
    @Schema(description = "登录时间")
    private Date loginTime;

    /**
     * 登录类型（PASSWORD-密码登录，CODE-验证码登录，SSO-单点登录）
     */
    @TableField(value = "login_type")
    @Schema(description = "登录类型（PASSWORD-密码登录，CODE-验证码登录，SSO-单点登录）")
    private String loginType;

    /**
     * 用户代理信息
     */
    @TableField(value = "user_agent")
    @Schema(description = "用户代理信息")
    private String userAgent;

    /**
     * 登录模块(前台/后台)
     */
    @TableField(value = "login_module")
    @Schema(description = "登录模块(前台/后台)")
    private String loginModule;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    @Schema(description = "创建时间")
    private Date createTime;
}