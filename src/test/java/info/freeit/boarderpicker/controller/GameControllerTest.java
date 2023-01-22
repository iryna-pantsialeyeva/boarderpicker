package info.freeit.boarderpicker.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import info.freeit.boarderpicker.controller.handlers.AppExceptionHandler;
import info.freeit.boarderpicker.controller.handlers.ObjectErrorResponse;
import info.freeit.boarderpicker.dto.NewGameDto;
import info.freeit.boarderpicker.dto.SavedGameDto;
import info.freeit.boarderpicker.service.GameService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashSet;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(GameController.class)
public class GameControllerTest {

    @Autowired
    AppExceptionHandler exceptionHandler;

    @MockBean
    ObjectErrorResponse objectErrorResponse;

    @Autowired
    private MockMvc mvc;

    @MockBean
    private GameService gameService;

    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    public void given_game_added() throws Exception {
        SavedGameDto gameDto = new SavedGameDto(1, "Heroes1", "Strategy1", "www1",
                new HashSet<>(1), 2, "Producer2");
        String expectedResponse = mapper.writeValueAsString(gameDto);

        when(gameService.saveGame(any())).thenReturn(gameDto);

        mvc.perform(post("/games").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(expectedResponse)));

    }
}
