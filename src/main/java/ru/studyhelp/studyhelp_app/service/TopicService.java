package ru.studyhelp.studyhelp_app.service;

import ru.studyhelp.studyhelp_app.dto.TopicDto;
import ru.studyhelp.studyhelp_app.dto.TopicResponse;

import java.util.List;

public interface TopicService {
    TopicDto create(TopicDto topicDto);

    TopicResponse getAll(int pageNum, int pageSize);

    TopicDto getById(Long topicId);

    void deleteById(Long id);

    TopicDto updateById(Long topicId, TopicDto topicDto);
}