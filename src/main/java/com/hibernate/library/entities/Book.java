package com.hibernate.library.entities;

import com.hibernate.library.entities.enums.TypeOfBook;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Table(name = "books")
public class Book {

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
    @ManyToMany(mappedBy = "books")
    private List<Author> authors = new ArrayList<>();

    @Getter
    @Setter
    @ManyToOne(fetch = FetchType.LAZY)   // default eager
    @JoinColumn(name = "reader_id")
    private Reader reader;

    @Getter
    @Setter
    @Version
    @Column(name = "version")
//    @Builder.Default
    private Integer version = 0;

    public Book(String name){
        this.name = name;
        this.reader = null;
    }

}
