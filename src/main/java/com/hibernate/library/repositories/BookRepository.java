package com.hibernate.library.repositories;

import com.hibernate.library.entities.Author;
import com.hibernate.library.entities.Book;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface BookRepository extends CrudRepository<Book, UUID> {

    @EntityGraph(attributePaths = {"authors", /*"reader"*/})
    List<Book> findAll();

}
