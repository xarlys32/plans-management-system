package com.fever.plans_management_system.plans_management.infrastructure.repository.postgres.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "zones")
public class ZoneEntity {

    @Id
    @Column(name = "zone_id")
    private Long zoneId;

    @ManyToOne
    @JoinColumn(name = "plan_id")
    private PlanEntity plan;

    @Column(name = "capacity")
    private Integer capacity;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "name")
    private String name;

    @Column(name = "numbered")
    private Boolean numbered;
}
