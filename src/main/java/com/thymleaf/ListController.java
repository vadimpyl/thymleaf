package com.thymleaf;


import com.sun.javafx.scene.traversal.Direction;
import com.thymleaf.helper.UserHelper;
import com.thymleaf.model.User;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@EnableAutoConfiguration
public class ListController
{

    private Date birthDateFrom;
    private Date birthDateTo;

    @RequestMapping("/list")
    public String list(Map<String, Object> model)
    {
        List<User> users = new ArrayList<>(UserHelper.listOfUsers());
        model.put("users", users);
        model.put("currentUser", getCurrentUser());
        return "list";
    }

    @RequestMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") long id, Map<String, Object> model) {

        UserHelper.listOfUsers().remove(UserHelper.load(id));
        model.put("users", UserHelper.listOfUsers());
        return "list";
    }

    @RequestMapping(value= "/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable("id") Long id, ModelMap model )
    {
        model.put("user", UserHelper.load(id));
        return  "edit";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String registerUserAccount(@ModelAttribute("user") User user)
    {
        UserHelper.update(user);
        return "redirect:/list";
    }

    @RequestMapping(value = "/searchByDate", method = RequestMethod.POST)
    public String searchByDate(Map<String, Object> model,
                               @RequestParam String birthDateFrom,
                               @RequestParam String birthDateTo) throws ParseException
    {

        ArrayList<User> result = new ArrayList<>();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        Date dateFrom = format.parse(birthDateFrom);
        Date dateTo = format.parse(birthDateTo);

        for (User user : UserHelper.listOfUsers())
        {
            Date bornDate = user.getBirthDate();

            if(dateFrom != null && bornDate.before(dateFrom))
            {
                continue;
            }

            if(dateTo != null && bornDate.after(dateTo))
            {
                continue;
            }

            result.add(user);
        }

        model.put("users", result);
        model.put("birthDateFrom", birthDateFrom);
        model.put("birthDateTo", birthDateTo);

        return "list";
    }




















    private String getCurrentUser()
    {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails)
        {
             username = ((UserDetails)principal).getUsername();
        }
        else
        {
             username = principal.toString();
        }

        return username;
    }

    public Date getBirthDateFrom()
    {
        return birthDateFrom;
    }

    public void setBirthDateFrom(Date birthDateFrom)
    {
        this.birthDateFrom = birthDateFrom;
    }

    public Date getBirthDateTo()
    {
        return birthDateTo;
    }

    public void setBirthDateTo(Date birthDateTo)
    {
        this.birthDateTo = birthDateTo;
    }
}
