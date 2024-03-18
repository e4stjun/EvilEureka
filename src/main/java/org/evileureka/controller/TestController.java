package org.evileureka.controller;

import org.evileureka.entity.UserLogin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @RequestMapping("/test")
    public UserLogin test(@RequestBody UserLogin userLogin)
    {
        userLogin = new UserLogin();
        userLogin.username = "admin";
        userLogin.password = "passw0rd";
        return userLogin;
    }
}
