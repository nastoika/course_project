package application.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "status")
@Getter
@Setter
@NoArgsConstructor
@Data
public class Status {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "status_id")
    private Long statusId;
    @OneToMany(mappedBy = "status")
    @Column(name = "status", nullable = false)
    private List<User> usersStatusList;
    @Column(name = "status_name")
    private String statusName;
}
