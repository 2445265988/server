package com.example.server.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin
@Controller
public class Login {

    @ResponseBody
    @RequestMapping(value = "/login")
    public Map userLogin(HttpServletRequest request){
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println(username);
        System.out.println(password);
        Map<String, String> map = new HashMap<String, String>();
        map.put("result", "true");
        System.out.println("now in userLogin");
        return map;
    }
}
