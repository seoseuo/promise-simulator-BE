package com.promisesimulator.simulation.service;

import com.promisesimulator.appointment.dto.AppointmentResponse;
import com.promisesimulator.friend.dto.FriendProfileResponse;
import com.promisesimulator.member.dto.MemberProfileResponse;
import com.promisesimulator.simulation.dto.SimulationPrompt;
import org.springframework.stereotype.Service;

/** 시뮬레이션 입력을 AI 프롬프트로 변환한다. */
@Service
public class SimulationPromptService {

    public SimulationPrompt createPrompt(
            MemberProfileResponse memberProfile,
            FriendProfileResponse friendProfile,
            AppointmentResponse appointment) {
        throw new UnsupportedOperationException("TODO: 시뮬레이션 프롬프트 생성 구현");
    }
}
