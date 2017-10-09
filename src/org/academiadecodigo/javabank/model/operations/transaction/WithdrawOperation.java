package org.academiadecodigo.javabank.model.operations.transaction;

import org.academiadecodigo.javabank.view.BankApplication;

public class WithdrawOperation extends AbstractAccountTransactionOperation {

    public WithdrawOperation(BankApplication bankApplication) {
        super(bankApplication);
    }

    @Override
    public void execute() {

        super.execute();

        Integer accountId = scanAccount();
        Double amount = scanAmount();

        if (customer.getAccountIds().contains(accountId)) {
            accountManager.withdraw(accountId, amount);
        }
    }
}
