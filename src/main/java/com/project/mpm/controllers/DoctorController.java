package com.project.mpm.controllers;

import java.util.List;

import jakarta.annotation.security.RolesAllowed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.mpm.dto.DoctorDataBackinBean;
import com.project.mpm.dto.DoctorVisitsDataBackinBean;
import com.project.mpm.dto.Response;
import com.project.mpm.dto.PatientDataBacking;
import com.project.mpm.services.DoctorServices;
import com.project.mpm.services.DoctorVisitsServices;
import com.project.mpm.services.PatientServices;
@CrossOrigin("*")
@RestController @RequestMapping("/api/doctor")
public class DoctorController  {
    @Autowired
    DoctorServices  doctorServices;
    @Autowired
    DoctorVisitsServices visitService;
    @Autowired
    PatientServices patientService;


    @GetMapping("/getAllDoctors")@RolesAllowed({"ROLE_DOCTOR","ROLE_RECEPTION"})
    public ResponseEntity<?> getAllPatients(){
        List<DoctorDataBackinBean> doctors=doctorServices.getAllDoctors();

        return Response.success(doctors);
    }
    @RolesAllowed({"ROLE_DOCTOR"})
    @PostMapping("/addPrescription")
    public void updatePatient(@RequestBody PatientDataBacking patientData) {

        doctorServices.updatePatientDetails(patientData);
    }
    @RolesAllowed({"ROLE_DOCTOR"})
    @PostMapping("/increaseVisitCount")
    public ResponseEntity<?> increaseVisitCount(@RequestBody DoctorVisitsDataBackinBean visitData) {
        visitService.increaseVisitCount(visitData);
        return Response.success("increamented");
    }
    @RolesAllowed({"ROLE_DOCTOR"})
    @GetMapping("/getPatients/{id}")
    public ResponseEntity<?> getPatientsOfDoctor(@PathVariable int id){
        List<PatientDataBacking> patientList=patientService.getPatientsOfDocter(id);
        return Response.success(patientList);
    }



}
