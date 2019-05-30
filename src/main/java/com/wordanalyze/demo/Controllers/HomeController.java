package com.wordanalyze.demo.Controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController
{

    @RequestMapping("/")
    public String home(){
        System.out.println(System.getProperty("user.dir"));
        return "backend for word frequency analyzer";
    }
}