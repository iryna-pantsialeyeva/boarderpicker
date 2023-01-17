package info.freeit.boarderpicker.repository;

import info.freeit.boarderpicker.entity.Producer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProducerRepository extends JpaRepository<Producer, Integer>, CustomProducerRepository {
}
