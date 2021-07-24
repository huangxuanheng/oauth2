package com.harry.client2;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

/**
 * @author harry
 * @email:
 * @des: 获取登录用户接口信息
 * @createTime:
 */
@RestController
public class UserController {
    @Value("${server.port}")
    private String port;
    @GetMapping("/getLogin")
    public String getLogin() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return "当前登录的用户是"+authentication.getName() + Arrays.toString(authentication.getAuthorities().toArray())+",登录端口："+port;
    }
}
