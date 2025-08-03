package com.fever.plans_management_system.plans_provider.infrastructure.repository.xml.entity;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import lombok.Data;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class ZoneXml {

    @XmlAttribute(name = "zone_id")
    private Long zoneId;

    @XmlAttribute(name = "capacity")
    private Integer capacity;

    @XmlAttribute(name = "price")
    private Double price;

    @XmlAttribute(name = "name")
    private String name;

    @XmlAttribute(name = "numbered")
    private Boolean numbered;

}

