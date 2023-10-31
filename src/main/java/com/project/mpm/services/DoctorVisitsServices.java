package com.project.mpm.services;

import com.project.mpm.dto.DoctorVisitsDataBackinBean;

public interface DoctorVisitsServices {
    int addVisit(DoctorVisitsDataBackinBean visitData);
    void increaseVisitCount(DoctorVisitsDataBackinBean visitData);
}
