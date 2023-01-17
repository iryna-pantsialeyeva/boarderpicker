package info.freeit.boarderpicker.repository;

import info.freeit.boarderpicker.entity.Producer;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomProducerRepository {

    Producer findByName(String name);

}
