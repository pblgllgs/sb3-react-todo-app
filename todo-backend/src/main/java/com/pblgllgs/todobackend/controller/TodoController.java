package com.pblgllgs.todobackend.controller;

import com.pblgllgs.todobackend.dto.TodoDto;
import com.pblgllgs.todobackend.service.ITodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
@CrossOrigin("*")
public class TodoController {
    @Value("${messages.controllers.todo.deleted-success}")
    private String messageDeletedSuccessfully;

    private final ITodoService iTodoService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<TodoDto> createTodo(@RequestBody TodoDto todoDto) {
        return new ResponseEntity<>(iTodoService.createTodo(todoDto), HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/{todoId}")
    public ResponseEntity<TodoDto> findTodoById(@PathVariable("todoId") Long todoId) {
        return new ResponseEntity<>(iTodoService.findTodoById(todoId), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping
    public ResponseEntity<List<TodoDto>> findAllTodos() {
        return new ResponseEntity<>(iTodoService.findAllTodos(), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{todoId}")
    public ResponseEntity<TodoDto> updateTodoById(@PathVariable("todoId") Long todoId, @RequestBody TodoDto todoDto) {
        return new ResponseEntity<>(iTodoService.updateTodo(todoId, todoDto), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{todoId}")
    public ResponseEntity<String> deleteTodoById(@PathVariable("todoId") Long todoId) {
        iTodoService.deleteTodo(todoId);
        return new ResponseEntity<>(messageDeletedSuccessfully + todoId,HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @PatchMapping("/{todoId}/complete-incomplete")
    public ResponseEntity<TodoDto> changeStatus(@PathVariable("todoId") Long todoId) {
        return new ResponseEntity<>(iTodoService.changeStatus(todoId), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @PatchMapping("/{todoId}/complete")
    public ResponseEntity<TodoDto> changeStatusComplete(@PathVariable("todoId") Long todoId) {
        return new ResponseEntity<>(iTodoService.changeStatusComplete(todoId), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @PatchMapping("/{todoId}/incomplete")
    public ResponseEntity<TodoDto> changeStatusInComplete(@PathVariable("todoId") Long todoId) {
        return new ResponseEntity<>(iTodoService.changeStatusIncomplete(todoId), HttpStatus.OK);
    }

}
