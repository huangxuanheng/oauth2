package com.harry.security;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
class SecurityApplicationTests {

    @Test
    void contextLoads() {
        String encode = new BCryptPasswordEncoder().encode("123456");
        System.out.println(encode);
    }

}
