package info.freeit.boarderpicker.dto;

import info.freeit.boarderpicker.entity.Game;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SavedGameDto {

    private Integer id;
    private String name;
    private String description;
    private String picPath;
    private Set<CategoryDto> categories = new HashSet<>();
    private Integer producerId;
    private String producerName;

    public static SavedGameDto fromGame(Game game, Set<CategoryDto> categoryDtos) {
        SavedGameDtoBuilder savedGameDtoBuilder = SavedGameDto.builder()
                .id(game.getId())
                .name(game.getName())
                .description(game.getDescription())
                .picPath(game.getPicPath())
                .producerId(game.getProducer().getId())
                .producerName(game.getProducer().getName())
                .categories(categoryDtos);
        return savedGameDtoBuilder.build();
    }
}
