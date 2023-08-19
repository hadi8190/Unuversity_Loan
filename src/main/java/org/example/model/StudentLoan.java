package org.example.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "student_loan")
public class StudentLoan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "loan_type")
    private String loanType;
    @Column(name = "payment")
    private int payment;
    @Column(name = "nationalcode")
    private String nationalCode;
    @Column(name = "housing_rental_agreement_number")
    private Long housingRentalAgreementNumber;
    @Column(name = "address")
    private String address;
    @Column(name = "user_id")
    private int userAccount;

    public StudentLoan(){

    }

    public StudentLoan(String loanType, int payment, String nationalCode, Long housingRentalAgreementNumber, String address, int userAccount) {
        this.loanType = loanType;
        this.payment = payment;
        this.nationalCode = nationalCode;
        this.housingRentalAgreementNumber = housingRentalAgreementNumber;
        this.address = address;
        this.userAccount = userAccount;
    }

    public StudentLoan(String loanType, int payment , int userAccount) {
        this.loanType = loanType;
        this.payment = payment;
        this.userAccount = userAccount;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLoanType() {
        return loanType;
    }

    public int getPayment() {
        return payment;
    }

    public void setPayment(int payment) {
        this.payment = payment;
    }

    public String getNationalCode() {
        return nationalCode;
    }

    public void setUserAccount(int userAccount) {
        this.userAccount = userAccount;
    }
}
