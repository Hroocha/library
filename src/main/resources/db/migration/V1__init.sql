CREATE
EXTENSION IF NOT EXISTS "uuid-ossp";
CREATE TABLE IF NOT EXISTS authors
(
    id   UUID PRIMARY KEY DEFAULT public.uuid_generate_v4(),
    name VARCHAR(255) NOT NULL
    );
CREATE TABLE IF NOT EXISTS readers
(
    id   UUID PRIMARY KEY DEFAULT public.uuid_generate_v4(),
    name VARCHAR(255) NOT NULL,
    library_card INTEGER UNIQUE NOT NULL
    );
CREATE TABLE IF NOT EXISTS books
(
    id            UUID PRIMARY KEY DEFAULT public.uuid_generate_v4(),
    name          VARCHAR(255) NOT NULL,
    reader_id     UUID,
    constraint reader_fk foreign key (reader_id) references readers (id) on update cascade on delete cascade
);
CREATE TABLE IF NOT EXISTS books_authors
(
    id        UUID PRIMARY KEY DEFAULT public.uuid_generate_v4(),
    book_id   UUID NOT NULL,
    author_id UUID NOT NULL,
    constraint book_fk foreign key (book_id) references books (id) on update cascade on delete cascade,
    constraint author_fk foreign key (author_id) references authors (id) on update cascade on delete cascade
);