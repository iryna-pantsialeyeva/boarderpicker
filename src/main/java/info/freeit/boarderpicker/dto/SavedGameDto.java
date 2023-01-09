package info.freeit.boarderpicker.dto;

import info.freeit.boarderpicker.entity.Category;
import info.freeit.boarderpicker.entity.Game;
import info.freeit.boarderpicker.entity.User;
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
    private Set<Category> categories = new HashSet<>();
    private Integer producerId;
    private String producerName;

    public static SavedGameDto fromGame(Game game) {
        SavedGameDtoBuilder savedGameDtoBuilder = SavedGameDto.builder()
                .id(game.getId())
                .name(game.getName())
                .description(game.getDescription())
                .picPath(game.getPicPath())
                .producerId(game.getProducer().getId())
                .producerName(game.getProducer().getName())
                .categories(game.getCategories());
        return savedGameDtoBuilder.build();
    }
}
