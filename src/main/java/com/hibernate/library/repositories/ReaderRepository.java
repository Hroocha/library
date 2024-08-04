package com.hibernate.library.repositories;

import com.hibernate.library.entities.Author;
import com.hibernate.library.entities.Book;
import com.hibernate.library.entities.Reader;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ReaderRepository extends CrudRepository<Reader, UUID> {

//    @EntityGraph(attributePaths = {"books"})
    List<Reader> findAll();
    @Query("SELECT a FROM Reader a JOIN FETCH a.books")
    List<Reader> findAllBooksWithReaders();
}
