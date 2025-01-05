package org.example.logic;

import org.example.constant.GeneralConstant;
import org.example.exception.CreditSimulatorGeneralException;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.example.constant.GeneralConstant.ErrorMsg.*;
import static org.example.constant.GeneralConstant.InterestRate.INTEREST_BIKE_RATE;
import static org.example.constant.GeneralConstant.InterestRate.INTEREST_CAR_RATE;
import static org.example.constant.GeneralConstant.VehicleCondition.*;
import static org.example.constant.GeneralConstant.DownPaymentPercentage.*;
import static org.example.constant.GeneralConstant.VehicleType.*;
import static org.example.constant.GeneralConstant.Tenor.*;

public class LoanCalculator {
    private static final BigDecimal maxAllowedAmount = BigDecimal.valueOf(1000000000);

    public List<String> calculateMonthlyInstallment(LoanDetail loanDetail) {
        int currentYear = LocalDate.now().getYear() - 1;
        List<String> yearlyInstallments = new ArrayList<>();

        double vehicleInterestRate = loanDetail.getVehicleType().equalsIgnoreCase(CAR) ? INTEREST_CAR_RATE : INTEREST_BIKE_RATE;
        Double downPaymentPercentage = loanDetail.getVehicleCondition().equalsIgnoreCase(NEW.toString()) ? NEW_VEHICLE : USED_VEHICLE;

        if (loanDetail.getVehicleCondition().equalsIgnoreCase(NEW.getValue()) &&
        loanDetail.getVehicleYear() < currentYear) {
            throw new CreditSimulatorGeneralException(VEHICLE_YEAR_RULE.concat(String.valueOf(currentYear)));
        }

        double totalLoanAmount = validateAndCalculate(loanDetail, downPaymentPercentage, vehicleInterestRate);

        for (int year = 1; year <= loanDetail.getTenor(); year++) {
            double yearlyInterestRate = vehicleInterestRate + (0.1 * (year - 1));
            if (year > 1 && year % 2 == 0) {
                yearlyInterestRate += 0.5;
            }

            double monthlyInstallment = (totalLoanAmount * (1 + (yearlyInterestRate / 100))) / (loanDetail.getTenor() * 12);

            yearlyInstallments.add(String.format("tahun %d : Rp %s/bln , Suku Bunga : %.1f%%",
                    year, formatInstallment(new BigDecimal(monthlyInstallment)), yearlyInterestRate));
        }
        return yearlyInstallments;


    }

    private static Double validateAndCalculate(LoanDetail loanDetail,
                                         Double downPaymentPercentage,
                                         Double vehicleInterestRate) {
        Double loanAmountWithPercentage = loanDetail.getLoanAmount() * downPaymentPercentage;

        if (loanDetail.getTenor() < MIN_TENOR || loanDetail.getTenor() > MAX_TENOR) {
            throw new CreditSimulatorGeneralException(TENOR_RULE);
        }

        if (loanDetail.getDownPaymentAmount() < loanAmountWithPercentage) {
            throw new CreditSimulatorGeneralException(GeneralConstant.ErrorMsg.DOWN_PAYMENT_RULE);
        }

        if (BigDecimal.valueOf(loanDetail.getLoanAmount()).compareTo(maxAllowedAmount) > 0) {
            throw new CreditSimulatorGeneralException(MAX_OF_LOAN);
        }

        Double loanAfterDownPayment = loanDetail.getLoanAmount() - loanDetail.getDownPaymentAmount();
        Double totalInterests = loanAfterDownPayment * vehicleInterestRate * loanDetail.getTenor();
        return loanAfterDownPayment + totalInterests;
    }

    public String formatInstallment(BigDecimal installmentAmount) {

        String pattern;
        if (installmentAmount.compareTo(new BigDecimal("1000000000")) >= 0) {
            pattern = "#,###,###,###.00";
        } else if (installmentAmount.compareTo(new BigDecimal("1000000")) >= 0) {
            pattern = "#,###,###.00";
        } else if (installmentAmount.compareTo(new BigDecimal("1000")) >= 0) {
            pattern = "#,###.00";
        } else {
            pattern = "#.00";
        }

        DecimalFormat df = new DecimalFormat(pattern);
        return "Rp " + df.format(installmentAmount);
    }
}


//tahun 1 : Rp Rp 700,560.00/bln , Suku Bunga : 0.1%
//tahun 2 : Rp Rp 704,760.00/bln , Suku Bunga : 0.7%
//tahun 3 : Rp Rp 701,960.00/bln , Suku Bunga : 0.3%
//tahun 4 : Rp Rp 706,160.00/bln , Suku Bunga : 0.9%
//tahun 5 : Rp Rp 703,360.00/bln , Suku Bunga : 0.5%