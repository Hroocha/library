package com.hibernate.library.entities;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@Table(name = "readers")
public class Reader {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "library_card")
    private Integer libraryCard;

    @BatchSize(size = 10)
    @OneToMany (mappedBy = "reader") // lazy
    private List<Book> books = new ArrayList<>();

    public Reader(String name, Integer libraryCard){}
}
