package com.bycnit.mytodo.repositories;

import com.bycnit.mytodo.models.ToDo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ToDoRepository extends JpaRepository<ToDo, Long> {
}
