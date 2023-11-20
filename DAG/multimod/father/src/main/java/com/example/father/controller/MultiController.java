package com.example.father.controller;

import com.example.father.service.MultiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MultiController {
    @Autowired
    private MultiService multiService;
    
    @RequestMapping("/")
    public int hello(){
        return multiService.service();
    }
}
