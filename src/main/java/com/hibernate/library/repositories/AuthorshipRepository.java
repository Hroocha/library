package com.hibernate.library.repositories;

import com.hibernate.library.entities.Authorship;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AuthorshipRepository extends CrudRepository <Authorship, UUID> {
}
