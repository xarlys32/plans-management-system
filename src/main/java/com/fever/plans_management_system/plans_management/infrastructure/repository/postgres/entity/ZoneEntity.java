package com.fever.plans_management_system.plans_management.infrastructure.repository.postgres.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "zones")
public class ZoneEntity  implements Serializable {

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



    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ZoneEntity that = (ZoneEntity) o;
        return Objects.equals(zoneId, that.zoneId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(zoneId);
    }
}
