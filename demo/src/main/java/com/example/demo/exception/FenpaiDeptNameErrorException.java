package com.example.demo.exception;

/**
 * 部门id和部门名称不对应
 */
public class FenpaiDeptNameErrorException extends MyException{
    public FenpaiDeptNameErrorException() {
    }

    public FenpaiDeptNameErrorException(String message) {
        super(message);
    }

    public FenpaiDeptNameErrorException(String message, Throwable cause) {
        super(message, cause);
    }

    public FenpaiDeptNameErrorException(Throwable cause) {
        super(cause);
    }

    public FenpaiDeptNameErrorException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
