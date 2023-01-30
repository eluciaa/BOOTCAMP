package com.nttdata.bootcamp.ms.bankaccount.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Setter
@Getter
@Document(collection = "accounts")
@Data
public class BankAccount {

    @Id
    private Integer accountId;
    private String accountNumber;
    private Float accountBalance;

    private Integer customerId;
    private String accountType;

    private Integer 
}
