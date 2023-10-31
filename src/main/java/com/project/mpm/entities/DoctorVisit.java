package com.project.mpm.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import lombok.Data;
import lombok.ToString.Exclude;

@Entity@Table(name = "doctor_visits")@Data
public class DoctorVisit {

    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Exclude
    @ManyToOne(cascade = CascadeType.PERSIST)@JoinColumn(name = "pat_id")
    private Patient patient;

    @Exclude
    @ManyToOne(cascade = CascadeType.PERSIST)@JoinColumn(name = "doctor_id")
    private Doctor doctor;
    private int visits;
}
