package info.freeit.boarderpicker.service.Impl;

import info.freeit.boarderpicker.entity.Category;
import info.freeit.boarderpicker.entity.Game;
import info.freeit.boarderpicker.entity.Game.GameBuilder;
import info.freeit.boarderpicker.entity.Producer;
import info.freeit.boarderpicker.repository.CategoryRepository;
import info.freeit.boarderpicker.repository.GameRepository;
import info.freeit.boarderpicker.repository.ProducerRepository;
import info.freeit.boarderpicker.service.GameService;
import info.freeit.boarderpicker.service.exception.GamesNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class GameServiceImpl implements GameService {

    private final GameRepository gameRepository;
    private final ProducerRepository producerRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public Game saveGame(Game game) {
        GameBuilder gameBuilder = Game.builder()
                .name(game.getName())
                .description(game.getDescription())
                .picPath(game.getPicPath());

        if (gameRepository.findByName(game.getName()) != null) {
            throw new IllegalArgumentException(String.format("Game %s already exists",
                    game.getName()));
        } else {
            Producer producer = game.getProducer().getId() != null
                    ? producerRepository.findById(game.getProducer().getId())
                    .orElseThrow(() -> new IllegalArgumentException(String.format("The producer with id %d does not exist!!!",
                            game.getProducer().getId())))
                    : producerRepository.save(game.getProducer());
            gameBuilder.producer(producer);

//            Set<Integer> categoryIds = game.getCategories()
//                    .stream().map(category -> category.getId())
//                    .collect(Collectors.toSet());
//            Set<Category> categories = new HashSet<>(categoryRepository.findAllById(categoryIds));
            gameBuilder.categories(game.getCategories());

            return gameRepository.save(gameBuilder.build());
        }
    }

    @Override
    public List<Game> getAllGames() throws GamesNotFoundException {
        List<Game> games = gameRepository.findAll();
        if (games.size() != 0) {
            return games;
        } else {
            throw new GamesNotFoundException("There is no any games in DB!!!");
        }
    }

    @Override
    public Game getGameById(int id) {
        return gameRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(String.format("The game with id %d does not exist!!!", id)));
    }

    @Override
    @Transactional
    public Game updateGame(int id, Game game) {
        Game foundGame = gameRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(String.format("The game with id %d does not exist!!!", id)));

        if (gameRepository.findByName(game.getName()) != null) {
            throw new IllegalArgumentException(String.format("Game %s already exists",
                    game.getName()));
        } else {
            foundGame.setName(game.getName());
            foundGame.setDescription(game.getDescription());
            foundGame.setPicPath(game.getPicPath());

            Producer producer = game.getProducer().getId() != null
                    ? producerRepository.findById(game.getProducer().getId())
                    .orElseThrow(() -> new IllegalArgumentException(String.format("The producer with id %d does not exist!!!",
                            game.getProducer().getId())))
                    : producerRepository.save(Producer.builder()
                    .name(game.getProducer().getName())
                    .build());
            foundGame.setProducer(producer);

            Set<Integer> categoryIds = game.getCategories()
                    .stream().map(category -> category.getId())
                    .collect(Collectors.toSet());
            Set<Category> categories = new HashSet<>(categoryRepository.findAllById(categoryIds));
            foundGame.setCategories(categories);
            return foundGame;
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
