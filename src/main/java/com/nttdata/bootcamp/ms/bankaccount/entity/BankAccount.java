package com.nttdata.bootcamp.ms.bankaccount.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document(collection = "accounts")
@Data
@AllArgsConstructor
@Generated
@Builder
public class BankAccount implements Serializable {

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
