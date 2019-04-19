package com.bogdan.myspringtask.service;

import com.bogdan.myspringtask.entity.User;
import com.bogdan.myspringtask.exception.MyException;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    void addUser(User user) throws MyException;
}
