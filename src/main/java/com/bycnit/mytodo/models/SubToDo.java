package com.bycnit.mytodo.models;

import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="sub_to_do")
public class SubToDo {
  
    @Id
    @SequenceGenerator(name = "sub_to_do_sequence", sequenceName = "sub_to_do_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sub_to_do_sequence")
    @Column(name="id", nullable=false)
    private Long id;
    
    @Column(name="description", nullable=false)
    @NotNull(message = "Description is required")
    private String description;
    
    @Column(name="status_code", nullable=false)
    @NotNull(message = "Task progress is required")
    private String statusCode;
    
    @Column(name="due_date", nullable=false)
    @NotNull(message = "Due date is required")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDateTime dueDate;

    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="toDo_id", referencedColumnName="id", nullable=false)
    private ToDo toDo;
    
    public SubToDo() {
        super();
    }

    public SubToDo(Long id, String description, String statusCode, LocalDateTime dueDate) {
        super();
        this.id = id;
        this.description = description;
        this.statusCode = statusCode;
        this.dueDate = dueDate;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ToDo getToDo() {
        return toDo;
    }

    public void setToDo(ToDo toDo) {
        this.toDo = toDo;
    }
    
    @Override
    public String toString() {
        return "SubToDo [id=" + id + ", description=" + description + ", statusCode=" + statusCode + ", dueDate=" + dueDate
                        + ", toDo=" + toDo + "]";
    }
}
