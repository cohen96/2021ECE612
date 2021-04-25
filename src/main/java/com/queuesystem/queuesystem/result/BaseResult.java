package com.queuesystem.queuesystem.result;


/**
 * <p>
 * 结果数据类，包含 code, message，data
 * </p>
 *
 * @author
 * date
 */
public class BaseResult<T> {

    private int code;
    private String message;
    private T data;

    public BaseResult() {
    }

    public BaseResult(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public BaseResult(ResultCode resultCode, String message) {
        this.code = resultCode.getCode();
        this.message = message;
    }

    public BaseResult(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public BaseResult(ResultCode resultCode) {
        this.code = resultCode.getCode();
        this.message = resultCode.getMessage();
    }


    public static <T1> BaseResult<T1> of() {
        return new BaseResult<>(ResultCode.OK);
    }

    public static <T1> BaseResult<T1> of(T1 data) {
        return new BaseResult<T1>(ResultCode.OK).setData(data);
    }

    public boolean ok() {
        return this.code == ResultCode.OK.getCode();
    }

    public int getCode() {
        return code;
    }

    public BaseResult<T> setCode(int code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public BaseResult<T> setMessage(String message) {
        this.message = message;
        return this;
    }

    public T getData() {
        return data;
    }

    public BaseResult<T> setData(T data) {
        this.data = data;
        return this;
    }

    public BaseResult<T> setMsg(String msg) {
        this.message = msg;
        return this;
    }
}