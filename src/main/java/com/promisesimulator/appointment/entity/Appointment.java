package com.promisesimulator.appointment.entity;

import com.promisesimulator.global.entity.BaseTimeEntity;
import com.promisesimulator.friend.entity.FriendProfile;
import com.promisesimulator.member.entity.MemberProfile;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

/** 사용자가 시뮬레이션할 약속의 시간·장소·계획을 보관한다. */
@Entity
@Table(name = "appointments")
public class Appointment extends BaseTimeEntity {

    // 약속의 내부 식별자다.
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    // 약속을 생성한 회원이다.
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "member_profile_id", nullable = false)
    private MemberProfile memberProfile;

    // 약속을 함께할 친구 프로필이다.
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "friend_profile_id", nullable = false)
    private FriendProfile friendProfile;

    // 약속이 열리는 날짜다.
    @Column(nullable = false)
    private LocalDate appointmentDate;

    // 약속 시작 시각이다.
    @Column(nullable = false)
    private LocalTime startTime;

    // 헤어지는 시각은 문서상 미정일 수 있으므로 null을 허용한다.
    private LocalTime endTime;

    // 만날 예정인 지역 또는 장소 설명이다.
    @Column(length = 200)
    private String location;

    // 사용자가 하고 싶은 활동을 자유 텍스트로 기록한다.
    @Column(length = 500)
    private String desiredActivities;

    // 이미 확정되어 변경하기 어려운 일정을 기록한다.
    @Column(length = 500)
    private String fixedSchedule;

    // 시뮬레이터에 제공하는 추가 조건 또는 요청이다.
    @Column(length = 1000)
    private String additionalRequest;

    public Appointment() {
        // JPA 기본 생성자
    }

    public UUID getId() { return id; }
    public MemberProfile getMemberProfile() { return memberProfile; }
    public FriendProfile getFriendProfile() { return friendProfile; }
    public LocalDate getAppointmentDate() { return appointmentDate; }
    public LocalTime getStartTime() { return startTime; }
    public LocalTime getEndTime() { return endTime; }
    public String getLocation() { return location; }
    public String getDesiredActivities() { return desiredActivities; }
    public String getFixedSchedule() { return fixedSchedule; }
    public String getAdditionalRequest() { return additionalRequest; }
    public void setMemberProfile(MemberProfile memberProfile) { this.memberProfile = memberProfile; }
    public void setFriendProfile(FriendProfile friendProfile) { this.friendProfile = friendProfile; }
    public void setAppointmentDate(LocalDate appointmentDate) { this.appointmentDate = appointmentDate; }
    public void setStartTime(LocalTime startTime) { this.startTime = startTime; }
    public void setEndTime(LocalTime endTime) { this.endTime = endTime; }
    public void setLocation(String location) { this.location = location; }
    public void setDesiredActivities(String desiredActivities) { this.desiredActivities = desiredActivities; }
    public void setFixedSchedule(String fixedSchedule) { this.fixedSchedule = fixedSchedule; }
    public void setAdditionalRequest(String additionalRequest) { this.additionalRequest = additionalRequest; }
}
