package org.example.constant;

public class GeneralConstant {

    public static class ErrorMsg {
        public static final String VEHICLE_YEAR_RULE = "Tahun kendaraan baru tidak boleh kurang dari tahun sekarang ";
        public static final String TENOR_RULE = "Tenor harus antara 1 dan 6 tahun";
        public static final String DOWN_PAYMENT_RULE = "Jumlah DP tidak mencukupi untuk jenis kendaraan dan kondisi yang dipilih";
        public static final String MAX_OF_LOAN = "Melebihi jumlah Maximal pinjaman";
    }

    public static class VehicleType {
        public static final String CAR = "MOBIL";
        public static final String MOTORCYCLE = "MOTOR";
    }

    public static class InterestRate {
        public static final Double INTEREST_BIKE_RATE = 0.09;
        public static final Double INTEREST_CAR_RATE = 0.08;
    }

    public static class Tenor {
        public static final Integer MAX_TENOR = 6;
        public static final Integer MIN_TENOR = 1;
    }

    public static class DownPaymentPercentage {
        public static final Double NEW_VEHICLE = 0.35;
        public static final Double USED_VEHICLE =  0.25;
    }

    public static enum VehicleCondition {
        NEW("Baru"),
        USED("Bekas");

        final String value;

        VehicleCondition(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }


}
