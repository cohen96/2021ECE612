package com.queuesystem.queuesystem.exception;

import com.queuesystem.queuesystem.result.BaseResult;
import com.queuesystem.queuesystem.result.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;
import java.io.FileNotFoundException;

/**
 * <p>
 * 异常处理
 * </p>
 *
 * @author
 * @Date
 * @Created
 */
@Slf4j
@RestControllerAdvice
public class HandleException {

    private static final String PARAM_CHECK_ERROR_STR = "参数校验异常";

    /**
     * 顶级未知异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(Throwable.class)
    public BaseResult<Object> globalExceptionHandler(Throwable e) {
        log.warn("未知异常", e);
        return new BaseResult<>(ResultCode.SERVER_INNER_ERROR).setMessage(e.getMessage());
    }

    /**
     * arg error
     *
     * @param e
     * @return
     */
    @ExceptionHandler(BindException.class)
    public BaseResult<Object> bindException(BindException e) {
        log.warn(PARAM_CHECK_ERROR_STR, e);
        return new BaseResult<>(ResultCode.PARAM_ERROR).setMessage(e.getMessage());
    }

    /**
     * arg error
     *
     * @param e
     * @return
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public BaseResult<Object> constraintViolationException(ConstraintViolationException e) {
        log.warn(PARAM_CHECK_ERROR_STR, e);
        return new BaseResult<>(ResultCode.PARAM_ERROR).setMessage(e.getMessage());
    }

    /**
     * arg error
     *
     * @param e
     * @return
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public BaseResult<Object> illegalArgumentException(IllegalArgumentException e) {
        log.warn(PARAM_CHECK_ERROR_STR, e);
        return new BaseResult<>(ResultCode.PARAM_ERROR).setMessage(e.getMessage());
    }

    /**
     * arg error
     *
     * @param validException
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public BaseResult<Object> methodArgumentNotValidException(MethodArgumentNotValidException validException) {
        log.warn(PARAM_CHECK_ERROR_STR, validException);
        StringBuilder errorMessage = new StringBuilder();
        validException.getBindingResult().getAllErrors().forEach(
                item -> errorMessage.append(item.getDefaultMessage()).append(",")
        );
        if (errorMessage.length() > 1) {
            errorMessage.setLength(errorMessage.length() - 1);
        }
        return new BaseResult<>(ResultCode.PARAM_ERROR).setMessage(errorMessage.toString());
    }

    @ExceptionHandler(FileNotFoundException.class)
    public BaseResult<Object> fileNotFoundException(FileNotFoundException e) {
        log.warn("file does not exist", e);
        return new BaseResult<>(ResultCode.RESOURCE_NOT_FOUND).setMessage(e.getMessage());
    }

    @ExceptionHandler(AccessDeniedException.class)
    public BaseResult<Object> accessDeniedException(AccessDeniedException e){
        log.warn("access denied", e);
        return new BaseResult<>(ResultCode.FORBIDDEN).setMessage(e.getMessage());
    }

}
