package com.hibernate.library.entities;

import com.hibernate.library.entities.enums.TypeOfBook;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "reader_id")
    private UUID readerId;

    @Version
    @Column(name = "version")
//    @Builder.Default
    private Integer version = 0;

    public Book(String name){
        this.name = name;
        this.readerId = null;
    }

}
