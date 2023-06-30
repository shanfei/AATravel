package com.home.aatravel.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class TransactionParticipationCompositeKey implements Serializable  {

    @Column(name = "transaction_id")
    private Long transactionId;

    @Column(name = "user_id")
    private Long userId;


}
