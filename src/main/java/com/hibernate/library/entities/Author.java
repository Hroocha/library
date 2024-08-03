package com.hibernate.library.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Table(name = "authors")
public class Author {

    @Getter
    @Setter
    @Id
    @GeneratedValue
    @Column(name = "id")
    private UUID id;

    @Getter
    @Setter
    @Column(name = "name")
    private String name;

    @Getter
    @Setter
    @ManyToMany// default lazy
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    @JoinTable(
            name = "books_authors",
            joinColumns = @JoinColumn(name = "author_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id")
    )
    private List<Book> books = new ArrayList<>();

    public Author(String name){
        this.name = name;
    }
}
