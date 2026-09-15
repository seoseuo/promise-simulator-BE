package com.promisesimulator.friend.controller;

import com.promisesimulator.friend.dto.FriendProfileRequest;
import com.promisesimulator.friend.dto.FriendProfileResponse;
import com.promisesimulator.friend.service.FriendProfileService;
import java.util.List;
import java.util.UUID;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** 친구 프로필 API의 HTTP 요청을 처리한다. */
@RestController
@RequestMapping("/api/v1/friend-profiles")
public class FriendProfileController {

    private final FriendProfileService friendProfileService;

    public FriendProfileController(FriendProfileService friendProfileService) {
        this.friendProfileService = friendProfileService;
    }

    @PostMapping
    public ResponseEntity<FriendProfileResponse> createFriend(
            @RequestHeader("X-Member-Profile-Id") UUID memberProfileId,
            @RequestBody FriendProfileRequest request) {
        return ResponseEntity.status(201).body(friendProfileService.createFriend(memberProfileId, request));
    }

    @GetMapping
    public ResponseEntity<List<FriendProfileResponse>> getFriends(
            @RequestHeader("X-Member-Profile-Id") UUID memberProfileId) {
        return ResponseEntity.ok(friendProfileService.getFriends(memberProfileId));
    }

    @GetMapping("/{friendProfileId}")
    public ResponseEntity<FriendProfileResponse> getFriend(
            @RequestHeader("X-Member-Profile-Id") UUID memberProfileId,
            @PathVariable UUID friendProfileId) {
        return ResponseEntity.ok(friendProfileService.getFriend(memberProfileId, friendProfileId));
    }

    @PutMapping("/{friendProfileId}")
    public ResponseEntity<FriendProfileResponse> updateFriend(
            @RequestHeader("X-Member-Profile-Id") UUID memberProfileId,
            @PathVariable UUID friendProfileId,
            @RequestBody FriendProfileRequest request) {
        return ResponseEntity.ok(friendProfileService.updateFriend(memberProfileId, friendProfileId, request));
    }

    @DeleteMapping("/{friendProfileId}")
    public ResponseEntity<Void> deleteFriend(
            @RequestHeader("X-Member-Profile-Id") UUID memberProfileId,
            @PathVariable UUID friendProfileId) {
        friendProfileService.deleteFriend(memberProfileId, friendProfileId);
        return ResponseEntity.noContent().build();
    }
}
