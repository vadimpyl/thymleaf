package com.thymleaf.helper;

import com.thymleaf.model.User;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserHelper
{
    static ArrayList<User> userList = new ArrayList<User>() {{
        add(new User(1,"user1","John","Bee",parseDate("1994-02-01"),"London","England","a"));
        add(new User(2,"user2","Krzysztof","Wilski", parseDate("1987-01-23"),"Szczecin","Poland","a"));
        add(new User(3,"user3","Andrew","Hook", parseDate("1961-11-29"),"Berlin","Germany", "a"));
        add(new User(4,"user4","Anna","Clark", parseDate("1999-08-09"),"Paris","France", "a"));
        add(new User(5,"user5","Dee","Knock", parseDate("1974-07-14"),"Madrid","Spain", "a"));
        add(new User(6,"user6","Foo","Lant", parseDate("2001-12-31"),"Lisbon","Portugal", "a"));
        add(new User(7,"user7","Ke","Larson",parseDate("1959-05-16"),"Prague","Czech Republic", "a"));
        add(new User(8, "user8","Richard","Ant", parseDate("1981-03-04"),"Rome","Italy", "a"));
        add(new User(9, "user9","Angelina","Goulton", parseDate("1990-09-29"),"Vienna","Austria", "a"));
        add(new User(10,"user10","Stephan","Took", parseDate("1989-01-01"),"Oslo","Norway", "a"));
    }};
    public static List<User> listOfUsers()
    {
        return userList;
    }

    public static void addToList(final User user)
    {
        userList.add(user);
    }

    public static User load(final long id)
    {
        return userList.stream().filter(user -> user.getId() == id).findFirst().get();
    }

    public static void update(final User user)
    {
        User userToEdit = load(user.getId());
        userToEdit.setFirstName(user.getFirstName());
        userToEdit.setLastName(user.getLastName());
        userToEdit.setBirthDate(user.getBirthDate());
        userToEdit.setBirthCity(user.getBirthCity());
        userToEdit.setBirthCountry(user.getBirthCity());
    }

    public static Date parseDate(String date) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(date);
        } catch (ParseException e) {
            return null;
        }
    }
}