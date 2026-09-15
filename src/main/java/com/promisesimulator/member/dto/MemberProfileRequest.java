package com.promisesimulator.member.dto;

import java.util.List;

/** 본인 프로필 생성·수정 요청 값이다. */
public class MemberProfileRequest {

    private String name;
    private String gender;
    private String ageRange;
    private String mbti;
    private List<String> preferredAppointmentTypes;
    private List<String> preferredTimeSlots;
    private String preferredDuration;
    private String preferredTravelTime;
    private String preferredSpending;
    private String alcoholPreference;
    private String memo;

    public MemberProfileRequest() {
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }
    public String getAgeRange() { return ageRange; }
    public void setAgeRange(String ageRange) { this.ageRange = ageRange; }
    public String getMbti() { return mbti; }
    public void setMbti(String mbti) { this.mbti = mbti; }
    public List<String> getPreferredAppointmentTypes() { return preferredAppointmentTypes; }
    public void setPreferredAppointmentTypes(List<String> preferredAppointmentTypes) { this.preferredAppointmentTypes = preferredAppointmentTypes; }
    public List<String> getPreferredTimeSlots() { return preferredTimeSlots; }
    public void setPreferredTimeSlots(List<String> preferredTimeSlots) { this.preferredTimeSlots = preferredTimeSlots; }
    public String getPreferredDuration() { return preferredDuration; }
    public void setPreferredDuration(String preferredDuration) { this.preferredDuration = preferredDuration; }
    public String getPreferredTravelTime() { return preferredTravelTime; }
    public void setPreferredTravelTime(String preferredTravelTime) { this.preferredTravelTime = preferredTravelTime; }
    public String getPreferredSpending() { return preferredSpending; }
    public void setPreferredSpending(String preferredSpending) { this.preferredSpending = preferredSpending; }
    public String getAlcoholPreference() { return alcoholPreference; }
    public void setAlcoholPreference(String alcoholPreference) { this.alcoholPreference = alcoholPreference; }
    public String getMemo() { return memo; }
    public void setMemo(String memo) { this.memo = memo; }
}
