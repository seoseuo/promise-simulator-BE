package com.promisesimulator.friend.dto;

import java.util.UUID;

/** Service와 API에서 반환하는 읽기 전용 친구 프로필 값이다. */
public record FriendProfileResponse(
        UUID id,
        String name,
        String closeness,
        String meetingFrequency,
        String conversationCompatibility,
        String memo) {
}
