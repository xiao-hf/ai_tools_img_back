package com.xiao.dao;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Date;
import lombok.Data;

/**
 * 系统操作日志
 */
@Schema(description = "系统操作日志")
@Data
@TableName(value = "sys_operation_log")
public class SysOperationLog {
    /**
     * 日志主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    @Schema(description = "日志主键")
    private Long id;

    /**
     * 模块名称
     */
    @TableField(value = "`module`")
    @Schema(description = "模块名称")
    private String module;

    /**
     * 操作类型
     */
    @TableField(value = "operation_type")
    @Schema(description = "操作类型")
    private String operationType;

    /**
     * 操作描述
     */
    @TableField(value = "description")
    @Schema(description = "操作描述")
    private String description;

    /**
     * 请求方法
     */
    @TableField(value = "`method`")
    @Schema(description = "请求方法")
    private String method;

    /**
     * 请求URL
     */
    @TableField(value = "request_url")
    @Schema(description = "请求URL")
    private String requestUrl;

    /**
     * 请求方式
     */
    @TableField(value = "request_method")
    @Schema(description = "请求方式")
    private String requestMethod;

    /**
     * 请求参数
     */
    @TableField(value = "request_params")
    @Schema(description = "请求参数")
    private String requestParams;

    /**
     * 返回结果
     */
    @TableField(value = "response_result")
    @Schema(description = "返回结果")
    private String responseResult;

    /**
     * 操作状态（0失败 1成功）
     */
    @TableField(value = "`status`")
    @Schema(description = "操作状态（0失败 1成功）")
    private Integer status;

    /**
     * 错误消息
     */
    @TableField(value = "error_msg")
    @Schema(description = "错误消息")
    private String errorMsg;

    /**
     * 操作用户ID
     */
    @TableField(value = "user_id")
    @Schema(description = "操作用户ID")
    private Long userId;

    /**
     * 操作用户名
     */
    @TableField(value = "username")
    @Schema(description = "操作用户名")
    private String username;

    /**
     * 操作用户IP
     */
    @TableField(value = "ip")
    @Schema(description = "操作用户IP")
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
     * 执行时长(毫秒)
     */
    @TableField(value = "`time`")
    @Schema(description = "执行时长(毫秒)")
    private Long time;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    @Schema(description = "创建时间")
    private Date createTime;
}