package com.springboot.cruddemo.service;

import com.springboot.cruddemo.dto.TodoDto;

public interface TodoService {

    TodoDto addTodo(TodoDto todoDto);

    TodoDto getTodo(Long id);

}
