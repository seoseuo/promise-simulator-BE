package com.promisesimulator.friend.repository;

import com.promisesimulator.friend.entity.FriendProfile;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FriendProfileRepository extends JpaRepository<FriendProfile, UUID> {

    List<FriendProfile> findAllByMemberProfile_Id(UUID memberProfileId);

    java.util.Optional<FriendProfile> findByIdAndMemberProfile_Id(UUID id, UUID memberProfileId);
}
