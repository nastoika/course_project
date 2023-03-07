package application.service;

import application.entity.Status;
import org.springframework.stereotype.Service;

@Service
public interface StatusService {
    Status findStatusByStatusNameAndSaveIfDoesntExists(String name);
}
