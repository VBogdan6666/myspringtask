package com.bogdan.task.controller;


import com.bogdan.task.entity.User;
import com.bogdan.task.exception.MyException;
import com.bogdan.task.service.RoleService;
import com.bogdan.task.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AdminController {

    private static final Logger logger = LogManager.getLogger(AdminController.class);

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserService userService;

    @GetMapping("/admin")
    public String showAdminPage(Model model){
        model.addAttribute("user", new User());
        model.addAttribute("roles",roleService.findAllRoles());
        return "admin";
    }

    @PostMapping("/admin")
    public String addUser(Model model,
                          User user){
        try {
            userService.addUser(user);
            model.addAttribute("statusMessage","user \""+user.getName()+"\" successfully added");
        } catch (MyException e) {
           logger.error(e.getMessage());
           model.addAttribute("statusMessage",e.getMessage());

        }
        return "user-status-form";
    }



}
