package com.home.aatravel.service;

import com.home.aatravel.entity.*;
import com.home.aatravel.model.TransactionDetail;
import com.home.aatravel.model.TransactionMemberDetail;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class PaymentServiceTest {

    @Autowired
    private PaymentService paymentService;


    @Test
    public void testPaymentService() throws Exception {

        User fei = new User();
        fei.setId(0L);
        fei.setName("Fei");

        User user1 = new User();
        user1.setId(1L);
        user1.setName("User1");

        User user2 = new User();
        user2.setId(2L);
        user2.setName("User2");

        List<Transaction> transactions = new ArrayList<>();

        Transaction tx1 = new Transaction();

        tx1.setId(0L);
        tx1.setAmount(BigDecimal.valueOf(100));
        tx1.setDescription("Test 1");
        tx1.setDivideType(DivideType.AVERAGE);

        List<TransactionParticipation> transactionParticipationList = new ArrayList<>();

        transactionParticipationList.add(createTransactionParticipate(tx1, fei, true));
        transactionParticipationList.add(createTransactionParticipate(tx1, user1, false));
        transactionParticipationList.add(createTransactionParticipate(tx1, user2, false));

        tx1.setParticipationList(transactionParticipationList);

        transactions.add(tx1);

        List<TransactionMemberDetail> results = paymentService.calculate(transactions);
        assertEquals(2L, results.size());

        List<TransactionDetail> transactionMemberDetails =
                results.stream().filter( tm -> tm.getPayer().equalsIgnoreCase("User1")).findAny().get().getDetailList();

        assertEquals(1, transactionMemberDetails.size());
        assertEquals(BigDecimal.valueOf(33.33), transactionMemberDetails.get(0).getAmount());

    }

    public TransactionParticipation createTransactionParticipate(Transaction tx, User user, boolean isPayer) {

        TransactionParticipation transactionParticipation = new TransactionParticipation();
        transactionParticipation.setId(new TransactionParticipationCompositeKey(tx.getId(), user.getId()));
        transactionParticipation.setUser(user);
        transactionParticipation.setPayer(isPayer);
        transactionParticipation.setTransaction(tx);

        return transactionParticipation;

    }


}
