package info.freeit.boarderpicker.dto;

import info.freeit.boarderpicker.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTOForSaveUpdate {

    private String username;
    private String password;
    private String email;

    public static User fromUserDTOForSaveUpdate(UserDTOForSaveUpdate userDTO) {
        return User.builder()
                .userName(userDTO.username)
                .password(userDTO.password)
                .email(userDTO.email)
                .build();
    }
}