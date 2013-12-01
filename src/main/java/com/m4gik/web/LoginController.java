package com.m4gik.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author xpoft
 */
@Controller
@RequestMapping("/")
public class LoginController {
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String loginForm(HttpServletRequest request, ModelMap modelMap) {
        if (request.getAttribute("shiroLoginFailure") != null) {
            String exception = (String) request
                    .getAttribute("shiroLoginFailure");
            if (exception.endsWith("UnknownAccountException")
                    || exception.endsWith("IncorrectCredentialsException")) {
                modelMap.put("userNotFound", true);
            } else if (exception.endsWith("LockedAccountException")) {
                modelMap.put("userLocked", true);
            } else {
                modelMap.put("error", true);
            }
        }

        return "login";
    }
}
