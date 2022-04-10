package com.magazin.demo.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.function.Predicate;

@Service
@AllArgsConstructor
public class EmailValidatorImpl implements Predicate<String> {

    @Override
    public boolean test(String s) {
        return true;
    }
}
