package com.project.mpm.services.Imp;

import java.util.List;


import com.project.mpm.repositories.*;
import com.project.mpm.services.PatientServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.mpm.exceptions.NoSuchPatientFoundException;
import com.project.mpm.exceptions.PatientAlreadyExistsException;


import com.project.mpm.dto.ChargesCalculationBeanPatient;
import com.project.mpm.dto.MedicineAssignedDataBackinBean;
import com.project.mpm.dto.PatientDataBacking;
import com.project.mpm.entities.Doctor;
import com.project.mpm.entities.DoctorVisit;
import com.project.mpm.entities.MedicineAssigned;
import com.project.mpm.entities.Patient;
import com.project.mpm.entities.UserEntity;


import static com.project.mpm.dto.PatientDataBacking.*;
import static com.project.mpm.dto.MedicineAssignedDataBackinBean.*;
import static com.project.mpm.dto.ChargesCalculationBeanPatient.*;

@Service
@Transactional
public class PatientServicesImp implements PatientServices {
    @Autowired
    UserRepository userRepository;
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    PatientRepository patientRepository;
    @Autowired
    DoctorRepository doctorRepository;
    @Autowired
    DoctorVisitRepository visitsRepository;


    public int addPatient(PatientDataBacking patientDetails) throws PatientAlreadyExistsException {
        if(!userRepository.existsByEmail(patientDetails.getEmail())) {
            userRepository.insertIntoUsers(0, patientDetails.getFirstName(), patientDetails.getLastName(), patientDetails.getEmail(), patientDetails.getPassword(), patientDetails.getCellNo(), patientDetails.getRole());
            UserEntity user=userRepository.findByEmail(patientDetails.getEmail());
            int updateCount=patientRepository.insertIntoPatients(0, user.getId(),patientDetails.getDoctorId() , patientDetails.getDateOfAdmission(), patientDetails.getBloodGroup(), patientDetails.getDob(), patientDetails.getPrescription(), patientDetails.getBedAlloted(), patientDetails.getPaymentStatus(), patientDetails.getPatientProblem());
            Patient patient=patientRepository.findByUserId(user.getId());
            visitsRepository.insertIntoDoctorVisitsTable(0, patient.getId(), patientDetails.getDoctorId(),0);
            return updateCount;
        }else {
            throw new PatientAlreadyExistsException("patient with email = "+patientDetails.getEmail()+" exists!!!");

        }


    }

    public List<PatientDataBacking> getAllPatients(){
        List<Patient> patients=patientRepository.findAll();
        List<PatientDataBacking> patientList=createPatient(patients);
        return patientList;

    }

    public PatientDataBacking getPatientById(int id) throws NoSuchPatientFoundException {
        if(patientRepository.existsById(id)) {
            Patient patient=patientRepository.getById(id);
            PatientDataBacking patientDetailsToSend=getByIdPatient(patient);
            return patientDetailsToSend;
        }else {
            throw new NoSuchPatientFoundException("patient with id = "+id+" does not exists!!!");
        }



    }
    //get patients medicines by patient id
    public List<MedicineAssignedDataBackinBean> getMedicineByPatId(int id) {
        Patient patient=patientRepository.getById(id);
        List<MedicineAssigned> medicines=patient.getMedicines();
        List<MedicineAssignedDataBackinBean> medicineToSend=createMedicineListForPatient(medicines);
        return medicineToSend;


    }

    //update patient details
    public void updatePatientDetails(PatientDataBacking patientDetails) throws NoSuchPatientFoundException {

        if(patientRepository.existsById(patientDetails.getPatId())) {
            Patient updatePatient = patientRepository.getById(patientDetails.getPatId());
            //to add to visit table

            DoctorVisit initVisit=visitsRepository.getVisitsByPatIdAndDoctorId(patientDetails.getPatId(), patientDetails.getDoctorId());
            System.out.println("------------------>initvisit"+initVisit);
            if(initVisit==null) {
                visitsRepository.insertIntoDoctorVisitsTable(0, patientDetails.getPatId(), patientDetails.getDoctorId(), 0);
            }

            //======================================================================

            Doctor updatedDoctor=doctorRepository.getById(patientDetails.getDoctorId());//got new doctor by id



            updatedDoctor.addPatient(updatePatient);//patient added in doctor list
            updatePatient.setDoctor(updatedDoctor);//doctor added to patient list

            updatePatient.getUser().setFirstName(patientDetails.getFirstName());
            updatePatient.getUser().setLastName(patientDetails.getLastName());
            updatePatient.getUser().setCellNo(patientDetails.getCellNo());

            updatePatient.setDob(patientDetails.getDob());
            updatePatient.setBedAlloted(patientDetails.getBedAlloted());
            updatePatient.setBloodGroup(patientDetails.getBloodGroup());
            //=======================================
            //update visits
            Patient savedPatient = patientRepository.save(updatePatient);


        }else {
            throw new NoSuchPatientFoundException("patient with id = "+patientDetails.getPatId()+" does not exists!!!");
        }

    }

    public int removePatientById(int id) throws NoSuchPatientFoundException {
        if(patientRepository.existsById(id)) {
            patientRepository.deleteById(id);
            return 1;
        }else {
            throw new NoSuchPatientFoundException("patient with id = "+id+" does not exists!!!");
        }

    }

    public ChargesCalculationBeanPatient calculateChargesByPatId(int patId) {
        ChargesCalculationBeanPatient patientCharges=new ChargesCalculationBeanPatient();
        int daysStayed = patientRepository.calculateDaysOfStayOfPatient(patId);
        Patient patient=patientRepository.getById(patId);
        patientCharges=calculateCharges(patient, daysStayed);
        return patientCharges;
    }

    public void updatePaymentStatusByPatId(PatientDataBacking patientData) throws NoSuchPatientFoundException {
        if(patientRepository.existsById(patientData.getPatId())) {
            Patient patient=patientRepository.getById(patientData.getPatId());
            patient.setPaymentStatus(patientData.getPaymentStatus());
            patientRepository.save(patient);
        }else {
            throw new NoSuchPatientFoundException("patient with id = "+patientData.getPatId()+" does not exists!!!");
        }

    }
    //check if bedalloted exits
    public Boolean checkIfBedAvailable(PatientDataBacking bedData) {

        return patientRepository.existsByBedAlloted(bedData.getBedAlloted());
    }

    public List<PatientDataBacking> getPatientsOfDocter(int id) {
        List<Patient> patients=patientRepository.findAll();
        List<PatientDataBacking> patientList=createPatientsOfDoctor(patients,id);
        return patientList;

    }



}