package com.hustlers.springboot.blog.services;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Service
public class ErrorServiceImpl implements ErrorService {

    @Override
    public String handle_404_500_error(HttpServletRequest request, Exception exc) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (status != null) {
            Integer statusCode = Integer.parseInt(status.toString());

            if (statusCode == HttpStatus.NOT_FOUND.value()) {
                return "error/404";
            } else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                return "error/500";
            }
        }
        return "error/error";
    }
}
