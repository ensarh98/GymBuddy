CREATE SCHEMA core;

ALTER SCHEMA core OWNER TO postgres;

CREATE TABLE core.gyms (
    id integer NOT NULL GENERATED ALWAYS AS IDENTITY,
    name VARCHAR(255) NOT NULL,
    capacity integer NOT NULL,
    members integer NOT NULL,
    membership_package VARCHAR(50),
    opening_hours VARCHAR(255) NOT NULL,
    parking_spaces INTEGER,
    showers boolean,
    sauna boolean,
    trainers INTEGER,
    group_classes boolean,
    nutrition_counseling boolean,
    locker_rooms boolean
);

ALTER TABLE core.gyms OWNER TO postgres;