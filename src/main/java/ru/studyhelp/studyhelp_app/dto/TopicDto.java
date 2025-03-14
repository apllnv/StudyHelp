package ru.studyhelp.studyhelp_app.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TopicDto {
    private Long id;
    private String title;
    private List<QuestionDto> questionList = new ArrayList<>();
}