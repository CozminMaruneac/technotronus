CREATE TABLE account
(
    id         uuid,
    first_name varchar NOT NULL,
    last_name  varchar NOT NULL,
    email      varchar,
    profile_image_url varchar,
    PRIMARY KEY (id)
);
