package org.evileureka.controller;

import org.evileureka.entity.UserLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class Test1Controller {

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/test1")
    public UserLogin test1()
    {
        UserLogin userLogin = new UserLogin();
        userLogin.username = "requser";
        userLogin.password = "reqpass";
        UserLogin res = this.restTemplate.postForObject("http://eurekaclient/test", userLogin, UserLogin.class);
        return res;
    }
}
