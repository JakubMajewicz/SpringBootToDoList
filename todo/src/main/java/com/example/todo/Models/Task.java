package com.example.todo.Models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


@Entity
@Table(name = "task")
public class Task {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Task name is required")
    @Size(max = 100, message = "Task name must be less than 100 characters")
    private String task_name;
    @NotBlank(message = "Description is required")
    @Size(max = 225, message = "Description must be less than 225 characters")
    private String description;

    public Task(){
        super();
    }
    public Task (@JsonProperty("id") Long id,
                 @JsonProperty("task_name") String task_name,
                 @JsonProperty("description") String description){

        this.id = id;
        this.task_name = task_name;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTask_name() {
        return task_name;
    }

    public void setTask_name(String task_name) {
        this.task_name = task_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
