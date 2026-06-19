package com.sanket.razorpayorderservice.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sanket.razorpayorderservice.DTO.CapturePaymentRequestDTO;
import com.sanket.razorpayorderservice.DTO.PaymentDTO;
import com.sanket.razorpayorderservice.Service.PaymentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/v1/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping("/{id}/capture")
    public ResponseEntity<PaymentDTO> capturePayment(
            @PathVariable String id,
            @RequestBody CapturePaymentRequestDTO requestDTO) {

        return ResponseEntity.ok(
                paymentService.capturePayment(id, requestDTO));
    }
}