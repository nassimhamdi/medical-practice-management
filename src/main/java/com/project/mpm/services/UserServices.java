package com.project.mpm.services;

import com.project.mpm.dto.EmployeeUserDataBacking;
import com.project.mpm.entities.UserEntity;

import java.util.List;

public interface UserServices {
    List<UserEntity> findAllUsers();
    public UserEntity findUserById(int userId);
    public UserEntity findUserByEmail(String email);
    EmployeeUserDataBacking getUserByEmailAndPassword(EmployeeUserDataBacking userData);
    Boolean checkIfEmailExists(EmployeeUserDataBacking userData);

}
