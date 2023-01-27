package info.freeit.boarderpicker.mapper;

import info.freeit.boarderpicker.dto.UserDTO;
import info.freeit.boarderpicker.entity.User;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapper {
    private final ModelMapper modelMapper;

    public UserDTO fromUser(User user) {
        return modelMapper.map(user,UserDTO.class);
    }

    public User fromUserDTO(UserDTO userDTO) {
        return modelMapper.map(userDTO, User.class);
    }
}
