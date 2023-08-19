package org.example.model;

public enum BigCity {
    DUMMY(1,""),
    TEHRAN(32000000,"Once during each section"),
    GILAN(26000000,"Once during each section"),
    ESFEHAN(26000000,"Once during each section"),
    EASTAZARBAYEJAN(26000000,"Once during each section"),
    FARS(26000000,"Once during each section"),
    KHOZESTAN(26000000,"Once during each section"),
    QOM(26000000,"Once during each section"),
    MASHHAD(26000000,"Once during each section"),
    ALBORZ(26000000,"Once during each section"),
    ANOTHERCITY(19500000,"Once during each section");

    private final int value;
    private final String payment;

    BigCity(int value, String payment) {
        this.value = value;
        this.payment = payment;
    }

    public String getPayment() {
        return payment;
    }

    public int getValue() {
        return value;
    }
}
