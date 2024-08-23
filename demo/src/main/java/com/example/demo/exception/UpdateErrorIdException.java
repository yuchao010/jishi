package com.example.demo.exception;

/**
 * 没有该id的工单
 */
public class UpdateErrorIdException extends MyException{
    public UpdateErrorIdException() {
    }

    public UpdateErrorIdException(String message) {
        super(message);
    }

    public UpdateErrorIdException(String message, Throwable cause) {
        super(message, cause);
    }

    public UpdateErrorIdException(Throwable cause) {
        super(cause);
    }

    public UpdateErrorIdException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
