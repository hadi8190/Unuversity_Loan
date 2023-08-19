package org.example.model;

public enum LoanForStudent {
    DUMMY(1,""),
    KARDANI(1300000,"  Once during each med term") ,
    KARSHENASI_PEYVASTE(1300000,"  Once during each med term"),
    KARSHENASI_NAPEYVASTE(1300000,"  Once during each med term"),
    KARSHENASI_ARSHAD_PEYVASTE(2600000,"  Once during each med term"),
    KARSHENASI_ARSHAD_NAPEYVASTE(2600000,"  Once during each med term"),
    DOCTORA_HERFEIE(2600000,"  Once during each med term"),
    DOCTORA_PEYVASTE(2600000,"  Once during each med term"),
    DOCTORA_TAKHASOSI_NAPEYVASTE(65000000,"  Once during each med term");
    private final int values;
    private final String payment;

    LoanForStudent(int values, String payment) {
        this.values = values;
        this.payment = payment;
    }

    public int getValues() {
        return values;
    }

    public String getPayment() {
        return payment;
    }
}
