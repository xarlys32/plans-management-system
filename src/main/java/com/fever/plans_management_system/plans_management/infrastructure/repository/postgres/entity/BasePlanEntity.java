package com.fever.plans_management_system.plans_management.infrastructure.repository.postgres.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "base_plans")
public class BasePlanEntity {

    @Id
    @Column(name = "base_plan_id")
    private Long basePlanId;

    @Column(name = "sell_mode")
    private String sellMode;

    @Column(name = "title")
    private String title;

    @Column(name = "organizer_company_id")
    private Long organizerCompanyId;

    @OneToMany(mappedBy = "basePlan", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PlanEntity> plans;
}