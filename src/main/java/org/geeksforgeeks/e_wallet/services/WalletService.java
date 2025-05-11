package org.geeksforgeeks.e_wallet.services;

import feign.FeignException;
import org.geeksforgeeks.e_wallet.clients.PaymentGatewayClient;
import org.geeksforgeeks.e_wallet.dto.PaymentGatewayRequest;
import org.geeksforgeeks.e_wallet.entities.User;
import org.geeksforgeeks.e_wallet.entities.Wallet;
import org.geeksforgeeks.e_wallet.repository.UserRepository;
import org.geeksforgeeks.e_wallet.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WalletService {

    private final WalletRepository walletRepository;
    private final PaymentGatewayClient paymentGatewayClient;
    private final UserRepository userRepository;

    @Autowired
    public WalletService(WalletRepository walletRepository, PaymentGatewayClient paymentGatewayClient,
                         UserRepository userRepository) {
        this.walletRepository = walletRepository;
        this.paymentGatewayClient = paymentGatewayClient;
        this.userRepository = userRepository;
    }

    public boolean addMoney(String phoneNo, double amount) {
        // TODO: Handle this properly
        User user = this.userRepository.findByPhoneNo(phoneNo).orElseThrow(() -> new RuntimeException(""));
        Wallet wallet = user.getWallet();
        try {
            this.paymentGatewayClient.capturePayment(new PaymentGatewayRequest(amount));
        } catch (FeignException.FeignClientException ex) {
            System.out.println("Payment Gateway failed.");
            return false;
        }
        wallet.modifyBalanceBy(amount);
        this.walletRepository.save(wallet);
        return true;
    }
}
