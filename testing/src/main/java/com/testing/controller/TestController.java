package com.testing.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TestController {

    @GetMapping("")
    public String getTest(){
        return "ravi";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String getTestAdmin(){
        return "api/admin called test controller";
    }

    @GetMapping("/user")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public String getTestNormal(){
        return "user/normal called test controller";
    }

    @GetMapping("/ravi")
    @PreAuthorize("hasAnyRole('RAVI')")
    public String getTestRavi(){
        return "ravi called test controller";
    }

}
