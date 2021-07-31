package com.harry.security;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author harry
 * @email: huangxuanheng@163.com
 * @des:
 * @DATE: 2021/7/24 0024
 */
@RestController
public class HelloController {

//    @PreAuthorize("admin")
    @RequestMapping("/sayHello")
    public String sayHello(){

        return "十年生死两茫茫，不思量，自难忘----苏轼，hello "+getLoginUser();
    }

    @RequestMapping("/admin/home")
    public String home(){
        return "天生我材必有用，千金散尽还复来----李白，hello admin :"+getLoginUser();
    }

    @RequestMapping("/user/home")
    public String userHome(){
        return "滚滚长江东逝水----苏轼，hello user: "+getLoginUser();
    }

    //获取登录用户
    private String getLoginUser(){
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
