package com.harry.security;

import com.harry.security.filter.JwtAuthenticationTokenFilter;
import com.harry.security.filter.MenuAccessDecisionManager;
import com.harry.security.filter.MenuFilterInvocationSecurityMetadataSource;
import com.harry.security.provider.UsernamePasswordProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import java.util.Collection;
import java.util.LinkedHashMap;


/**
 * @author harry
 * @email: huangxuanheng@163.com
 * @des:
 * @DATE: 2021/7/24 0024
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true,securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private AuthenticationSuccessHandler authenticationSuccessHandler;
    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;
    @Autowired
    private AuthenticationEntryPoint authenticationEntryPoint;
    @Autowired
    private LogoutSuccessHandler logoutSuccessHandler;
    @Autowired
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;


    @Autowired
    private MenuAccessDecisionManager menuAccessDecisionManager;
    @Autowired
    private MenuFilterInvocationSecurityMetadataSource menuFilterInvocationSecurityMetadataSource;

    @Bean
    public DaoAuthenticationProvider getDaoAuthenticationProvider(){
        UsernamePasswordProvider usernamePasswordProvider = new UsernamePasswordProvider();
        usernamePasswordProvider.setUserDetailsService(userDetailsService);
        return usernamePasswordProvider;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
//                .antMatchers("/getVerifyCode")
//                .permitAll()
                .anyRequest().authenticated()
                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                    @Override
                    public <O extends FilterSecurityInterceptor> O postProcess(O object) {
                        object.setSecurityMetadataSource(menuFilterInvocationSecurityMetadataSource); //动态获取url权限配置
                        object.setAccessDecisionManager(menuAccessDecisionManager); //权限判断
                        return object;
                    }
                })
                .and().formLogin()
                .successHandler(authenticationSuccessHandler)
                .failureHandler(authenticationFailureHandler)
        .permitAll()
                .and().exceptionHandling()
                .authenticationEntryPoint(authenticationEntryPoint)
                .and().logout().logoutSuccessHandler(logoutSuccessHandler)
                .and().csrf().disable()
        ;
        http.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);

    }

    @Bean
    public LinkedHashMap<RequestMatcher, Collection<ConfigAttribute>> getAnyRequest() {
        LinkedHashMap<RequestMatcher, Collection<ConfigAttribute>>anyRequst=new LinkedHashMap<>();
        anyRequst.put(new AntPathRequestMatcher("/getVerifyCode", null),null);
        return anyRequst;
    }
}
