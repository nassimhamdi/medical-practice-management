package com.project.mpm.services;

import com.project.mpm.dto.DoctorDataBackinBean;
import com.project.mpm.dto.PatientDataBacking;
import com.project.mpm.exceptions.NoSuchPatientFoundException;

import java.util.List;

public interface DoctorServices {

    List<DoctorDataBackinBean> getAllDoctors();
    void updatePatientDetails(PatientDataBacking patientData);
}
