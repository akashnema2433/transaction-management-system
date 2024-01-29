package com.ip.tms.repository;

import com.ip.tms.entities.TransactionDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionDetails,Long> {
    Optional<TransactionDetails> findByTxnId(String txnId);
    @Query("SELECT t FROM TransactionDetails t WHERE t.amount BETWEEN :initialRange AND :finalRange")
    List<TransactionDetails> findByAmountRange(@Param("initialRange") Float initialRange, @Param("finalRange") Float finalRange);

    List<TransactionDetails> findByOrderByAmountAsc();
}
