package com.promisesimulator.member.repository;

import com.promisesimulator.member.entity.MemberProfile;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberProfileRepository extends JpaRepository<MemberProfile, UUID> {
}
