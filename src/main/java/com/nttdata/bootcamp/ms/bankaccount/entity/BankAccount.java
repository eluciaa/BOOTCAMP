package com.nttdata.bootcamp.ms.bankaccount.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Setter
@Getter
@Document(collection = "accounts")
@Data
@AllArgsConstructor
public class BankAccount {

    @Id
    private Integer accountId;
    private String accountNumber;
    private Float accountBalance;

    private Integer customerId;
    private String accountType;

    private Float maintenanceFee;

    private Float transactionFee;

    private Integer transactionLimit;
}
