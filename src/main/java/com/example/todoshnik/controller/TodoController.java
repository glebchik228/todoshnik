package com.example.todoshnik.controller;

import com.example.todoshnik.model.Task;
import com.example.todoshnik.service.TodoService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todo")
@AllArgsConstructor
public class TodoController {
    private final TodoService todoService;

    @GetMapping
    public List<Task> findAll() {
        return todoService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> findById(@PathVariable Long id) {
        return todoService.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Task create(@RequestBody Task task) {
        return todoService.save(task);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> update(@PathVariable Long id, @RequestBody Task task) {
        return todoService.findById(id).map(oldTask -> {
            oldTask.setTitle(task.getTitle());
            oldTask.setCompleted(task.isCompleted());
            return ResponseEntity.ok(todoService.save(oldTask));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (todoService.findById(id).isPresent()) {
            todoService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
