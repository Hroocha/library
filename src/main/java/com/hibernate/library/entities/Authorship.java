package com.hibernate.library.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.util.UUID;

@Entity
public class Authorship {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private UUID id;

    @Column(name = "book_id")
    private UUID bookId;

    @Column(name = "author_id")
    private UUID authorId;
}
