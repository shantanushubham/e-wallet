package org.geeksforgeeks.e_wallet.mapper;

import org.geeksforgeeks.e_wallet.entities.Transaction;
import org.geeksforgeeks.e_wallet.entities.User;
import org.geeksforgeeks.e_wallet.entities.input.TxnInputEntity;
import org.geeksforgeeks.e_wallet.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TransactionInputMapper {

    private final UserRepository userRepository;

    @Autowired
    public TransactionInputMapper(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Transaction mapToModel(TxnInputEntity input) {
        List<User> list = this.userRepository.findAllById(List.of(input.getSenderId(), input.getReceiverId()));
        User sender = null;
        User receiver = null;
        for (User u: list) {
            if (u.getId() == input.getSenderId()) {
                sender = u;
            } else if (u.getId() == input.getReceiverId()) {
                receiver = u;
            }
        }
        return Transaction.builder()
                .sender(sender)
                .receiver(receiver)
                .amount(input.getAmount())
                .build();
    }
}
