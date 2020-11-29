package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author gao.rui36
 * @Date 2020/11/28
 **/
@RequestMapping("/test")
@RestController
public class TestController {
    @GetMapping
    public String hello() {
        return "test";
    }
}
