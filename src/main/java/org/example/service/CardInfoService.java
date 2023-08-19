package org.example.service;

import org.example.model.CardInfo;
import org.example.model.StudentLoan;
import org.example.model.UserAccount;
import org.example.repository.CardInfoRepo;
import org.example.repository.StudentLoanRepo;
import org.example.repository.UserAccountRepo;

import java.time.LocalDate;

public class CardInfoService {
    public void createCard(Long cardNumber , int cvv , LocalDate exp , int id){
        CardInfoRepo cardInfoRepo = new CardInfoRepo();
        CardInfo cardInfo = new CardInfo(cardNumber,cvv,exp,id);
        cardInfoRepo.createCard(cardInfo);
    }

    public boolean checkCard(Long cardNumber , int cvv , LocalDate exp , int id) {
        CardInfoRepo cardInfoRepo = new CardInfoRepo();
        CardInfo cardInfo = cardInfoRepo.findCard(cardNumber,cvv,exp,id);
        if (cardInfo != null) {
            if (cardInfo.getCardNumber().equals(cardNumber) || cardInfo.getCvv() == cvv || cardInfo.getExp() == exp || cardInfo.getCardNumber().equals(id)) {
                System.out.println("Card Exist.");
                return true;
            }
            return true;
        }else {

            return false;
        }
    }
}
