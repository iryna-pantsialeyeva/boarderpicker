package info.freeit.boarderpicker.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import info.freeit.boarderpicker.controller.handlers.AppExceptionHandler;
import info.freeit.boarderpicker.dto.NewGameDto;
import info.freeit.boarderpicker.dto.SavedGameDto;
import info.freeit.boarderpicker.entity.Game;
import info.freeit.boarderpicker.entity.Producer;
import info.freeit.boarderpicker.mapper.MyModelMapper;
import info.freeit.boarderpicker.service.GameService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.HashSet;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@ExtendWith(SpringExtension.class)
@WebMvcTest(GameController.class)
public class GameControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private GameService gameService;

    @MockBean
    private AppExceptionHandler exceptionHandler;

    @Autowired
    private ObjectMapper mapper = new ObjectMapper();

    @MockBean
    private MyModelMapper modelMapper;

    @Test
    @WithMockUser(username = "Dasha", password = "Dasha", roles = "Admin")
    public void givenGame_whenAdd_thenStatus200andGameReturned() throws Exception {
        NewGameDto newGameDto = getNewGameDtoMock();
        Game game = getGameMock();
        SavedGameDto savedGameDto = getSavedGameDtoMock();

        String request = mapper.writeValueAsString(newGameDto);
        String expectedResponse = mapper.writeValueAsString(savedGameDto);

        when(modelMapper.map(any(), eq(SavedGameDto.class))).thenReturn(savedGameDto);

        mvc.perform(post("/games")
                        .with(csrf())
                        .content(request)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(expectedResponse)));
    }

    @Test
    @WithMockUser(username = "Dasha", password = "Dasha", roles = "Admin")
    public void whenGet_thenStatus200andAllGamesReturned() throws Exception {
        Game game = getGameMock();
        SavedGameDto savedGameDto = getSavedGameDtoMock();

        String expectedResponse = mapper.writeValueAsString(Arrays.asList(savedGameDto, savedGameDto));

        when(gameService.getAllGames()).thenReturn(Arrays.asList(game, game));
        when(modelMapper.map(any(), eq(SavedGameDto.class))).thenReturn(savedGameDto);

        mvc.perform(get("/games")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(expectedResponse)));
    }

    @Test
    @WithMockUser(username = "Dasha", password = "Dasha", roles = "Admin")
    public void givenId_whenGetGame_thenStatus200andGameReturned() throws Exception {
        SavedGameDto savedGameDto = getSavedGameDtoMock();

        String expectedResult = mapper.writeValueAsString(savedGameDto);

        when(modelMapper.map(any(), eq(SavedGameDto.class))).thenReturn(savedGameDto);

        mvc.perform(get("/games/1")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(expectedResult)))
                .andExpect(jsonPath("$.id").value("1"));
    }


    @Test
    @WithMockUser(username = "Dasha", password = "Dasha", roles = "Admin")
    public void givenId_whenUpdateGame_thenStatus200andUpdatedGameReturned() throws Exception {
        NewGameDto newGameDto = getNewGameDtoMock();
        SavedGameDto savedGameDto = getSavedGameDtoMock();

        String request = mapper.writeValueAsString(newGameDto);
        String expectedResponse = mapper.writeValueAsString(savedGameDto);

        when(modelMapper.map(any(), eq(SavedGameDto.class))).thenReturn(savedGameDto);

        mvc.perform(put("/games/1")
                        .with(csrf())
                        .content(request)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(expectedResponse)))
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    @WithMockUser(username = "Dasha", password = "Dasha", roles = "Admin")
    public void givenId_whenDeleteGame_thenStatus200() throws Exception {
        mvc.perform(delete("/games/1")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    private NewGameDto getNewGameDtoMock() {
        return NewGameDto.builder()
                .name("test")
                .description("test")
                .picPath("test")
                .categories(new HashSet<>())
                .producer(2)
                .build();
    }

    private Game getGameMock() {
        return Game.builder()
                .id(1)
                .name("test")
                .description("test")
                .picPath("test")
                .sales(new HashSet<>())
                .categories(new HashSet<>())
                .producer(new Producer())
                .build();
    }

    private SavedGameDto getSavedGameDtoMock() {
        return SavedGameDto.builder()
                .id(1)
                .name("test")
                .description("test")
                .picPath("test")
                .categories(new HashSet<>())
                .producerId(2)
                .producerName("Producer2")
                .build();
    }
}
