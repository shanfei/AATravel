package com.home.aatravel.service;

import com.home.aatravel.entity.Transaction;
import com.home.aatravel.entity.TransactionParticipation;
import com.home.aatravel.model.TransactionDetail;
import com.home.aatravel.model.TransactionMemberDetail;
import com.home.aatravel.repository.ProjectRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PaymentService {

    private ProjectRepository projectRepository;

    public List<TransactionMemberDetail> calculateByUserAndProject(long mid, long pid) throws Exception {
            // TODO implement me
            return new ArrayList<>();
    }

    public List<TransactionMemberDetail> calculate(List<Transaction> transactionList) throws Exception {

        Map<String, TransactionMemberDetail> map = new HashMap<>();

        for (Transaction transaction : transactionList) {

            TransactionParticipation payer = transaction.getParticipationList().stream().filter(TransactionParticipation::isPayer)
                    .findAny()
                    .orElseThrow(Exception::new);

            List<TransactionParticipation> participaters = transaction.getParticipationList().stream().filter(u -> !u.isPayer()).toList();

            switch (transaction.getDivideType()) {
                case AVERAGE -> {
                    BigDecimal amount = transaction.getAmount()
                            .divide(BigDecimal.valueOf(transaction.getParticipationList().size()), 2, RoundingMode.HALF_UP);
                    transaction.getParticipationList().forEach(tp -> tp.setAmount(amount));
                }
                case PRECENTAGE -> {

                }
            }


            participaters.forEach(p -> {
                String key = p.getUser().getName() + " - " + payer.getUser().getName();
                TransactionMemberDetail transactionMemberDetail = map.getOrDefault(key, new TransactionMemberDetail());
                transactionMemberDetail.addTransactionDetail(new TransactionDetail(p.getTransaction().getDescription(), p.getAmount()));
                map.put(key, transactionMemberDetail);
            });

        }

        for (Map.Entry<String, TransactionMemberDetail> entry : map.entrySet()) {

            BigDecimal totalAmount = entry.getValue().getDetailList().stream().map(TransactionDetail::getAmount)
                    .reduce(BigDecimal.ONE, BigDecimal::add);

            entry.getValue().setTotal(totalAmount);
            String[] parties = entry.getKey().split(" - ");
            entry.getValue().setPayer(parties[0]);
            entry.getValue().setPayee(parties[1]);
        }

        return map.values().stream().toList();

    }

}
