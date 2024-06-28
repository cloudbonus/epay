package com.github.epay.service;

import com.github.epay.domain.dto.common.PaymentDto;
import com.github.epay.domain.dto.request.PaymentCancelRequest;
import com.github.epay.domain.dto.request.PaymentProcessRequest;

/**
 * @author Raman Haurylau
 */
public interface AcquirerService {
    PaymentDto cancel(PaymentCancelRequest request);

    PaymentDto process(PaymentProcessRequest request);
}
