package com.application.authentication.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/test")
public class AuthController {

    @GetMapping("")
    public String getTest(){
        return "ravi";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String getTestAdmin(){
        return "admin";
    }

    @GetMapping("/user")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public String getTestNormal(){
        return "normal";
    }

    @GetMapping("/ravi")
    @PreAuthorize("hasAnyRole('RAVI')")
    public String getTestRavi(){
        return "Ravidpatel";
    }

}
