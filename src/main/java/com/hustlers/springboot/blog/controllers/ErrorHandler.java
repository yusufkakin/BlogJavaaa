package com.hustlers.springboot.blog.controllers;

import com.hustlers.springboot.blog.services.ErrorService;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ErrorHandler implements ErrorController {

    private ErrorService errorService;

    public ErrorHandler(ErrorService errorService) {
        this.errorService = errorService;
    }

    @GetMapping("/error")
    public String handleError(HttpServletRequest request, Exception exc) {

        return errorService.handle_404_500_error(request, exc);
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
