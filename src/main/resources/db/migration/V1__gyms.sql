CREATE SCHEMA core;

ALTER SCHEMA core OWNER TO postgres;

CREATE TABLE core.gyms (
    id integer NOT NULL GENERATED ALWAYS AS IDENTITY,
    name VARCHAR(255) NOT NULL,
    capacity integer NOT NULL,
    members integer NOT NULL
);

ALTER TABLE core.gyms OWNER TO postgres;