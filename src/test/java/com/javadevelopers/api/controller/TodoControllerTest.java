package com.javadevelopers.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javadevelopers.api.TestData;
import com.javadevelopers.api.model.TodoRequest;
import com.javadevelopers.api.model.TodoResponse;
import com.javadevelopers.api.service.TodoService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class TodoControllerTest {

    @Autowired
    private MockMvc mockMvc;
    private ObjectMapper mapper = new ObjectMapper();
    @MockBean
    private TodoService todoService;

    @Test
    public void testSaveTodo() throws Exception {
        TodoRequest todoRequest = TestData.getTodoRequest();
        TodoResponse todoResponse = TestData.getTodoResponse();
        when(todoService.saveTodo(todoRequest)).thenReturn(todoResponse);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/v1/todo")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(todoRequest));

        MvcResult mvcResult = mockMvc.perform(requestBuilder)
                .andExpect(jsonPath("$.name", Matchers.is("Learn JUnit")))
                .andExpect(status().is(201))
                .andReturn();
    }

    @Test
    public void testFindTodoById() throws Exception {
        String todoId = "1";
        TodoResponse todoResponse = TestData.getTodoResponse();

        when(todoService.findTodoById(todoId)).thenReturn(todoResponse);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/todo/{id}", todoId)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        MvcResult mvcResult = mockMvc.perform(requestBuilder)
                .andExpect(jsonPath("$.name", Matchers.is("Learn JUnit")))
                .andExpect(status().is(200))
                .andReturn();
    }
 }