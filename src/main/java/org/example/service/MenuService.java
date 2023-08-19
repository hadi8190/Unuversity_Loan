package org.example.service;

public class MenuService {
    public static void menuForLoginAndRegister(){
        System.out.println("-------------------- WELCOME TO LOAN HOME --------------------");
        System.out.println("\tPress your choice: ");
        System.out.println("\t1. Login");
        System.out.println("\t2. Register");
    }

    public static void MenuForLoanType(){
        System.out.println("==================== LOAN TYPE ====================");
        System.out.println("\tPress your choice: ");
        System.out.println("\t1. Education Loan");
        System.out.println("\t2. House Loan");
        System.out.println("\t3. Student Loan");
    }
    public static void MenuForStudentLoan(){
        System.out.println("******************** HOME MENU ********************");
        System.out.println("\tPress your choice: ");
        System.out.println("\t1. Create Loan");
        System.out.println("\t2. Amount");
    }

    public static void MenuForPayment(){
        System.out.println(".................... PAYMENT MENU ...................");
        System.out.println("\tPress your choice: ");
        System.out.println("\t1. Loan Payments");
        System.out.println("\t2. Installments Paid");
        System.out.println("\t3. Unpaid Installments");
    }

    public static void Banks(){
        System.out.println("\tPress your choice: ");
        System.out.println("\t1. MELI BANK");
        System.out.println("\t2. REFAH BANK");
        System.out.println("\t3. TEJARAT BANK");
        System.out.println("\t4. MASKAN BANK");
    }

}
