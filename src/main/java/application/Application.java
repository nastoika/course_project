package application;

import application.entity.Role;
import application.entity.Status;
import application.entity.Topic;
import application.entity.User;
import application.repository.TopicRepository;
import application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }

    @Bean
    public CommandLineRunner commandLineRunner(@Autowired UserService userService,
                                               @Autowired TopicRepository topicRepository,
                                               @Autowired PasswordEncoder passwordEncoder) {
        return args -> {
            if (userService.findUserByLogin("ADMIN") == null) {
                User user = new User();
                user.setLogin("ADMIN");
                user.setPassword(passwordEncoder.encode("ADMINadmin1"));
                Role role = new Role();
                role.setRoleName("ADMIN");
                Status status = new Status();
                status.setStatusName("ACTIVE");
                userService.saveUser(user, role, status);
            }
            List<Topic> topics = topicRepository.findAll();
            if (topics.size() != 3) {
                Topic topic1 = new Topic();
                topic1.setTopic("ТЕХНИЧЕСКИЙ");
                Topic topic2 = new Topic();
                topic2.setTopic("АНГЛИЙСКИЙ");
                Topic topic3 = new Topic();
                topic3.setTopic("ЛОГИЧЕСКИЙ");
                topicRepository.save(topic1);
                topicRepository.save(topic2);
                topicRepository.save(topic3);
            }
        };
    }
}
