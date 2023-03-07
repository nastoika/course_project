package application.service.service_impl;

import application.entity.Topic;
import application.repository.TestRepository;
import application.service.TestService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class TestServiceImplTest {

    @Mock
    private TestRepository testRepository;

    @Test
    void findTestByTestName() {
        String testName = "test";
        application.entity.Test test = new application.entity.Test();
        TestService testService = new TestServiceImpl(testRepository);

        Mockito.when(testRepository.findTestByTest(testName)).thenReturn(test);
        application.entity.Test returnedTest = testService.findTestByTestName(testName);

        Mockito.verify(testRepository, Mockito.times(1)).findTestByTest(testName);
        assertEquals(test, returnedTest);
    }

    @Test
    void saveTest() {
        String testName = "test";
        Topic topic = new Topic();
        TestService testService = new TestServiceImpl(testRepository);

        testService.saveTest(testName, topic);

        Mockito.verify(testRepository, Mockito.times(1)).save(Mockito.any(application.entity.Test.class));
    }
}