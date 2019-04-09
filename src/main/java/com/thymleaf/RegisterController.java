package com.thymleaf;

import com.thymleaf.helper.FieldsValueMatchValidator;
import com.thymleaf.helper.UserHelper;
import com.thymleaf.model.User;
import com.thymleaf.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;

import javax.validation.Valid;
import java.util.Map;

@Controller
@EnableAutoConfiguration
public class RegisterController
{
    @RequestMapping("/register")
    public String welcome(Map<String, Object> model)
    {
        model.put("user", new User());
        return "register";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registerUserAccount(@ModelAttribute("user") @Valid User user,
             BindingResult result, WebRequest request, Errors errors)
    {

        if (result.hasErrors())
        {
            return "register";
        }

        UserHelper.listOfUsers().add(user);

        UserDetails userDetails = createUserDetailsService().loadUserByUsername(user.getLogin());
        Authentication auth = new UsernamePasswordAuthenticationToken(userDetails.getUsername (),userDetails.getPassword (),userDetails.getAuthorities ());
        SecurityContextHolder.getContext().setAuthentication(auth);

        return "redirect:/list";

    }


    @Bean
    public MyUserDetailsService createUserDetailsService() {
        return new MyUserDetailsService();
    }
}
