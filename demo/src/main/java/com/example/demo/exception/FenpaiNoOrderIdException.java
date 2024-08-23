package com.example.demo.exception;

/**
 * 工单id没有对应的工单
 */
public class FenpaiNoOrderIdException extends MyException{
    public FenpaiNoOrderIdException() {
    }

    public FenpaiNoOrderIdException(String message) {
        super(message);
    }

    public FenpaiNoOrderIdException(String message, Throwable cause) {
        super(message, cause);
    }

    public FenpaiNoOrderIdException(Throwable cause) {
        super(cause);
    }

    public FenpaiNoOrderIdException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
