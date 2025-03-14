package ru.studyhelp.studyhelp_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.studyhelp.studyhelp_app.entity.Topic;

@Repository
public interface TopicRepository extends JpaRepository<Topic, Long> {
}