package com.project.mpm.services.Imp;

import java.util.List;
import static com.project.mpm.dto.MedicineAssignedDataBackinBean.*;

import com.project.mpm.services.MedicineServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.mpm.repositories.*;

import com.project.mpm.dto.DoctorDataBackinBean;
import com.project.mpm.dto.MedicineAssignedDataBackinBean;
import com.project.mpm.entities.Medicine;
import com.project.mpm.entities.UserEntity;

@Service @Transactional
public class MedicineServicesImp implements MedicineServices {
    @Autowired
    UserRepository userDao;
    @Autowired
    EmployeeRepository employeeDao;
    @Autowired
    MedicineRepository medicineDao;
    @Autowired
    MedicineAssignedRepository medicineAssingedDao;

    public List<MedicineAssignedDataBackinBean> getAllMedicines(){
        List<Medicine> medicine=medicineDao.findAll();
        List<MedicineAssignedDataBackinBean> medicinesTosend=createAllMedicineList(medicine);
        return medicinesTosend;

    }

    public int addMedicine(MedicineAssignedDataBackinBean medicineData) {
        return  medicineDao.insertIntoMedicineTable(0, medicineData.getMedicineName(), medicineData.getMedicinePrice());

    }

    public void removeMedicine(int medicineId) {
        medicineDao.deleteById(medicineId);

    }






}
