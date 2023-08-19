package org.example.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
public class StudentLoanPayment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "payment_amount")
    private double paymentAmount;

    @OneToMany(mappedBy = "payment", cascade = CascadeType.ALL)
    private List<StudentLoanInstallment> installmentPlan;

    public StudentLoanPayment() {
    }

    public void setPaymentAmount(double paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public void setInstallmentPlan(List<StudentLoanInstallment> installmentPlan) {
        this.installmentPlan = installmentPlan;
    }
}
