package info.freeit.boarderpicker.controller.handlers;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor
@Component
public class ObjectErrorResponse {

    private int status;
    private String message;
}
