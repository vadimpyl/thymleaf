package com.thymleaf;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
@EnableAutoConfiguration
public class LoginController
{

    @RequestMapping("/login")
    public String welcome(Map<String, Object> model)

    {
        return "login";
    }

}
