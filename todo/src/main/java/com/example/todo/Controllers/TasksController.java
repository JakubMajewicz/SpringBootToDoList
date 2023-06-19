package com.example.todo.Controllers;

import com.example.todo.Models.Task;
import com.example.todo.Services.TasksService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("tasks")
public class TasksController {

    private TasksService tasksService;

    @Autowired
    public TasksController(TasksService tasksService){
        this.tasksService = tasksService;
    }

    @GetMapping
    public ResponseEntity<List<Task>> showTasks(HttpServletRequest request){
        tasksService.incrementCount(request);
        List<Task> tasks = tasksService.showTasks();
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Task>> showOneTask(HttpServletRequest request, @PathVariable("id") Long id){
        tasksService.incrementCount(request);
        Optional<Task> task = tasksService.findById(id);
        return new ResponseEntity<>(task, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Task> addTask(HttpServletRequest request, @Valid @RequestBody Task task){
        tasksService.incrementCount(request);
        Task newTask = tasksService.addTask(task);
        return new ResponseEntity<>(newTask, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(HttpServletRequest request, @PathVariable("id") Long id, @Valid @RequestBody Task task) {
        tasksService.incrementCount(request);
        Task updateTask = tasksService.updateTask(id, task);
        return new ResponseEntity<>(updateTask, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTask(HttpServletRequest request,@PathVariable("id") Long id){
        tasksService.incrementCount(request);
        tasksService.deleteTask(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/status")
    public ResponseEntity<Map> getStatusMetric(HttpServletRequest request){
        tasksService.incrementCount(request);
        Map tasksStatus = tasksService.getAllCounts();
        return new ResponseEntity<>(tasksStatus, HttpStatus.OK);
    }



}

