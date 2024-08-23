package com.example.demo.exception;

/**
 * 工单编号不能为空
 * 工单类型不能为空
 * 标题不能为空
 * 内容不能为空
 */
public class CreateNoOperationException extends MyException{
    public CreateNoOperationException() {
    }

    public CreateNoOperationException(String message) {
        super(message);
    }

    public CreateNoOperationException(String message, Throwable cause) {
        super(message, cause);
    }

    public CreateNoOperationException(Throwable cause) {
        super(cause);
    }

    public CreateNoOperationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
