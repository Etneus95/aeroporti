package com.example.aeroporti.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DebugController {

    @GetMapping("/test")
    public String test() {
        System.out.println("GET /test chiamato");
        return "test";
    }
}
