package com.example.demo.config;

import com.example.demo.filter.JwtAuthenticationFilter;
import com.example.demo.service.LoginUserDetailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;

/**
 * @author Ruby Ceng 曾春佳
 * @version 1.0.0
 * @ClassName WebSecurityConfig.java
 * @Description TODO SpringSecurity配置类
 * @createTime 2024年06月02日 17:32:00
 */

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Resource
    private LoginUserDetailService loginUserDetailService;
    @Resource
    private JwtAuthenticationFilter jwtAuthenticationFilter;
    @Bean
    public PasswordEncoder passwordEncoder(){
        // 使用BCrypt加密密码
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception{
        return super.authenticationManagerBean();
    }


    /*
     * @description: TODO 设置自定义的用户登录实现类以及passwordEncoding 密码加密
     * @author: Ruby Ceng 曾春佳
     * @date: 2024/6/3 13:49
     * @param: [auth]
     * @return: void
     **/
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(loginUserDetailService)// 设置自定义的userDetailsService
                .passwordEncoder(passwordEncoder());
    }





    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http
              // 禁用跨站csrf攻击防御
                .csrf().disable()
                // 关闭通过Session获取SecurityContext 通过在缓存中进行获取
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                // 对于登录,注册接口允许 匿名访问
                .antMatchers("/login").anonymous()
                .antMatchers("/register").anonymous()
                //剩余的都进行鉴权认证
                .anyRequest().authenticated()
        ;
        http.logout().disable();
        // 将过滤器JWTTokenAuthentication加入到过滤器链中 需要注意必须在Security的UsernamePasswordAuthenticationFilter过滤器链前获取到SecurityContext对象
        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);


    }




}
