package com.pblgllgs.todobackend.exception;
/*
 *
 * @author pblgl
 * Created on 27-02-2024
 *
 */

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class TodoApiException extends RuntimeException {
    public TodoApiException(String message) {
        super(message);
    }
}
