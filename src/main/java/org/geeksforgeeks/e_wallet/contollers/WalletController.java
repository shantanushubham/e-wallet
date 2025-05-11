package org.geeksforgeeks.e_wallet.contollers;

import org.geeksforgeeks.e_wallet.services.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/wallet")
public class WalletController {

    private final WalletService walletService;

    @Autowired
    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    @PostMapping("/add_money")
    public ResponseEntity<?> addMoney(@AuthenticationPrincipal UserDetails userDetails, @RequestParam double amount) {
        boolean isSuccess = this.walletService.addMoney(userDetails.getUsername(), amount);
        if (isSuccess) {
            return ResponseEntity.ok(isSuccess);
        } return ResponseEntity.badRequest().build();
    }
}
