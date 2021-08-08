package com.harry.security;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

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

    /**
     *  @PreAuthorize("principal.username.equals('mike')") 注解的约束是，只有当前登录用户名为 mike 的用户才可以访问该方法。
     * @return
     */
    @PreAuthorize("principal.username.equals('mike')")
    @RequestMapping("/hello")
    public String hello(){
        return "一望二三里----某某诗人，hello user: "+getLoginUser();
    }

    /**
     *  @PreAuthorize("hasRole('admin')") 表示访问该方法的用户必须具备 admin 角色。
     * @return
     */
    @RequestMapping("/admin/index")
    @PreAuthorize("hasRole('admin')")
    public String adminIndex(){
        return "落霞与孤鹜齐飞，秋水共长天一色----某某诗人，hello user: "+getLoginUser();
    }

    /**
     * @Secured({"ROLE_user"})表示方法该方法的用户必须具备 user 角色，但是注意 user 角色需要加上 ROLE_ 前缀。
     * @return
     */
    @RequestMapping("/user/index")
    @Secured({"ROLE_user"})
    public String userIndex(){
        return "采菊东篱下悠然见南山----陶渊明，hello user: "+getLoginUser();
    }

    /**
     * 访问该接口的 score 参数必须大于 80，否则请求不予通过。
     * @return
     */
    @RequestMapping("/score")
    @PreAuthorize("#score>80")
    public String getScore(int score){
        return "当前登录用户"+getLoginUser()+"查询成绩："+score;
    }

    @RequestMapping("/getAllStudentScore")
    @PostFilter("filterObject>5")
    public List<Integer> getAllStudentScore() {
        List<Integer> users = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            users.add(i);
        }
        return users;
    }

    @RequestMapping("/getAllAge")
    @PreFilter("filterObject%2==0")
    public List<Integer> getAllAge(@RequestBody List<Integer> ages) {
        System.out.println("ages = " + ages);
        return ages;
    }

}
