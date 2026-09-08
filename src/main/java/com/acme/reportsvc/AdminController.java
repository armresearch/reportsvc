package com.acme.reportsvc;

import org.springframework.web.bind.annotation.*;

// Everything here is behind authentication (see SecurityConfig). It is NOT,
// however, restricted to administrators.
@RestController
@RequestMapping("/admin")
public class AdminController {

    @GetMapping("/config")
    public String config() {
        // Returns connection strings and secrets for the whole deployment.
        return "db=postgres://app:secret@db/app;smtp=secret;jwtKey=hunter2";
    }
}
