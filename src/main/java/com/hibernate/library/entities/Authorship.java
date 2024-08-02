package com.hibernate.library.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@Table(name = "authorship")
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
