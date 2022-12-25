package info.freeit.boarderpicker.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@Table(name = "games")
@NoArgsConstructor
@AllArgsConstructor
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "GAMENAME")
    private String gameName;
    private String description;
    private String picPath;

    public Game(String gameName, String description, String picPath) {
        this.gameName = gameName;
        this.description = description;
        this.picPath = picPath;
    }

    @ManyToMany
    @JoinTable(name = "sales",
            joinColumns = {@JoinColumn(name = "games_id")},
            inverseJoinColumns = {@JoinColumn(name = "users_id")})
    private List<User> users;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category category;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "producer_id", referencedColumnName = "id")
    private Producer producer;
}
