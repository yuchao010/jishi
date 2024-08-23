package com.example.demo.exception;

/**
 * 工单编号不能重复
 */
public class CreateOperationRepeatException extends MyException{
    public CreateOperationRepeatException() {
    }

    public CreateOperationRepeatException(String message) {
        super(message);
    }

    public CreateOperationRepeatException(String message, Throwable cause) {
        super(message, cause);
    }

    public CreateOperationRepeatException(Throwable cause) {
        super(cause);
    }

    public CreateOperationRepeatException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
