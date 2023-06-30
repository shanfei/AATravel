package com.home.aatravel.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "transaction_participation_tbl")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionParticipation {

    @EmbeddedId
    private TransactionParticipationCompositeKey id;

    @ManyToOne
    @MapsId("userId")
    private User user;

    @ManyToOne
    @MapsId("transactionId")
    private Transaction transaction;

    @Column(columnDefinition = "DECIMAL(7,2)")
    private BigDecimal amount;

    private boolean isPayer;
}
