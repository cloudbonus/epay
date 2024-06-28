package com.github.epay.service.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.epay.config.kafka.KafkaTopicProperties;
import com.github.epay.domain.dto.common.PaymentDto;
import com.github.epay.domain.enums.BlogKafkaTopic;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 * @author Raman Haurylau
 */
@Log4j2
@Component
@RequiredArgsConstructor
public class PaymentProducer {

    private final ObjectMapper objectMapper;
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final KafkaTopicProperties kafkaTopicProperties;

    @SneakyThrows
    public PaymentDto sendMessage(PaymentDto paymentDto) {
        String requestAsMessage = objectMapper.writeValueAsString(paymentDto);
        kafkaTemplate.send(kafkaTopicProperties.getTopic(BlogKafkaTopic.UPDATE), requestAsMessage);

        log.info("Payment request produced {}", requestAsMessage);

        return paymentDto;
    }
}
