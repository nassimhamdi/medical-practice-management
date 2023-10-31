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
@RestController
@RolesAllowed("ROLE_DOCTOR")
@RequestMapping("/api/doctorVisit")
public class DoctorVisitsController {
    @Autowired
    DoctorVisitsServices visitServices;



    @PostMapping("/addVisit")
    public ResponseEntity<?> addWard(@RequestBody DoctorVisitsDataBackinBean visitData) {
        int updateCount = visitServices.addVisit(visitData);
        if (updateCount == 1)
            return Response.success("VISIT_ADDED");
        return Response.success("FAILED");
    }



}
