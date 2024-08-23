package com.example.demo.exception;

/**
 * 分页参数不能为空
 */
public class SelectNoPageVoException extends MyException{
    public SelectNoPageVoException() {
    }

    public SelectNoPageVoException(String message) {
        super(message);
    }

    public SelectNoPageVoException(String message, Throwable cause) {
        super(message, cause);
    }

    public SelectNoPageVoException(Throwable cause) {
        super(cause);
    }

    public SelectNoPageVoException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
