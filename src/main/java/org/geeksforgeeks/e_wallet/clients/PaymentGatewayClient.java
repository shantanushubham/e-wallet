package org.geeksforgeeks.e_wallet.clients;

import org.geeksforgeeks.e_wallet.dto.PaymentGatewayRequest;
import org.geeksforgeeks.e_wallet.dto.PaymentGatewayResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "payment-gateway-client", url = "http://localhost:9000")
public interface PaymentGatewayClient {

    @PostMapping("/make_payment")
    PaymentGatewayResponse capturePayment(PaymentGatewayRequest request);

}
