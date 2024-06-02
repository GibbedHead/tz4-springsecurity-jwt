INSERT INTO role (name)
VALUES
    ('ROLE_USER'), ('ROLE_ADMIN');

INSERT INTO "user" (user_name, password, email)
VALUES
    ('user', '$2a$12$5CRiwtHPnPp4mM2Yi0qJSedpd3QNDWAH.vFIQOAGgSfncw60uOjua', 'user@domain.com'),
    ('admin', '$2a$12$Q5qVXBrBpF9FMJyK7b8fnOyuHVxgKwIfFscOySl5JhUFX49F/Q0W2', 'admin@domain.com');

INSERT INTO user_role
VALUES
    (1, 1), (2, 2);
