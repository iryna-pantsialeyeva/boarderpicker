package info.freeit.boarderpicker.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "games")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
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

    @ManyToMany
    @JoinTable(name = "games_categories",
            joinColumns = {@JoinColumn(name = "games_id")},
            inverseJoinColumns = {@JoinColumn(name = "categories_id")})
    private Set<Category> categories = new HashSet<>();

//    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "producers_id", referencedColumnName = "id", nullable = true)
    private Producer producer;
}
