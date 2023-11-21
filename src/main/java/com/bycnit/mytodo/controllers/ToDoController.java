package com.bycnit.mytodo.controllers;

import com.bycnit.mytodo.exceptions.ToDoNotFoundException;
import com.bycnit.mytodo.models.ToDo;
import com.bycnit.mytodo.services.impl.ToDoServiceImpl;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1") // the url wil start with this
public class ToDoController {

    private final ToDoServiceImpl toDoServiceImpl;
    
    @Autowired 
    public ToDoController(ToDoServiceImpl toDoServiceImpl) {
        this.toDoServiceImpl = toDoServiceImpl;
    }
    
    @GetMapping("/todo")
    public ResponseEntity<List<ToDo>> getAllToDo() {
        List<ToDo> toDoList = toDoServiceImpl.getAllToDo();
        if (toDoList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();}
        return ResponseEntity.of(Optional.of(toDoList));
    }
    
    @PostMapping("/todo")
//    change JSON to java Type object 
    public ResponseEntity<ToDo> addNewTodo(@RequestBody ToDo toDo) {
        try {
            toDoServiceImpl.addNewTodo(toDo);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();}
    }
    
    @DeleteMapping("/todo/{id}")
    public ResponseEntity<ToDo> deleteToDo(@PathVariable("id") Long id) throws ToDoNotFoundException {
        try {
            toDoServiceImpl.deleteToDo(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();}
    }
    
    @PutMapping("/todo/{id}")
    public ResponseEntity<ToDo> updateToDo(@PathVariable("id") Long id, @RequestBody ToDo toDo) throws ToDoNotFoundException {
        try {
            toDoServiceImpl.updateToDo(id, toDo);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
          }
    }
    
    @GetMapping("/todo/{id}")
    public ResponseEntity<Optional<ToDo>> getToDoById(@PathVariable("id") Long id) {
        Optional<ToDo> toDo = toDoServiceImpl.getToDoById(id);
        if (!toDo.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();} 
        return ResponseEntity.of(Optional.of(toDo));
    }
    
}
