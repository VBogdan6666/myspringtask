package com.bogdan.task.service.impl;

import com.bogdan.task.entity.Role;
import com.bogdan.task.entity.User;
import com.bogdan.task.exception.MyException;
import com.bogdan.task.repository.UserRepository;
import com.bogdan.task.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class UserServiceImpl implements UserService {

    private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username){
        User user = userRepository.findByName(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        String[] roles = user.getRoles().stream().map(Role::getName).toArray(String[]::new);
        org.springframework.security.core.userdetails.User.UserBuilder builder = null;
        builder = org.springframework.security.core.userdetails.User.withUsername(username);
        builder.password(user.getPassword());
        builder.roles(roles);

        return builder.build();
    }

    @Override
    public void addUser(User user) throws MyException {
        if (user.getName() != null && user.getPassword() != null && !user.getRoles().isEmpty()) {
            if (userRepository.findByName(user.getName()) == null) {
                user.setPassword(passwordEncoder.encode(user.getPassword()));
                userRepository.save(user);
            } else {
                logger.error("name: \"" + user.getName() + "\" is already taken.");
                throw new MyException("name: \"" + user.getName() + "\" is already taken.");
            }
        } else {
            logger.warn("not all fields are filled");
            throw new MyException("not all fields are filled");
        }
    }


}
