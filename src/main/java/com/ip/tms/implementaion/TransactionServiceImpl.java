package com.ip.tms.implementaion;

import com.ip.tms.entities.TransactionDetails;
import com.ip.tms.repository.TransactionRepository;
import com.ip.tms.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public ResponseEntity<?> addTransaction(TransactionDetails transactionDetails) {
        try {
            if (transactionDetails.getAmount() > 0) {
                transactionDetails.setTxnId(generateTransactionId());
                transactionRepository.save(transactionDetails);
                return ResponseEntity.status(HttpStatus.OK).body("Successfully save transaction!!");
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Enter amount must not be zero!!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong!! " + e.getMessage());
        }
    }

    private static String generateTransactionId() {
        int idLength = 8;
        Random random = new Random();
        StringBuilder transactionIdBuilder = new StringBuilder(idLength);
        for (int i = 0; i < idLength; i++) {
            transactionIdBuilder.append(random.nextInt(10));
        }
        return transactionIdBuilder.toString();
    }

    @Override
    public ResponseEntity<?> getTransaction(String txnId) {
        try {
            Optional<TransactionDetails> txnIdIs = transactionRepository.findByTxnId(txnId);
            if (txnIdIs.isPresent()) {
                return ResponseEntity.status(HttpStatus.OK).body(txnIdIs.get());
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No such type of txn!!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong!! " + e.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> getTransactionRangeByAmount(Float initialRange, Float finalRange) {
        try {
            if (initialRange > 0 && finalRange > 0) {
                List<TransactionDetails> amountRange = transactionRepository.findByAmountRange(initialRange, finalRange);
                if (!amountRange.isEmpty()) {
                    return ResponseEntity.status(HttpStatus.OK).body(amountRange);
                } else {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No such type of range!!");
                }
            } else {
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Enter the proper amount!!");
            }

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong!! " + e.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> getTransactionSortByAmount() {
        try {
            List<TransactionDetails> amountAsc = transactionRepository.findByOrderByAmountAsc();
            return ResponseEntity.status(HttpStatus.OK).body(amountAsc);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong!! " + e.getMessage());
        }
    }
}
