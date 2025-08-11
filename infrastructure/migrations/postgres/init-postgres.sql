
\connect events;

CREATE TABLE IF NOT EXISTS base_plans (
    base_plan_id BIGINT PRIMARY KEY,
    sell_mode VARCHAR(50) NOT NULL,
    title VARCHAR(255) NOT NULL,
    organizer_company_id BIGINT
);

CREATE TABLE IF NOT EXISTS plans (
    plan_id BIGINT PRIMARY KEY,
    base_plan_id BIGINT NOT NULL,
    plan_start_date TIMESTAMP NOT NULL,
    plan_end_date TIMESTAMP NOT NULL,
    sell_from TIMESTAMP NOT NULL,
    sell_to TIMESTAMP NOT NULL,
    sold_out BOOLEAN NOT NULL,

    CONSTRAINT fk_base_plan
        FOREIGN KEY (base_plan_id)
        REFERENCES base_plans (base_plan_id)
        ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS zones (
    zone_id BIGINT PRIMARY KEY,
    plan_id BIGINT NOT NULL,
    capacity INTEGER NOT NULL,
    price NUMERIC(10, 2) NOT NULL,
    name VARCHAR(255) NOT NULL,
    numbered BOOLEAN NOT NULL,

    CONSTRAINT fk_plan
        FOREIGN KEY (plan_id)
        REFERENCES plans (plan_id)
        ON DELETE CASCADE
);