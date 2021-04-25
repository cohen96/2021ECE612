package com.queuesystem.queuesystem.result;

/**
 * 业务状态码
 *
 * @author
 */

public enum ResultCode {

    /**
     * 正常业务状态
     */
    OK(200, "OK"),

    /**
     * 未授权
     */
    UNAUTHORIZED(401, "未授权"),

    /**
     * 拒绝请求
     */
    FORBIDDEN(403, "拒绝请求"),

    /**
     * 资源不存在
     */
    RESOURCE_NOT_FOUND(404, "资源不存在"),

    /**
     * 服务器内部错误
     */
    SERVER_INNER_ERROR(500, "服务器内部错误"),

    PARAM_ERROR(704, "参数错误"),

    CAPTCHA_ERROR(705, "验证码错误"),

    AUTH_ERROR(401, "认证失败"),
    AUDIT_USER(10002, "账户审核中"),
    USER_EXIST_ERROR(10003, "用户已经存在"),

    PASSWORD_UPDATE_ERROR(10010, "密码修改失败"),

    MEDIA_UPDATE_ERROR(10020, "媒体信息更新失败"),

    JOB_DELETE_ERROR(10030, "删除任务及关联数据失败");

    private final int code;
    private final String message;

    ResultCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}