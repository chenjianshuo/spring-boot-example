package com.cjs.exception.exception;

import com.cjs.exception.dto.ErrorInfo;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by chenjianshuo on 2018/8/27 17:30.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public ModelAndView defaultErrorHandle(HttpServletRequest request, Exception e) {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("excetion", e);
        modelAndView.addObject("url", request.getRequestURL().toString());
        modelAndView.setViewName("error");
        return modelAndView;
    }

    @ExceptionHandler(value = MyException.class)
    @ResponseBody
    public ErrorInfo<String> jsonErrorHandle(HttpServletRequest req, MyException e) {
        ErrorInfo<String> errorInfo = new ErrorInfo<>();
        errorInfo.setMessage(e.getMessage());
        errorInfo.setCode(ErrorInfo.ERROR);
        errorInfo.setData("Some Data");
        errorInfo.setUrl(req.getRequestURL().toString());
        return errorInfo;

    }
}
