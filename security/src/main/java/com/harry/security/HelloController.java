package com.harry.security;

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

    @RequestMapping("/sayHello")
    public String sayHello(){
        return "十年生死两茫茫，不思量，自难忘----苏轼，hello";
    }
}
