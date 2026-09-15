package com.promisesimulator.appointment.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

/** 약속 생성·수정 요청 값이다. */
public class AppointmentRequest {

    private UUID friendProfileId;
    private LocalDate appointmentDate;
    private LocalTime startTime;
    private LocalTime endTime;
    private String location;
    private String desiredActivities;
    private String fixedSchedule;
    private String additionalRequest;

    public AppointmentRequest() {
    }

    public UUID getFriendProfileId() { return friendProfileId; }
    public void setFriendProfileId(UUID friendProfileId) { this.friendProfileId = friendProfileId; }
    public LocalDate getAppointmentDate() { return appointmentDate; }
    public void setAppointmentDate(LocalDate appointmentDate) { this.appointmentDate = appointmentDate; }
    public LocalTime getStartTime() { return startTime; }
    public void setStartTime(LocalTime startTime) { this.startTime = startTime; }
    public LocalTime getEndTime() { return endTime; }
    public void setEndTime(LocalTime endTime) { this.endTime = endTime; }
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    public String getDesiredActivities() { return desiredActivities; }
    public void setDesiredActivities(String desiredActivities) { this.desiredActivities = desiredActivities; }
    public String getFixedSchedule() { return fixedSchedule; }
    public void setFixedSchedule(String fixedSchedule) { this.fixedSchedule = fixedSchedule; }
    public String getAdditionalRequest() { return additionalRequest; }
    public void setAdditionalRequest(String additionalRequest) { this.additionalRequest = additionalRequest; }
}
