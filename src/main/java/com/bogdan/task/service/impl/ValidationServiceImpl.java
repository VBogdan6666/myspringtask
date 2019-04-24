package com.bogdan.task.service.impl;

import com.bogdan.task.service.ValidationService;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
public class ValidationServiceImpl implements ValidationService {

    @Override
    public boolean checkUsername(String username) {
        boolean check;
        if (username.length()>2 && username.length()<=50) {
            check=Pattern.matches("^[A-Za-z\\d]+(_?[A-Za-z\\d]+)?(-?[A-Za-z\\d]+)?$", username);
        } else {
            check=false;
        }
        return check;
    }

    @Override
    public boolean checkPassword(String password){
        boolean check;
        if (password.length()>2 && password.length()<=254) {
            check=Pattern.matches("^\\w+$", password);
        } else {
            check=false;
        }
        return check;
    }


}
