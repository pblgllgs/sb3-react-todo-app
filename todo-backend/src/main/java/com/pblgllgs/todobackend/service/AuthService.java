package com.pblgllgs.todobackend.service;

import com.pblgllgs.todobackend.dto.JwtAuthResponse;
import com.pblgllgs.todobackend.dto.LoginDto;
import com.pblgllgs.todobackend.dto.RegisterDto;

import java.util.Map;

public interface AuthService {

    Map<String, String> register(RegisterDto registerDto);
    JwtAuthResponse login(LoginDto loginDto);
}
