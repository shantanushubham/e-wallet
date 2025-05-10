package org.geeksforgeeks.e_wallet.repository;

import org.geeksforgeeks.e_wallet.entities.Transaction;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, UUID> {

//    @Query("SELECT t FROM Transaction t WHERE t.sender.id = :userId OR t.receiver.id = :userId")
//    List<Transaction> findBySenderOrReceiver(@Param("userId") Long userId);


    List<Transaction> findBySender_IdOrReceiver_Id(long id, long id1, Sort sort);
}
