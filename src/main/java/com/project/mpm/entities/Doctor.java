package com.project.mpm.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.ToString.Exclude;

@Entity
@Table(name = "doctors")
@Getter
@Setter
@AllArgsConstructor
@ToString
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    //*******************************connecting doctors empid
    @Exclude
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name ="emp_id" )
    private Employee employee;


    private double charges;


    @Exclude
    @OneToMany(mappedBy = "doctor",cascade = CascadeType.PERSIST)
    private List<Patient> patients;
    //doctor visits table link

    @Exclude
    @OneToMany(mappedBy = "doctor",cascade = CascadeType.PERSIST)
    private List<DoctorVisit> visits;



    public Doctor() {
        patients=new ArrayList<Patient>();
    }


    public void addPatient(Patient patient) {
        patient.setDoctor(this);
        this.patients.add(patient);
    }
    //adding visit to doctor visit
    public void addVisit(DoctorVisit visit) {
        visit.setDoctor(this);
        this.visits.add(visit);
    }
    /////***********************connecting foreign key
    public void addEmployee(Employee e) {
        this.employee=e;
        this.employee.setDoctor(this);

    }

    public Doctor(int id, double charges) {
        super();
        this.id = id;
        this.charges = charges;
    }




}
