package info.freeit.boarderpicker.service;

import info.freeit.boarderpicker.config.TestConfig;
import info.freeit.boarderpicker.dto.SavedGameDto;
import info.freeit.boarderpicker.entity.Category;
import info.freeit.boarderpicker.entity.Game;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {TestConfig.class})
public class GameServiceImplTest {

    @MockBean
    private TestConfig.TestGameRepository gameRepository;

    private TestConfig.TestGameService gameService;

    @BeforeEach
    public void before() {
//        gameService = new TestConfig.TestGameService(gameRepository);
    }

    @Test
    public void get_by_id_should_return_SavedGameDto() {
        SavedGameDto gameMock = new SavedGameDto (1, "Heroes1", "Strategy1", "www1",
                new HashSet<>(1), 2, "Producer2");

//        when(gameRepository.findById(anyInt())).thenReturn(Optional.of(gameMock));
        SavedGameDto found = gameService.getGameById(1);

        Assertions.assertNotNull(found);
        Assertions.assertEquals(gameMock, found);
    }
}
