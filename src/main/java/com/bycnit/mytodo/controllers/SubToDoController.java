package com.bycnit.mytodo.controllers;

import com.bycnit.mytodo.models.SubToDo;
import com.bycnit.mytodo.models.ToDo;
import com.bycnit.mytodo.services.impl.SubToDoServiceImpl;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1")
public class SubToDoController {
    private final SubToDoServiceImpl subToDoServiceImpl;
    
    @Autowired
    public SubToDoController(SubToDoServiceImpl subToDoServiceImpl) {
        this.subToDoServiceImpl = subToDoServiceImpl;
    }
    
    @PostMapping("/todo/{toDoId}/subtodo")
    public ResponseEntity<ToDo> addNewSubToDo(@PathVariable("toDoId") Long toDoId, @RequestBody SubToDo subToDo) {
        try {
            subToDoServiceImpl.addNewSubToDo(toDoId, subToDo);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    @DeleteMapping("/todo/subtodo/{subToDoId}")
    public ResponseEntity<ToDo> deleteSubToDo(@PathVariable("subToDoId") Long subToDoId) {
        try {
            subToDoServiceImpl.deleteSubToDo(subToDoId);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e) {
            e.printStackTrace();
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
//    @PutMapping("/todo/subtodo/{subToDoId}")
//    public ResponseEntity<ToDo> updateSubToDo(@PathVariable("subToDoId") Long subToDoId, @RequestBody SubToDo subToDo) throws SubToDoNotFoundException {
//        try {
//            subToDoServiceImpl.updateSubToDo(subToDoId, subToDo);
//            return ResponseEntity.status(HttpStatus.OK).build();
//        } catch (Exception e) {
//            e.printStackTrace();
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//          }
//    }
}
