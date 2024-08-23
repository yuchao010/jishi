package com.example.demo.exception;

/**
 * 删除的工单id不存在
 */
public class DeleteNoIdException extends MyException{
    public DeleteNoIdException() {
    }

    public DeleteNoIdException(String message) {
        super(message);
    }

    public DeleteNoIdException(String message, Throwable cause) {
        super(message, cause);
    }

    public DeleteNoIdException(Throwable cause) {
        super(cause);
    }

    public DeleteNoIdException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
