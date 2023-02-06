package com.nttdata.bootcamp.ms.bankaccount.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Setter
@Getter
@Document(collection = "accounts")
@Data
@AllArgsConstructor
@Generated
public class BankAccount {

    @Id
    private Integer accountId;
    private String accountNumber;
    private Float accountBalance;

    private String customerId;
    private String accountType;

    private Float maintenanceFee;

    private Float transactionFee;

    private Integer transactionLimit;
}
