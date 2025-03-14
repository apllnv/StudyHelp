package ru.studyhelp.studyhelp_app.dto;

import lombok.Data;

import java.util.List;

@Data
public class TopicResponse {
    private List<TopicDto> content;
    private int pageNum;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean isLast;
}