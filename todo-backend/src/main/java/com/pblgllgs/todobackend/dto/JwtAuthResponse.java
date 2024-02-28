package com.pblgllgs.todobackend.dto;


public record JwtAuthResponse(String accessToken, String tokenType, String role) {
}
