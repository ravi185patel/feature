package com.application.authentication.security;

import com.application.authentication.config.ConfigProperitesFiles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@EnableResourceServer
@Configuration
@Order(2)
public class ResourceServerConfig  extends ResourceServerConfigurerAdapter {

    @Autowired
    private ConfigProperitesFiles configProperitesFiles;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .headers()
                .frameOptions()
                .disable()
                .and()
                .authorizeRequests()
                .antMatchers(configProperitesFiles.getResourceAllUrl()).permitAll()
                .antMatchers("/api/test/adminapi").hasRole("ADMIN")
                .anyRequest().authenticated();

        /*http
                .headers()
                .frameOptions()
                .disable()
                .and()
                .authorizeRequests()
                .antMatchers("/api/register").permitAll()
                .antMatchers("/oauth/token","/","/home","/register","/login").permitAll()
                .antMatchers("/api/login","/api/admin/**","/api/adminmock/**","/api/user/**"
                        ,"/api/doctor/**","/api/patient/**","/api/test/**","/api/file/**").permitAll();//.authenticated();*/
    }
}