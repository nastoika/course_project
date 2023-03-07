package application.service.service_impl;

import application.entity.Status;
import application.repository.StatusRepository;
import application.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatusServiceImpl implements StatusService {
    private final StatusRepository statusRepository;

    @Autowired
    public StatusServiceImpl(StatusRepository statusRepository) {
        this.statusRepository = statusRepository;
    }

    @Override
    public Status findStatusByStatusNameAndSaveIfDoesntExists(String statusName) {
        Status status = statusRepository.findStatusByStatusName(statusName);
        if (status == null) {
            Status userStatus = new Status();
            userStatus.setStatusName(statusName);
            statusRepository.save(userStatus);
            return userStatus;
        } else return status;
    }
}
