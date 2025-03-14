package ru.studyhelp.studyhelp_app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.studyhelp.studyhelp_app.dto.TopicDto;
import ru.studyhelp.studyhelp_app.dto.TopicResponse;
import ru.studyhelp.studyhelp_app.entity.Topic;
import ru.studyhelp.studyhelp_app.exceptions.TopicNotFoundException;
import ru.studyhelp.studyhelp_app.repository.TopicRepository;
import ru.studyhelp.studyhelp_app.service.TopicService;

import java.util.List;

import static ru.studyhelp.studyhelp_app.mapper.TopicMapper.mapToTopic;
import static ru.studyhelp.studyhelp_app.mapper.TopicMapper.mapToTopicDto;

@Service
public class TopicServiceImpl implements TopicService {
    private TopicRepository topicRepository;

    @Autowired
    public TopicServiceImpl(TopicRepository topicRepository) {
        this.topicRepository = topicRepository;
    }


    @Override
    public TopicDto create(TopicDto topicDto) {
        Topic topic = Topic.builder()
                .title(topicDto.getTitle())
                .build();
        Topic newTopic = topicRepository.save(topic);
        return mapToTopicDto(newTopic);
    }

    @Override
    public TopicResponse getAll(int pageNum, int pageSize) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        Page<Topic> page = topicRepository.findAll(pageable);
        List<TopicDto> listOfTopicsDto = page.getContent().stream()
                .map(t -> mapToTopicDto(t))
                .toList();

        TopicResponse topicResponse = new TopicResponse();
        topicResponse.setContent(listOfTopicsDto);
        topicResponse.setPageNum(pageNum);
        topicResponse.setPageSize(pageSize);
        topicResponse.setTotalPages(page.getTotalPages());
        topicResponse.setTotalElements(page.getTotalElements());
        topicResponse.setLast(page.isLast());

        return topicResponse;
    }

    @Override
    public TopicDto getById(Long topicId) {
        Topic topic = topicRepository.findById(topicId).orElseThrow(() -> new TopicNotFoundException("Topic could not be found"));
        return mapToTopicDto(topic);
    }

    @Override
    public void deleteById(Long id) {
        Topic topic = topicRepository.findById(id).orElseThrow(() -> new TopicNotFoundException("Topic could not be found"));
        topicRepository.deleteById(id);
    }

    @Override
    public TopicDto updateById(Long topicId, TopicDto topicDto) {
        Topic topic = topicRepository.findById(topicId).orElseThrow(() -> new TopicNotFoundException("Topic could not be found"));

        Topic res = mapToTopic(topicDto);
        res.setQuestionList(topic.getQuestionList());

        Topic newTopic = topicRepository.save(topic);
        return mapToTopicDto(newTopic);
    }
}