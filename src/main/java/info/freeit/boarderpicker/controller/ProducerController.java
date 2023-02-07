package info.freeit.boarderpicker.controller;

import info.freeit.boarderpicker.dto.NewProducerDto;
import info.freeit.boarderpicker.dto.SavedProducerDto;
import info.freeit.boarderpicker.entity.Producer;
import info.freeit.boarderpicker.mapper.MyModelMapper;
import info.freeit.boarderpicker.service.ProducerService;
import info.freeit.boarderpicker.service.exception.ProducersNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/producer")
public class ProducerController {

    private final ProducerService producerService;
    private final MyModelMapper modelMapper;

    @PostMapping
    public SavedProducerDto addProducer(@RequestBody NewProducerDto producerDto) {
        Producer newProducer = producerService.saveProducer(modelMapper.map(producerDto, Producer.class));
        return modelMapper.map(newProducer, SavedProducerDto.class);
    }

    @GetMapping
    public List<SavedProducerDto> getProducers() throws ProducersNotFoundException {
        List<SavedProducerDto> producerDto = new ArrayList<>();
        for (Producer producer : producerService.getAllProducers()) {
            producerDto.add(modelMapper.map(producer, SavedProducerDto.class));
        }
        return producerDto;
    }

    @GetMapping(value = "/{id}")
    public SavedProducerDto getProducerById(@PathVariable int id) {
        return modelMapper.map(producerService.getProducerById(id), SavedProducerDto.class);
    }

    @PutMapping(value = "/{id}")
    public SavedProducerDto updateGame(@RequestBody NewProducerDto producerDto, @PathVariable int id) {
        Producer producer = producerService.updateProducer(id, modelMapper.map(producerDto, Producer.class));
        return modelMapper.map(producer, SavedProducerDto.class);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteProducer(@PathVariable int id) {
        producerService.deleteProducer(id);
    }
}
