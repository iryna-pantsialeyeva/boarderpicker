package info.freeit.boarderpicker.controller;

import info.freeit.boarderpicker.dto.NewGameDto;
import info.freeit.boarderpicker.dto.SavedGameDto;
import info.freeit.boarderpicker.entity.Game;
import info.freeit.boarderpicker.mapper.MyModelMapper;
import info.freeit.boarderpicker.service.GameService;
import info.freeit.boarderpicker.service.exception.GamesNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/games")
public class GameController {

    private final GameService gameService;
    private final MyModelMapper modelMapper;

    @PostMapping
    public SavedGameDto addGame(@RequestBody NewGameDto gameDto) {
        return modelMapper.map(gameService.saveGame(modelMapper.newGameDtoToGame(gameDto)), SavedGameDto.class);
    }

    @GetMapping
    public List<SavedGameDto> getGames() throws GamesNotFoundException {
        List<SavedGameDto> gamesDto = new ArrayList<>();
        for (Game game : gameService.getAllGames()) {
            gamesDto.add(modelMapper.map(game, SavedGameDto.class));
        }
        return gamesDto;
    }

    @GetMapping(value = "/{id}")
    public SavedGameDto getGameById(@PathVariable int id) {
        return modelMapper.map(gameService.getGameById(id), SavedGameDto.class);
    }

    @PutMapping(value = "/{id}")
    public SavedGameDto updateGame(@RequestBody NewGameDto gameDto, @PathVariable int id) {
        return modelMapper.map(gameService.updateGame(id, modelMapper.map(gameDto, Game.class)), SavedGameDto.class);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteGame(@PathVariable int id) {
        gameService.deleteGame(id);
    }

}
