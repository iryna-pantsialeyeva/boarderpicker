package info.freeit.boarderpicker.entity;

import jakarta.persistence.*;
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

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "producer")
    private Set<Game> games = new HashSet<>();
}
