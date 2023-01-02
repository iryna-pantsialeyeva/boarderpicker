package info.freeit.boarderpicker.controller;

import info.freeit.boarderpicker.entity.Category;
import info.freeit.boarderpicker.entity.Game;
import info.freeit.boarderpicker.entity.Producer;
import info.freeit.boarderpicker.service.CategoryService;
import info.freeit.boarderpicker.service.GameService;
import info.freeit.boarderpicker.service.ProducerService;
import info.freeit.boarderpicker.service.exception.GamesNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/games")
public class GameController {

    @Autowired
    private GameService gameService;
    @Autowired
    private ProducerService producerService;
    @Autowired
    private CategoryService categoryService;

    @PostMapping(value = "/add")
    public void addGame(@RequestBody Game game) {
        if(producerService.getProducerByName(game.getProducer().getName()) == null) {
            producerService.saveProducer(game.getProducer());
        }
        Producer producer = producerService.getProducerByName(game.getProducer().getName());
        game.setProducer(producer);

        Set<Category> categories = new HashSet<>();
        for (Category category : game.getCategories()) {
            if(categoryService.getCategoryByName(category.getName()) == null) {
                categoryService.saveCategory(category);
            }
            Category savedCategory = categoryService.getCategoryByName(category.getName());
            categories.add(savedCategory);
        }
        game.setCategories(categories);

        gameService.saveGame(game);
    }

    @GetMapping(value = "/getAll")
    public List<Game> getGames() throws GamesNotFoundException {
        return gameService.getAllGames();
    }

    @GetMapping(value = "/getById/{id}")
    public Game getGameById(@PathVariable int id) throws GamesNotFoundException {
        return gameService.getGameById(id);
    }

    @PutMapping(value = "/update/{id}")
    public void updateGame(@RequestBody Game game, @PathVariable int id) throws GamesNotFoundException {
        game.setId(id);
        gameService.updateGame(game);
    }

    @DeleteMapping(value = "/delete/{id}")
    public void deleteGame(@PathVariable int id) {
        gameService.deleteGame(id);
    }

}
