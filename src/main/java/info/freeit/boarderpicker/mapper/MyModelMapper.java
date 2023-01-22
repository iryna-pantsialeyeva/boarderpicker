package info.freeit.boarderpicker.mapper;

import info.freeit.boarderpicker.dto.SavedGameDto;
import info.freeit.boarderpicker.entity.Game;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class MyModelMapper {
    private final ModelMapper modelMapper = new ModelMapper();

    public SavedGameDto toSavedGameDto(Game game) {
        return modelMapper.map(game, SavedGameDto.class);
    }
}
