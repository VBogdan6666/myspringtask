package com.bogdan.myspringtask.service;

import com.bogdan.myspringtask.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    boolean addUser(User user);
}
