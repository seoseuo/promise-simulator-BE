package com.promisesimulator.appointment.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

/** Service와 API에서 반환하는 읽기 전용 약속 값이다. */
public record AppointmentResponse(
        UUID id,
        UUID friendProfileId,
        LocalDate appointmentDate,
        LocalTime startTime,
        LocalTime endTime,
        String location,
        String desiredActivities,
        String fixedSchedule,
        String additionalRequest) {
}
