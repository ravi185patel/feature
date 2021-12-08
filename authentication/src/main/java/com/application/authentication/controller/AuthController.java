package com.application.authentication.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/test")
public class AuthController {

    @GetMapping("")
    @PreAuthorize("hasRole('USER')")
    public String getTest(){
        return "ravi";
    }

    @GetMapping("/adminapi")
//    @PreAuthorize("hasRole('ADMIN')")
    public String getTestAdmin(){
        return "admin";
    }

    @GetMapping("/normal")
    public String getTestNormal(){
        return "normal";
    }

}
