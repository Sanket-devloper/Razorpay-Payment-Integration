package com.sanket.razorpayorderservice.DTO;

import lombok.Data;

@Data
public class CapturePaymentRequestDTO {

    Integer amount;
    String currency;
}