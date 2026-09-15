package com.promisesimulator.simulation.dto;

/** 시뮬레이션 재생성 여부를 전달한다. */
public class SimulationRequest {

    private boolean regenerate;

    public SimulationRequest() {
    }

    public boolean isRegenerate() { return regenerate; }
    public void setRegenerate(boolean regenerate) { this.regenerate = regenerate; }
}
