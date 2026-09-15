# Controller 요청과 서비스 흐름

## 목적

프론트엔드는 본인 프로필, 친구 프로필, 약속을 순서대로 저장한 뒤 약속 결과를
시뮬레이션한다. 이 문서는 첫 API 구현에서 Controller가 받는 요청과 각 요청이
호출할 Service를 정의한다. API 기본 경로는 `/api/v1`이며 JSON을 사용한다.

인증이 붙기 전 개발 환경에서는 `X-Member-Profile-Id` 헤더로 현재 사용자를
식별한다. 운영에서는 인증 필터가 토큰에서 `authSubject`를 꺼내며, Controller는
이 값을 Service에 전달한다. 클라이언트가 다른 사용자의 ID를 요청 본문으로
보내지 않는다.

## 공통 규칙

- 식별자는 UUID 문자열을 사용한다.
- 날짜는 `YYYY-MM-DD`, 시각은 `HH:mm` 형식이다.
- 본문에서 `(선택)`으로 표시한 필드는 생략하거나 `null`로 보낼 수 있다.
- 선택지 값은 화면에 표시되는 한국어 문구를 그대로 사용한다. 임의 문자열은
  `400 Bad Request`로 처리한다.
- 소유하지 않은 친구 또는 약속에 접근하면 존재 여부와 관계없이 `404 Not Found`를
  반환한다.
- 검증 실패 응답은 `400 Bad Request`와 필드별 오류 목록을 반환한다.

```json
{
  "code": "VALIDATION_ERROR",
  "message": "요청 값을 확인해 주세요.",
  "fieldErrors": [
    { "field": "startTime", "message": "만나는 시간을 입력해 주세요." }
  ]
}
```

## MemberProfileController

### `POST /member-profiles`

가입 직후 본인 프로필을 만든다. `authSubject`는 인증 정보에서 가져오므로 요청에
포함하지 않는다. 같은 인증 주체의 프로필이 이미 있으면 `409 Conflict`를 반환한다.

```json
{
  "name": "민지",
  "gender": "여성",
  "ageRange": "20대",
  "mbti": "ENFP",
  "preferredAppointmentTypes": ["맛집", "카페", "전시"],
  "preferredTimeSlots": ["오후", "저녁"],
  "preferredDuration": "적당히 3~4시간",
  "preferredTravelTime": "1시간 이내",
  "preferredSpending": "2~5만원",
  "alcoholPreference": "가끔",
  "memo": "조용한 카페를 좋아해요"
}
```

- 필수: `name`, `preferredAppointmentTypes`, `preferredTimeSlots`,
  `preferredDuration`, `preferredTravelTime`, `preferredSpending`,
  `alcoholPreference`
- 선택: `gender`, `ageRange`, `mbti`, `memo`
- `name`은 30자 이하, `mbti`는 대문자 영문 4자, `memo`는 500자 이하다.
- 서비스: `MemberProfileService.createProfile(authSubject, request)`가 선택지와
  길이를 검증하고 `preferences` JSONB를 조립한 뒤 저장한다.
- 성공: `201 Created`, 생성된 프로필을 반환한다.

### `GET /member-profiles/me`

현재 로그인한 사용자의 프로필을 조회한다.

- 서비스: `MemberProfileService.getMyProfile(authSubject)`가 인증 주체로 프로필을
  조회한다.
- 성공: `200 OK`; 프로필이 없으면 `404 Not Found`다.

### `PUT /member-profiles/me`

본인 프로필 전체를 수정한다. 요청 본문과 검증 규칙은 생성 요청과 같다.

- 서비스: `MemberProfileService.updateMyProfile(authSubject, request)`가 프로필을
  조회하고 변경 값을 저장한다.
- 성공: `200 OK`

## FriendProfileController

### `POST /friend-profiles`

현재 사용자가 친구 프로필을 등록한다.

```json
{
  "name": "준호",
  "closeness": "꽤 친함",
  "meetingFrequency": "한 달에 1~2번",
  "conversationCompatibility": "잘 통함",
  "memo": "영화를 좋아하고 매운 음식은 못 먹음"
}
```

- 필수: `name`, `closeness`, `meetingFrequency`, `conversationCompatibility`
- 선택: `memo`
- 허용 선택지: 친밀도는 `아직 어색함`, `어느 정도 친함`, `꽤 친함`, `매우 친함`;
  만남 빈도는 `거의 안 만남`, `몇 달에 한 번`, `한 달에 1~2번`, `자주 만남`;
  대화 궁합은 `잘 안 통함`, `무난함`, `잘 통함`, `매우 잘 통함`이다.
- 서비스: `FriendProfileService.createFriend(memberProfileId, request)`가 현재
  사용자 프로필을 확인하고 친구를 저장한다.
- 성공: `201 Created`

### `GET /friend-profiles`

현재 사용자가 등록한 친구 목록을 최신 등록 순으로 조회한다.

- 서비스: `FriendProfileService.getFriends(memberProfileId)`
- 성공: `200 OK`

### `GET /friend-profiles/{friendProfileId}` / `PUT /friend-profiles/{friendProfileId}` / `DELETE /friend-profiles/{friendProfileId}`

친구 한 명을 조회, 수정, 삭제한다. 수정 요청의 필드와 검증 규칙은 생성 요청과 같다.

- 서비스: `getFriend`, `updateFriend`, `deleteFriend`는 모두 친구의 소유자를
  확인한다.
- 성공: 조회·수정은 `200 OK`, 삭제는 `204 No Content`다.

## AppointmentController

### `POST /appointments`

시뮬레이션 대상 약속을 생성한다. 현재 단계에서는 친구 한 명만 선택한다.

```json
{
  "friendProfileId": "7a9133bb-7597-46d3-a863-4d6a075fb7e1",
  "appointmentDate": "2026-09-20",
  "startTime": "13:00",
  "endTime": "20:00",
  "location": "성수",
  "desiredActivities": "맛집과 카페, 전시를 가고 싶다",
  "fixedSchedule": "15:00 전시 예약",
  "additionalRequest": "비가 올 경우도 고려해 줘"
}
```

- 필수: `friendProfileId`, `appointmentDate`, `startTime`
- 선택: `endTime`, `location`, `desiredActivities`, `fixedSchedule`,
  `additionalRequest`
- `endTime`이 있으면 `startTime`보다 늦어야 한다. `location`은 200자 이하,
  `desiredActivities`와 `fixedSchedule`은 각각 500자 이하,
  `additionalRequest`는 1,000자 이하다.
- 서비스: `AppointmentService.createAppointment(memberProfileId, request)`가
  친구 소유 여부를 확인하고 약속을 저장한다. 친구-약속 관계는 다음 구현에서
  `AppointmentParticipant`로 영속화한다. 그 전까지는 요청의 친구 ID를
  시뮬레이션 실행 정보로 함께 보관하거나 별도 입력 모델로 전달한다.
- 성공: `201 Created`

### `GET /appointments/{appointmentId}` / `PUT /appointments/{appointmentId}`

약속을 조회하거나 수정한다. 수정 요청은 생성 요청과 같은 형식을 사용한다.

- 서비스: `AppointmentService.getAppointment(memberProfileId, appointmentId)`,
  `AppointmentService.updateAppointment(memberProfileId, appointmentId, request)`
- 성공: `200 OK`

## SimulationController

### `POST /appointments/{appointmentId}/simulations`

저장된 약속과 본인·친구 프로필을 조합해 시뮬레이션을 실행한다. 결과 생성 시간이
길어질 수 있으므로 첫 구현은 동기 처리로 `200 OK`를 반환하고, AI 호출이 길어지면
`202 Accepted`와 작업 ID를 반환하는 비동기 방식으로 바꾼다.

```json
{
  "regenerate": false
}
```

- 선택: `regenerate` (기본값 `false`). 이미 결과가 있을 때 `true`면 새 결과를
  생성한다.
- 서비스 실행 순서:
  1. `AppointmentService.getAppointment`으로 약속 소유자를 확인한다.
  2. `MemberProfileService.getMyProfile`과 `FriendProfileService.getFriend`로
     시뮬레이션 입력을 구성한다.
  3. `SimulationPromptService.createPrompt`가 프로필·약속·선택지를 AI 입력으로
     변환한다.
  4. `SimulationService.simulate`가 AI 제공자를 호출하고 결과 형식을 검증한다.
  5. `SimulationResultService.save`가 결과와 입력 스냅샷을 저장한다.
- 성공: `200 OK`, 아래 구조의 시뮬레이션 결과를 반환한다.

```json
{
  "simulationResultId": "df647da5-d342-4ee2-aea2-7fe4e5f925c1",
  "timeline": [
    {
      "time": "13:00",
      "activity": "만남과 점심",
      "expectedSituation": "둘 다 식당을 고르는 중이다.",
      "behavior": "가까운 맛집을 함께 찾는다.",
      "variables": "대기 줄과 날씨"
    }
  ],
  "summary": {
    "enjoyment": 84,
    "compatibility": 91,
    "satisfaction": 72,
    "fatigue": 63,
    "cost": 21
  },
  "mostEnjoyableMoment": "저녁 식사 후 대화 시간",
  "mostRiskyMoment": "카페 다음 일정이 비는 시간",
  "recommendation": "카페 이후 활동 하나를 정해 두세요.",
  "verdict": "나가라.",
  "expectedSatisfaction": 84,
  "verdictReason": "대화 궁합과 활동 선호가 잘 맞습니다."
}
```

타임라인에는 반드시 `time`, `activity`, `expectedSituation`, `behavior`,
`variables`를 포함한다. 요약에는 재미도(`enjoyment`), 대화 궁합(`compatibility`),
일정 만족도(`satisfaction`), 체력 소모(`fatigue`), 비용 만족도(`cost`)를 0~100
정수로 포함한다.

### `GET /appointments/{appointmentId}/simulations/latest`

해당 약속의 가장 최근 시뮬레이션 결과를 조회한다.

- 서비스: `SimulationResultService.getLatest(memberProfileId, appointmentId)`가
  약속 소유권을 확인하고 최신 결과를 조회한다.
- 성공: `200 OK`; 생성된 결과가 없으면 `404 Not Found`다.
