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

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity@Table(name = "medicines_assigned")@Getter@Setter@NoArgsConstructor@AllArgsConstructor
public class MedicineAssigned {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    //	pat_id, medicine_id, prescription, medicine_qty
    //one patient may have more than one medicine
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "pat_id")
    private Patient patient ;


    //foreign key from medicines table
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "medicine_id")
    private Medicine medicine ;

    private String prescription;

    private int medicineQty;

    //constructor without medicine
    public MedicineAssigned( String prescription, int medicineQty) {
        super();

        this.prescription = prescription;
        this.medicineQty = medicineQty;
    }



}
