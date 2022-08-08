package com.javadevelopers.api.controller;

import com.javadevelopers.api.model.TodoRequest;
import com.javadevelopers.api.model.TodoResponse;
import com.javadevelopers.api.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/todo")
public class TodoController {

    private TodoService todoService;

    @Autowired
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @PostMapping
    public ResponseEntity<TodoResponse> saveTodo(@RequestBody TodoRequest todoRequest) {
        TodoResponse response = todoService.saveTodo(todoRequest);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TodoResponse> findById(@PathVariable("id") String id) throws Exception {
        TodoResponse response = todoService.findTodoById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<TodoResponse>> findAll() {
        List<TodoResponse> responses = todoService.findAllTodo();
        return new ResponseEntity<>(responses, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TodoResponse> updateById(@PathVariable("id") String id, @RequestBody TodoRequest todoRequest) throws Exception {
        TodoResponse response = todoService.updateTodoById(id, todoRequest);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") String id) {
        Long todoId = todoService.deleteById(id);
        Map<String, Long> result = new HashMap<>();
        result.put("id", todoId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
