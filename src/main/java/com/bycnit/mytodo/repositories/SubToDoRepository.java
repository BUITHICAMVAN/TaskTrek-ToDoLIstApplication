package com.bycnit.mytodo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.bycnit.mytodo.models.SubToDo;

public interface SubToDoRepository extends JpaRepository<SubToDo, Long> {
}
