package com.promisesimulator.friend.service;

import com.promisesimulator.friend.dto.FriendProfileRequest;
import com.promisesimulator.friend.dto.FriendProfileResponse;
import com.promisesimulator.friend.entity.FriendProfile;
import com.promisesimulator.friend.repository.FriendProfileRepository;
import com.promisesimulator.member.entity.MemberProfile;
import com.promisesimulator.member.repository.MemberProfileRepository;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/** 현재 사용자가 등록한 친구 프로필을 관리한다. */
@Service
public class FriendProfileService {

    private final FriendProfileRepository friendProfileRepository;
    private final MemberProfileRepository memberProfileRepository;

    public FriendProfileService(
            FriendProfileRepository friendProfileRepository,
            MemberProfileRepository memberProfileRepository) {
        this.friendProfileRepository = friendProfileRepository;
        this.memberProfileRepository = memberProfileRepository;
    }

    @Transactional
    public FriendProfileResponse createFriend(UUID memberProfileId, FriendProfileRequest request) {
        MemberProfile memberProfile = memberProfileRepository.findById(memberProfileId)
                .orElseThrow(() -> new IllegalArgumentException("회원 프로필을 찾을 수 없습니다."));
        FriendProfile friendProfile = new FriendProfile();
        friendProfile.setMemberProfile(memberProfile);
        applyRequest(friendProfile, request);
        return toResponse(friendProfileRepository.save(friendProfile));
    }

    public List<FriendProfileResponse> getFriends(UUID memberProfileId) {
        return friendProfileRepository.findAllByMemberProfile_Id(memberProfileId).stream()
                .map(this::toResponse)
                .toList();
    }

    public FriendProfileResponse getFriend(UUID memberProfileId, UUID friendProfileId) {
        FriendProfile friendProfile = findOwnedFriend(memberProfileId, friendProfileId);
        return toResponse(friendProfile);
    }

    public FriendProfileResponse updateFriend(
            UUID memberProfileId, UUID friendProfileId, FriendProfileRequest request) {
        throw new UnsupportedOperationException("TODO: 친구 프로필 수정 구현");
    }

    public void deleteFriend(UUID memberProfileId, UUID friendProfileId) {
        throw new UnsupportedOperationException("TODO: 친구 프로필 삭제 구현");
    }

    private FriendProfileResponse toResponse(FriendProfile friendProfile) {
        return new FriendProfileResponse(
                friendProfile.getId(), friendProfile.getName(), friendProfile.getCloseness(),
                friendProfile.getMeetingFrequency(), friendProfile.getConversationCompatibility(), friendProfile.getMemo());
    }

    private void applyRequest(FriendProfile friendProfile, FriendProfileRequest request) {
        friendProfile.setName(request.getName());
        friendProfile.setCloseness(request.getCloseness());
        friendProfile.setMeetingFrequency(request.getMeetingFrequency());
        friendProfile.setConversationCompatibility(request.getConversationCompatibility());
        friendProfile.setMemo(request.getMemo());
    }

    private FriendProfile findOwnedFriend(UUID memberProfileId, UUID friendProfileId) {
        return friendProfileRepository.findByIdAndMemberProfile_Id(friendProfileId, memberProfileId)
                .orElseThrow(() -> new IllegalArgumentException("친구 프로필을 찾을 수 없습니다."));
    }
}
