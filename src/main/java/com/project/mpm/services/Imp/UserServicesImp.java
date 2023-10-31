package com.project.mpm.services.Imp;

import com.project.mpm.dto.EmployeeUserDataBacking;
import com.project.mpm.entities.UserEntity;
import com.project.mpm.repositories.EmployeeRepository;
import com.project.mpm.repositories.UserRepository;
import com.project.mpm.services.UserServices;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import java.util.List;

import static com.project.mpm.dto.EmployeeUserDataBacking.createUser;

@Service
@AllArgsConstructor
public class UserServicesImp implements UserServices {
    @Autowired
    UserRepository userRepository;
    @Autowired
    EmployeeRepository employeeRepository;

    public List<UserEntity> findAllUsers() {
        return userRepository.findAll();
    }

    public UserEntity findUserById(int userId) {
        return userRepository.getReferenceById(userId);
    }

    public UserEntity findUserByEmail(String email) {
        return userRepository.findByEmail(email);

    }
    //****************get user by email and password*********************
    public EmployeeUserDataBacking getUserByEmailAndPassword(EmployeeUserDataBacking userData) {
        EmployeeUserDataBacking userCreated=new EmployeeUserDataBacking();
        UserEntity validUser=userRepository.findByEmail(userData.getEmail());
        if(validUser!=null)
            return userCreated=createUser(validUser);
        return null;


    }

    //*********************check if email is unique************
    public Boolean checkIfEmailExists(EmployeeUserDataBacking userData) {
        return userRepository.existsByEmail(userData.getEmail());
    }

}