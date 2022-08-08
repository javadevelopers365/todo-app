package com.javadevelopers.api.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javadevelopers.api.entity.Todo;
import com.javadevelopers.api.model.TodoRequest;
import com.javadevelopers.api.model.TodoResponse;
import com.javadevelopers.api.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TodoService {

    private TodoRepository todoRepository;
    private ObjectMapper mapper = new ObjectMapper();

    @Autowired
    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public TodoResponse saveTodo(TodoRequest todoRequest) {
        Todo todo = new Todo();
        todo.setName(todoRequest.getName());
        todo.setCategory(todoRequest.getCategory());
        todo.setIsCompleted(todoRequest.getIsCompleted());
        Todo savedTodo = todoRepository.save(todo);
        return mapper.convertValue(savedTodo, TodoResponse.class);
    }

    // Get the todo by id
    public TodoResponse findTodoById(String id) throws Exception {
        Optional<Todo> optionalTodo = todoRepository.findById(Long.valueOf(id));
        if (optionalTodo.isPresent()) {
            Todo todo = optionalTodo.get();
            return mapper.convertValue(todo, TodoResponse.class);
        } else {
            throw new Exception("Requested Todo is not presend in the database.");
        }
    }

    // Get all the todos
    public List<TodoResponse> findAllTodo() {
        List<TodoResponse> responses = new ArrayList<>();
        todoRepository.findAll().forEach(todo -> responses.add(mapper.convertValue(todo, TodoResponse.class)));
        return responses;
    }

    public TodoResponse updateTodoById(String id, TodoRequest todoRequest) throws Exception {
        Optional<Todo> optionalTodo = todoRepository.findById(Long.valueOf(id));
        if (optionalTodo.isPresent()) {
            Todo todo = optionalTodo.get();
            todo.setName(todoRequest.getName());
            todo.setCategory(todoRequest.getCategory());
            todo.setIsCompleted(todoRequest.getIsCompleted());
            Todo savedTodo = todoRepository.save(todo);
            return mapper.convertValue(savedTodo, TodoResponse.class);
        } else {
            throw new Exception("Data is not present in the database.");
        }
    }

    public Long deleteById(String id) {
        todoRepository.deleteById(Long.valueOf(id));
        return Long.valueOf(id);
    }
}
