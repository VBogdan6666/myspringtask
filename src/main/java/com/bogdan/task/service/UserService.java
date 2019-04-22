package com.bogdan.task.service;

import com.bogdan.task.entity.User;
import com.bogdan.task.exception.MyException;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    void addUser(User user) throws MyException;
}
