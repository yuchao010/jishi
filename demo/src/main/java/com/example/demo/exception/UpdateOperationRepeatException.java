package com.example.demo.exception;

/**
 * 工单编号不能重复
 */
public class UpdateOperationRepeatException extends MyException{
    public UpdateOperationRepeatException() {
    }

    public UpdateOperationRepeatException(String message) {
        super(message);
    }

    public UpdateOperationRepeatException(String message, Throwable cause) {
        super(message, cause);
    }

    public UpdateOperationRepeatException(Throwable cause) {
        super(cause);
    }

    public UpdateOperationRepeatException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
