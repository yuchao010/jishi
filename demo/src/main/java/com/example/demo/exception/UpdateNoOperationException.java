package com.example.demo.exception;

/**
 * 工单id不能为空
 */
public class UpdateNoOperationException extends MyException{
    public UpdateNoOperationException() {
    }

    public UpdateNoOperationException(String message) {
        super(message);
    }

    public UpdateNoOperationException(String message, Throwable cause) {
        super(message, cause);
    }

    public UpdateNoOperationException(Throwable cause) {
        super(cause);
    }

    public UpdateNoOperationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
