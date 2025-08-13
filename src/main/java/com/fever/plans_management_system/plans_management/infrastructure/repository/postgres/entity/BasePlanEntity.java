package com.fever.plans_management_system.plans_management.infrastructure.repository.postgres.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;


@Getter
@Setter
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "base_plans")
public class BasePlanEntity  implements Serializable  {

    @Id
    @Column(name = "base_plan_id")
    private Long basePlanId;

    @Column(name = "sell_mode")
    private String sellMode;

    @Column(name = "title")
    private String title;

    @Column(name = "organizer_company_id")
    private Long organizerCompanyId;

    @OneToMany(mappedBy = "basePlan", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<PlanEntity> plans;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        BasePlanEntity that = (BasePlanEntity) o;
        return Objects.equals(basePlanId, that.basePlanId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(basePlanId);
    }
}