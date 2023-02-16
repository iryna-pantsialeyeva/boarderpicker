package info.freeit.boarderpicker.controller;

import info.freeit.boarderpicker.dto.NewGameDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/message")
public class MessageController {

    final private KafkaTemplate<Long, NewGameDto> kafkaTemplate;

    @PostMapping
    public void sendMsg(Long msgId, NewGameDto msg) {
        CompletableFuture<SendResult<Long, NewGameDto>> future = kafkaTemplate.send("msg", msgId, msg);
        future.whenComplete((result, ex) -> {
            if (ex != null) {
                System.err.println(ex.getMessage());
            } else {
                System.out.println(result);
            }
        });
    }
}
