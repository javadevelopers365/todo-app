package com.javadevelopers.api.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javadevelopers.api.TestData;
import com.javadevelopers.api.entity.Todo;
import com.javadevelopers.api.model.TodoRequest;
import com.javadevelopers.api.model.TodoResponse;
import com.javadevelopers.api.repository.TodoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class TodoServiceTest {

    @Mock
    private TodoRepository todoRepository;
    private ObjectMapper mapper = new ObjectMapper();

    @InjectMocks
    private TodoService todoService;

    @Test
    public void testSaveTodo() {
        TodoRequest todoRequest = TestData.getTodoRequest();
        Todo savedTodo = mapper.convertValue(todoRequest, Todo.class);
        when(todoRepository.save(any(Todo.class))).thenReturn(savedTodo);

        TodoResponse responseTodo = todoService.saveTodo(todoRequest);
        Assertions.assertEquals(savedTodo.getName(), responseTodo.getName());
    }

    @Test
    public void testFindAll() {
        List<TodoResponse> todoResponses = TestData.getTodoResponses();
        List<Todo> todos = new ArrayList<>();
        todoResponses.forEach(todoResponse -> todos.add(mapper.convertValue(todoResponse, Todo.class)));
        when(todoRepository.findAll()).thenReturn(todos);

        List<TodoResponse> responses = todoService.findAllTodo();
        Assertions.assertEquals(todos.size(), responses.size());
    }

}