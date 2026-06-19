package com.sanket.razorpayorderservice.Service;

import com.sanket.razorpayorderservice.DTO.CapturePaymentRequestDTO;
import com.sanket.razorpayorderservice.DTO.PaymentDTO;

public interface PaymentService {

    PaymentDTO capturePayment(
            String paymentId,
            CapturePaymentRequestDTO requestDTO);
}