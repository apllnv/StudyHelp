package ru.studyhelp.studyhelp_app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.studyhelp.studyhelp_app.dto.QuestionDto;
import ru.studyhelp.studyhelp_app.dto.QuestionResponse;
import ru.studyhelp.studyhelp_app.dto.TopicDto;
import ru.studyhelp.studyhelp_app.dto.TopicResponse;
import ru.studyhelp.studyhelp_app.entity.Question;
import ru.studyhelp.studyhelp_app.entity.Topic;
import ru.studyhelp.studyhelp_app.exceptions.QuestionNotFoundException;
import ru.studyhelp.studyhelp_app.exceptions.TopicNotFoundException;
import ru.studyhelp.studyhelp_app.repository.QuestionRepository;
import ru.studyhelp.studyhelp_app.repository.TopicRepository;
import ru.studyhelp.studyhelp_app.service.QuestionService;

import java.util.List;

import static ru.studyhelp.studyhelp_app.mapper.QuestionMapper.mapToQuestion;
import static ru.studyhelp.studyhelp_app.mapper.QuestionMapper.mapToQuestionDto;
import static ru.studyhelp.studyhelp_app.mapper.TopicMapper.mapToTopicDto;

@Service
public class QuestionServiceImpl implements QuestionService {
    private TopicRepository topicRepository;
    private QuestionRepository questionRepository;

    @Autowired
    public QuestionServiceImpl(TopicRepository topicRepository,
                               QuestionRepository questionRepository) {
        this.topicRepository = topicRepository;
        this.questionRepository = questionRepository;
    }

    @Override
    public QuestionResponse getAll(int pageNum, int pageSize) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        Page<Question> page = questionRepository.findAll(pageable);
        List<QuestionDto> listOfQuestionDto = page.getContent().stream()
                .map(q -> mapToQuestionDto(q))
                .toList();

        QuestionResponse questionResponse = new QuestionResponse();
        questionResponse.setContent(listOfQuestionDto);
        questionResponse.setPageNum(pageNum);
        questionResponse.setPageSize(pageSize);
        questionResponse.setTotalPages(page.getTotalPages());
        questionResponse.setTotalElements(page.getTotalElements());
        questionResponse.setLast(page.isLast());

        return questionResponse;
    }

    @Override
    public QuestionResponse getAllInTopic(Long topicId, int pageNum, int pageSize) {
        Topic topic = topicRepository.findById(topicId).orElseThrow(() -> new TopicNotFoundException("Topic could not be found"));
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        Page<Question> page = questionRepository.findQuestionsByTopic(topicId, pageable);

        List<QuestionDto> listOfQuestionDto = page.getContent().stream()
                .map(q -> mapToQuestionDto(q))
                .toList();

        QuestionResponse questionResponse = new QuestionResponse();
        questionResponse.setContent(listOfQuestionDto);
        questionResponse.setPageNum(pageNum);
        questionResponse.setPageSize(pageSize);
        questionResponse.setTotalPages(page.getTotalPages());
        questionResponse.setTotalElements(page.getTotalElements());
        questionResponse.setLast(page.isLast());

        return questionResponse;
    }

    @Override
    public QuestionDto create(Long topicId, QuestionDto questionDto) {
        Topic topic = topicRepository.findById(topicId).orElseThrow(() -> new TopicNotFoundException("Topic could not be found"));
        Question question = Question.builder()
                .text(questionDto.getText())
                .answer(questionDto.getAnswer())
                .build();
        question.setTopic(topic);
        Question newQuestion = questionRepository.save(question);
        return mapToQuestionDto(newQuestion);
    }


    @Override
    public QuestionDto getByIdAndTopicId(Long topicId, Long questionId) {
        Topic topic = topicRepository.findById(topicId).orElseThrow(() -> new TopicNotFoundException("Topic could not be found"));
        Question question = questionRepository.findById(questionId).orElseThrow(() -> new QuestionNotFoundException("Question could not be found"));
        if (!topic.equals(question.getTopic())) {
            throw new TopicNotFoundException("Question not in this topic");
        }
        return mapToQuestionDto(question);
    }

    @Override
    public void deleteByIdAndTopicId(Long topicId, Long questionId) {
        Topic topic = topicRepository.findById(topicId).orElseThrow(() -> new TopicNotFoundException("Topic could not be found"));
        Question question = questionRepository.findById(questionId).orElseThrow(() -> new QuestionNotFoundException("Question could not be found"));
        if (!topic.equals(question.getTopic())) {
            throw new TopicNotFoundException("Question not in this topic");
        }
        questionRepository.deleteById(questionId);
    }

    @Override
    public QuestionDto updateByIdAndTopicId(Long topicId, Long questionId, QuestionDto questionDto) {
        Topic topic = topicRepository.findById(topicId).orElseThrow(() -> new TopicNotFoundException("Topic could not be found"));
        Question question = questionRepository.findById(questionId).orElseThrow(() -> new QuestionNotFoundException("Question could not be found"));
        if (!topic.equals(question.getTopic())) {
            throw new TopicNotFoundException("Question not in this topic");
        }
        question = mapToQuestion(questionDto);
        question.setId(questionId);
        question.setTopic(topic);
        Question newQuestion = questionRepository.save(question);
        return mapToQuestionDto(newQuestion);
    }
}