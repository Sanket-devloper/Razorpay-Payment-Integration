package com.sanket.razorpayorderservice.Entity;

import java.util.HashMap;
import java.util.Map;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MapKeyColumn;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "orders")
public class Order {

    @Id 
    @Column(name = "id")
    String id;

    @Column(name = "amount", nullable = false)
    Integer amount;

    @Column(name = "amountPaid", nullable = true)
    Integer amount_paid;

    @Column(name = "amountDue", nullable = true)
    Integer amount_due;

    @Column(name = "currency", length = 3, nullable = false)
    String currency;

    @Column(name = "receipt", length = 40 , unique = true, nullable = true)
    String receipt; 

    @Column(name = "status", nullable = true)
    String status;

    @Column(name = "attempts", nullable = true)
    Integer attempts;

    @Column(name = "createdAt", nullable = false)
    Long created_at;

    @ElementCollection
    @CollectionTable(name = "order_notes")
    @MapKeyColumn(name = "note_key")
    @Column(name = "note_value")
    Map<String, String> notes = new HashMap<>();

    public String getEntity() {
        return "order";
    }

    public String getOfferId() {
         
        return null;
    }

}

