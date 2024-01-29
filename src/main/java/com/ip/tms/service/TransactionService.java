package com.ip.tms.service;

import com.ip.tms.entities.TransactionDetails;
import org.springframework.http.ResponseEntity;

public interface TransactionService {

    ResponseEntity<?> addTransaction(TransactionDetails transactionDetails);
    ResponseEntity<?> getTransaction(String txnId);
    ResponseEntity<?> getTransactionRangeByAmount(Float initialRange,Float finalRange);
    ResponseEntity<?> getTransactionSortByAmount();

}
