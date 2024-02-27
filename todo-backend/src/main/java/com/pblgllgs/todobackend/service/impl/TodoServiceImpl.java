package com.pblgllgs.todobackend.service.impl;
/*
 *
 * @author pblgl
 * Created on 26-02-2024
 *
 */

import com.pblgllgs.todobackend.dto.TodoDto;
import com.pblgllgs.todobackend.entity.Todo;
import com.pblgllgs.todobackend.exception.ResourceNotFoundException;
import com.pblgllgs.todobackend.mapper.TodoMapper;
import com.pblgllgs.todobackend.repository.TodoRepository;
import com.pblgllgs.todobackend.service.ITodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoServiceImpl implements ITodoService {

    @Value("${messages.exceptions.todo.not-found}")
    private String messageTodoNotFound;
    private final TodoRepository todoRepository;

    @Override
    public TodoDto createTodo(TodoDto todoDto) {
        Todo todo = TodoMapper.todoDtoToTodo(todoDto);
        Todo todoSaved = todoRepository.save(todo);
        return TodoMapper.todoToTodoDto(todoSaved);
    }

    @Override
    public TodoDto findTodoById(Long todoId) {
        Todo todo = todoRepository
                .findById(todoId)
                .orElseThrow(
                        () -> new ResourceNotFoundException(messageTodoNotFound + todoId)
                );
        return TodoMapper.todoToTodoDto(todo);
    }

    @Override
    public List<TodoDto> findAllTodos() {
        return todoRepository.findAll()
                .stream()
                .map(TodoMapper::todoToTodoDto)
                .toList();
    }

    @Override
    public TodoDto updateTodo(Long todoId, TodoDto todoDto) {
        Todo todo = todoRepository
                .findById(todoId)
                .orElseThrow(
                        () -> new ResourceNotFoundException(messageTodoNotFound + todoId)
                );
        todo.setTitle(todoDto.title());
        todo.setDescription(todoDto.description());
        todo.setCompleted(todoDto.completed());
        Todo todoUpdated = todoRepository.save(todo);
        return TodoMapper.todoToTodoDto(todoUpdated);
    }

    @Override
    public void deleteTodo(Long todoId) {
        Todo todo = todoRepository
                .findById(todoId)
                .orElseThrow(
                        () -> new ResourceNotFoundException(messageTodoNotFound + todoId)
                );
        todoRepository.deleteById(todo.getId());
    }

    @Override
    public TodoDto changeStatus(Long todoId) {
        Todo todo = todoRepository
                .findById(todoId)
                .orElseThrow(
                        () -> new ResourceNotFoundException(messageTodoNotFound + todoId)
                );
        todo.setCompleted(!todo.isCompleted());
        Todo todoCompleted = todoRepository.save(todo);
        return TodoMapper.todoToTodoDto(todoCompleted);
    }

    @Override
    public TodoDto changeStatusComplete(Long todoId) {
        Todo todo = todoRepository
                .findById(todoId)
                .orElseThrow(
                        () -> new ResourceNotFoundException(messageTodoNotFound + todoId)
                );
        todo.setCompleted(true);
        Todo todoCompleted = todoRepository.save(todo);
        return TodoMapper.todoToTodoDto(todoCompleted);
    }

    @Override
    public TodoDto changeStatusIncomplete(Long todoId) {
        Todo todo = todoRepository
                .findById(todoId)
                .orElseThrow(
                        () -> new ResourceNotFoundException(messageTodoNotFound + todoId)
                );
        todo.setCompleted(false);
        Todo todoCompleted = todoRepository.save(todo);
        return TodoMapper.todoToTodoDto(todoCompleted);
    }
}
