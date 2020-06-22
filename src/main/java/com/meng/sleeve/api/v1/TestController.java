package com.meng.sleeve.api.v1;

import com.meng.sleeve.model.Test;
import jdk.nashorn.internal.objects.annotations.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("test")
public class TestController {

    @Autowired
    private Test test;

    @GetMapping
    public void getTest(){
        System.out.println(this.test);
    }

}
