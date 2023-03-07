package application.service.service_impl;

import application.entity.Topic;
import application.repository.TopicRepository;
import application.service.TopicService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class TopicServiceImplTest {

    @Mock
    private TopicRepository topicRepository;

    @Test
    void findTopicByTopicName() {
        String topicName = "topic";
        Topic topic = new Topic();
        TopicService topicService = new TopicServiceImpl(topicRepository);

        Mockito.when(topicRepository.findTopicByTopic(topicName)).thenReturn(topic);
        Topic returnedTopic = topicService.findTopicByTopicName(topicName);

        Mockito.verify(topicRepository, Mockito.times(1)).findTopicByTopic(topicName);
        assertEquals(topic, returnedTopic);
    }
}