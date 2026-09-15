package com.promisesimulator.simulation.controller;

import com.promisesimulator.simulation.dto.SimulationRequest;
import com.promisesimulator.simulation.dto.SimulationResultResponse;
import com.promisesimulator.simulation.service.SimulationResultService;
import com.promisesimulator.simulation.service.SimulationService;
import java.util.UUID;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** 약속 시뮬레이션 실행과 결과 조회 요청을 처리한다. */
@RestController
@RequestMapping("/api/v1/appointments/{appointmentId}/simulations")
public class SimulationController {

    private final SimulationService simulationService;
    private final SimulationResultService simulationResultService;

    public SimulationController(
            SimulationService simulationService, SimulationResultService simulationResultService) {
        this.simulationService = simulationService;
        this.simulationResultService = simulationResultService;
    }

    @PostMapping
    public ResponseEntity<SimulationResultResponse> createSimulation(
            @RequestHeader("X-Member-Profile-Id") UUID memberProfileId,
            @PathVariable UUID appointmentId,
            @RequestBody SimulationRequest request) {
        return ResponseEntity.ok(simulationService.simulate(memberProfileId, appointmentId, request));
    }

    @GetMapping("/latest")
    public ResponseEntity<SimulationResultResponse> getLatestSimulation(
            @RequestHeader("X-Member-Profile-Id") UUID memberProfileId,
            @PathVariable UUID appointmentId) {
        return ResponseEntity.ok(simulationResultService.getLatest(memberProfileId, appointmentId));
    }
}
