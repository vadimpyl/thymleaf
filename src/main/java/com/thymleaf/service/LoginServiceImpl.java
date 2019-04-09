package com.thymleaf.service;

import com.thymleaf.helper.UserHelper;

public class LoginServiceImpl implements LoginService
{
    @Override
    public boolean login(final String username, final String password)
    {

        return UserHelper.listOfUsers().stream().
                anyMatch(user -> username.equals(user.getLogin()) && password.equals(user.getPassword()));
    }
}
