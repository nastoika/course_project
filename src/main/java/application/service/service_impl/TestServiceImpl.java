package application.service.service_impl;

import application.entity.Test;
import application.entity.Topic;
import application.repository.TestRepository;
import application.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl implements TestService {
    private final TestRepository testRepository;

    @Autowired
    public TestServiceImpl(TestRepository testRepository) {
        this.testRepository = testRepository;
    }

    @Override
    public Test findTestByTestName(String testName) {
        return testRepository.findTestByTest(testName);
    }

    @Override
    public void saveTest(String testName, Topic topic) {
        Test test = new Test();
        test.setTest(testName);
        test.setTopic(topic);
        testRepository.save(test);
    }
}
