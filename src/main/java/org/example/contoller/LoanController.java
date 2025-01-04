package org.example.contoller;

import org.example.logic.LoanCalculator;
import org.example.logic.LoanDetail;
import org.example.view.ConsoleView;

import java.math.BigDecimal;
import java.util.List;

public class LoanController {

    private final LoanCalculator loanCalculator;
    private final ConsoleView consoleView;

    public LoanController(LoanCalculator loanCalculator, ConsoleView consoleView) {
        this.loanCalculator = loanCalculator;
        this.consoleView = consoleView;
    }

    public void runWithInputFromUser() {
        try {
            LoanDetail loanDetail = consoleView.displayInputForm();
            List<String> monthlyPayment = loanCalculator.calculateMonthlyInstallment(loanDetail);
            consoleView.displayMessage(monthlyPayment);
        } catch (Exception e) {
            consoleView.displayError(e.getMessage());
        }

    }

    public void runWithFile(LoanDetail loanRequest) {
        try {
            List<String> monthlyInstallment = loanCalculator.calculateMonthlyInstallment(loanRequest);
            consoleView.displayMessage(monthlyInstallment);
        } catch (IllegalArgumentException e) {
            consoleView.displayError(e.getMessage());
        }
    }
}
