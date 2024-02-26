package com.pblgllgs.todobackend.mapper;
/*
 *
 * @author pblgl
 * Created on 26-02-2024
 *
 */

import com.pblgllgs.todobackend.dto.TodoDto;
import com.pblgllgs.todobackend.entity.Todo;

public class TodoMapper {

    public static TodoDto todoToTodoDto(Todo todo){
        return new TodoDto(
                todo.getId(),
                todo.getTitle(),
                todo.getDescription(),
                todo.isCompleted()
        );
    }

    public static Todo todoDtoToTodo(TodoDto todoDto){
        return new Todo(todoDto.id(),todoDto.title(),todoDto.description(),todoDto.completed());
    }
}
