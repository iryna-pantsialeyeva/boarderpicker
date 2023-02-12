package info.freeit.boarderpicker.mapper;

import info.freeit.boarderpicker.dto.NewGameDto;
import info.freeit.boarderpicker.dto.NewProducerDto;
import info.freeit.boarderpicker.dto.NewSaleDto;
import info.freeit.boarderpicker.entity.Category;
import info.freeit.boarderpicker.entity.Game;
import info.freeit.boarderpicker.entity.Producer;
import info.freeit.boarderpicker.entity.Sale;
import info.freeit.boarderpicker.repository.CategoryRepository;
import info.freeit.boarderpicker.repository.GameRepository;
import info.freeit.boarderpicker.repository.ProducerRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.Condition;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@Component
@RequiredArgsConstructor
public class MyModelMapper extends ModelMapper {
    private final CategoryRepository categoryRepository;
    private final ProducerRepository producerRepository;
    private final GameRepository gameRepository;
    private Converter<Integer, Producer> producerIdToProducerConverter;
    private Converter<Set<Integer>, Set<Category>> categoryIdToCategoryConverter;
    private Converter<NewProducerDto, Producer> producerDtoToProducerConverter;
    private Converter<Integer, Game> gameIdToGameConverter;

    private void initConverters() {
        producerIdToProducerConverter = ctx -> producerRepository.findById(ctx.getSource())
                .orElseThrow(() -> new IllegalArgumentException(String.format("The producer with id %d does not exist!!!",
                        ctx.getSource())));

        categoryIdToCategoryConverter = ctx ->
                new HashSet<>(categoryRepository.findAllById(ctx.getSource()));

        producerDtoToProducerConverter = ctx -> Producer.builder().active(true).build();

        gameIdToGameConverter = ctx -> gameRepository.findById(ctx.getSource())
                .orElseThrow(() -> new IllegalArgumentException(String.format("The game with id %d does not exist!!!",
                        ctx.getSource())));
    }

    @PostConstruct
    public void mapperConfig() {
        initConverters();
        Condition<Integer, Producer> hasId = ctx -> ctx.getSource() != null;
        Condition<Integer, Game> hasGameId = ctx -> ctx.getSource() != null;
        typeMap(NewGameDto.class, Game.class)
                .addMappings(mapper -> mapper.when(hasId)
                        .using(producerIdToProducerConverter)
                        .map(NewGameDto::getProducer, Game::setProducer))
                .addMappings(mapper -> mapper.using(categoryIdToCategoryConverter)
                        .map(NewGameDto::getCategories, Game::setCategories));
        typeMap(NewSaleDto.class, Sale.class)
                .addMappings(mapper -> mapper.when(hasGameId)
                        .using(gameIdToGameConverter)
                        .map(NewSaleDto::getGame, Sale::setGame));
    }
}
