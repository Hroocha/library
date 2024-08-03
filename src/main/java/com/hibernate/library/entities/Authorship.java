package com.hibernate.library.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@Table(name = "books_authors")
public class Authorship {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private UUID id;

    @ManyToOne   /*(fetch = FetchType.LAZY)*/   // default eager
    @JoinTable(
            name = "books",
            joinColumns = @JoinColumn(name = "name"))
    @Column(name = "book_id")
    private Book book;

    @ManyToOne
    @JoinTable(
            name = "authors",
            joinColumns = @JoinColumn(name = "name"))
    @Column(name = "author_id")
    private Author author;
}
