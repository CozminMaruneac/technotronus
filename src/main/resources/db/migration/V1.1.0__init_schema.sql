CREATE TABLE domain
(

    id            integer generated always as identity,
    name          varchar,
    academic_year varchar,
    PRIMARY KEY (id)
);

CREATE TABLE study_program
(
    id           integer generated always as identity,
    acronym      varchar,
    name         varchar,
    description  varchar,
    secretary_id uuid,
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

CREATE TABLE academic_year
(
    id                    integer generated always as identity,
    current_academic_year varchar,
    status                varchar,
    faculty_name          varchar,
    faculty_acronym       varchar,
    dean_name             varchar,
    chief_secretary_name  varchar,
    primary key (id)
);

CREATE TABLE certificate_request
(
    id             integer generated always as identity,
    user_id        uuid,
    reason         varchar,
    status         varchar,
    secretary_id   uuid,
    requested_date date,
    PRIMARY KEY (id),
    foreign key (user_id) references account (id),
    FOREIGN KEY (secretary_id) REFERENCES account (id)
);