package info.freeit.boarderpicker.controller;

import info.freeit.boarderpicker.entity.Game;
import info.freeit.boarderpicker.service.GameService;
import info.freeit.boarderpicker.service.exception.CustomNullException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/games")
public class GameController {

    @Autowired
    private GameService gameService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public void addGame(@RequestBody Game game) {
        gameService.saveGame(game);
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public List<Game> getGames() {
        try {
            return gameService.getAllGames();
        } catch (CustomNullException cne) {
            return null;
        }
    }

    @RequestMapping(value = "/delete/id", method = RequestMethod.DELETE)
    public void deleteGame(@PathVariable Integer id) {
        Game game = new Game();
        game.setId(id);
        gameService.deleteGame(game);
    }
}
