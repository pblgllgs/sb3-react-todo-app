package com.pblgllgs.todobackend.utils;
/*
 *
 * @author pblgl
 * Created on 27-02-2024
 *
 */

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordEncoderImpl {
    public static void main(String[] args) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        System.out.println(passwordEncoder.encode("user"));
        System.out.println(passwordEncoder.encode("admin"));
    }
}
