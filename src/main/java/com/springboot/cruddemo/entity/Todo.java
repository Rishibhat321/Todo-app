package com.springboot.cruddemo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "todos")
public class Todo {

    // In-case the column name is not provided, jpa will use the field name as the column name
    // define fields

 /* provides primary key as auto increment */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    // Not Null
    @Column(name = "title", nullable = false)
    private String title;

    // Not Null
    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "completed")
    private boolean completed;

    // define constructors

    public Todo() {}

    public Todo(boolean completed, String description, String title) {
        this.completed = completed;
        this.description = description;
        this.title = title;
    }

    // define getters/setters

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

    // define toString method

    @Override
    public String toString() {
        return "Todo{" +
                "completed=" + completed +
                ", id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

}
