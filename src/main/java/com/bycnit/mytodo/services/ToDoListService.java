package com.bycnit.mytodo.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bycnit.mytodo.models.ToDoList;
import com.bycnit.mytodo.repositories.ToDoListRepository;
import jakarta.transaction.Transactional;
import jakarta.persistence.EntityNotFoundException;

@Service
public class ToDoListService {

    private final ToDoListRepository toDoListRepo;
    
    @Autowired
    public ToDoListService(ToDoListRepository toDoListRepo) {
        this.toDoListRepo = toDoListRepo;
    }
        
    public List<ToDoList> getToDoListService() {
        return toDoListRepo.findAll();    
    }

    public Long addNewTaskService(ToDoList toDoList) {
        ToDoList savedToDo = toDoListRepo.save(toDoList);
        Long nextToDoId = savedToDo.getId();
        nextToDoId += 1;
        return nextToDoId;
    }


    public void deleteToDoListService(Long id) {
        toDoListRepo.deleteById(id);
    }
    
    @Transactional
    public void updateToDoListService(Long id, ToDoList toDoList) {
        Optional<ToDoList> toDoListFromDb = toDoListRepo.findById(id);
        if (toDoListFromDb.isPresent()) {
            ToDoList existingToDoList = toDoListFromDb.get();
            existingToDoList.setTitle(toDoList.getTitle());
            existingToDoList.setDescription(toDoList.getDescription());
            existingToDoList.setPriority(toDoList.getPriority());
            toDoListRepo.save(existingToDoList);
        } else {
            throw new EntityNotFoundException("ToDoList with ID " + id + " not found");
        }
    }
    
    public Optional<ToDoList> getToDoListByIdService(Long id) {
        return toDoListRepo.findById(id);
    }

    public Long getLastToDoId() {
        Long lastToDoId = toDoListRepo.getLastToDoId();
        return lastToDoId != null ? lastToDoId : 0;
    }
}
