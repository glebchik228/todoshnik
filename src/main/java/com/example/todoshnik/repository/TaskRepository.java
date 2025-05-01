package com.example.todoshnik.repository;

import com.example.todoshnik.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
