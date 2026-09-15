package com.promisesimulator.appointment.controller;

import com.promisesimulator.appointment.dto.AppointmentRequest;
import com.promisesimulator.appointment.dto.AppointmentResponse;
import com.promisesimulator.appointment.service.AppointmentService;
import java.util.UUID;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** 약속 API의 HTTP 요청을 처리한다. */
@RestController
@RequestMapping("/api/v1/appointments")
public class AppointmentController {

    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @PostMapping
    public ResponseEntity<AppointmentResponse> createAppointment(
            @RequestHeader("X-Member-Profile-Id") UUID memberProfileId,
            @RequestBody AppointmentRequest request) {
        return ResponseEntity.status(201).body(appointmentService.createAppointment(memberProfileId, request));
    }

    @GetMapping("/{appointmentId}")
    public ResponseEntity<AppointmentResponse> getAppointment(
            @RequestHeader("X-Member-Profile-Id") UUID memberProfileId,
            @PathVariable UUID appointmentId) {
        return ResponseEntity.ok(appointmentService.getAppointment(memberProfileId, appointmentId));
    }

    @PutMapping("/{appointmentId}")
    public ResponseEntity<AppointmentResponse> updateAppointment(
            @RequestHeader("X-Member-Profile-Id") UUID memberProfileId,
            @PathVariable UUID appointmentId,
            @RequestBody AppointmentRequest request) {
        return ResponseEntity.ok(appointmentService.updateAppointment(memberProfileId, appointmentId, request));
    }
}
