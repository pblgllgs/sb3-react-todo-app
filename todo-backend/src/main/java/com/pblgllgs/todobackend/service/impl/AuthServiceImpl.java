package com.pblgllgs.todobackend.service.impl;
/*
 *
 * @author pblgl
 * Created on 27-02-2024
 *
 */

import com.pblgllgs.todobackend.dto.LoginDto;
import com.pblgllgs.todobackend.dto.RegisterDto;
import com.pblgllgs.todobackend.entity.Role;
import com.pblgllgs.todobackend.entity.User;
import com.pblgllgs.todobackend.exception.TodoApiException;
import com.pblgllgs.todobackend.repository.RoleRepository;
import com.pblgllgs.todobackend.repository.UserRepository;
import com.pblgllgs.todobackend.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    @Value("${messages.exceptions.user-details.email-already-exists}")
    private String messageEmailAlreadyExists;
    @Value("${messages.exceptions.user-details.username-already-exists}")
    private String messageUsernameAlreadyExists;
    @Value("${messages.services.user-details.create-success}")
    private String messageUserSuccessfullyCreated;
    @Value("${messages.services.user-details.login-success}")
    private String messageUserSuccessfullyLogin;


    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Override
    public Map<String, String> register(RegisterDto registerDto) {
        if (Boolean.TRUE.equals(userRepository.existsByEmail(registerDto.email()))) {
            throw new TodoApiException(messageEmailAlreadyExists);
        }
        if (Boolean.TRUE.equals(userRepository.existsByUsername(registerDto.username()))) {
            throw new TodoApiException(messageUsernameAlreadyExists);
        }
        User user = User.builder()
                .name(registerDto.name())
                .username(registerDto.username())
                .password(passwordEncoder.encode(registerDto.password()))
                .email(registerDto.email())
                .build();
        Set<Role> roles = new HashSet<>();
        Role roleUser = roleRepository.findByName("ROLE_USER");
        roles.add(roleUser);
        user.setRoles(roles);
        userRepository.save(user);
        return Map.of("message", messageUserSuccessfullyCreated);
    }

    @Override
    public Map<String, String> login(LoginDto loginDto) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.usernameOrEmail(),
                loginDto.password()
        ));
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        return Map.of("message", messageUserSuccessfullyLogin);
    }
}
