package com.springboot.cruddemo.controller;

import com.springboot.cruddemo.dto.TodoDto;
import com.springboot.cruddemo.entity.Todo;
import com.springboot.cruddemo.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/todos")
public class TodoController {

    private TodoService todoService;

    // Constructor Injection
    @Autowired
    public TodoController(TodoService todoService) {
        this.todoService=todoService;
    }

    // Build Add Todo REST API
    @PostMapping
    public ResponseEntity<TodoDto> addTodo(@RequestBody TodoDto todoDto) {

        TodoDto savedTodo = todoService.addTodo(todoDto);

        return new ResponseEntity<>(savedTodo, HttpStatus.CREATED);
    }


    // Build Get TODO REST API
    @GetMapping("{todoId}")
    public ResponseEntity<TodoDto> getTodo(@PathVariable Long todoId) {
        TodoDto todoDto = todoService.getTodo(todoId);

        return new ResponseEntity<>(todoDto, HttpStatus.OK);
    }

    // Build Get All Todos REST API
    @GetMapping
    public ResponseEntity<List<TodoDto>> getAllTodos() {
        List<TodoDto> todos = todoService.getAllTodos();

    //    return new ResponseEntity<>(todos, HttpStatus.OK);

        return ResponseEntity.ok(todos);
    }

    // Build update Todo REST API
    @PutMapping("{id}")
    public ResponseEntity<TodoDto> updateTodo(@RequestBody TodoDto todoDto, @PathVariable("id") Long id) {
       TodoDto updatedTodo =  todoService.updateTodo(todoDto, id);

       return ResponseEntity.ok(updatedTodo);
    }

    // Build Delete Todo REST API
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteTodo(@PathVariable("id") Long todoId) {
        todoService.deleteTodo(todoId);

        return ResponseEntity.ok("Todo deleted successfully");
    }

    // Build complete Todo REST API
    @PatchMapping("{id}/complete")
    public ResponseEntity<TodoDto> completeTodo(@PathVariable("id") Long todoId) {

      TodoDto updatedTodo =  todoService.completeTodo(todoId);

      return ResponseEntity.ok(updatedTodo);
    }

    // Build In Complete Todo REST API
    @PatchMapping("{id}/inComplete")
    public ResponseEntity<TodoDto> inCompleteTodo(@PathVariable("id") Long todoId) {

        TodoDto updatedTodo = todoService.inCompleteTodo(todoId);

        return ResponseEntity.ok(updatedTodo);
    }


}
