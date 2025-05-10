package org.geeksforgeeks.e_wallet.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.geeksforgeeks.e_wallet.enums.TransactionStatus;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "transaction")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "sender_id", nullable = false)
    private User sender;

    @ManyToOne
    @JoinColumn(name = "receiver_id", nullable = false)
    private User receiver;

    @Column(name = "amount", nullable = false)
    private double amount;

    @Column(name = "txn_time", nullable = false)
    @Builder.Default
    private LocalDateTime transactionTime = LocalDateTime.now();

    @Column(name = "status")
    @Builder.Default
    private TransactionStatus transactionStatus = TransactionStatus.SUCCESS;
}
