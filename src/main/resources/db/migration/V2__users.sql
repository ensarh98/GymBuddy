CREATE TABLE core.users (
    id INTEGER GENERATED ALWAYS AS IDENTITY,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    is_enabled Boolean NOT NULL,
    role VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    CONSTRAINT users_pk PRIMARY KEY (id),
	CONSTRAINT email_uq UNIQUE (email)
);

ALTER TABLE core.users OWNER TO postgres;