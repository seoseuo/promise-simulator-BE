package com.promisesimulator.simulation.dto;

/** Service 내부에서 AI 제공자에 전달하는 읽기 전용 프롬프트다. */
public record SimulationPrompt(String content) {
}
