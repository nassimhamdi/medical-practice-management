package com.project.mpm.services;

import com.project.mpm.dto.EmployeeUserDataBacking;

import java.util.List;

public interface EmployeeServices {
    int addEmployee(EmployeeUserDataBacking userData);
    List<EmployeeUserDataBacking> getAllEmployees();
    void updateEmployee(EmployeeUserDataBacking userData) ;
    int deleteUserByCellNoAndUserId(EmployeeUserDataBacking userData);

}
