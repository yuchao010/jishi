package com.example.demo.advice;

import com.example.demo.exception.CreateNoOperationException;
import com.example.demo.exception.DeleteNoIdException;
import com.example.demo.exception.MyException;
import com.example.demo.result.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@ControllerAdvice
public class ExceptionHandle {
    //直接接收所有自定义异常
    @ExceptionHandler(MyException.class)
    @ResponseBody
    public Result<String> createNoOperationException(MyException exception){
        return Result.error(exception.getMessage());
    }
}
