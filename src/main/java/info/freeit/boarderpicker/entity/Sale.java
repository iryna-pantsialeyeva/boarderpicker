package info.freeit.boarderpicker.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "sales")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private double price;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "users_id")
    private User user;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "games_id")
    private Game game;

}
