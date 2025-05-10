package org.geeksforgeeks.e_wallet.repository;

import org.geeksforgeeks.e_wallet.entities.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletRepository extends JpaRepository<Wallet, Long> {
}
