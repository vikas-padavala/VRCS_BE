package com.vrcs.livemenu.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ItemCategoryNotFound extends RuntimeException {

    public ItemCategoryNotFound(String message) {
        super(message);
    }

}
