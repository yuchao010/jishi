package com.example.demo.exception;

/**
 * 工单id已经有处理部门了
 */
public class FenPaiHandleIdExistsException extends MyException{
    public FenPaiHandleIdExistsException() {
    }

    public FenPaiHandleIdExistsException(String message) {
        super(message);
    }

    public FenPaiHandleIdExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public FenPaiHandleIdExistsException(Throwable cause) {
        super(cause);
    }

    public FenPaiHandleIdExistsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
