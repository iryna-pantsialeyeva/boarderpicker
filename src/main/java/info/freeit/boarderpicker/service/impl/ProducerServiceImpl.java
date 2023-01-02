package info.freeit.boarderpicker.service.impl;

import info.freeit.boarderpicker.entity.Producer;
import info.freeit.boarderpicker.repository.ProducerRepository;
import info.freeit.boarderpicker.service.ProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProducerServiceImpl implements ProducerService {

    @Autowired
    private ProducerRepository producerRepository;

    public void saveProducer (Producer producer) {
        producerRepository.save(producer);
    }

    public Producer getProducerByName (String name) {
        return producerRepository.findByName(name);
    }
}
