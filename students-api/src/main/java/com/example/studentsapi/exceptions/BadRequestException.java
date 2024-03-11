package com.example.studentsapi.exceptions;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class BadRequestException extends Exception {

    private final List<String> badFiels;

    public BadRequestException(String message, List<String> badFiels) {
        super(message);
        this.badFiels = Collections.unmodifiableList(badFiels);
    }

    public List<String> getBadFiels() {
        return badFiels;
    }

}
