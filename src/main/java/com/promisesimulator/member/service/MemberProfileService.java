package com.promisesimulator.member.service;

import com.promisesimulator.member.dto.MemberProfileRequest;
import com.promisesimulator.member.dto.MemberProfileResponse;
import com.promisesimulator.member.entity.MemberProfile;
import com.promisesimulator.member.repository.MemberProfileRepository;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tools.jackson.core.JacksonException;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

/** 본인 프로필의 생성·조회·수정을 담당한다. */
@Service
public class MemberProfileService {

    private final MemberProfileRepository memberProfileRepository;
    private final ObjectMapper objectMapper;

    public MemberProfileService(MemberProfileRepository memberProfileRepository, ObjectMapper objectMapper) {
        this.memberProfileRepository = memberProfileRepository;
        this.objectMapper = objectMapper;
    }

    @Transactional
    public MemberProfileResponse createProfile(String authSubject, MemberProfileRequest request) {
        if (memberProfileRepository.existsByAuthSubject(authSubject)) {
            throw new IllegalStateException("이미 회원 프로필이 존재합니다.");
        }

        MemberProfile memberProfile = MemberProfile.create(authSubject, request, serializePreferences(request));
        return toResponse(memberProfileRepository.save(memberProfile));
    }

    public MemberProfileResponse getMyProfile(String authSubject) {
        MemberProfile memberProfile = memberProfileRepository.findByAuthSubject(authSubject)
                .orElseThrow(() -> new IllegalArgumentException("회원 프로필을 찾을 수 없습니다."));
        return toResponse(memberProfile);
    }

    @Transactional
    public MemberProfileResponse updateMyProfile(String authSubject, MemberProfileRequest request) {
        MemberProfile memberProfile = memberProfileRepository.findByAuthSubject(authSubject)
                .orElseThrow(() -> new IllegalArgumentException("회원 프로필을 찾을 수 없습니다."));
        memberProfile.update(request, serializePreferences(request));
        return toResponse(memberProfile);
    }

    private String serializePreferences(MemberProfileRequest request) {
        Map<String, Object> preferences = new LinkedHashMap<>();
        preferences.put("preferredAppointmentTypes", request.getPreferredAppointmentTypes());
        preferences.put("preferredTimeSlots", request.getPreferredTimeSlots());
        preferences.put("preferredDuration", request.getPreferredDuration());
        preferences.put("preferredTravelTime", request.getPreferredTravelTime());
        preferences.put("preferredSpending", request.getPreferredSpending());
        preferences.put("alcoholPreference", request.getAlcoholPreference());
        try {
            return objectMapper.writeValueAsString(preferences);
        } catch (JacksonException exception) {
            throw new IllegalStateException("회원 취향 정보를 저장할 수 없습니다.", exception);
        }
    }

    private MemberProfileResponse toResponse(MemberProfile memberProfile) {
        try {
            JsonNode preferences = objectMapper.readTree(memberProfile.getPreferences());
            return new MemberProfileResponse(
                    memberProfile.getId(), memberProfile.getName(), memberProfile.getGender(),
                    memberProfile.getAgeRange(), memberProfile.getMbti(),
                    objectMapper.convertValue(preferences.path("preferredAppointmentTypes"),
                            objectMapper.getTypeFactory().constructCollectionType(List.class, String.class)),
                    objectMapper.convertValue(preferences.path("preferredTimeSlots"),
                            objectMapper.getTypeFactory().constructCollectionType(List.class, String.class)),
                    preferences.path("preferredDuration").isNull() ? null : preferences.path("preferredDuration").asText(),
                    preferences.path("preferredTravelTime").isNull() ? null : preferences.path("preferredTravelTime").asText(),
                    preferences.path("preferredSpending").isNull() ? null : preferences.path("preferredSpending").asText(),
                    preferences.path("alcoholPreference").isNull() ? null : preferences.path("alcoholPreference").asText(),
                    memberProfile.getMemo());
        } catch (JacksonException exception) {
            throw new IllegalStateException("저장된 회원 취향 정보를 읽을 수 없습니다.", exception);
        }
    }
}
