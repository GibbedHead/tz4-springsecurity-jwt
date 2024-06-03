CREATE TABLE roles
(
    id   BIGINT  NOT NULL GENERATED BY DEFAULT AS IDENTITY,
    name VARCHAR(255) NOT NULL UNIQUE,
    CONSTRAINT pk_role PRIMARY KEY (id)
);

CREATE TABLE "users"
(
    id        BIGINT NOT NULL GENERATED BY DEFAULT AS IDENTITY,
    user_name VARCHAR(255) NOT NULL UNIQUE,
    password  VARCHAR(255) NOT NULL,
    email     VARCHAR(255) NOT NULL UNIQUE,
    CONSTRAINT pk_user PRIMARY KEY (id)
);

CREATE TABLE user_role
(
    role_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    CONSTRAINT pk_user_role PRIMARY KEY (role_id, user_id),
    CONSTRAINT fk_user_role_on_role FOREIGN KEY (role_id) REFERENCES roles (id) ON DELETE CASCADE,
    CONSTRAINT fk_user_role_on_user FOREIGN KEY (user_id) REFERENCES "users" (id) ON DELETE CASCADE
);