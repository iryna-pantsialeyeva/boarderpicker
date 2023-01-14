package info.freeit.boarderpicker.controller;

import info.freeit.boarderpicker.dto.NewGameDto;
import info.freeit.boarderpicker.dto.SavedGameDto;
import info.freeit.boarderpicker.service.GameService;
import info.freeit.boarderpicker.service.exception.GamesNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/games")
public class GameController {

    @Autowired
    private GameService gameService;

    @PostMapping
    public SavedGameDto addGame(@RequestBody NewGameDto game) {
        return gameService.saveGame(game);
    }

    @GetMapping
    public List<SavedGameDto> getGames() throws GamesNotFoundException {
        return gameService.getAllGames();
    }

    @GetMapping(value = "/{id}")
    public SavedGameDto getGameById(@PathVariable int id) {
        return gameService.getGameById(id);
    }

    @PutMapping(value = "/{id}")
    public SavedGameDto updateGame(@RequestBody NewGameDto gameDto, @PathVariable int id) {
        return gameService.updateGame(id, gameDto);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteGame(@PathVariable int id) {
        gameService.deleteGame(id);
    }

}
