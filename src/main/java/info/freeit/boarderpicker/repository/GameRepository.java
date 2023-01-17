package info.freeit.boarderpicker.repository;

import info.freeit.boarderpicker.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends JpaRepository<Game, Integer> {

    Game findByName(String gameName);
}
