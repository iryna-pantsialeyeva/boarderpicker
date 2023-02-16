package info.freeit.boarderpicker;

import info.freeit.boarderpicker.dto.NewGameDto;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;

@EnableKafka
@SpringBootApplication
public class BoarderpickerApplication {

	@KafkaListener(topics = "msg")
	public void msgListener(ConsumerRecord<Long, NewGameDto> record) {
		System.out.println(record.value());
		System.out.println(record.key());
	}

	public static void main(String[] args) {
		SpringApplication.run(BoarderpickerApplication.class, args);
	}

}
