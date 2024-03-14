CREATE TABLE core.tokens (
    id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY,
    token VARCHAR(255) NOT NULL,
    is_logged_out BOOLEAN NOT NULL,
    user_id INTEGER,
    CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES core.users (id)
);

ALTER TABLE core.tokens OWNER TO postgres;

