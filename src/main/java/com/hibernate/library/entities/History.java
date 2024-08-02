package com.hibernate.library.entities;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@Table(name = "history")
public class History {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private UUID id;

    @Column(name = "book_id")
    private UUID bookId;

    @Column(name = "reader_id")
    private UUID readerId;

    @Column(name = "borrowing_date")
    private LocalDateTime borrowingDate;

    @Column(name = "return_date")
    private LocalDateTime returnDate;

    @Version
    @Column(name = "version")
//    @Builder.Default
    private Integer version = 0;

    public History(UUID bookId, UUID readerId, LocalDateTime borrowingDate){
        this.bookId = bookId;
        this.readerId = readerId;
        this.borrowingDate = borrowingDate;
    }
}
