package com.magazin.demo.exception;

import springfox.documentation.spring.web.json.Json;

public class NotFoundException extends RuntimeException{
    public NotFoundException(String message) {
        super(message);
    }
}
