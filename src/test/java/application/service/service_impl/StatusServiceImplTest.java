package application.service.service_impl;

import application.entity.Role;
import application.entity.Status;
import application.repository.StatusRepository;
import application.service.StatusService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class StatusServiceImplTest {

    @Mock
    private StatusRepository statusRepository;

    @Test
    void findStatusByStatusNameAndSaveIfDoesntExists() {
        String statusName = "ACTIVE";
        Status status = new Status();
        StatusService service = new StatusServiceImpl(statusRepository);

        Mockito.when(statusRepository.findStatusByStatusName(statusName)).thenReturn(status);
        Status checkStatus = service.findStatusByStatusNameAndSaveIfDoesntExists(statusName);

        Mockito.verify(statusRepository, Mockito.times(1)).findStatusByStatusName(statusName);
        Mockito.verify(statusRepository, Mockito.never()).save(Mockito.any(Status.class));
        Assertions.assertEquals(status, checkStatus);
    }

    @Test
    void findStatusByStatusNameAndSaveIfDoesntExistsTest() {
        String statusName = "ACTIVE";
        Status statusForCheck = new Status();
        statusForCheck.setStatusName(statusName);
        StatusService service = new StatusServiceImpl(statusRepository);

        Mockito.when(statusRepository.findStatusByStatusName(statusName)).thenReturn(null);
        Status returnedStatus = service.findStatusByStatusNameAndSaveIfDoesntExists(statusName);

        Mockito.verify(statusRepository, Mockito.times(1)).findStatusByStatusName(statusName);
        Mockito.verify(statusRepository, Mockito.times(1)).save(Mockito.any(Status.class));
        assertEquals(statusForCheck, returnedStatus);
    }
}