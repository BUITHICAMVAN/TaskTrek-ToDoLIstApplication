package com.bycnit.mytodo.services;

import com.bycnit.mytodo.exceptions.ToDoNotFoundException;
import com.bycnit.mytodo.models.ToDo;
import java.util.List;
import java.util.Optional;

public interface ToDoService {
        
    public List<ToDo> getAllToDo();
    public ToDo addNewTodo(ToDo toDo);
    public void deleteToDo(Long id) throws ToDoNotFoundException;
    public void updateToDo(Long id, ToDo toDo) throws ToDoNotFoundException;
    public Optional<ToDo> getToDoById(Long id);
}
