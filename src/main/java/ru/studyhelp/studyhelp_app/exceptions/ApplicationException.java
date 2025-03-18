package ru.studyhelp.studyhelp_app.exceptions;

import lombok.Getter;

@Getter
public class ApplicationException extends RuntimeException {

    private final ErrorDiscriptor discriptor;

    public ApplicationException(ErrorDiscriptor discriptor) {
        super(discriptor.getMessage());
        this.discriptor = discriptor;
    }
}
