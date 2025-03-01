package com.springboot.cruddemo.repository;

import com.springboot.cruddemo.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Long> {
    // provides implementation for all CRUD operations and transactions.
}
