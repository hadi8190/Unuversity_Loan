package org.example.service;

import org.example.model.StudentLoanInstallment;
import org.example.repository.StudentLoanInstallmentRepo;

import java.util.ArrayList;

public class StudentLoanInstallmentService {
    public void changePaymentStatus(int number, int userAccount) {
        StudentLoanInstallmentRepo studentLoanInstallmentRepo = new StudentLoanInstallmentRepo();
            studentLoanInstallmentRepo.updatePaymentStatus(number,userAccount);
            System.out.println("Payment Changed to 'Yes'");
    }


    public void showTableForPayment(int id){
        StudentLoanInstallmentRepo studentLoanInstallmentRepo = new StudentLoanInstallmentRepo();
        ArrayList<StudentLoanInstallment> studentLoanInstallments = studentLoanInstallmentRepo.showTableForPayment(id);
        for (int i = 0; i <studentLoanInstallments.size() ; i++) {
            System.out.println(studentLoanInstallments.get(i));
        }
    }

    public void showTableForPaymentStatusYes(int id){
        StudentLoanInstallmentRepo studentLoanInstallmentRepo = new StudentLoanInstallmentRepo();
        ArrayList<StudentLoanInstallment> studentLoanInstallments = studentLoanInstallmentRepo.showTableForPaymentStatusYes(id);
        for (int i = 0; i <studentLoanInstallments.size() ; i++) {
            System.out.println(studentLoanInstallments.get(i));
        }
    }
}
