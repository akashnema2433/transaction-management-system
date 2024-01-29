package com.ip.tms.controller;

import com.ip.tms.entities.TransactionDetails;
import com.ip.tms.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class TransactionController {
    @Autowired
    private TransactionService transactionService;
    @PostMapping("/txn/add")
    public ResponseEntity<?> addTransaction(@RequestBody TransactionDetails transactionDetails){
        return transactionService.addTransaction(transactionDetails);
    }

    @GetMapping("/txn")
    public ResponseEntity<?> getTransaction(@RequestParam(name = "txnId")String txnId ){
        return transactionService.getTransaction(txnId);
    }

    @GetMapping("/txn/amount/{initial_range}/{final_range}")
    public ResponseEntity<?> getTransactionRangeByAmount(@PathVariable("initial_range")Float initialRange, @PathVariable("final_range")Float finalRange ){
        return transactionService.getTransactionRangeByAmount(initialRange,finalRange);
    }

    @GetMapping("/sort/amount")
    public ResponseEntity<?> getTransactionSortByAmount(){
        return transactionService.getTransactionSortByAmount();
    }

}
