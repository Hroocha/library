package com.hibernate.library.repositories;

import com.hibernate.library.entities.Author;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AuthorRepository extends CrudRepository <Author, UUID> {
}
