package org.example.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class StudentLoanInstallment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "payment_id")
    private StudentLoanPayment payment;
    @Column(name = "payment_date")
    private LocalDate paymentDate;
    @Column(name = "installment_number")
    private int installmentNumber;

    @Column(name = "interest_payment")
    private double interestPayment;

    @Column(name = "principal_payment")
    private double principalPayment;

    @Column(name = "total_payment")
    private double totalPayment;
    @Column(name = "user_id")
    private int userAccount;
    @Column(name = "payment_status")
    private LocalDate paymentStatus;

    public StudentLoanInstallment() {
    }


    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

    public StudentLoanPayment getPayment() {
        return payment;
    }

    public void setPayment(StudentLoanPayment payment) {
        this.payment = payment;
    }

    public void setInstallmentNumber(int installmentNumber) {
        this.installmentNumber = installmentNumber;
    }

    public void setInterestPayment(double interestPayment) {
        this.interestPayment = interestPayment;
    }

    public void setPrincipalPayment(double principalPayment) {
        this.principalPayment = principalPayment;
    }

    public void setTotalPayment(double totalPayment) {
        this.totalPayment = totalPayment;
    }

    public void setUserAccount(int userAccount) {
        this.userAccount = userAccount;
    }

    public void setPaymentStatus(LocalDate paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    @Override
    public String toString() {
        return "StudentLoanInstallment{" +
                "id=" + id +
                ", payment=" + payment +
                ", paymentDate=" + paymentDate +
                ", installmentNumber=" + installmentNumber +
                ", interestPayment=" + interestPayment +
                ", principalPayment=" + principalPayment +
                ", totalPayment=" + totalPayment +
                ", paymentStatus='" + paymentStatus + '\'' +
                '}';
    }
}
