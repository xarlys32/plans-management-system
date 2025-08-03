package com.fever.plans_management_system.plans_provider.infrastructure.repository.xml.entity;

import jakarta.xml.bind.annotation.*;
import lombok.Data;

@Data
@XmlRootElement(name = "planList")
@XmlAccessorType(XmlAccessType.FIELD)
public class PlanListXml {

    @XmlElement(name = "output")
    private OutputXml output;
}

