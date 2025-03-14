package ru.studyhelp.studyhelp_app.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.studyhelp.studyhelp_app.entity.Question;
import ru.studyhelp.studyhelp_app.entity.Topic;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    @Query("SELECT q FROM Question q WHERE q.topic.id = :topicId")
    Page<Question> findQuestionsByTopic(@Param("topicId") Long topicId, Pageable pageable);
}
