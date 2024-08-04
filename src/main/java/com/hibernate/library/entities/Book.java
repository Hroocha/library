package com.hibernate.library.entities;

import com.hibernate.library.entities.enums.TypeOfBook;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.BatchSize;
import org.springframework.boot.autoconfigure.batch.BatchDataSource;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Table(name = "books")
@Getter
@Setter
public class Book {


    @Id
    @GeneratedValue
    @Column(name = "id")
    private UUID id;

    @Column(name = "name")
    private String name;


    @ManyToMany(mappedBy = "books")
    private List<Author> authors = new ArrayList<>();


    @ManyToOne   (fetch = FetchType.LAZY)   // default eager
    @JoinColumn(name = "reader_id")
    private Reader reader;

    public Book(String name){
        this.name = name;
        this.reader = null;
    }

}
