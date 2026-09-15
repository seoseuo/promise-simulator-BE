package com.promisesimulator.simulation.service;

import com.promisesimulator.simulation.dto.SimulationResultResponse;
import java.util.UUID;
import org.springframework.stereotype.Service;

/** 생성된 시뮬레이션 결과의 저장·조회 기능을 담당한다. */
@Service
public class SimulationResultService {

    public SimulationResultResponse save(
            UUID memberProfileId, UUID appointmentId, SimulationResultResponse simulationResult) {
        throw new UnsupportedOperationException("TODO: 시뮬레이션 결과 저장 구현");
    }

    public SimulationResultResponse getLatest(UUID memberProfileId, UUID appointmentId) {
        throw new UnsupportedOperationException("TODO: 최신 시뮬레이션 결과 조회 구현");
    }
}
