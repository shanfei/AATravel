package com.home.aatravel.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@Data
public class TransactionMemberDetail {

    private String payer;

    private String payee;

    private List<TransactionDetail> detailList;

    private BigDecimal total;

    public void addTransactionDetail(TransactionDetail transactionDetail) {
        if (this.detailList == null) {
            this.detailList = new ArrayList<>();
        }
        this.detailList.add(transactionDetail);
    }


}
