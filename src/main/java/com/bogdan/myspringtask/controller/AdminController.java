package com.bogdan.myspringtask.controller;


import com.bogdan.myspringtask.entity.User;
import com.bogdan.myspringtask.service.RoleService;
import com.bogdan.myspringtask.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AdminController {

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
    public String addUser(User user){
        boolean add = userService.addUser(user);
        return "redirect:/admin";
    }

}
