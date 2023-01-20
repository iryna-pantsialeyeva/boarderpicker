package info.freeit.boarderpicker.mapper;

import info.freeit.boarderpicker.dto.CategoryDto;
import info.freeit.boarderpicker.entity.Category;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class MyModelMapper {
    private final ModelMapper modelMapper = new ModelMapper();

    public Set<CategoryDto> toCategoryDto(Set<Category> categories) {
        return categories.stream()
                .map((category) -> modelMapper.map(category, CategoryDto.class))
                .collect(Collectors.toSet());
    }
}
