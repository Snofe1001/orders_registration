CREATE TABLE IF NOT EXISTS work.request_statuses(
    id              serial PRIMARY KEY,
    name            text,
    description     text,
    UNIQUE (name)
);

CREATE TABLE IF NOT EXISTS work.requests(
    id                  serial PRIMARY KEY,
    message             text,
    create_date         date,
    update_date         date,
    request_status_id   integer REFERENCES work.request_statuses(id),
    created_by          integer REFERENCES auth.users(id)
);