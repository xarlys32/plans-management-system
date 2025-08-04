package com.fever.plans_management_system.plans_management.infrastructure.repository.postgres.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "plans")
public class PlanEntity {

    @Id
    @Column(name = "plan_id")
    private Long planId;

    @ManyToOne
    @JoinColumn(name = "base_plan_id")
    private BasePlanEntity basePlan;

    @Column(name = "plan_start_date")
    private LocalDateTime planStartDate;

    @Column(name = "plan_end_date")
    private LocalDateTime planEndDate;

    @Column(name = "sell_from")
    private LocalDateTime sellFrom;

    @Column(name = "sell_to")
    private LocalDateTime sellTo;

    @Column(name = "sold_out")
    private Boolean soldOut;

    @OneToMany(mappedBy = "plan", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ZoneEntity> zones;

}
