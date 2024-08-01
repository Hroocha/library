package com.hibernate.library.entities;

import com.hibernate.library.entities.enums.TypeOfBook;
import jakarta.persistence.*;
import lombok.Builder;

import java.util.UUID;

@Entity
public class Book {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private UUID id;

    @Column(name = "type_of_book")
    private Enum <TypeOfBook> typeOfBook;

    @Column(name = "quantity_available")
    private Integer quantityAvailable;

    @Version
    @Column(name = "version")
    @Builder.Default
    private Integer version = 0;
}
