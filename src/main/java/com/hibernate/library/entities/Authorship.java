package com.hibernate.library.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
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

    @ManyToOne  (fetch = FetchType.LAZY)  // default eager
    @JoinTable(
            name = "books",
            joinColumns = @JoinColumn(name = "id"))
    private Book book;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinTable(
            name = "authors",
            joinColumns = @JoinColumn(name = "id"))
    private Author author;


}
