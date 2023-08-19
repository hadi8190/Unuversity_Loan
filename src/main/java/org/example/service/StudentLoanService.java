package org.example.service;

import org.example.model.StudentLoan;
import org.example.model.UserAccount;
import org.example.repository.StudentLoanRepo;
import org.example.repository.UserAccountRepo;

public class StudentLoanService {
    public void createLoan(String type, int id, int payment) {
        StudentLoanRepo studentLoanRepo = new StudentLoanRepo();
        StudentLoan studentLoan = new StudentLoan(type, payment, id);
        studentLoanRepo.createLoan(studentLoan);
    }

    public void createLoanForHouse(String type,String address, int payment, Long houseNumber, String nationalCode, int id) {
        StudentLoanRepo studentLoanRepo = new StudentLoanRepo();
        StudentLoan studentLoan = new StudentLoan(type, payment, nationalCode, houseNumber, address, id);
        studentLoanRepo.createLoan(studentLoan);
    }

    public boolean addLoan(String loanType, int userAccount) {
        StudentLoanRepo studentLoanRepo = new StudentLoanRepo();
        StudentLoan studentLoan = studentLoanRepo.findLoanByLoanType(loanType, userAccount);
        if (loanType != null) {
            if (studentLoan == null) {
                System.out.println("You Didn't Get This.");
                return false;
            } else if (studentLoan.getLoanType().equals(loanType)) {
                System.out.println("You got This Loan Before.");
                return true;
            }
        }//catch
        System.out.println("Wait...");
        return true;
    }


    public boolean checkSpouseLoan(String loanType,String nationalCode) {
        StudentLoanRepo studentLoanRepo = new StudentLoanRepo();
        StudentLoan studentLoan = studentLoanRepo.findLoanByNationalCode(loanType, nationalCode);
        if (studentLoan == null) {
            return true;
        } else if (studentLoan.getNationalCode().equals(nationalCode)){
            return false;
        }
        System.out.println("Wait...");
        return false;
    }
}
