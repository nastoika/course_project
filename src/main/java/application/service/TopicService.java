package application.service;

import application.entity.Topic;
import org.springframework.stereotype.Service;

@Service
public interface TopicService {
    Topic findTopicByTopicName(String topicName);
}
