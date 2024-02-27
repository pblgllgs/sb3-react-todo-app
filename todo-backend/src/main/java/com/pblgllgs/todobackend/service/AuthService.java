package com.pblgllgs.todobackend.service;

import com.pblgllgs.todobackend.dto.LoginDto;
import com.pblgllgs.todobackend.dto.RegisterDto;

import java.util.Map;

public interface AuthService {

    Map<String, String> register(RegisterDto registerDto);
    Map<String, String> login(LoginDto loginDto);
}
