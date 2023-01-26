package info.freeit.boarderpicker.service;

import info.freeit.boarderpicker.entity.Producer;
import info.freeit.boarderpicker.service.exception.ProducersNotFoundException;

import java.util.List;

public interface ProducerService {

    Producer saveProducer(Producer producer);

    List<Producer> getAllProducers() throws ProducersNotFoundException;

    Producer getProducerById(int id);

    Producer updateProducer(int id, Producer producer);

    void deleteProducer(int id);

}
