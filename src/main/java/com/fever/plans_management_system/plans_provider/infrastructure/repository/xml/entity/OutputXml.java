package com.fever.plans_management_system.plans_provider.infrastructure.repository.xml.entity;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import lombok.Data;
import java.util.List;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class OutputXml {

    @XmlElement(name = "base_plan")
    private List<BasePlanXml> basePlans;

}
