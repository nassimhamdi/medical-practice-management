package com.project.mpm.entities;

import java.util.List;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity@Table(name = "medicines")@Getter@Setter@NoArgsConstructor@AllArgsConstructor
public class Medicine {
    //	id, name, price
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private Double price;

    @OneToMany(mappedBy = "medicine",cascade = CascadeType.PERSIST)
    private List<MedicineAssigned> mappedMedicines;

    //to set the medicine id in assigned medicine
    public void addAssignedMedicine(MedicineAssigned medicineAssigned) {
        medicineAssigned.setMedicine(this);
        mappedMedicines.add(medicineAssigned);

    }


}
