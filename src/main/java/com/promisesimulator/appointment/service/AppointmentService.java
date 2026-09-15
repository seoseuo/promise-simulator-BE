package com.promisesimulator.appointment.service;

import com.promisesimulator.appointment.dto.AppointmentRequest;
import com.promisesimulator.appointment.dto.AppointmentResponse;
import com.promisesimulator.appointment.entity.Appointment;
import com.promisesimulator.appointment.repository.AppointmentRepository;
import com.promisesimulator.friend.entity.FriendProfile;
import com.promisesimulator.friend.repository.FriendProfileRepository;
import com.promisesimulator.member.entity.MemberProfile;
import com.promisesimulator.member.repository.MemberProfileRepository;
import java.util.UUID;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/** 약속의 생성·조회·수정을 담당한다. */
@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final MemberProfileRepository memberProfileRepository;
    private final FriendProfileRepository friendProfileRepository;

    public AppointmentService(
            AppointmentRepository appointmentRepository,
            MemberProfileRepository memberProfileRepository,
            FriendProfileRepository friendProfileRepository) {
        this.appointmentRepository = appointmentRepository;
        this.memberProfileRepository = memberProfileRepository;
        this.friendProfileRepository = friendProfileRepository;
    }

    @Transactional
    public AppointmentResponse createAppointment(UUID memberProfileId, AppointmentRequest request) {
        MemberProfile memberProfile = memberProfileRepository.findById(memberProfileId)
                .orElseThrow(() -> new IllegalArgumentException("회원 프로필을 찾을 수 없습니다."));
        FriendProfile friendProfile = findOwnedFriend(memberProfileId, request.getFriendProfileId());

        Appointment appointment = new Appointment();
        appointment.setMemberProfile(memberProfile);
        appointment.setFriendProfile(friendProfile);
        applyRequest(appointment, request);
        return toResponse(appointmentRepository.save(appointment));
    }

    public AppointmentResponse getAppointment(UUID memberProfileId, UUID appointmentId) {
        return toResponse(findOwnedAppointment(memberProfileId, appointmentId));
    }

    @Transactional
    public AppointmentResponse updateAppointment(
            UUID memberProfileId, UUID appointmentId, AppointmentRequest request) {
        Appointment appointment = findOwnedAppointment(memberProfileId, appointmentId);
        FriendProfile friendProfile = findOwnedFriend(memberProfileId, request.getFriendProfileId());
        appointment.setFriendProfile(friendProfile);
        applyRequest(appointment, request);
        return toResponse(appointment);
    }

    private FriendProfile findOwnedFriend(UUID memberProfileId, UUID friendProfileId) {
        return friendProfileRepository.findByIdAndMemberProfile_Id(friendProfileId, memberProfileId)
                .orElseThrow(() -> new IllegalArgumentException("친구 프로필을 찾을 수 없습니다."));
    }

    private Appointment findOwnedAppointment(UUID memberProfileId, UUID appointmentId) {
        return appointmentRepository.findByIdAndMemberProfile_Id(appointmentId, memberProfileId)
                .orElseThrow(() -> new IllegalArgumentException("약속을 찾을 수 없습니다."));
    }

    private void applyRequest(Appointment appointment, AppointmentRequest request) {
        appointment.setAppointmentDate(request.getAppointmentDate());
        appointment.setStartTime(request.getStartTime());
        appointment.setEndTime(request.getEndTime());
        appointment.setLocation(request.getLocation());
        appointment.setDesiredActivities(request.getDesiredActivities());
        appointment.setFixedSchedule(request.getFixedSchedule());
        appointment.setAdditionalRequest(request.getAdditionalRequest());
    }

    private AppointmentResponse toResponse(Appointment appointment) {
        return new AppointmentResponse(
                appointment.getId(), appointment.getFriendProfile().getId(), appointment.getAppointmentDate(),
                appointment.getStartTime(), appointment.getEndTime(), appointment.getLocation(),
                appointment.getDesiredActivities(), appointment.getFixedSchedule(), appointment.getAdditionalRequest());
    }
}
