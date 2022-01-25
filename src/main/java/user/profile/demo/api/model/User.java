package user.profile.demo.api.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import user.profile.demo.api.model.enums.Role;
import user.profile.demo.api.model.enums.Status;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "age")
    @Size(min = 18, max = 60)
    private int age;

    @Column(name = "name")
    private String name;

    @Column(name = "username")
    private String username;

    @Column(name = "email")
    private String email;


    @Column(name = "password")
    private String password;


    @OneToOne(mappedBy = "user")
    private Profile profile;

    @OneToMany(mappedBy="user")
    private Set<Phone> phone;


    @Enumerated(value = EnumType.STRING)
    @Column(name = "role")
    private Role role;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "status")
    private Status status;


}
