package info.freeit.boarderpicker.controller;

import info.freeit.boarderpicker.entity.Game;
import info.freeit.boarderpicker.service.GameService;
import info.freeit.boarderpicker.service.exception.GamesNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/games")
public class GameController {

    @Autowired
    private GameService gameService;

    @PostMapping
    public void addGame(@RequestBody Game game) throws IllegalArgumentException {
        gameService.saveGame(game);
    }

    @GetMapping
    public List<Game> getGames() throws GamesNotFoundException {
        return gameService.getAllGames();
    }

    @GetMapping(value = "/{id}")
    public Game getGameById(@PathVariable int id) {
        return gameService.getGameById(id);
    }

    @PutMapping(value = "/{id}")
    public void updateGame(@RequestBody Game game, @PathVariable int id) {
        gameService.updateGame(id, game);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteGame(@PathVariable int id) {
        gameService.deleteGame(id);
    }

}
