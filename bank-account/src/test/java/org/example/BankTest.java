package org.example;

import org.junit.jupiter.api.BeforeEach;

import static org.mockito.Mockito.mock;

class BankTest {

    private Bank bank;
    private AccountService accountService;

    @BeforeEach
    public void setUp() {
        accountService = mock(AccountService.class);
        bank = new Bank(accountService);
    }
}
