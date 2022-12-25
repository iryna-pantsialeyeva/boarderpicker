package info.freeit.boarderpicker.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@Table(name="roles")
@NoArgsConstructor
@AllArgsConstructor
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String role;

    public Role(String role) {
        this.role = role;
    }

    @ManyToMany
    @JoinTable(name = "users_roles",
            joinColumns = {@JoinColumn(name = "roles_id")},
            inverseJoinColumns = {@JoinColumn(name = "users_id")})
    private List<User> users;
}
