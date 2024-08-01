package com.hibernate.library.repositories;

import com.hibernate.library.entities.Reader;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ReaderRepository extends CrudRepository<Reader, UUID> {
}
