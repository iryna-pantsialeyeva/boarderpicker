package info.freeit.boarderpicker.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Table(name = "games")
@NoArgsConstructor
@AllArgsConstructor
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "gamename")
    private String gameName;
    private String description;
    @Column(name = "picpath")
    private String picPath;

    public Game(String gameName, String description, String picPath) {
        this.gameName = gameName;
        this.description = description;
        this.picPath = picPath;
    }

    @OneToMany(mappedBy = "game")
    private Set<Sale> sales = new HashSet<>();

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "games_categories",
            joinColumns = {@JoinColumn(name = "games_id")},
            inverseJoinColumns = {@JoinColumn(name = "categories_id")})
    private Set<Category> categories = new HashSet<>();

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "producers_id", referencedColumnName = "id")
    private Producer producer;
}
