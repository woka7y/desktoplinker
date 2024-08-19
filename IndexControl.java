package com.springmvc.controller;

import com.springmvc.pojo.*;
import com.springmvc.service.AdminService;
import com.springmvc.service.IndexService;
import com.springmvc.utils.LoginSet;
import com.springmvc.utils.TokenSys;
import org.springframework.stereotype.Controller;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Controller
public class IndexControl {

    @Resource
    AdminService adminService;
    @Resource
    IndexService indexService;
    @Resource
    LoginSet loginSet;


    @RequestMapping("/index")
    public String index(HttpServletRequest request,Model model){
        indexService.BuildShop(model,request);
        return "index";
    }

    @RequestMapping("/user/login")
    public String login(Model model){
        model.addAttribute("locat","/root/Picture");
        return "login";
    }

    @RequestMapping("/test")
    public String test(Model model){
        return "test";
    }

    @RequestMapping(value = "/user/check",method = RequestMethod.POST)
    public String check(HttpServletResponse response, HttpServletRequest request, Model model) throws Exception{
        Admin rul;
        String LoginUser, LoginPass;
        LoginPass = request.getParameter("password");
        LoginUser = request.getParameter("username");
        rul = adminService.check(LoginUser,LoginPass);
        if(rul!=null){
            Map<String, Object> loginInfo = new HashMap<String, Object>();
            loginInfo.put("userId", LoginUser);
            model.addAttribute("LoginCondition",LoginUser);
            String sessionId = TokenSys.createToken(loginInfo);
            loginSet.AddCookie(response,"token",sessionId);
            //System.out.println("sessionID \r\n\r\n" +sessionId);
            response.sendRedirect("/index");
            return null;
        }else {
            model.addAttribute("regrul","登陆失败请重试！");
            login(model);
            return "login";
        }
    }
}
