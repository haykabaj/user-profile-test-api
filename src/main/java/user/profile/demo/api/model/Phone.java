package user.profile.demo.api.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "phones")
@Data
@NoArgsConstructor
public class Phone {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "value")
    private String value;

    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private User user;
}
