package com.project.mpm.services.Imp;

import java.util.List;

import com.project.mpm.repositories.*;
import com.project.mpm.services.MedicineAssignedServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.mpm.exceptions.NoSuchMedicineExistsException;
import com.project.mpm.repositories.MedicineRepository;
import com.project.mpm.dto.DoctorDataBackinBean;
import com.project.mpm.dto.MedicineAssignedDataBackinBean;
import com.project.mpm.entities.UserEntity;

@Service @Transactional
public class MedicineAssignedServicesImp implements MedicineAssignedServices {
    @Autowired
    UserRepository userDao;
    @Autowired
    EmployeeRepository employeeDao;
    @Autowired
    MedicineAssignedRepository medicineAssingedDao;
    @Autowired
    MedicineRepository medicineDao;

    public void addMedicineToPatient(MedicineAssignedDataBackinBean medicineData) throws NoSuchMedicineExistsException {

        medicineAssingedDao.addIntoMedicineAssigned(medicineData.getPatId(), medicineData.getMedicineId(), medicineData.getMedicinePrescription(), medicineData.getMedicineQty());


    }

    public void removeMedicineOfPatient(int medicineAssignId) {
        medicineAssingedDao.deleteById(medicineAssignId);
    }
}