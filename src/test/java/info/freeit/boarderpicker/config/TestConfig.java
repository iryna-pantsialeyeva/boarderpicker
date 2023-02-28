package info.freeit.boarderpicker.config;

import info.freeit.boarderpicker.dto.SavedGameDto;
import info.freeit.boarderpicker.entity.Game;
import info.freeit.boarderpicker.repository.GameRepository;
import info.freeit.boarderpicker.service.Impl.GameServiceImpl;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@TestConfiguration
public class TestConfig {

    @Repository
    public interface TestGameRepository extends GameRepository {

    }

    @Service
    public static class TestGameService extends GameServiceImpl {
//        public TestGameService(TestGameRepository testGameRepository) {
//            super(testGameRepository);
//        }

        public SavedGameDto getGameById(int id) {
            return super.getGameById(id);
        }
    }
}
