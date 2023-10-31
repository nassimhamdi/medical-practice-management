package com.project.mpm.controllers;

import com.project.mpm.dto.EmployeeUserDataBacking;
import com.project.mpm.entities.UserEntity;
import com.project.mpm.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/user")
public class UserController {
    UserServices userServices;

    @Autowired
    public UserController(UserServices userServices) {
        this.userServices = userServices;
    }


    @GetMapping("/list")
    public List<UserEntity> listCategory() {
        return userServices.findAllUsers();
    }

    @PostMapping("/add")
    public String createUser(@RequestBody EmployeeUserDataBacking userData) {
        userServices.getUserByEmailAndPassword(userData);
        return "success";
    }

}
