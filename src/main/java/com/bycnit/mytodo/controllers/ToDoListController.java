package com.bycnit.mytodo.controllers;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bycnit.mytodo.models.ToDoList;
import com.bycnit.mytodo.services.ToDoListService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/v1") // the url wil start with this
public class ToDoListController {

    private final ToDoListService toDoListService;
    
    @Autowired 
    public ToDoListController(ToDoListService toDoListService) {
        this.toDoListService = toDoListService;
    }
    
    @GetMapping("/todolists")
    public List<ToDoList> getToDoLists() {
        return toDoListService.getToDoListService();
    }
    
    @GetMapping("/todolists/{id}")
    public Optional<ToDoList> getToDoListsById(@PathVariable("id") Long id) {
        return toDoListService.getToDoListByIdService(id);
    }
    
    @GetMapping("/todolists/lastToDoId")
    public Long getLastToDoId() {
        return toDoListService.getLastToDoId();
    }
    
    @PostMapping("/todolists")
//    change JSON to java Type object 
    public Long addNewTask(@RequestBody ToDoList todoList) {
        return toDoListService.addNewTaskService(todoList);
    }
    
    @DeleteMapping("/todolists/{id}")
    public void deleteToDoList(@PathVariable("id") Long id) {
        toDoListService.deleteToDoListService(id);
    }
    
    @PutMapping("/todolists/{id}")
    public void updateToDoList(@PathVariable("id") Long id, @RequestBody ToDoList toDoList) {
        toDoListService.updateToDoListService(id, toDoList);
    }
}
