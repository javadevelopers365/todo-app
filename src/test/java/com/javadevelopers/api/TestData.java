package com.javadevelopers.api;

import com.javadevelopers.api.model.TodoRequest;
import com.javadevelopers.api.model.TodoResponse;

import java.util.Arrays;
import java.util.List;

public class TestData {

    public static TodoRequest getTodoRequest() {
        TodoRequest todoRequest = new TodoRequest();
        todoRequest.setName("Learn JUnit");
        todoRequest.setCategory("Programming");
        todoRequest.setIsCompleted(false);
        return todoRequest;
    }

    public static TodoResponse getTodoResponse() {
        TodoResponse todoResponse = new TodoResponse();
        todoResponse.setId(1L);
        todoResponse.setName("Learn JUnit");
        todoResponse.setCategory("Programming");
        todoResponse.setIsCompleted(false);
        return todoResponse;
    }

    public static List<TodoResponse> getTodoResponses() {
        return Arrays.asList(
                new TodoResponse(1L, "Learn AWS Cloud", "Cloud", false),
                new TodoResponse(2L, "Learn Azure Cloud", "Cloud", false),
                new TodoResponse(3L, "Learn GCP Cloud", "Cloud", false)
        );
    }
}
