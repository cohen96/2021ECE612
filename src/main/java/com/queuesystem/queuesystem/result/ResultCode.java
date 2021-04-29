package com.queuesystem.queuesystem.result;

/**
 * 业务状态码
 *
 * @author
 */

public enum ResultCode {

    /**
     * Normal status
     */
    OK(200, "OK"),

    /**
     * with no auth
     */
    UNAUTHORIZED(401, "No authorization"),

    /**
     * req deny
     */
    FORBIDDEN(403, "request denied"),

    /**
     * resource not found
     */
    RESOURCE_NOT_FOUND(404, "resource not found"),

    /**
     * error
     */
    SERVER_INNER_ERROR(500, "server inner error"),

    PARAM_ERROR(704, "param error"),

    CAPTCHA_ERROR(705, "wrong captcha"),

    AUTH_ERROR(401, "auth error"),
    AUDIT_USER(10002, "audit user"),
    USER_EXIST_ERROR(10003, "user already exist"),

    PASSWORD_UPDATE_ERROR(10010, "change pw failed"),

    MEDIA_UPDATE_ERROR(10020, "media info update failed"),

    JOB_DELETE_ERROR(10030, "job and data del error");

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