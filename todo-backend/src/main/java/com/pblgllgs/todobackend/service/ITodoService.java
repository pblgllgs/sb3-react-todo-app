package com.pblgllgs.todobackend.service;
/*
 *
 * @author pblgl
 * Created on 26-02-2024
 *
 */

import com.pblgllgs.todobackend.dto.TodoDto;

import java.util.List;

public interface ITodoService {

    TodoDto createTodo(TodoDto todoDto);
    TodoDto findTodoById(Long todoId);

    List<TodoDto> findAllTodos();

    TodoDto updateTodo(Long todoId,TodoDto todoDto);

    void deleteTodo(Long todoId);

    TodoDto changeStatus(Long todoId);

    TodoDto changeStatusComplete(Long todoId);

    TodoDto changeStatusIncomplete(Long todoId);
}
