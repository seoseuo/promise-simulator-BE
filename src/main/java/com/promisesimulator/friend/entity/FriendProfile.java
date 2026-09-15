package com.promisesimulator.friend.entity;

import com.promisesimulator.global.entity.BaseTimeEntity;
import com.promisesimulator.member.entity.MemberProfile;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.UUID;

/** 한 사용자가 직접 등록한 친구와 두 사람 사이의 관계 정보를 나타낸다. */
@Entity
@Table(name = "friend_profiles")
public class FriendProfile extends BaseTimeEntity {

    // 친구 프로필의 내부 식별자다.
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    // 친구는 서비스 계정이 아닐 수 있으므로 등록한 사용자와만 관계를 맺는다.
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "member_profile_id", nullable = false)
    private MemberProfile memberProfile;

    // 등록한 사용자가 구분하기 위한 친구 이름이다.
    @Column(nullable = false, length = 30)
    private String name;

    // 사용자와 친구 사이의 친밀도 선택값이다.
    @Column(length = 30)
    private String closeness;

    // 사용자와 친구가 만나는 빈도 선택값이다.
    @Column(length = 30)
    private String meetingFrequency;

    // 두 사람의 대화가 잘 통하는 정도를 나타내는 선택값이다.
    @Column(length = 30)
    private String conversationCompatibility;

    // 시뮬레이션에 반영할 친구 관련 자유 입력 메모다.
    @Column(length = 500)
    private String memo;

    public FriendProfile() {
        // JPA 기본 생성자
    }

    public UUID getId() { return id; }
    public MemberProfile getMemberProfile() { return memberProfile; }
    public String getName() { return name; }
    public String getCloseness() { return closeness; }
    public String getMeetingFrequency() { return meetingFrequency; }
    public String getConversationCompatibility() { return conversationCompatibility; }
    public String getMemo() { return memo; }
    public void setMemberProfile(MemberProfile memberProfile) { this.memberProfile = memberProfile; }
    public void setName(String name) { this.name = name; }
    public void setCloseness(String closeness) { this.closeness = closeness; }
    public void setMeetingFrequency(String meetingFrequency) { this.meetingFrequency = meetingFrequency; }
    public void setConversationCompatibility(String conversationCompatibility) { this.conversationCompatibility = conversationCompatibility; }
    public void setMemo(String memo) { this.memo = memo; }
}
