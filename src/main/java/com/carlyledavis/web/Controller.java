package com.carlyledavis.web;

import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @RequestMapping( method= RequestMethod.GET, value="/hello" )
    @RequiresAuthentication
    public String hello(){
        return "hello";
    }
}
