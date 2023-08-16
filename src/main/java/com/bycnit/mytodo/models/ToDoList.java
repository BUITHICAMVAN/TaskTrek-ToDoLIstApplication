package com.bycnit.mytodo.models;

import jakarta.persistence.*;

@Entity
@Table
public class ToDoList {
    
    @Id
    @SequenceGenerator(name = "todolist_sequence", sequenceName = "todolist_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "todolist_sequence")
    @Column(name="id",updatable=false)
    private Long id;
    
    @Column(name="title", nullable=false)
    private String title;
    
    @Column(name="description", nullable=false)
    private String description;
    
    @Column(name="priority", nullable=false)
    private String priority;
    
    public ToDoList() {
    }

    public ToDoList(Long id, String title, String description, String priority) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.priority = priority;
    }
    
    public ToDoList(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    @Override
    public String toString() {
        return "ToDoList [id=" + id + ", title=" + title + ", description=" + description + ", priority=" + priority + "]";
    }
}
