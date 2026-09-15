package com.promisesimulator.friend.service;

import com.promisesimulator.friend.dto.FriendProfileRequest;
import com.promisesimulator.friend.dto.FriendProfileResponse;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;

/** 현재 사용자가 등록한 친구 프로필을 관리한다. */
@Service
public class FriendProfileService {

    public FriendProfileResponse createFriend(UUID memberProfileId, FriendProfileRequest request) {
        throw new UnsupportedOperationException("TODO: 친구 프로필 생성 구현");
    }

    public List<FriendProfileResponse> getFriends(UUID memberProfileId) {
        throw new UnsupportedOperationException("TODO: 친구 목록 조회 구현");
    }

    public FriendProfileResponse getFriend(UUID memberProfileId, UUID friendProfileId) {
        throw new UnsupportedOperationException("TODO: 친구 프로필 조회 구현");
    }

    public FriendProfileResponse updateFriend(
            UUID memberProfileId, UUID friendProfileId, FriendProfileRequest request) {
        throw new UnsupportedOperationException("TODO: 친구 프로필 수정 구현");
    }

    public void deleteFriend(UUID memberProfileId, UUID friendProfileId) {
        throw new UnsupportedOperationException("TODO: 친구 프로필 삭제 구현");
    }
}
