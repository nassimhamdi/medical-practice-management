package com.project.mpm.repositories;

import java.util.Date;

import com.project.mpm.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository <Employee,Integer> {

    @Modifying
    @Query(value="insert into employees values(:id,:userId,:dob,:hireDate,:salary)",nativeQuery = true)
    int insertIntoEmployeesTable(@Param("id") int id,@Param("userId") int userId,@Param("dob") Date dob,@Param("hireDate")Date hireDate,@Param("salary") double salary);

    @Query(value="select id from employees where user_id=(select id from users where email= :email)",nativeQuery = true)
    int getEmpIdByEmail(@Param("email")String email);

}
