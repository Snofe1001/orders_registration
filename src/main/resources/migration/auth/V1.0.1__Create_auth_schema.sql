CREATE TABLE IF NOT EXISTS auth.users(
    id          serial PRIMARY KEY,
    login       text,
    password    text,
    is_enable   boolean,
    UNIQUE (login)
);

CREATE TABLE IF NOT EXISTS auth.roles(
    id          serial PRIMARY KEY,
    name        text,
    description text,
    UNIQUE (name)
);

CREATE TABLE IF NOT EXISTS auth.user_role(
    user_id     integer REFERENCES auth.users(id),
    role_id     integer REFERENCES auth.roles(id),
    PRIMARY KEY (user_id, role_id)
);