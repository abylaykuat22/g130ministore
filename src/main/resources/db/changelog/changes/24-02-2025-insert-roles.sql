-- liquibase formatted sql

-- changeset alisher:1
INSERT INTO g130ministoredb.roles(name)
VALUES
    ('ROLE_USER'),
    ('ROLE_ADMIN'),
    ('ROLE_MANAGER');


-- changeset alisher:2
INSERT INTO g130ministoredb.users(password_hash, username, full_name)
VALUES ('$2a$12$E2KpL7QoDPCjXtmNqaZVQeQAlNAiOPAUnTo55hey2g8xaI/hEW/zK', 'admin', 'ADMIN ADMIN');

-- changeset alisher:3
INSERT INTO g130ministoredb.user_roles(user_id, role_id)
VALUES (1, 1)



