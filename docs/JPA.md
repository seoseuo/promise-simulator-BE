# JPA 설계 가이드

## 선택

Spring Boot **4.1.1** 기준으로 **Spring Data JPA + Hibernate + PostgreSQL**을 사용한다.
`spring-boot-starter-data-jpa`와 PostgreSQL JDBC 드라이버를 의존성에 추가한다.
Hibernate의 `@JdbcTypeCode(SqlTypes.JSON)`으로 PostgreSQL `jsonb`를 매핑하므로,
별도 JSON 타입 라이브러리는 필요하지 않다.

## 모델링 원칙

- `MemberProfile`, `FriendProfile`, `Appointment`처럼 관계와 조회가 중요한 정보는
  일반 컬럼과 외래 키로 모델링한다.
- 활동 취향처럼 복수 선택이고 아직 질문 구성이 바뀔 수 있는 값만 `jsonb`에 둔다.
- JSONB 안의 특정 값으로 자주 검색하게 되면 일반 컬럼 또는 별도 테이블로 승격한다.
- 엔티티에는 생성·수정 시각을 공통으로 저장한다. API 요청/응답은 엔티티를 직접
  노출하지 않고 DTO를 사용한다.

## 초기 스키마 생성

로컬 프로필에서는 `spring.jpa.hibernate.ddl-auto=update`로 애플리케이션 기동 시
테이블을 생성·변경한다. 이는 로컬 개발 편의용이며 운영 환경에서는 사용하지 않는다.
운영 스키마 변경은 Flyway 또는 Liquibase 같은 마이그레이션 도구로 관리한다.

## 현재 초안

`src/main/java/com/promisesimulator`에 기본 엔티티를 둔다. `MemberProfile`은 내
프로필, `FriendProfile`은 내 계정에 등록한 친구, `Appointment`는 한 번의 약속을
나타낸다. 친구와 약속 참여 관계, AI 시뮬레이션 결과는 다음 단계에서 별도 엔티티로
추가한다.
