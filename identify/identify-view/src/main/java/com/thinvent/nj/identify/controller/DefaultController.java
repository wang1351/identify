package com.thinvent.nj.identify.controller;

import com.thinvent.nj.common.util.StringUtil;
import com.thinvent.nj.uc.shiro.UserContextUtil;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author yanyj
 * @date 20181213
 */
@Controller
public class DefaultController implements ErrorController {

    @RequestMapping(path = "/index")
    public String index(HttpServletRequest request, HttpServletResponse httpServletResponse) throws ServletException, IOException {




        String url = getFirstMenuPath(request.getSession().getAttribute("ipAddress").toString(), UserContextUtil.currentUser().getMenus());

        if(!StringUtil.isNullOrEmpty(url)) {
            httpServletResponse.sendRedirect(url);
        }

        return "index";
    }



    private String getFirstMenuPath(String requestPath, List<Map<String, Object>> menus) {

        if(requestPath.endsWith("/index")) {
            requestPath = requestPath.substring(0, requestPath.length() - 6);
        }


        if(requestPath.endsWith("/")) {
            requestPath = requestPath.substring(0, requestPath.length() - 1);
        }

        for (Map<String, Object> menu : menus) {
            if(((String) menu.get("uri")).equals(requestPath)) {

                List<Map<String, Object>> children = (List<Map<String, Object>>)menu.get("children");

                while(children.size() > 0 && ((List<Map<String, Object>>)children.get(0).get("children")).size() > 0) {
                    children = (List<Map<String, Object>>)children.get(0).get("children");
                }


                return (String) children.get(0).get("uri");
            }
        }


        return null;
    }


    // error Controller

    @RequestMapping("/error")
    public ModelAndView handleError(HttpServletRequest request){
        //获取statusCode:401,404,500
        Integer statusCode = (Integer) request.getAttribute("status_error_code");

        if(statusCode == null) {
            request.setAttribute("status_error_code", 404);
            request.setAttribute("onlyBody",   request.getParameter("onlyBody"));
        }

        return new ModelAndView("error");

    }

    @RequestMapping("/error/403")
    public String handleError403(HttpServletRequest request){
        //获取statusCode:401,404,500

        return "error";

    }

    @Override
    public String getErrorPath() {
        return null;
    }
}
