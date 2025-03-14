package ru.studyhelp.studyhelp_app.dto;

import lombok.Data;

import java.util.List;

@Data
public class QuestionResponse {
    private List<QuestionDto> content;
    private int pageNum;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean isLast;
}
