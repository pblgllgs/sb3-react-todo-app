package com.pblgllgs.todobackend.repository;

import com.pblgllgs.todobackend.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo,Long> {
}
