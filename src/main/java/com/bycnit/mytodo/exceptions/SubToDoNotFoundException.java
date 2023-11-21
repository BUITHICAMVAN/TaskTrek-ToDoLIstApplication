package com.bycnit.mytodo.exceptions;

public class SubToDoNotFoundException extends Exception  {
    public SubToDoNotFoundException(Long id){
        super("Could not find sub to do of to do" + id);
    }
}

