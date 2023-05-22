CREATE TABLE account
(
    id                             uuid,
    first_name                     varchar NOT NULL,
    father_initial                 varchar,
    last_name                      varchar NOT NULL,
    email                          varchar UNIQUE,
    profile_image_url              varchar,
    role                           varchar,
    signature                      varchar,
    date_of_birth                  date,
    personal_identification_number varchar,
    address                        varchar,
    phone_number                   varchar,
    PRIMARY KEY (id)
);

CREATE TABLE certificate
(
    id              integer generated always as identity,
    number          integer,
    release_date    date,
    faculty_acronym varchar,
    PRIMARY KEY (id)
);

CREATE TABLE faculty
(

    id      integer generated always as identity,
    acronym varchar,
    name    varchar,
    PRIMARY KEY (id)
);

CREATE TABLE certificate_request
(
    id          integer generated always as identity,
    user_id     uuid,
    reason      varchar,
    is_approved boolean,
    PRIMARY KEY (id),
    foreign key (user_id) references account (id)
);

CREATE TABLE academic_year
(
    id                    integer generated always as identity,
    current_academic_year varchar,
    status                varchar,
    primary key (id)
);