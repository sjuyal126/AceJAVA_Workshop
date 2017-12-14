package com.java.error;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(value = { Exception.class })
    public ModelAndView handleError(Exception e) {
    	ModelAndView model = new ModelAndView("error");
        model.addObject("message", e.getMessage());
        return model;

    }
}