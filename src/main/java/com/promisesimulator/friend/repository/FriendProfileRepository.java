package com.promisesimulator.friend.repository;

import com.promisesimulator.friend.entity.FriendProfile;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FriendProfileRepository extends JpaRepository<FriendProfile, UUID> {
}
