package com.felixherder.ftotbe.exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class UsernameConflictException extends RuntimeException {

    public UsernameConflictException(String message) {
        super(message);
    }
}
