package application.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "test")
@Getter
@Setter
@NoArgsConstructor
public class Test {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "test_id")
    private Long id;
    @Column(nullable = false)
    private String test;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "topic_id")
    private Topic topic;
    @OneToMany(mappedBy = "test")
    private List<Question> questionList;
    @OneToMany(mappedBy = "test")
    private List<Result> resultList;
}
