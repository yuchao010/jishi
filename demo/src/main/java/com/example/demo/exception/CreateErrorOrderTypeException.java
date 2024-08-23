package com.example.demo.exception;

/**
 * 工单类型只能是0/1/3
 */
public class CreateErrorOrderTypeException extends MyException{
    public CreateErrorOrderTypeException() {
    }

    public CreateErrorOrderTypeException(String message) {
        super(message);
    }

    public CreateErrorOrderTypeException(String message, Throwable cause) {
        super(message, cause);
    }

    public CreateErrorOrderTypeException(Throwable cause) {
        super(cause);
    }

    public CreateErrorOrderTypeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
