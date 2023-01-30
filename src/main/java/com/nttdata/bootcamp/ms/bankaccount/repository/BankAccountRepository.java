package com.nttdata.bootcamp.ms.bankaccount.repository;

import com.nttdata.bootcamp.ms.bankaccount.entity.BankAccount;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankAccountRepository extends ReactiveMongoRepository<BankAccount, Integer> {

}
