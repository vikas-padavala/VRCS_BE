package com.vrcs.livemenu.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class UserNotFound extends RuntimeException {

    public UserNotFound(String message) {
        super(message);
    }

}
