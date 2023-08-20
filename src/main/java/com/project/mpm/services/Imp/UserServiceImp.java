package com.project.mpm.services.Imp;

import com.project.mpm.entities.User;
import com.project.mpm.repositories.UserRepos;
import com.project.mpm.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImp implements UserService {
    @Autowired
    UserRepos userRepos;


    @Override
    public List<User> listUsers() {
        return userRepos.findAll();
    }
}