CREATE
EXTENSION IF NOT EXISTS "uuid-ossp";
CREATE TABLE books
(
    id                 UUID PRIMARY KEY DEFAULT public.uuid_generate_v4(),
    name               VARCHAR(255) NOT NULL,
    weight             FLOAT        NOT NULL,
    binding_type       VARCHAR(255) NOT NULL,
    type_of_book       VARCHAR(255) NOT NULL,
    quantity_available INTEGER      NOT NULL,
    status             VARCHAR(255) NOT NULL,
    version            INTEGER      NOT NULL
);
CREATE TABLE authors
(
    id   UUID PRIMARY KEY DEFAULT public.uuid_generate_v4(),
    name VARCHAR(255) NOT NULL
);
CREATE TABLE readers
(
    id   UUID PRIMARY KEY DEFAULT public.uuid_generate_v4(),
    name VARCHAR(255) NOT NULL
);
CREATE TABLE authorship
(
    id        UUID PRIMARY KEY DEFAULT public.uuid_generate_v4(),
    book_id   UUID NOT NULL,
    author_id UUID NOT NULL
);