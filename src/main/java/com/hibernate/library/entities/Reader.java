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

    @OneToMany (mappedBy = "reader") // lazy
    private List<Book> Books = new ArrayList<>();

    public Reader(String name, Integer libraryCard){}
}
