package com.promisesimulator.appointment.service;

import com.promisesimulator.appointment.dto.AppointmentRequest;
import com.promisesimulator.appointment.dto.AppointmentResponse;
import java.util.UUID;
import org.springframework.stereotype.Service;

/** 약속의 생성·조회·수정을 담당한다. */
@Service
public class AppointmentService {

    public AppointmentResponse createAppointment(UUID memberProfileId, AppointmentRequest request) {
        throw new UnsupportedOperationException("TODO: 약속 생성 구현");
    }

    public AppointmentResponse getAppointment(UUID memberProfileId, UUID appointmentId) {
        throw new UnsupportedOperationException("TODO: 약속 조회 구현");
    }

    public AppointmentResponse updateAppointment(
            UUID memberProfileId, UUID appointmentId, AppointmentRequest request) {
        throw new UnsupportedOperationException("TODO: 약속 수정 구현");
    }
}
