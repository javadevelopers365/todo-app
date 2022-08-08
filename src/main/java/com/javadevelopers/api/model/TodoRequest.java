package com.javadevelopers.api.model;

import lombok.Data;

@Data
public class TodoRequest {
    private String name;
    private String category;
    private Boolean isCompleted;
}
