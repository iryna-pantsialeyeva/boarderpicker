package info.freeit.boarderpicker.repository;

import info.freeit.boarderpicker.entity.Producer;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomProducerRepository {

    Producer findByName(String name);

}
