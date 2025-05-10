package org.geeksforgeeks.e_wallet.contollers;

import org.geeksforgeeks.e_wallet.entities.Transaction;
import org.geeksforgeeks.e_wallet.entities.input.TxnInputEntity;
import org.geeksforgeeks.e_wallet.mapper.TransactionInputMapper;
import org.geeksforgeeks.e_wallet.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/txn")
public class TransactionController {

    private final TransactionService transactionService;
    private final TransactionInputMapper transactionInputMapper;

    @Autowired
    public TransactionController(TransactionService transactionService, TransactionInputMapper transactionInputMapper) {
        this.transactionService = transactionService;
        this.transactionInputMapper = transactionInputMapper;
    }

    @PostMapping("/add")
    public ResponseEntity<?> createTransaction(@RequestBody TxnInputEntity txnInputEntity) {
        Transaction t = this.transactionInputMapper.mapToModel(txnInputEntity);
        return new ResponseEntity<>(this.transactionService.addTransaction(t), HttpStatus.CREATED);
    }
}
