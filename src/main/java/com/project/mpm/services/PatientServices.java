package com.project.mpm.services;

import com.project.mpm.dto.ChargesCalculationBeanPatient;
import com.project.mpm.dto.MedicineAssignedDataBackinBean;
import com.project.mpm.dto.PatientDataBacking;

import java.util.List;

public interface PatientServices {
    int addPatient(PatientDataBacking patientDetails);
    List<PatientDataBacking> getAllPatients();
    PatientDataBacking getPatientById(int id);
    List<MedicineAssignedDataBackinBean> getMedicineByPatId(int id);
    void updatePatientDetails(PatientDataBacking patientDetails);
    int removePatientById(int id);
    ChargesCalculationBeanPatient calculateChargesByPatId(int patId);
    void updatePaymentStatusByPatId(PatientDataBacking patientData);
    Boolean checkIfBedAvailable(PatientDataBacking bedData);
    List<PatientDataBacking> getPatientsOfDocter(int id);
}
