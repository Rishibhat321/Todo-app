package com.springboot.cruddemo.service.impl;

import com.springboot.cruddemo.dto.TodoDto;
import com.springboot.cruddemo.entity.Todo;
import com.springboot.cruddemo.exception.ResourceNotFoundException;
import com.springboot.cruddemo.repository.TodoRepository;
import com.springboot.cruddemo.service.TodoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.module.ResolutionException;
import java.nio.file.ReadOnlyFileSystemException;
import java.util.List;
import java.util.stream.Collectors;

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
        List<Todo> todos = todoRepository.findAll();

        // convert to list of TodoDtos

        return todos.stream().map((todo) -> modelMapper.map(todo, TodoDto.class))
                .collect(Collectors.toList());
    }


    @Override
    public TodoDto updateTodo(TodoDto todoDto, Long id) {

       Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Todo not found with id- " + id));

       todo.setTitle(todoDto.getTitle());
       todo.setDescription(todoDto.getDescription());
       todo.setCompleted(todoDto.isCompleted());

       // save method performs both insert and update operation
      Todo updatedTodo =  todoRepository.save(todo);

      // convert updated jpa entity to todo dto
        return modelMapper.map(updatedTodo, TodoDto.class);

    }


    @Override
    public void deleteTodo(Long id) {

       Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Todo not found with id - " + id));

       todoRepository.deleteById(id);
    }


    @Override
    public TodoDto completeTodo(Long id) {

        // change the status of the todo object
      Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Todo not found with id : " + id));

      todo.setCompleted(Boolean.TRUE);

       Todo updatedTodo =  todoRepository.save(todo);

        return modelMapper.map(updatedTodo, TodoDto.class);
    }

    @Override
    public TodoDto inCompleteTodo(Long id) {

        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Todo not found with id : " + id));

        todo.setCompleted(Boolean.FALSE);

        Todo updatedTodo = todoRepository.save(todo);

        return modelMapper.map(updatedTodo, TodoDto.class);
    }

}
