package com.pblgllgs.todobackend.controller;

import com.pblgllgs.todobackend.dto.TodoDto;
import com.pblgllgs.todobackend.service.ITodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
 *
 * @author pblgl
 * Created on 26-02-2024
 *
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/1.0/todos")
public class TodoController {
    @Value("${messages.controllers.todo.deleted-success}")
    private String messageDeletedSuccessfully;

    private final ITodoService iTodoService;

    @PostMapping
    public ResponseEntity<TodoDto> createTodo(@RequestBody TodoDto todoDto) {
        return new ResponseEntity<>(iTodoService.createTodo(todoDto), HttpStatus.CREATED);
    }

    @GetMapping("/{todoId}")
    public ResponseEntity<TodoDto> findTodoById(@PathVariable("todoId") Long todoId) {
        return new ResponseEntity<>(iTodoService.findTodoById(todoId), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<TodoDto>> findAllTodos() {
        return new ResponseEntity<>(iTodoService.findAllTodos(), HttpStatus.OK);
    }

    @PutMapping("/{todoId}")
    public ResponseEntity<TodoDto> updateTodoById(@PathVariable("todoId") Long todoId, @RequestBody TodoDto todoDto) {
        return new ResponseEntity<>(iTodoService.updateTodo(todoId, todoDto), HttpStatus.OK);
    }

    @DeleteMapping("/{todoId}")
    public ResponseEntity<String> deleteTodoById(@PathVariable("todoId") Long todoId) {
        iTodoService.deleteTodo(todoId);
        return new ResponseEntity<>(messageDeletedSuccessfully + todoId,HttpStatus.OK);
    }

    @PatchMapping("/{todoId}/complete-incomplete")
    public ResponseEntity<TodoDto> changeStatus(@PathVariable("todoId") Long todoId) {
        return new ResponseEntity<>(iTodoService.changeStatus(todoId), HttpStatus.OK);
    }

}