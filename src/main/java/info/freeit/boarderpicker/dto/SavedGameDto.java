package info.freeit.boarderpicker.dto;

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
}
