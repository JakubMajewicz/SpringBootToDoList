package com.example.todo.Services;

import com.example.todo.Models.Task;
import com.example.todo.Repos.TasksDao;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class TasksService {
    @Autowired
    private TasksDao tasksDao;

    private static Map<String, Integer> requestCountMap = new HashMap<>();

    public void incrementCount(HttpServletRequest request) {
        String currentUrl = request.getMethod() + " " + request.getRequestURI();
        int count = requestCountMap.getOrDefault(currentUrl, 0);
        requestCountMap.put(currentUrl, count + 1);
    }

    public Map<String, Integer> getAllCounts() {
        return requestCountMap;
    }

    public TasksService(TasksDao tasksRepo){
        this.tasksDao = tasksRepo;
    }

    public List<Task> showTasks(){
        return tasksDao.findAll();
    }

    public <S extends Task> S addTask(S entity){
        return tasksDao.save(entity);
    }

    public void deleteTask(Long id){
        tasksDao.deleteById(id);
    }

    public Optional<Task> findById(Long id){
        return tasksDao.findById(id);
    }

    public Task updateTask(Long id, Task task){
        Task updateTask = tasksDao.findTaskById(id);
        updateTask.setTask_name(task.getTask_name());
        updateTask.setDescription(task.getDescription());
       return tasksDao.save(updateTask);
    }
}
