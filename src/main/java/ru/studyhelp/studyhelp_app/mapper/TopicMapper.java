package ru.studyhelp.studyhelp_app.mapper;

import ru.studyhelp.studyhelp_app.dto.TopicDto;
import ru.studyhelp.studyhelp_app.entity.Topic;

import java.util.Collections;

import static ru.studyhelp.studyhelp_app.mapper.QuestionMapper.mapToQuestionDto;

public class TopicMapper {
    public static Topic mapToTopic(TopicDto topicDto) {
        Topic topic = Topic.builder()
                .title(topicDto.getTitle())
                .build();
        return topic;
    }

    public static TopicDto mapToTopicDto(Topic topic) {
        TopicDto topicDto = TopicDto.builder()
                .id(topic.getId())
                .title(topic.getTitle())
                .questionList(topic.getQuestionList() != null
                        ? topic.getQuestionList().stream()
                        .map(QuestionMapper::mapToQuestionDto)
                        .toList()
                        : Collections.emptyList())
                .build();
        return topicDto;
    }
}