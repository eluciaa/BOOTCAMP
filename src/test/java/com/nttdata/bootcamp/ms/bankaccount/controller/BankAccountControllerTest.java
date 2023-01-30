package com.nttdata.bootcamp.ms.bankaccount.controller;

import com.nttdata.bootcamp.ms.bankaccount.entity.BankAccount;
import com.nttdata.bootcamp.ms.bankaccount.service.BankAccountService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@WebFluxTest(BankAccountController.class)
public class BankAccountControllerTest {

    @MockBean
    private BankAccountService bankAccountService;

    @Autowired
    private WebTestClient webTestClient;

    /* Test to return all the bank account registered at the moment */
    @Test
    void getAll(){
        Flux<BankAccount> bankAccountFlux = Flux.just(new BankAccount(1, "3459",
                20.1F, 1, "AHORRO FIJO", 3F, 4F,
                2),new BankAccount(2, "345934",
                30.1F, 1, "AHORRO", 1F, 2F,
                4));

        when(bankAccountService.getAll()).thenReturn(bankAccountFlux);

        Flux<BankAccount> responseBody = webTestClient.get()
                .uri("/accounts")
                .accept(MediaType.APPLICATION_NDJSON)
                .exchange()
                .expectStatus().isOk()
                .returnResult(BankAccount.class)
                .getResponseBody();

        StepVerifier.create(responseBody)
                .expectSubscription()
                .expectNext(new BankAccount(1, "3459",
                        20.1F, 1, "AHORRO FIJO", 3F, 4F,
                        2))
                .expectNext(new BankAccount(2, "345934",
                        30.1F, 1, "AHORRO", 1F, 2F,
                        4))
                .verifyComplete();
    }

    /* Test to return the bank account associated with an ID */
    @Test
    void getAccountById(){
        BankAccount bankAccount = new BankAccount(1, "3459",
                20.1F, 1, "AHORRO FIJO", 3F, 4F,
                2);

        Mono<BankAccount> bankAccountMono = Mono.just(new BankAccount(1, "3459",
                20.1F, 1, "AHORRO FIJO", 3F, 4F,
                2 ));

        when (bankAccountService.getAccountById(bankAccountMono.block().getAccountId()))
                .thenReturn(bankAccountMono);

        webTestClient.get()
                .uri("/accounts/account/" + bankAccountMono.block().getAccountId())
                .accept(MediaType.APPLICATION_NDJSON)
                .exchange()
                .expectStatus().isOk()
                .returnResult(BankAccount.class)
                .getResponseBody();

        StepVerifier.create(bankAccountMono)
                .expectNext(bankAccount)
                .verifyComplete();
    }

    @Test
    void getAccountByCustomerId(){

        BankAccount bankAccount = new BankAccount(1, "3459",
                20.1F, 1, "AHORRO FIJO", 3F, 4F,
                2);

        Flux<BankAccount> bankAccountFlux = Flux.just(new BankAccount(1, "4837-3954-3958-4565",
                20.1F, 1, "AHORRO FIJO", 3F, 4F,
                2),new BankAccount(2, "4837-3954-3958-4566",
                30.1F, 1, "AHORRO", 1F, 2F,
                4), new BankAccount(3, "4837-3954-3958-4567",
                        30.1F, 1, "CUENTA CORRIENTE", 1F, 2F,
                        4));

        when(bankAccountService.getAccountByCustomerId(bankAccount.getCustomerId())).thenReturn(bankAccountFlux);

        Flux<BankAccount> responseBody = webTestClient.get()
                .uri("/accounts/customer/" + bankAccount.getCustomerId())
                .accept(MediaType.APPLICATION_NDJSON)
                .exchange()
                .expectStatus().isOk()
                .returnResult(BankAccount.class)
                .getResponseBody();

        StepVerifier.create(responseBody)
                .expectSubscription()
                .expectNext(new BankAccount(1, "4837-3954-3958-4565",
                        20.1F, 1, "AHORRO FIJO", 3F, 4F,
                        2))
                .expectNext(new BankAccount(2, "4837-3954-3958-4566",
                        30.1F, 1, "AHORRO", 1F, 2F,
                        4))
                .expectNext(new BankAccount(3, "4837-3954-3958-4567",
                        30.1F, 1, "CUENTA CORRIENTE", 1F, 2F,
                        4))
                .verifyComplete();
    }

    @Test
    void saveAccount(){

        Mono<BankAccount> bankAccountMono = Mono.just(new BankAccount(1, "4837-3954-3958-4565",
                20.1F, 1, "AHORRO FIJO", 3F, 4F,
                2 ));

        BankAccount bankAccount = new BankAccount(1, "4837-3954-3958-4565",
                20.1F, 1, "AHORRO FIJO", 3F, 4F,
                2);

        when(bankAccountService.saveAccount(bankAccount))
                .thenReturn(bankAccountMono);

        webTestClient.post()
                .uri("/accounts")
                .body(bankAccountMono, BankAccount.class)
                .exchange()
                .expectStatus().isCreated();

        StepVerifier.create(bankAccountMono)
                .expectNext(bankAccount)
                .verifyComplete();
    }

    @Test
    void updateAccount(){

        BankAccount bankAccount = new BankAccount(1, "4837-3954-3958-4565",
                20.1F, 1, "AHORRO FIJO", 3F, 4F,
                2);

        Mono<BankAccount> bankAccountMono = Mono.just(new BankAccount(1, "4837-3954-3958-4565",
                20.1F, 1, "AHORRO FIJO", 3F, 4F,
                2 ));

        when(bankAccountService.updateAccount(bankAccount))
                .thenReturn(bankAccountMono);

        webTestClient.put()
                .uri("/accounts")
                .body(bankAccountMono, BankAccount.class)
                .exchange()
                .expectStatus().isOk()
                .returnResult(BankAccount.class)
                .getResponseBody();

        StepVerifier.create(bankAccountMono)
                .expectNext(bankAccount)
                .verifyComplete();

    }

    @Test
    void deleteAccount(){
        given(bankAccountService.deleteAccount(any())).willReturn(Mono.empty());
        webTestClient.delete()
                .uri("/accounts/1")
                .exchange()
                .expectStatus().isOk();

    }
}
