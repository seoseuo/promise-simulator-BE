package com.promisesimulator.simulation.dto;

import java.util.List;
import java.util.UUID;

/** Service와 API에서 반환하는 읽기 전용 약속 시뮬레이션 결과다. */
public record SimulationResultResponse(
        UUID simulationResultId,
        List<TimelineEntry> timeline,
        SimulationSummary summary,
        String mostEnjoyableMoment,
        String mostRiskyMoment,
        String recommendation,
        String verdict,
        int expectedSatisfaction,
        String verdictReason) {

    /** 시간대별 예상 흐름이다. */
    public record TimelineEntry(
            String time,
            String activity,
            String expectedSituation,
            String behavior,
            String variables) {
    }

    /** 약속의 핵심 점수 요약이다. */
    public record SimulationSummary(
            int enjoyment,
            int compatibility,
            int satisfaction,
            int fatigue,
            int cost) {
    }
}
