package com.project.mpm.entities;


import jakarta.persistence.*;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import lombok.ToString.Exclude;


@Entity
@Table(name = "user")
@Data
@NoArgsConstructor
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String password;
    private String role;
    private String cellNo;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private List<Role> roles = new ArrayList<>();

    @Exclude
    @OneToOne(mappedBy = "user",cascade = CascadeType.ALL)
    private Employee employee;

    @Exclude
    @OneToOne(mappedBy = "user",cascade = CascadeType.ALL)
    private Patient patient ;


    //***************connection to employee
    public void addEmployee(Employee e) {
        this.employee=e;
        this.employee.setUser(this);

    }
    //***************connection to patient
    public void addPatient(Patient p) {
        this.patient=p;
        this.patient.setUser(this);

    }

    public UserEntity(String firstName, String lastName, String username,String email, String password, String role, String cellNo) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
        this.cellNo = cellNo;

    }
}

