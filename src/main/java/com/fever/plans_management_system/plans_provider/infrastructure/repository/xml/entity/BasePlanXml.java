package com.fever.plans_management_system.plans_provider.infrastructure.repository.xml.entity;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import lombok.Data;
import java.util.List;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class BasePlanXml {

    @XmlAttribute(name = "base_plan_id")
    private Long basePlanId;

    @XmlAttribute(name = "sell_mode")
    private String sellMode;

    @XmlAttribute(name = "title")
    private String title;

    @XmlAttribute(name = "organizer_company_id")
    private Long organizerCompanyId;

    @XmlElement(name = "plan")
    private List<PlanXml> plans;

}

