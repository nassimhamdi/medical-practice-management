package com.project.mpm.services;

import com.project.mpm.dto.MedicineAssignedDataBackinBean;

import java.util.List;

public interface MedicineServices {
    List<MedicineAssignedDataBackinBean> getAllMedicines() ;
    int addMedicine(MedicineAssignedDataBackinBean medicineData) ;

    void removeMedicine(int medicineId) ;
}
