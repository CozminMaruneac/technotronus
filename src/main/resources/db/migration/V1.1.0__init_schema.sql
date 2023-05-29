CREATE TABLE domain
(

    id   integer generated always as identity,
    name varchar,
    PRIMARY KEY (id)
);

CREATE TABLE study_program
(
    id          integer generated always as identity,
    acronym     varchar,
    name        varchar,
    description varchar,
    primary key (id)
);

CREATE TABLE account
(
    id                uuid,
    first_name        varchar NOT NULL,
    father_initial    varchar,
    last_name         varchar NOT NULL,
    email             varchar UNIQUE,
    profile_image_url varchar,
    role              varchar,
    phone_number      varchar,
    domain_id         integer,
    study_program_id  integer,
    study_year        integer,
    financial_status  varchar,
    PRIMARY KEY (id),
    FOREIGN KEY (study_program_id) REFERENCES study_program (id),
    FOREIGN KEY (domain_id) REFERENCES domain (id)
);

CREATE TABLE certificate
(
    id              integer generated always as identity,
    number          integer,
    release_date    date,
    faculty_acronym varchar,
    PRIMARY KEY (id)
);

CREATE TABLE academic_year
(
    id                    integer generated always as identity,
    current_academic_year varchar,
    status                varchar,
    dean_id               uuid,
    chief_secretary_id    uuid,
    primary key (id),
    FOREIGN KEY (dean_id) REFERENCES account (id),
    FOREIGN KEY (chief_secretary_id) references account (id)
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