package com.github.epay.service;

import com.github.epay.domain.dto.request.PaymentCancelRequest;
import com.github.epay.domain.dto.request.PaymentProcessRequest;

/**
 * @author Raman Haurylau
 */
public interface PaymentService {
    void cancel(PaymentCancelRequest request);

    void process(PaymentProcessRequest request);
}
