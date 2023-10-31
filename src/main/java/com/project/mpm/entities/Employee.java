package com.project.mpm.entities;

import java.util.Date;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.ToString.Exclude;

@Entity
@Table(name = "employees")
@Getter
@Setter
@ToString
@JsonInclude(value = Include.NON_NULL)
public class Employee {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date dob;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date hireDate;

    @Exclude
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name ="user_id" )
    private UserEntity user;

    private double salary;

    @Exclude
    @OneToOne(mappedBy = "employee",cascade = CascadeType.ALL)
    private Doctor doctor;


    public void addDoctor(Doctor d) {
        doctor=d;
        doctor.setEmployee(this);
    }

    public Employee() {
        super();
        // TODO Auto-generated constructor stub
    }

    public Employee(Date dob, Date hireDate, UserEntity user, double salary) {
        super();
        this.dob = dob;
        this.hireDate = hireDate;
        this.user = user;
        this.user.setEmployee(this);
        this.salary = salary;
    }


    public Employee(Date dob, Date hireDate, double salary) {
        super();
        this.dob = dob;
        this.hireDate = hireDate;
        this.salary = salary;
    }






}
