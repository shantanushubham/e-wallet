package org.geeksforgeeks.e_wallet.entities.input;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@Builder
@With
@NoArgsConstructor
@AllArgsConstructor
public class TxnInputEntity {

    @NotNull
    private long senderId;

    @NotNull
    private long receiverId;

    @Min(0)
    @Max(10_000L)
    private long amount;
}
