package com.github.epay.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.epay.domain.dto.request.PaymentCancelRequest;
import com.github.epay.domain.dto.request.PaymentProcessRequest;
import com.github.epay.service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class PaymentRequestConsumer {

    private static final String PROCESS_TOPIC = "${topic.names.process}";
    private static final String CANCEL_TOPIC = "${topic.names.cancel}";

    private final ObjectMapper objectMapper;
    private final PaymentService paymentService;

    @SneakyThrows
    @KafkaListener(topics = PROCESS_TOPIC)
    public void consumePaymentRequestOnProcess(String message) {
        log.info("Payment request consumed {}", message);

        PaymentProcessRequest paymentProcessRequest = objectMapper.readValue(message, PaymentProcessRequest.class);
        paymentService.process(paymentProcessRequest);
    }

    @SneakyThrows
    @KafkaListener(topics = CANCEL_TOPIC)
    public void consumePaymentRequestOnCancel(String message) {
        log.info("Payment request {}", message);

        PaymentCancelRequest paymentCancelRequest = objectMapper.readValue(message, PaymentCancelRequest.class);
        paymentService.cancel(paymentCancelRequest);
    }
}