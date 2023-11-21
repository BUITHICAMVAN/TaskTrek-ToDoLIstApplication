package com.bycnit.mytodo.services.impl;

import com.bycnit.mytodo.exceptions.ToDoNotFoundException;
import com.bycnit.mytodo.models.SubToDo;
import com.bycnit.mytodo.models.ToDo;
import com.bycnit.mytodo.repositories.SubToDoRepository;
import com.bycnit.mytodo.repositories.ToDoRepository;
import com.bycnit.mytodo.services.SubToDoService;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class SubToDoServiceImpl implements SubToDoService {

    private SubToDoRepository subToDoRepository;
    private ToDoRepository toDoRepository;
    
    public SubToDoServiceImpl(SubToDoRepository subToDoRepository, ToDoRepository toDoRepository) {
        this.subToDoRepository = subToDoRepository;
        this.toDoRepository = toDoRepository;
    }
     
    @Override
    public SubToDo addNewSubToDo(Long toDoId, SubToDo subToDo) throws ToDoNotFoundException {
        Optional<ToDo> toDo = toDoRepository.findById(toDoId);
        if (!toDo.isPresent()) {
            throw new ToDoNotFoundException(toDoId);
        }
        subToDo.setToDo(toDo.get()); // set relationship of which subtodo is of which todo
        return subToDoRepository.save(subToDo);
    }

    @Override
    public void deleteSubToDo(Long subToDoId) {
        subToDoRepository.deleteById(subToDoId);
    }

//    @Override
//    @Transactional
//    public void updateSubToDo(Long subToDoId, SubToDo subToDo) throws SubToDoNotFoundException {
//        Optional<SubToDo> subToDoFromDb = subToDoRepository.findById(subToDoId);
//        if (!subToDoFromDb.isPresent()) {
//            throw new SubToDoNotFoundException(null);
//        }
//        SubToDo subToDoUpdated = subToDoFromDb.get();
//        subToDoUpdated.setDescription(subToDo.getDescription());
//        subToDoUpdated.setStatusCode(subToDo.getStatusCode());
//        subToDoUpdated.setDueDate(subToDo.getDueDate());
//    }
}
