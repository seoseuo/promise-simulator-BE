package com.promisesimulator.member.controller;

import com.promisesimulator.member.dto.MemberProfileRequest;
import com.promisesimulator.member.dto.MemberProfileResponse;
import com.promisesimulator.member.service.MemberProfileService;
import java.net.URI;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** 본인 프로필 API의 HTTP 요청을 처리한다. */
@RestController
@RequestMapping("/api/v1/member-profiles")
public class MemberProfileController {

    private final MemberProfileService memberProfileService;

    public MemberProfileController(MemberProfileService memberProfileService) {
        this.memberProfileService = memberProfileService;
    }

    @PostMapping
    public ResponseEntity<MemberProfileResponse> createProfile(
            @RequestHeader("X-Auth-Subject") String authSubject,
            @RequestBody MemberProfileRequest request) {
        MemberProfileResponse response = memberProfileService.createProfile(authSubject, request);
        return ResponseEntity.created(URI.create("/api/v1/member-profiles/me")).body(response);
    }

    @GetMapping("/me")
    public ResponseEntity<MemberProfileResponse> getMyProfile(
            @RequestHeader("X-Auth-Subject") String authSubject) {
        return ResponseEntity.ok(memberProfileService.getMyProfile(authSubject));
    }

    @PutMapping("/me")
    public ResponseEntity<MemberProfileResponse> updateMyProfile(
            @RequestHeader("X-Auth-Subject") String authSubject,
            @RequestBody MemberProfileRequest request) {
        return ResponseEntity.ok(memberProfileService.updateMyProfile(authSubject, request));
    }
}
