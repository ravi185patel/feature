package com.application.authentication.security;

import com.application.authentication.config.ConfigProperitesFiles;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@Order(1)
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService customUserDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ConfigProperitesFiles configProperitesFiles;

    @Autowired
    public void configureGlobal(final AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(customUserDetailsService)
                .passwordEncoder(passwordEncoder);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
      /*  http
                .authorizeRequests()
                .antMatchers("/api/register").permitAll()
                .antMatchers("/oauth/token","/","/home","/register","/login").permitAll()
                .antMatchers("/api/login","/api/admin/**","/api/adminmock/**","/api/file/**").permitAll()//.hasRole("ADMIN")
                .antMatchers("/api/doctor/**","/api/patient/**","/api/test/**","/api/file/**").permitAll()//.hasRole("USER")
                .anyRequest().authenticated()
                .and()
                .httpBasic()
                .and()
                .csrf().disable();*/

        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(configProperitesFiles.getSecurityAllUrl()).permitAll()
                .antMatchers("/api/test/adminapi").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .httpBasic();
//                .and()
//                .formLogin();
    }

//        @Bean
//    public CustomUsernamePasswordAuthenticationFilter customUsernamePasswordAuthenticationFilter() throws Exception {
//        CustomUsernamePasswordAuthenticationFilter authenticationFilter
//                = new CustomUsernamePasswordAuthenticationFilter();
//        authenticationFilter.setAuthenticationSuccessHandler(this::loginSuccessHandler);
//        authenticationFilter.setAuthenticationFailureHandler(this::loginFailureHandler);
//        authenticationFilter.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/oauth/token", "POST"));
//        // here you can change version number of application.
//        authenticationFilter.setAuthenticationManager(authenticationManagerBean());
//        return authenticationFilter;
//    }
//
//    private void loginSuccessHandler(
//            HttpServletRequest request,
//            HttpServletResponse response,
//            Authentication authentication) throws IOException {
//
//        User loggedInUser = (User) customUserDetailsService.loadUserByUsername(authentication.getName());
////                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + authentication.getName()));
//        response.setStatus(HttpStatus.OK.value());
//        objectMapper.writeValue(response.getWriter(), loggedInUser);
//    }
//
//    private void loginFailureHandler(
//            HttpServletRequest request,
//            HttpServletResponse response,
//            AuthenticationException e) throws IOException {
//
//        response.setStatus(HttpStatus.UNAUTHORIZED.value());
//        objectMapper.writeValue(response.getWriter(), "Nopity nop!");
//    }
//
//    private void logoutSuccessHandler(
//            HttpServletRequest request,
//            HttpServletResponse response,
//            Authentication authentication) throws IOException {
//
//        response.setStatus(HttpStatus.OK.value());
//        objectMapper.writeValue(response.getWriter(), "Bye!");
//    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
