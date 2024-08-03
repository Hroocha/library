package com.hibernate.library.repositories;

import com.hibernate.library.entities.Author;
import com.hibernate.library.entities.Authorship;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Repository
public interface AuthorshipRepository extends CrudRepository <Authorship, UUID> {

    @Query(value = "SELECT * FROM books_authors where book_id in (:books_id)", nativeQuery = true)
    List<Author> findAllAuthorsByBooks(@Param("books_id")Collection<UUID> books_id);
}
