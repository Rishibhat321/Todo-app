package com.springboot.cruddemo.dto;

public class TodoDto {

    // decouple the jpa entity with client
    //Dto to transfer data between client and server

    private Long id;
    private String title;
    private String description;
    private boolean completed;

    public TodoDto() {}

    public TodoDto(boolean completed, String description, Long id, String title) {
        this.completed = completed;
        this.description = description;
        this.id = id;
        this.title = title;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
