package com.springboot.cruddemo.service.impl;

import com.springboot.cruddemo.dto.TodoDto;
import com.springboot.cruddemo.entity.Todo;
import com.springboot.cruddemo.repository.TodoRepository;
import com.springboot.cruddemo.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TodoServiceImpl implements TodoService {

    private TodoRepository todoRepository;

    // Constructor Injection
    @Autowired
    public TodoServiceImpl(TodoRepository todoRepository) {
        this.todoRepository=todoRepository;
    }

    @Override
    public TodoDto addTodo(TodoDto todoDto) {

        // convert TodoDto into Todo jpa entity
        Todo todo = new Todo();
        todo.setTitle(todo.getTitle());
        todo.setDescription(todoDto.getDescription());
        todo.setCompleted(todoDto.isCompleted());

        // Todo Jpa entity
        Todo savedTodo = todoRepository.save(todo);

        // convert saved Todo Jpa entity object into TodoDto object
        TodoDto savedTodoDto = new TodoDto();
        savedTodoDto.setId(savedTodo.getId());
        savedTodoDto.setTitle(savedTodo.getTitle());
        savedTodoDto.setDescription(savedTodo.getDescription());
        savedTodoDto.setCompleted(savedTodo.isCompleted());

        return savedTodoDto;
    }

}
