package com.pblgllgs.todobackend.dto;

public record TodoDto(
        Long id,
        String title,
        String description,
        boolean completed
) {
}
