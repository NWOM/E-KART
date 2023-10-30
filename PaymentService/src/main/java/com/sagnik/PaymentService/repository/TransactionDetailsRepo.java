package com.sagnik.PaymentService.repository;

import com.sagnik.PaymentService.entity.TransactionDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionDetailsRepo extends JpaRepository<TransactionDetails,Long> {
  TransactionDetails findByOrderId(long orderId);
}
