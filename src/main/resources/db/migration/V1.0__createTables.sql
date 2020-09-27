CREATE TABLE IF NOT EXISTS ps_user
(
    id             BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY NOT NULL,
    email          text UNIQUE                                     NOT NULL,
    email_accepted bool                                                 default false,
    phone          text UNIQUE                                     NULL,
    phone_accepted bool                                                 default false,
    password       text                                            NOT NULL,
    banned         bool                                                 default false,
    authorities    text                                                 default 'USER',
    first_name     text                                            NULL,
    last_name      text                                            NULL,
    created_utc    timestamp                                       NULL DEFAULT (now() at time zone 'utc'),
    modified_utc   timestamp                                       NULL DEFAULT (now() at time zone 'utc')
);

CREATE TABLE IF NOT EXISTS ps_client
(
    id                     BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY NOT NULL,
    client_id              text UNIQUE                                     NOT NULL,
    secret                 text,
    scopes                 text                                            NULL,
    authorized_grand_types text                                            NULL,
    authorities            text                                            NULL,
    auto_approve_scopes    text                                            NULL
);