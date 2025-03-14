package ru.studyhelp.studyhelp_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.studyhelp.studyhelp_app.dto.QuestionDto;
import ru.studyhelp.studyhelp_app.dto.QuestionResponse;
import ru.studyhelp.studyhelp_app.service.QuestionService;
import ru.studyhelp.studyhelp_app.service.TopicService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/")
public class QuestionController {
    private QuestionService questionService;

    @Autowired
    public QuestionController(QuestionService questionService, TopicService topicService) {
        this.questionService = questionService;
    }

    @GetMapping("questions")
    public ResponseEntity<QuestionResponse> getQuestions(
            @RequestParam(value = "pageNum", defaultValue = "0", required = false) int pageNum,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize
    ) {
        QuestionResponse response = questionService.getAll(pageNum, pageSize);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("topics/{topicId}/questions")
    public ResponseEntity<QuestionResponse> getQuestionsInTopic(
            @PathVariable("topicId") Long topicId,
            @RequestParam(value = "pageNum", defaultValue = "0", required = false) int pageNum,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize
    ) {
        QuestionResponse response = questionService.getAllInTopic(topicId, pageNum, pageSize);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("topics/{topicId}/questions/{questionId}")
    public ResponseEntity<QuestionDto> getQuestionByIdAndTopicId(@PathVariable("topicId") Long topicId,
                                                                 @PathVariable("questionId") Long questionId) {
        QuestionDto questionDto = questionService.getByIdAndTopicId(topicId, questionId);
        return new ResponseEntity<>(questionDto, HttpStatus.OK);
    }

    @PostMapping("topics/{topicId}/questions/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<QuestionDto> createQuestion(@PathVariable("topicId") Long topicId,
                                                      @RequestBody QuestionDto questionDto) {
        QuestionDto newQuestion = questionService.create(topicId, questionDto);
        return new ResponseEntity<>(newQuestion, HttpStatus.OK);
    }

    @PutMapping("topics/{topicId}/questions/{questionId}/update")
    public ResponseEntity<QuestionDto> updateQuestion(@PathVariable("topicId") Long topicId,
                                                      @PathVariable("questionId") Long questionId,
                                                      @RequestBody QuestionDto questionDto) {
        QuestionDto newQuestion = questionService.updateByIdAndTopicId(topicId, questionId, questionDto);
        return new ResponseEntity<>(newQuestion, HttpStatus.OK);
    }

    @DeleteMapping("topics/{topicId}/questions/{questionId}/delete")
    public ResponseEntity<String> deletePokemon(@PathVariable("topicId") Long topicId,
                                                @PathVariable("questionId") Long questionId) {
        questionService.deleteByIdAndTopicId(topicId, questionId);
        return new ResponseEntity<>("Question delete", HttpStatus.OK);
    }
}