package org.example.logic;

public class LoanDetail {

    private Double loanAmount;
    private Double downPaymentAmount;
    private Integer vehicleYear;
    private Integer tenor;
    private String vehicleType;
    private String vehicleCondition;

    public Double getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(Double loanAmount) {
        this.loanAmount = loanAmount;
    }

    public Double getDownPaymentAmount() {
        return downPaymentAmount;
    }

    public void setDownPaymentAmount(Double downPaymentAmount) {
        this.downPaymentAmount = downPaymentAmount;
    }

    public Integer getVehicleYear() {
        return vehicleYear;
    }

    public void setVehicleYear(Integer vehicleYear) {
        this.vehicleYear = vehicleYear;
    }

    public Integer getTenor() {
        return tenor;
    }

    public void setTenor(Integer tenor) {
        this.tenor = tenor;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getVehicleCondition() {
        return vehicleCondition;
    }

    public void setVehicleCondition(String vehicleCondition) {
        this.vehicleCondition = vehicleCondition;
    }
}
