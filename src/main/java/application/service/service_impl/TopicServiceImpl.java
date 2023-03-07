package application.service.service_impl;

import application.entity.Topic;
import application.repository.TopicRepository;
import application.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TopicServiceImpl implements TopicService {
    private final TopicRepository topicRepository;

    @Autowired
    public TopicServiceImpl(TopicRepository topicRepository) {
        this.topicRepository = topicRepository;
    }

    @Override
    public Topic findTopicByTopicName(String topicName) {
        return topicRepository.findTopicByTopic(topicName);
    }
}
