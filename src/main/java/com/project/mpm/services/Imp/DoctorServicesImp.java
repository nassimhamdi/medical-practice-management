package com.project.mpm.services.Imp;

import com.project.mpm.dto.DoctorDataBackinBean;
import com.project.mpm.dto.PatientDataBacking;
import com.project.mpm.entities.Doctor;
import com.project.mpm.exceptions.NoSuchPatientFoundException;
import com.project.mpm.repositories.DoctorRepository;
import com.project.mpm.repositories.EmployeeRepository;
import com.project.mpm.repositories.PatientRepository;
import com.project.mpm.repositories.UserRepository;
import com.project.mpm.services.DoctorServices;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import static com.project.mpm.dto.DoctorDataBackinBean.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class DoctorServicesImp implements DoctorServices {
    @Autowired
    UserRepository userRepository;
        @Autowired
        EmployeeRepository employeeRepository;
        @Autowired
        DoctorRepository doctorRepository;
        @Autowired
        PatientRepository patientRepository;

        public List<DoctorDataBackinBean> getAllDoctors() {
            List<Doctor> doctors=doctorRepository.findAll();
            List<DoctorDataBackinBean> doctorDetail=createDoctorsList(doctors);

            return doctorDetail;

        }

        public void updatePatientDetails(PatientDataBacking patientData) throws NoSuchPatientFoundException {
            int updateCount;
            if(patientRepository.existsById(patientData.getPatId()))
                updateCount=patientRepository.updatePatientPrescription(patientData.getPrescription(),patientData.getPatId());
            else
                throw new NoSuchPatientFoundException("patient  with id "+patientData.getPatId()+" does not exists");
        }



}

