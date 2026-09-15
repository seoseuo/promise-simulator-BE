package com.promisesimulator.member.dto;

import java.util.List;
import java.util.UUID;

/** Service와 API에서 반환하는 읽기 전용 본인 프로필 값이다. */
public record MemberProfileResponse(
        UUID id,
        String name,
        String gender,
        String ageRange,
        String mbti,
        List<String> preferredAppointmentTypes,
        List<String> preferredTimeSlots,
        String preferredDuration,
        String preferredTravelTime,
        String preferredSpending,
        String alcoholPreference,
        String memo) {
}
