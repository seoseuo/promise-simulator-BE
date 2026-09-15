package com.promisesimulator.appointment.repository;

import com.promisesimulator.appointment.entity.Appointment;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment, UUID> {

    java.util.Optional<Appointment> findByIdAndMemberProfile_Id(UUID id, UUID memberProfileId);
}
