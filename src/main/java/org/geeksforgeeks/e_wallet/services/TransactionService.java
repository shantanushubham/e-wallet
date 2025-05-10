package org.geeksforgeeks.e_wallet.services;

import org.geeksforgeeks.e_wallet.entities.Transaction;
import org.geeksforgeeks.e_wallet.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public Transaction addTransaction(Transaction transaction) {
        return this.transactionRepository.save(transaction);
    }

    public List<Transaction> getTransactionsForUser(long userId) {
        return this.transactionRepository.findBySender_IdOrReceiver_Id(userId, userId, Sort.by(
                Sort.Direction.DESC, "transactionTime"));
    }
}
