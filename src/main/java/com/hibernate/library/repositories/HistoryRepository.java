package com.hibernate.library.repositories;

import com.hibernate.library.entities.History;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface HistoryRepository extends CrudRepository <History, UUID> {
}
