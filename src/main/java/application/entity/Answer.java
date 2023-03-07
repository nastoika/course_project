package application.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "answer")
@Getter
@Setter
@NoArgsConstructor
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "answer_id")
    private Long id;
    @Column(nullable = false)
    private String answer;
    @Column(nullable = false)
    private Boolean correctly;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "question_id")
    private Question question;

    public Answer(String answer, Boolean correctly, Question question) {
        this.answer = answer;
        this.correctly = correctly;
        this.question = question;
    }
}
