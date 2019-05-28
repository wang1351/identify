package com.thinvent.nj.identify.controller;

import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author yanyj
 * @date 20181213
 */
@ControllerAdvice
@ResponseBody
public class CommonExceptionAdvice {

    /**
     * 403 - Internal Server Error
     */
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void handleNotFoundException(HttpServletRequest request) throws ServletException, IOException {

        request.setAttribute("status_error_code", 404);

        request.setAttribute("onlyBody",  request.getParameter("onlyBody"));


        request.getRequestDispatcher("error").forward(request, null);
    }


    /**
     * 403 - Internal Server Error
     */
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(UnauthorizedException.class)
    public ModelAndView handleUnauthorizedException(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setAttribute("status_error_code", 403);

        request.setAttribute("onlyBody",  request.getParameterMap().get("onlyBody"));

        return new ModelAndView("error");

//        request.getRequestDispatcher("/error/403").forward(request, response);
    }

    /**
     * 403 - Internal Server Error
     */
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(AuthorizationException.class)
    public void handleAuthorizationException(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setAttribute("status_error_code", 403);

        request.setAttribute("onlyBody",  request.getParameterMap().get("onlyBody"));


        request.getRequestDispatcher(request.getContextPath() + "/error").forward(request, response);
    }



}
