package ru.studyhelp.studyhelp_app.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorDiscriptor {
    TOPIC_NOT_FOUND("Топик не найден", HttpStatus.NOT_FOUND),
    QUESTION_NOT_FOUND("Вопрос не найден", HttpStatus.NOT_FOUND);

    private final String message;
    private final HttpStatus httpStatus;

    ErrorDiscriptor(String message, HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
    }

}