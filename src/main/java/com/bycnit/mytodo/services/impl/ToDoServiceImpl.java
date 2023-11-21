package com.bycnit.mytodo.services.impl;

import com.bycnit.mytodo.models.ToDo;
import com.bycnit.mytodo.repositories.ToDoRepository;
import com.bycnit.mytodo.services.ToDoService;
import com.bycnit.mytodo.exceptions.*;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

@Service
public class ToDoServiceImpl implements ToDoService{

    private ToDoRepository toDoRepo;

    @Autowired
    public ToDoServiceImpl(ToDoRepository toDoRepo) {
        this.toDoRepo = toDoRepo;
    }
        
    public List<ToDo> getAllToDo() {
        return toDoRepo.findAll();    
    }

    public Optional<ToDo> getToDoById(Long id) {
        return toDoRepo.findById(id);
    }
    
    public ToDo addNewTodo(ToDo toDo) {
        return toDoRepo.save(toDo);
    }

    public void deleteToDo(Long id) throws ToDoNotFoundException {
        Optional<ToDo> toDoFromDb = toDoRepo.findById(id);
        if (toDoFromDb.isPresent()) {
            toDoRepo.deleteById(toDoFromDb.get().getId());
        } else {
            throw new ToDoNotFoundException(id);
        }
    }
    
    @Transactional
    public void updateToDo(Long id, ToDo toDo) throws ToDoNotFoundException {
        Optional<ToDo> toDoFromDb = toDoRepo.findById(id);
        if (toDoFromDb.isPresent()) {
            ToDo existingToDo = toDoFromDb.get();
            existingToDo.setTitle(toDo.getTitle());
            existingToDo.setDescription(toDo.getDescription());
            existingToDo.setPriority(toDo.getPriority());
            toDoRepo.save(existingToDo);
        } else {
            throw new ToDoNotFoundException(id);
        }
    }
}
