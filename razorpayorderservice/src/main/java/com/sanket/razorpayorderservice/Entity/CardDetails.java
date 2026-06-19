package com.sanket.razorpayorderservice.Entity;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class CardDetails {

    private String id;

    private String entity;

    private String name;

    private String last4;

    private String network;

    private String type;

    private String issuer;

    private Boolean international;

    private Boolean emi;

    private String subType;
}