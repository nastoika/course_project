package application.service.service_impl;

import application.entity.Result;
import application.entity.User;
import application.repository.ResultRepository;
import application.service.ResultService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ResultServiceImplTest {

    @Mock
    private ResultRepository resultRepository;

    @Test
    void findResultByUserAndTestAndResult() {
        User user = new User();
        application.entity.Test test = new application.entity.Test();
        Double result = 5.0;
        Result resultForCheck = new Result();
        ResultService resultService = new ResultServiceImpl(resultRepository);

        Mockito.when(resultRepository.findResultByResultAndUserAndTest(result, user, test)).thenReturn(resultForCheck);
        Result returnedResult = resultService.findResultByUserAndTestAndResult(user, test, result);

        assertEquals(resultForCheck, returnedResult);
        Mockito.verify(resultRepository, Mockito.times(1)).findResultByResultAndUserAndTest(result, user, test);

    }

    @Test
    void save() {
        User user = new User();
        application.entity.Test test = new application.entity.Test();
        double result = 5.0;
        ResultService resultService = new ResultServiceImpl(resultRepository);

        resultService.save(test,user,result);

        Mockito.verify(resultRepository,Mockito.times(1)).save(Mockito.any(Result.class));
    }
}