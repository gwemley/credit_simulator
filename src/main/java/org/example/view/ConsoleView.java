package org.example.view;

import org.example.logic.LoanDetail;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

public class ConsoleView {

    private final Scanner scanner;

    public ConsoleView() {
        scanner = new Scanner(System.in);
    }

    public void displayMessage(List<String> message) {
        message.forEach(System.out::println);
    }

    public void displayError(String message) {
        System.out.println("Error: " + message);
    }

    public LoanDetail displayInputForm() {
        System.out.print("Masukkan Jenis Kendaraan (Motor/Mobil): ");
        String vehicleType = scanner.nextLine();

        System.out.print("Masukkan Kondisi Kendaraan (Baru/Bekas): ");
        String vehicleCondition = scanner.nextLine();

        System.out.print("Masukkan Tahun Kendaraan: ");
        int year = Integer.parseInt(scanner.nextLine());

        System.out.print("Masukkan Jumlah Pinjaman Total: ");
        double loanAmount = Double.parseDouble(scanner.nextLine());

        System.out.print("Masukkan Tenor Pinjaman (1-6 tahun): ");
        int tenor = Integer.parseInt(scanner.nextLine());

        System.out.print("Masukkan Jumlah DP: ");
        double dpAmount = Double.parseDouble(scanner.nextLine());

        return initLoanDetail(vehicleType, vehicleCondition, year, loanAmount, tenor, dpAmount);
    }

    public LoanDetail initLoanDetail(String vehicleType, String vehicleCondition, int year, double loanAmount, int tenor, double dpAmount) {
        LoanDetail loanDetail = new LoanDetail();
        loanDetail.setVehicleType(vehicleType);
        loanDetail.setVehicleCondition(vehicleCondition);
        loanDetail.setVehicleYear(year);
        loanDetail.setLoanAmount(loanAmount);
        loanDetail.setTenor(tenor);
        loanDetail.setDownPaymentAmount(dpAmount);
        return loanDetail;
    }


}
