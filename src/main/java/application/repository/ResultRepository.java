package application.repository;

import application.entity.Result;
import application.entity.Test;
import application.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResultRepository extends JpaRepository<Result, Long> {
    Result findResultByResultAndUserAndTest(Double result, User user, Test test);
}
