package ru.studyhelp.studyhelp_app.mapper;

import ru.studyhelp.studyhelp_app.dto.QuestionDto;
import ru.studyhelp.studyhelp_app.entity.Question;

public class QuestionMapper {
    public static Question mapToQuestion(QuestionDto questionDto) {
        Question question = Question.builder()
                .text(questionDto.getText())
                .answer(questionDto.getAnswer())
                .build();
        return question;
    }

    public static QuestionDto mapToQuestionDto(Question question) {
        QuestionDto questionDto = QuestionDto.builder()
                .id(question.getId())
                .text(question.getText())
                .answer(question.getAnswer())
                .topicId(question.getTopic().getId())
                .build();
        return questionDto;
    }
}