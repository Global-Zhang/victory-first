package com.hitTheRoad.server.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("hello")
    public String hello(){
        return "hello";
    }

    @GetMapping("/employee/basic/hello")
    public String hello01(){
        return "/employee/basic/hello";
    }

    @GetMapping("/employee/advance/hello")
    public String hello02(){
        return "/employee/advance/hello";
    }
}
