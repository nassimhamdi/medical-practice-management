package com.project.mpm.services.Imp;
import com.project.mpm.repositories.DoctorVisitRepository;
import com.project.mpm.repositories.EmployeeRepository;
import com.project.mpm.services.DoctorVisitsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.mpm.controllers.DoctorVisitsController;
import com.project.mpm.repositories.DoctorVisitRepository;
import com.project.mpm.repositories.EmployeeRepository;
import com.project.mpm.repositories.UserRepository;

import com.project.mpm.dto.DoctorVisitsDataBackinBean;
import com.project.mpm.entities.DoctorVisit;

@Service
@Transactional
public class DoctorVisitsServicesImp implements DoctorVisitsServices {
    @Autowired
    UserRepository userRepository;
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    DoctorVisitRepository doctorVisitRepository;

    public int addVisit(DoctorVisitsDataBackinBean visitData)  {
        return doctorVisitRepository.insertIntoDoctorVisitsTable(0, visitData.getPatientId(), visitData.getDoctorId(), 0);
    }
    public void increaseVisitCount(DoctorVisitsDataBackinBean visitData) {

        DoctorVisit visit=doctorVisitRepository.getVisitsByPatIdAndDoctorId(visitData.getPatientId(),visitData.getDoctorId());
        visit.setVisits(visit.getVisits()+1);
        doctorVisitRepository.save(visit);
    }
}
