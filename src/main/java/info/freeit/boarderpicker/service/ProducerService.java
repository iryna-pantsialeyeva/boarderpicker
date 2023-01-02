package info.freeit.boarderpicker.service;

import info.freeit.boarderpicker.entity.Producer;

public interface ProducerService {

    void saveProducer (Producer producer);
    Producer getProducerByName (String name);
}
