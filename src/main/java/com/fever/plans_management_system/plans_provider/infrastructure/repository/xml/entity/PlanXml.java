package com.fever.plans_management_system.plans_provider.infrastructure.repository.xml.entity;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import lombok.Data;
import java.util.List;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class PlanXml {

    @XmlAttribute(name = "plan_id")
    private Long planId;

    @XmlAttribute(name = "plan_start_date")
    private String startDate;

    @XmlAttribute(name = "plan_end_date")
    private String endDate;

    @XmlAttribute(name = "sell_from")
    private String sellFrom;

    @XmlAttribute(name = "sell_to")
    private String sellTo;

    @XmlAttribute(name = "sold_out")
    private Boolean soldOut;

    @XmlElement(name = "zone")
    private List<ZoneXml> zones;

}

