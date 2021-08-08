package com.harry.security;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Random;

/**
 * @author harry
 * @email: huangxuanheng@163.com
 * @des:
 * @DATE: 2021/8/8 0008
 */
@RestController
public class VerifyController {

    @GetMapping("/getVerifyCode")
    public String getVerifyCode(HttpSession session){
        String verifyCode=getVerifyCode();
        session.setAttribute("verifyCode",verifyCode);
        return verifyCode;
    }

    private String getVerifyCode() {
        StringBuilder sb=new StringBuilder();
        Random random=new Random();
        for (int x=0;x<4;x++){
            int i = random.nextInt(10);
            sb.append(i);
        }
        return sb.toString();
    }

}
