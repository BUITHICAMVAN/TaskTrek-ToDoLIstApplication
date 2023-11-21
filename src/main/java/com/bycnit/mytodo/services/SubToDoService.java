package com.bycnit.mytodo.services;

import com.bycnit.mytodo.exceptions.ToDoNotFoundException;
import com.bycnit.mytodo.models.SubToDo;

public interface SubToDoService {

    public SubToDo addNewSubToDo(Long toDoId, SubToDo subToDo) throws ToDoNotFoundException;
    public void deleteSubToDo(Long subToDoId);
//    public void updateSubToDo(Long subToDoId, SubToDo subToDo) throws SubToDoNotFoundException;
}
