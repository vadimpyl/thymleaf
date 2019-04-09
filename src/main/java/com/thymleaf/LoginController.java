package com.thymleaf;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
@EnableAutoConfiguration
public class LoginController
{
    private String message = "Hello World";

    @RequestMapping("/login")
    public String welcome(Map<String, Object> model)

    {
        //model.put("message", this.message);
        return "login";
    }

}
