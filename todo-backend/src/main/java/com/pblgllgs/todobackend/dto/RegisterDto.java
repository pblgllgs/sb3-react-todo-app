package com.pblgllgs.todobackend.dto;
/*
 *
 * @author pblgl
 * Created on 27-02-2024
 *
 */

public record RegisterDto(
        String name,
        String username,
        String email,
        String password) {
}
