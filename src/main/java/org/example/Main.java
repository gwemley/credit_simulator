package org.example;

import org.example.contoller.LoanController;
import org.example.logic.LoanCalculator;
import org.example.logic.LoanDetail;
import org.example.view.ConsoleView;

import java.io.BufferedReader;
import java.io.FileReader;

public class Main {
    public static void main(String[] args) {
        ConsoleView consoleView = new ConsoleView();
        LoanCalculator loanCalculator = new LoanCalculator();
        LoanController loanController = new LoanController(loanCalculator, consoleView);

        if (args.length > 0) {
           String filePath = args[0];
           loadInputFromFile(filePath, loanController, consoleView);
        } else {
            loanController.runWithInputFromUser();
        }

    }

    private static void loadInputFromFile(String filePath, LoanController loanController, ConsoleView consoleView) {
        try (BufferedReader buff = new BufferedReader((new FileReader(filePath)))) {
            String line;
            while ((line = buff.readLine()) != null) {
                LoanDetail loanRequest = getLoanRequest(line, consoleView);
                loanController.runWithFile(loanRequest);
            }
        } catch (Exception e) {
            consoleView.displayError(e.getMessage());
        }
    }

    private static LoanDetail getLoanRequest(String line, ConsoleView consoleView) {
        String[] inputs = line.split(",");
        String vehicleType = inputs[0].trim();
        String vehicleCondition = inputs[1].trim();
        int year = Integer.parseInt(inputs[2].trim());
        double loanAmount = Double.parseDouble(inputs[3].trim());
        int tenor = Integer.parseInt(inputs[4].trim());
        double dpAmount = Double.parseDouble(inputs[5].trim());
        return consoleView.initLoanDetail(vehicleType, vehicleCondition, year, loanAmount, tenor, dpAmount);
    }
}