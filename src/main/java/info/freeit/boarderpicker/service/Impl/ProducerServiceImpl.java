package info.freeit.boarderpicker.service.Impl;

import info.freeit.boarderpicker.entity.Producer;
import info.freeit.boarderpicker.repository.ProducerRepository;
import info.freeit.boarderpicker.service.ProducerService;
import info.freeit.boarderpicker.service.exception.ProducersNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProducerServiceImpl implements ProducerService {

    private final ProducerRepository producerRepository;

    @Override
    public Producer saveProducer(Producer producer) {
        if (producerRepository.findByName(producer.getName()) != null) {
            throw new IllegalArgumentException(String.format("Producer %s already exists",
                    producer.getName()));
        } else {
            producer.setActive(true);
            return producerRepository.save(producer);
        }
    }

    @Override
    public List<Producer> getAllProducers() throws ProducersNotFoundException {
        List<Producer> producers = producerRepository.findAll();
        if (producers.size() != 0) {
            return producers;
        } else {
            throw new ProducersNotFoundException("There is no any producers in DB!!!");
        }
    }

    @Override
    public Producer getProducerById(int id) {
        return producerRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(String.format("The producer with id %d does not exist!!!", id)));
    }

    @Override
    @Transactional
    public Producer updateProducer(int id, Producer producer) {
        Producer foundProducer = producerRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(String.format("The producer with id %d does not exist!!!", id)));

        if (producerRepository.findByName(producer.getName()) != null) {
            throw new IllegalArgumentException(String.format("Producer %s already exists",
                    producer.getName()));
        } else {
            foundProducer.setName(producer.getName());
            return foundProducer;
        }
    }

    @Override
    @Transactional
    public void deleteProducer(int id) {
        if (producerRepository.existsById(id)) {
            producerRepository.getReferenceById(id).setActive(false);
        } else {
            throw new RuntimeException(String.format("The producer with id %d does not exist!!!", id));
        }
    }
}
