package com.bycnit.mytodo.exceptions;

public class ToDoNotFoundException extends Exception  {
    public ToDoNotFoundException(Long id){
        super("Could not find todo " + id);
    }
}

