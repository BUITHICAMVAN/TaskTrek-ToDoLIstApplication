package com.bycnit.mytodo.models;

import java.util.List;
import jakarta.persistence.*;

@Entity 
@Table(name="to_do")
public class ToDo {
    
    @Id
    @SequenceGenerator(name = "todo_sequence", sequenceName = "todo_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "todo_sequence")
    @Column(name="id",updatable=false)
    private Long id;
    
    @Column(name="title", nullable=false)
    private String title;
    
    @Column(name="description", nullable=false)
    private String description;
    
    @Column(name="priority", nullable=false)
    private String priority;
    
    @Column(name="sub_to_do")
//    must map the name that defined in ManyToOne relationship
    @OneToMany(fetch=FetchType.LAZY, mappedBy="toDo",cascade = {CascadeType.PERSIST,CascadeType.REMOVE})
    private List<SubToDo> subToDos;
    
    public ToDo() {
    }

    public ToDo(Long id, String title, String description, String priority, List<SubToDo> subToDos) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.subToDos = subToDos;
    }
    
    public ToDo(String title, String description) {
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

    
    public List<SubToDo> getAllSubToDo() {
        return subToDos;
    }

    @Override
    public String toString() {
        return "ToDoList [id=" + id + ", title=" + title + ", description=" + description + ", priority=" + priority + "]";
    }
}
