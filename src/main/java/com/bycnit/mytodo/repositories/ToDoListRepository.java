package com.bycnit.mytodo.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.bycnit.mytodo.models.ToDoList;

public interface ToDoListRepository extends JpaRepository<ToDoList, Long> {

    @Query("SELECT MAX(t.id) FROM ToDoList t")
    Long getLastToDoId();

}
