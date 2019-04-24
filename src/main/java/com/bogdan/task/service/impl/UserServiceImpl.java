package com.bogdan.task.service.impl;

import com.bogdan.task.entity.Role;
import com.bogdan.task.entity.User;
import com.bogdan.task.exception.MyException;
import com.bogdan.task.repository.UserRepository;
import com.bogdan.task.service.UserService;
import com.bogdan.task.service.ValidationService;
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

    private final ValidationService validationService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, ValidationService validationService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.validationService = validationService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
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
            String username = user.getName().trim();
            String password = user.getPassword().trim();
            if (validationService.checkUsername(username) && validationService.checkPassword(password)) {
                if (userRepository.findByName(username) == null) {
                    user.setPassword(passwordEncoder.encode(password));
                    userRepository.save(user);
                } else {
                    logger.error("name: \"" + username + "\" is already taken.");
                    throw new MyException("name: \"" + username + "\" is already taken.");
                }
            } else {
                logger.warn("incorrect username or password");
                throw new MyException("incorrect username or password");
            }
        } else {
            logger.warn("not all fields are filled");
            throw new MyException("not all fields are filled");
        }
    }


}
