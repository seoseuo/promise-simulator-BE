package com.promisesimulator.simulation.service;

import com.promisesimulator.simulation.dto.SimulationPrompt;
import com.promisesimulator.simulation.dto.SimulationRequest;
import com.promisesimulator.simulation.dto.SimulationResultResponse;
import java.util.UUID;
import org.springframework.stereotype.Service;

/** AI 제공자를 호출해 약속 시뮬레이션 결과를 생성한다. */
@Service
public class SimulationService {

    public SimulationResultResponse simulate(
            UUID memberProfileId, UUID appointmentId, SimulationRequest request) {
        throw new UnsupportedOperationException("TODO: 시뮬레이션 실행 흐름 구현");
    }

    public SimulationResultResponse simulate(SimulationPrompt prompt) {
        throw new UnsupportedOperationException("TODO: AI 시뮬레이션 실행 구현");
    }
}
