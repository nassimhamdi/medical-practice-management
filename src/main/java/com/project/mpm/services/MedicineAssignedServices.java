package com.project.mpm.services;

import com.project.mpm.dto.MedicineAssignedDataBackinBean;

public interface MedicineAssignedServices {
    void addMedicineToPatient(MedicineAssignedDataBackinBean medicineData) ;
    void removeMedicineOfPatient(int medicineAssignId) ;

}
