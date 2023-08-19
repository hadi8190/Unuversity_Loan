package org.example.model;

import jakarta.persistence.*;

import java.time.LocalDate;
@Entity
@Table(name = "card_info")
public class CardInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "card_number")
    private Long cardNumber;
    @Column(name = "cvv")
    private int cvv;
    @Column(name = "ex")
    private LocalDate ex;
    @Column(name = "user_id")
    private int userId;

    public CardInfo(Long cardNumber, int cvv, LocalDate ex, int userId) {
        this.cardNumber = cardNumber;
        this.cvv = cvv;
        this.ex = ex;
        this.userId = userId;
    }

    public CardInfo() {
    }


    public Long getCardNumber() {
        return cardNumber;
    }

    public int getCvv() {
        return cvv;
    }

    public LocalDate getExp() {
        return ex;
    }

}
