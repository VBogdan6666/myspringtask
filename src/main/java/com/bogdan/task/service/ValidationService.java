package com.bogdan.task.service;

public interface ValidationService {
    boolean checkUsername(String username);

    boolean checkPassword(String password);
}
