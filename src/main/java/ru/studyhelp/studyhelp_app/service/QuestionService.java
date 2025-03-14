package ru.studyhelp.studyhelp_app.service;

import ru.studyhelp.studyhelp_app.dto.QuestionDto;
import ru.studyhelp.studyhelp_app.dto.QuestionResponse;

import java.util.List;

public interface QuestionService {
    QuestionDto create(Long topicId, QuestionDto topicDto);

    QuestionResponse getAll(int pageNum, int pageSize);

    QuestionResponse getAllInTopic(Long topicId, int pageNum, int pageSize);

    QuestionDto getByIdAndTopicId(Long topicId, Long questionId);

    void deleteByIdAndTopicId(Long topicId, Long id);

    QuestionDto updateByIdAndTopicId(Long topicId, Long questionId, QuestionDto questionDto);
}
