package com.example.demo.exception;

/**
 * 部门id没有对应的部门
 */
public class FenpaiNoDeptIdException extends MyException{
    public FenpaiNoDeptIdException() {
    }

    public FenpaiNoDeptIdException(String message) {
        super(message);
    }

    public FenpaiNoDeptIdException(String message, Throwable cause) {
        super(message, cause);
    }

    public FenpaiNoDeptIdException(Throwable cause) {
        super(cause);
    }

    public FenpaiNoDeptIdException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
