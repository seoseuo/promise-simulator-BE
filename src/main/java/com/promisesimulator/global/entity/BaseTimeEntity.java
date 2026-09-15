package com.promisesimulator.global.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import java.time.LocalDateTime;

/** 모든 엔티티에 생성·수정 시각을 일관되게 기록한다. */
@MappedSuperclass
public abstract class BaseTimeEntity {

    // 레코드가 처음 저장된 시각이며, 생성 이후 변경하지 않는다.
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    // 레코드가 마지막으로 변경된 시각이다.
    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        LocalDateTime now = LocalDateTime.now();
        createdAt = now;
        updatedAt = now;
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
