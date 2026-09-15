package com.promisesimulator.member.service;

import com.promisesimulator.member.dto.MemberProfileRequest;
import com.promisesimulator.member.dto.MemberProfileResponse;
import org.springframework.stereotype.Service;

/** 본인 프로필의 생성·조회·수정을 담당한다. */
@Service
public class MemberProfileService {

    public MemberProfileResponse createProfile(String authSubject, MemberProfileRequest request) {
        throw new UnsupportedOperationException("TODO: 프로필 생성 구현");
    }

    public MemberProfileResponse getMyProfile(String authSubject) {
        throw new UnsupportedOperationException("TODO: 내 프로필 조회 구현");
    }

    public MemberProfileResponse updateMyProfile(String authSubject, MemberProfileRequest request) {
        throw new UnsupportedOperationException("TODO: 내 프로필 수정 구현");
    }
}
