package com.springboot.cruddemo.service.impl;

import com.springboot.cruddemo.dto.TodoDto;
import com.springboot.cruddemo.entity.Todo;
import com.springboot.cruddemo.exception.ResourceNotFoundException;
import com.springboot.cruddemo.repository.TodoRepository;
import com.springboot.cruddemo.service.TodoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoServiceImpl implements TodoService {

    private TodoRepository todoRepository;

    private ModelMapper modelMapper;

    // Constructor Injection
    @Autowired
    public TodoServiceImpl(TodoRepository todoRepository, ModelMapper modelMapper) {
        this.todoRepository=todoRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public TodoDto addTodo(TodoDto todoDto) {

        // convert TodoDto into Todo jpa entity
/*        Todo todo = new Todo();
        todo.setTitle(todoDto.getTitle());
        todo.setDescription(todoDto.getDescription());
        todo.setCompleted(todoDto.isCompleted());

 */

        Todo todo = modelMapper.map(todoDto, Todo.class);

        // Todo Jpa entity
        Todo savedTodo = todoRepository.save(todo);

        // convert saved Todo Jpa entity object into TodoDto object
  /*      TodoDto savedTodoDto = new TodoDto();
        savedTodoDto.setId(savedTodo.getId());
        savedTodoDto.setTitle(savedTodo.getTitle());
        savedTodoDto.setDescription(savedTodo.getDescription());
        savedTodoDto.setCompleted(savedTodo.isCompleted());

   */

        TodoDto savedTodoDto = modelMapper.map(savedTodo, TodoDto.class);

        return savedTodoDto;
    }


    @Override
    public TodoDto getTodo(Long id) {

        // retrieve from db
    //   Todo todo =  todoRepository.findById(id).get();

        Todo todo = todoRepository.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("Todo not found with id: " + id));

       // convert Todo jpa to TodoDto
        return modelMapper.map(todo, TodoDto.class);
    }


    @Override
    public List<TodoDto> getAllTodos() {
        return List.of();
    }

}
