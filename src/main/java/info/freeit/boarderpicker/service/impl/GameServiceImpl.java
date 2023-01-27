package info.freeit.boarderpicker.service.Impl;

import info.freeit.boarderpicker.dto.NewGameDto;
import info.freeit.boarderpicker.dto.SavedGameDto;
import info.freeit.boarderpicker.entity.Category;
import info.freeit.boarderpicker.entity.Game;
import info.freeit.boarderpicker.entity.Game.GameBuilder;
import info.freeit.boarderpicker.entity.Producer;
import info.freeit.boarderpicker.mapper.MyModelMapper;
import info.freeit.boarderpicker.repository.CategoryRepository;
import info.freeit.boarderpicker.repository.GameRepository;
import info.freeit.boarderpicker.repository.ProducerRepository;
import info.freeit.boarderpicker.service.GameService;
import info.freeit.boarderpicker.service.exception.GamesNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
public class GameServiceImpl implements GameService {

    private final GameRepository gameRepository;
    private final ProducerRepository producerRepository;
    private final CategoryRepository categoryRepository;
    private final MyModelMapper modelMapper;

    @Override
    public SavedGameDto saveGame(NewGameDto gameDto) {
        GameBuilder gameBuilder = Game.builder()
                .name(gameDto.getName())
                .description(gameDto.getDescription())
                .picPath(gameDto.getPicPath());

        if (gameRepository.findByName(gameDto.getName()) != null) {
            throw new IllegalArgumentException(String.format("Game %s already exists",
                    gameDto.getName()));
        } else {
            Producer producer = gameDto.getProducerId() != null
                    ? producerRepository.findById(gameDto.getProducerId())
                    .orElseThrow(() -> new IllegalArgumentException(String.format("The producer with id %d does not exist!!!",
                            gameDto.getProducerId())))
                    : producerRepository.save(Producer.builder()
                    .name(gameDto.getProducerName())
                    .build());
            gameBuilder.producer(producer);

            Set<Integer> categoryIds = gameDto.getCategories();
            Set<Category> categories = new HashSet<>(categoryRepository.findAllById(categoryIds));
            gameBuilder.categories(categories);

            return modelMapper.toSavedGameDto(gameRepository.save(gameBuilder.build()));
        }
    }

    @Override
    public List<SavedGameDto> getAllGames() throws GamesNotFoundException {
        List<Game> games = gameRepository.findAll();
        if (games.size() != 0) {
            List<SavedGameDto> gamesDto = new ArrayList<>();
            for (Game game : games) {
                gamesDto.add(modelMapper.toSavedGameDto(game));
            }
            return gamesDto;
        } else {
            throw new GamesNotFoundException("There is no any games in DB!!!");
        }
    }

    @Override
    public SavedGameDto getGameById(int id) {
        Game game = gameRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(String.format("The game with id %d does not exist!!!",
                                id)));
        return modelMapper.toSavedGameDto(game);
    }

    @Override
    @Transactional
    public SavedGameDto updateGame(int id, NewGameDto gameDto) {
        Game foundGame = gameRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(String.format("The game with id %d does not exist!!!",
                                id)));

        if (gameRepository.findByName(gameDto.getName()) != null) {
            throw new IllegalArgumentException(String.format("Game %s already exists",
                    gameDto.getName()));
        } else {
            foundGame.setName(gameDto.getName());
            foundGame.setDescription(gameDto.getDescription());
            foundGame.setPicPath(gameDto.getPicPath());

            Producer producer = gameDto.getProducerId() != null
                    ? producerRepository.findById(gameDto.getProducerId())
                    .orElseThrow(() -> new IllegalArgumentException(String.format("The producer with id %d does not exist!!!",
                            gameDto.getProducerId())))
                    : producerRepository.save(Producer.builder()
                    .name(gameDto.getProducerName())
                    .build());
            foundGame.setProducer(producer);

            Set<Integer> categoryIds = gameDto.getCategories();
            Set<Category> categories = new HashSet<>(categoryRepository.findAllById(categoryIds));
            foundGame.setCategories(categories);
            return modelMapper.toSavedGameDto(gameRepository.save(foundGame));
        }
    }

    @Override
    public void deleteGame(int id) {
        if (gameRepository.existsById(id)) {
            gameRepository.deleteById(id);
        } else {
            throw new RuntimeException(String.format("The game with id %d does not exist!!!", id));
        }
    }
}
