package ru.studyhelp.studyhelp_app.exceptions;

import lombok.Data;

import java.util.Date;

@Data
public class ErrorObject {
    private Integer statusCode;
    private String massage;
    private Date timestamp;
}
