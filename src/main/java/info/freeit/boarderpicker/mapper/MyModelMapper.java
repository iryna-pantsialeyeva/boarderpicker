package info.freeit.boarderpicker.mapper;

import info.freeit.boarderpicker.dto.NewGameDto;
import info.freeit.boarderpicker.entity.Category;
import info.freeit.boarderpicker.entity.Game;
import info.freeit.boarderpicker.entity.Producer;
import info.freeit.boarderpicker.repository.CategoryRepository;
import info.freeit.boarderpicker.repository.ProducerRepository;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.Condition;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@Component
public class MyModelMapper extends ModelMapper {
    private final CategoryRepository categoryRepository;
    private final ProducerRepository producerRepository;
    private Converter<Integer, Producer> producerIdToProducerConverter;
    private Converter<String, Producer> producerNameToProducerConverter;
    private Converter<Set<Integer>, Set<Category>> categoryIdToCategoryConverter;

    public MyModelMapper(CategoryRepository categoryRepository, ProducerRepository producerRepository) {
        this.categoryRepository = categoryRepository;
        this.producerRepository = producerRepository;
        initConverters();
    }

    private void initConverters() {
        producerIdToProducerConverter = ctx -> producerRepository.findById(ctx.getSource())
                .orElseThrow(() -> new IllegalArgumentException(String.format("The producer with id %d does not exist!!!",
                        ctx.getSource())));

        producerNameToProducerConverter = ctx -> Producer.builder().name(ctx.getSource()).build();

        categoryIdToCategoryConverter = ctx ->
                new HashSet<>(categoryRepository.findAllById(ctx.getSource()));
    }

//    public SavedGameDto toSavedGameDto(Game game) {
//        return modelMapper.map(game, SavedGameDto.class);
//    }

    @PostConstruct
    public void mapperConfig() {
        initConverters();
//        Condition<Integer, Producer> hasId = ctx -> ctx.getSource() != null;
//        Condition<String, Producer> hasName = ctx -> ctx.getSource() != null;
//        typeMap(NewGameDto.class, Game.class)
////                .addMappings(mapper -> mapper.when(hasId)
////                        .using(producerIdToProducerConverter)
////                        .map(NewGameDto::getProducerId, Game::setProducer))
//                .addMappings(mapper -> mapper
//                        .using(producerNameToProducerConverter)
//                        .map(NewGameDto::getProducerName, Game::setProducer))
//                .addMappings(mapper -> mapper.using(categoryIdToCategoryConverter)
//                        .map(NewGameDto::getCategories, Game::setCategories));
    }
}
