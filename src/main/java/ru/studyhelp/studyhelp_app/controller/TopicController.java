package ru.studyhelp.studyhelp_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.studyhelp.studyhelp_app.dto.TopicDto;
import ru.studyhelp.studyhelp_app.dto.TopicResponse;
import ru.studyhelp.studyhelp_app.service.TopicService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/topics")
public class TopicController {
    private TopicService topicService;

    @Autowired
    public TopicController(TopicService topicService) {
        this.topicService = topicService;
    }

    @GetMapping()
    public ResponseEntity<TopicResponse> getTopics(
            @RequestParam(value = "pageNum", defaultValue = "0", required = false) int pageNum,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize
    ) {
        TopicResponse response = topicService.getAll(pageNum, pageSize);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TopicDto> getTopicById(@PathVariable("id") long topicId) {
        TopicDto topicDto = topicService.getById(topicId);
        return new ResponseEntity<>(topicDto, HttpStatus.OK);
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<TopicDto> createTopic(@RequestBody TopicDto topicDto) {
        TopicDto newTopic = topicService.create(topicDto);
        return new ResponseEntity<>(newTopic, HttpStatus.OK);
    }

    @PutMapping("/{id}/update")
    public ResponseEntity<TopicDto> updateTopic(@PathVariable("id") long topicId,
                                                @RequestBody TopicDto topicDto) {
        TopicDto newTopic = topicService.updateById(topicId, topicDto);
        return new ResponseEntity<>(newTopic, HttpStatus.OK);
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<String> deletePokemon(@PathVariable("id") long topicId) {
        topicService.deleteById(topicId);
        return new ResponseEntity<>("Topic delete", HttpStatus.OK);
    }
}