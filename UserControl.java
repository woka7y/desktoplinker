package com.springmvc.controller;

import com.springmvc.pojo.Admin;
import com.springmvc.service.*;
import com.springmvc.utils.LoginSet;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import org.springframework.stereotype.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.*;
import java.io.File;

@Controller
public class UserControl {
    @Resource
    AdminService adminService;
    @Resource
    IndexService indexService;
    @Resource
    LoginSet loginSet;

    @RequestMapping("/user/loginout")
    public void LoginOut(Model model,HttpServletRequest request,HttpServletResponse response) throws Exception{
        loginSet.delCookie(request,response,"token");
        response.sendRedirect("/index");
    }

}
