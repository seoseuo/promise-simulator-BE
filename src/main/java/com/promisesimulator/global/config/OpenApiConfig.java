package com.promisesimulator.global.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI promiseSimulatorOpenApi() {
        return new OpenAPI().info(new Info()
                .title("Promise Simulator API")
                .version("v1")
                .description("약속 시뮬레이터 백엔드 API 명세입니다."));
    }
}
