package info.freeit.boarderpicker.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "producers")
@NoArgsConstructor
@AllArgsConstructor
public class Producer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;

    public Producer(String name) {
        this.name = name;
    }

    @OneToMany(mappedBy = "producer")
    private Set<Game> games = new HashSet<>();
}
