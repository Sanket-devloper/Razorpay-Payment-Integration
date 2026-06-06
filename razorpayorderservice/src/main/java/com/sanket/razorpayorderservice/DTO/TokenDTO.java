package com.sanket.razorpayorderservice.DTO;

import lombok.Data;

@Data
public class TokenDTO {
    String authType;
    Integer expireAt;
    String failureReason;
    Integer firstPaymentAmount;
    String frequency;
    Integer maxAmount;
    String recurringStatus;
}
