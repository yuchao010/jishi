package com.example.demo.exception;

/**
 * 工单id不能为空
 * 部门id不能为空
 * 部门名称不能为空
 */
public class FenPaiNoOperationException extends MyException{
    public FenPaiNoOperationException() {
    }

    public FenPaiNoOperationException(String message) {
        super(message);
    }

    public FenPaiNoOperationException(String message, Throwable cause) {
        super(message, cause);
    }

    public FenPaiNoOperationException(Throwable cause) {
        super(cause);
    }

    public FenPaiNoOperationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
