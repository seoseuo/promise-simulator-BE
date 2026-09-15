package com.promisesimulator.member.entity;

import com.promisesimulator.global.entity.BaseTimeEntity;
import com.promisesimulator.member.dto.MemberProfileRequest;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.UUID;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

/** 로그인한 사용자의 기본 정보와 약속 취향을 보관한다. */
@Entity
@Table(name = "member_profiles")
public class MemberProfile extends BaseTimeEntity {

    // 회원 프로필의 내부 식별자다. 외부 인증 식별자는 authSubject로 관리한다.
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    // 인증 제공자가 발급한 사용자 식별자. 카카오/네이버 연동 시 고유성을 보장한다.
    @Column(nullable = false, unique = true, length = 100)
    private String authSubject;

    // 화면과 시뮬레이션 결과에 표시할 회원 이름이다.
    @Column(nullable = false, length = 30)
    private String name;

    // 회원이 선택한 성별 값이다. 선택하지 않을 수 있다.
    @Column(length = 10)
    private String gender;

    // 회원이 선택한 연령대 값이다. 정확한 생년월일은 저장하지 않는다.
    @Column(length = 20)
    private String ageRange;

    // 약속 성향 파악에 사용하는 MBTI 값이다.
    @Column(length = 4)
    private String mbti;

    // 질문 구성이 바뀔 수 있는 취향은 PostgreSQL jsonb에 유연하게 저장한다.
    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb")
    private String preferences;

    // 시뮬레이터에 전달할 회원의 추가 설명이다.
    @Column(length = 500)
    private String memo;

    protected MemberProfile() {
        // JPA가 리플렉션으로 엔티티를 생성할 때 사용한다.
    }

    public static MemberProfile create(String authSubject, MemberProfileRequest request, String preferences) {
        MemberProfile memberProfile = new MemberProfile();
        memberProfile.authSubject = authSubject;
        memberProfile.apply(request, preferences);
        return memberProfile;
    }

    public void update(MemberProfileRequest request, String preferences) {
        apply(request, preferences);
    }

    private void apply(MemberProfileRequest request, String preferences) {
        this.name = request.getName();
        this.gender = request.getGender();
        this.ageRange = request.getAgeRange();
        this.mbti = request.getMbti();
        this.preferences = preferences;
        this.memo = request.getMemo();
    }

    public UUID getId() { return id; }
    public String getAuthSubject() { return authSubject; }
    public String getName() { return name; }
    public String getGender() { return gender; }
    public String getAgeRange() { return ageRange; }
    public String getMbti() { return mbti; }
    public String getPreferences() { return preferences; }
    public String getMemo() { return memo; }
}
