package com.sanket.razorpayorderservice.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardDTO {
    String id;

    String entity;

    String name;

    String last4;

    String network;

    String type;

    String issuer;

    Boolean international;

    Boolean emi;

    String subType;

   
}
