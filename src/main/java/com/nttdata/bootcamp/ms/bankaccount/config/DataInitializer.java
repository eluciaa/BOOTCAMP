package com.nttdata.bootcamp.ms.bankaccount.config;

import com.nttdata.bootcamp.ms.bankaccount.entity.BankAccount;
import com.nttdata.bootcamp.ms.bankaccount.repository.RedisRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
@Slf4j
public class DataInitializer implements CommandLineRunner {

    private final RedisRepository redisRepository;

    public DataInitializer(RedisRepository redis) {
        this.redisRepository = redis;
    }

    @Override
    public void run(String[] args) {
        log.info("start data initialization  ...");
        this.redisRepository
                .deleteAll()
                .thenMany(
                        Flux
                                .just("Post one", "Post two")
                                .flatMap(
                                        title -> {
                                            Integer id = 1;
                                            return this.redisRepository.save(
                                                    BankAccount.builder().accountId(id).accountType("AHORRO").accountNumber("3452-2353-3533-3345")
                                                            .build());
                                        }
                                )
                )
                .log()
                .subscribe(
                        null,
                        null,
                        () -> log.info("done initialization...")
                );

    }

}
