package info.freeit.boarderpicker.service;

import info.freeit.boarderpicker.dto.NewGameDto;
import info.freeit.boarderpicker.dto.SavedGameDto;
import info.freeit.boarderpicker.entity.Game;
import info.freeit.boarderpicker.service.exception.GamesNotFoundException;

import java.util.List;

public interface GameService {

    SavedGameDto saveGame(NewGameDto game);
    List<SavedGameDto> getAllGames() throws GamesNotFoundException;
    SavedGameDto getGameById(int id);
    SavedGameDto updateGame(int id, NewGameDto gameDto);
    void deleteGame(int id);
}
