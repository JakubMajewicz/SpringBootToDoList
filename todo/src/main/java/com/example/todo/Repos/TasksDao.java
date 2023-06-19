package com.example.todo.Repos;

import com.example.todo.Models.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TasksDao extends JpaRepository<Task, Long> {
Task findTaskById(Long id);
}

