package application.validation;

import application.entity.Test;
import application.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TestValidation {

    @Autowired
    private TestService testService;

    public boolean checkExistsOfTest(String testName) {
        Test test = testService.findTestByTestName(testName);
        return test == null;
    }
}
