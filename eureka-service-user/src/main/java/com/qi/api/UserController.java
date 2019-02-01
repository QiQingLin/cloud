package com.qi.api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Value("${name}")
    private String name;

    @Value("${server.port}")
    private String port;


    @GetMapping("/")
    public String getName() {
        return "端口:" + port + "...." + "姓名:" + name;
    }

}
