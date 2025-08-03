package com.fever.plans_management_system.plans_provider.domain.entity;

import com.fever.plans_management_system.plans_provider.domain.valueobject.ZoneId;

import java.math.BigDecimal;

public class Zone {
    private ZoneId id;
    private String name;
    private int capacity;
    private BigDecimal price;
    private boolean isNumbered;

    private Zone(Builder builder) {
        id = builder.id;
        name = builder.name;
        capacity = builder.capacity;
        price = builder.price;
        isNumbered = builder.isNumbered;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private ZoneId id;
        private String name;
        private int capacity;
        private BigDecimal price;
        private boolean isNumbered;

        private Builder() {
        }

        public Builder id(ZoneId val) {
            id = val;
            return this;
        }

        public Builder name(String val) {
            name = val;
            return this;
        }

        public Builder capacity(int val) {
            capacity = val;
            return this;
        }

        public Builder price(BigDecimal val) {
            price = val;
            return this;
        }

        public Builder isNumbered(boolean val) {
            isNumbered = val;
            return this;
        }

        public Zone build() {
            return new Zone(this);
        }
    }

    public ZoneId getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getCapacity() {
        return capacity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public boolean isNumbered() {
        return isNumbered;
    }
}

