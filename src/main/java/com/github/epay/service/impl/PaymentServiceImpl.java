package com.github.epay.service.impl;

import com.github.epay.domain.dto.common.PaymentDto;
import com.github.epay.domain.dto.request.PaymentCancelRequest;
import com.github.epay.domain.dto.request.PaymentProcessRequest;
import com.github.epay.domain.enums.PaymentEvent;
import com.github.epay.domain.enums.PaymentState;
import com.github.epay.service.AcquirerService;
import com.github.epay.service.PaymentService;
import com.github.epay.service.statemachine.PaymentStateMachine;
import com.github.epay.service.statemachine.PaymentStateMachineFactory;
import com.github.epay.service.util.PaymentProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author Raman Haurylau
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentStateMachineFactory paymentStateMachineFactory;
    private final PaymentProducer paymentProducer;
    private final AcquirerService acquirerService;


    @Override
    public void cancel(PaymentCancelRequest request) {
        PaymentDto paymentDto = acquirerService.cancel(request);

        PaymentStateMachine sm = paymentStateMachineFactory.create(paymentDto);
        sm.sendEvent(PaymentEvent.CANCEL);

        log.info("Payment [{}] [{}]", paymentDto.getId(), paymentDto.getState());
    }

    @Override
    public void process(PaymentProcessRequest request) {
        PaymentDto paymentDto = acquirerService.process(request);

        PaymentStateMachine sm = paymentStateMachineFactory.create(paymentDto);
        PaymentState newState = sm.sendEvent(PaymentEvent.PLACE);

        paymentDto.setState(newState);
        paymentDto =  paymentProducer.sendMessage(paymentDto);

        log.info("Payment [{}] [{}]", paymentDto.getId(), paymentDto.getState());
    }
}
