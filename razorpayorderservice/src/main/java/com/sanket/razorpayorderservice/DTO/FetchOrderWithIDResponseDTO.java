package com.sanket.razorpayorderservice.DTO;

import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FetchOrderWithIDResponseDTO {

    String id;

    String entity;

    Integer amount;

    Integer amountPaid;

    Integer amountDue;

    String currency;

    String receipt;

    String offerId;

    List<String> offers;

    String status;

    Integer attempts;

    Map<String, String> notes;

    Long createdAt;
    
}
