package com.bogdan.myspringtask.controller;


import com.bogdan.myspringtask.entity.User;
import com.bogdan.myspringtask.exception.MyException;
import com.bogdan.myspringtask.service.RoleService;
import com.bogdan.myspringtask.service.UserService;
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
           e.printStackTrace();
           logger.error(e.getMessage());
           model.addAttribute("statusMessage",e.getMessage());

        }
        return "addUserStatusForm"  ;
    }



}
