package org.geeksforgeeks.e_wallet.services;

import org.geeksforgeeks.e_wallet.entities.Transaction;
import org.geeksforgeeks.e_wallet.entities.Wallet;
import org.geeksforgeeks.e_wallet.repository.TransactionRepository;
import org.geeksforgeeks.e_wallet.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final WalletRepository walletRepository;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository, WalletRepository walletRepository) {
        this.transactionRepository = transactionRepository;
        this.walletRepository = walletRepository;
    }

    @Transactional
    public Transaction addTransaction(Transaction transaction) {
        Wallet senderWallet = transaction.getSender().getWallet();
        Wallet receiverWallet = transaction.getReceiver().getWallet();
        if (senderWallet.getBalance() < transaction.getAmount()) {
            // TODO: Handle this properly
            throw new RuntimeException("Insufficient Funds!");
        }
        transaction = this.transactionRepository.save(transaction);
        senderWallet.modifyBalanceBy(-1 * transaction.getAmount());
        receiverWallet.modifyBalanceBy(transaction.getAmount());
        this.walletRepository.saveAll(List.of(senderWallet, receiverWallet));
        return transaction;
    }

    public List<Transaction> getTransactionsForUser(long userId) {
        return this.transactionRepository.findBySender_IdOrReceiver_Id(userId, userId, Sort.by(
                Sort.Direction.DESC, "transactionTime"));
    }
}
