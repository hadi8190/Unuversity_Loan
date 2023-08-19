package org.example;

import org.example.model.*;
import org.example.service.*;

import java.security.SecureRandom;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;


import static org.example.repository.StudentLoanInstallmentRepo.insertStudentLoanPayment;

public class Main {
    public static Scanner scanner = new Scanner(System.in);
    private static StudentLoanInstallmentService studentLoanInstallmentService = new StudentLoanInstallmentService();
    private static StudentLoanService studentLoanService = new StudentLoanService();
    private static String pattern = "[a-zA-Z]+";
    private static String patternForNumber = "^(?:[1-9]|10)$";
    private static String patternForPass = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}";
    private static final String UPPERCASE_LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LOWERCASE_LETTERS = "abcdefghijklmnopqrstuvwxyz";
    private static final String ACTION = "@#$%^&+=";
    private static final String DIGITS = "0123456789";


    public static void main(String[] args) {
        Main main = new Main();

        boolean flag = false;
        boolean flag1 = false;
        boolean flag2 = false;

        while (!flag) {
            try {
                MenuService.menuForLoginAndRegister();
                int choice = Integer.parseInt(main.getValidInput(scanner, "Please enter your choice: ", input -> input.matches(patternForNumber)));
                Integer.valueOf(choice);
                switch (choice) {
                    case 1:
                        while (main.login()){
                            flag = false;
                        }
                        while (!flag1) {
                            MenuService.MenuForStudentLoan();
                            int choice1 = Integer.parseInt(main.getValidInput(scanner, "Please enter your choice: ", input -> input.matches(patternForNumber)));
                            switch (choice1) {
                                case 1:
//                                    if (main.checkGraduation() == true){
//                                    }else {
//                                        break;
//                                    }
                                    MenuService.MenuForLoanType();
                                    int choice2 = Integer.parseInt(main.getValidInput(scanner, "Please enter your choice: ", input -> input.matches(patternForNumber)));

                                    switch (choice2) {
                                        case 1:
//                                            main.checkDate();
                                                try {
                                                    if (UserAccountService.loggedInUser.getUniversityType() == 1){
                                                        System.out.println("You Cannot add This Loan.");
                                                        break;
                                                    }else {
                                                        System.out.println("Wait...");
                                                    }

                                                    if (main.checkIfStudentHasLoan(String.valueOf(choice2),UserAccountService.loggedInUser.getId())){
                                                        System.out.println("Wait...");
                                                    }else {
                                                        System.out.println("Failed!");
                                                        break;
                                                    }

                                                    main.loanMenuForEducation(String.valueOf(choice2));

                                                }catch (NullPointerException e){
                                                    System.out.println("Enter Correct Information");
                                                }

                                            break;
                                        case 2:
//                                            main.checkDate();
                                            try {
                                               if (main.checkIfStudentHasLoan(String.valueOf(choice2),UserAccountService.loggedInUser.getId())){
                                                   System.out.println("Wait...");
                                               }else {
                                                   System.out.println("Failed!");
                                                   break;
                                               }


                                                if (main.checkSpouse() == true) {
                                                    System.out.println("Wait...");
                                                }else {
                                                    System.out.println("Failed!");
                                                    break;
                                                }



                                                if (main.checkLoanForSpouse(String.valueOf(choice2)) == true){
                                                    System.out.println("Wait...");
                                                }else {
                                                    System.out.println("Failed!");
                                                    break;
                                                }



                                                Long houseId = (long) Integer.parseInt(main.getValidInput(scanner, "Please enter your House number: ", input -> input.matches("\\d+")));
                                                Integer.valueOf(Math.toIntExact(houseId));
                                                System.out.println("Enter your address: ");
                                                String address = scanner.next();
                                                String.valueOf(address);

                                               main.checkCity(String.valueOf(choice2),houseId,address);

                                            }catch (NullPointerException e){
                                                System.out.println("Enter Correct Information");
                                            }

                                            break;
                                        case 3:
//                                            main.checkDate();
                                            try {

                                                 if (main.checkIfStudentHasLoan(String.valueOf(choice2),UserAccountService.loggedInUser.getId())){
                                                     System.out.println("Wait...");
                                                 }else {
                                                     System.out.println("Failed!");
                                                     break;
                                                 }


                                                 main.addStudentLoan(String.valueOf(choice2));


                                                }catch (NullPointerException e){
                                                    System.out.println("Enter Correct Information");
                                                }

                                            break;
                                    }break;
                                case 2:
//                                    if (main.checkGraduation() == true){
//                                    }else {
//                                        break;
//                                    }

                                    MenuService.MenuForPayment();

                                    while (!flag2) {
                                        int choice3 = Integer.parseInt(main.getValidInput(scanner, "Please enter your choice: ", input -> input.matches(patternForNumber)));

                                        switch (choice3) {

                                            case 1:
                                                int yesOrNo = Integer.parseInt(main.getValidInput(scanner, "Do You Have Card? 1. NO  2. Yes ", input -> input.matches(patternForNumber)));
                                                if (yesOrNo == 1) {
                                                    main.addCard();
                                                }else {
                                                    while (main.getCardFromUser() == false){
                                                        flag2 = false;
                                                    }
                                                }
                                                MenuService.Banks();
                                                int chooseBank = Integer.parseInt(main.getValidInput(scanner, "Witch bank do you want to pay? ", input -> input.matches("[1-3]")));
                                                int yesOrNO = Integer.parseInt(main.getValidInput(scanner, "Plese Enter Number 1 to pay? ", input -> input.matches("[1]")));
                                                studentLoanInstallmentService.changePaymentStatus(yesOrNO,UserAccountService.loggedInUser.getId());
                                                break;
                                            case 2:
                                                studentLoanInstallmentService.showTableForPaymentStatusYes(UserAccountService.loggedInUser.getId());
                                                break;
                                            case 3:
                                                studentLoanInstallmentService.showTableForPayment(UserAccountService.loggedInUser.getId());
                                                break;
                                        }
                                    }
                            }
                        }
                    case 2:
                        main.register();
                }
            } catch (InputMismatchException e) {
                System.out.println("Please Enter Correct Number.");
                break;
            }
        }

    }
















    public boolean login() {
        UserAccountService userAccountService = new UserAccountService();
        System.out.println("Enter your userName(Your nationalcode): ");
        String nationalCode = scanner.nextLine();
        System.out.println("Enter your Password: ");
        String password = scanner.nextLine();
        if (userAccountService.login(nationalCode, password)) {
            return false;
        } else {
            return true;
        }
    }










    public void register() {
        UserAccountService userAccountService = new UserAccountService();
        try {


            String name = getValidInput(scanner, "Please enter your Name: ", input -> input.matches(pattern));
            if (name.matches(pattern) != true) {
                System.out.println("Enter Correct Name.");
            }


            String lastName = getValidInput(scanner, "Please enter your Last Name: ", input -> input.matches(pattern));
            if (lastName.matches(pattern) != true) {
                System.out.println("Enter Correct lastName.");
            }


            String fatherName = getValidInput(scanner, "Please enter your Father Name: ", input -> input.matches(pattern));
            if (fatherName.matches(pattern) != true) {
                System.out.println("Enter Correct fatherName.");
            }


            String motherName = getValidInput(scanner, "Please enter your Mother Name: ", input -> input.matches(pattern));
            if (motherName.matches(pattern) != true) {
                System.out.println("Enter Correct motherName.");
            }


            String idNumber = getValidInput(scanner, "Please enter your Id Number: ", input -> input.matches("\\d+"));
            Integer.valueOf(idNumber);


            String nationalCode = getValidInput(scanner, "Please enter your NationalCode: ", input -> input.matches("^(\\d)(?!\\1{9})\\d{9}$"));
            Integer.valueOf(nationalCode);



            LocalDate birthDay1 = askForValidBirthdate();


            for (int i = 1 ; i < BigCity.values().length;i++) {
                System.out.println(i + ". " + BigCity.values()[i]);
            }

            String city = getValidInput(scanner, "Please enter your City: ", input -> input.matches(patternForNumber));


            Long studentId = Long.valueOf(getValidInput(scanner, "Please enter your Student Id: ", input -> input.matches("\\d+")));
            Long.valueOf(studentId);


            String universityName = getValidInput(scanner, "Please enter your University Name: ", input -> input.matches(pattern));
            if (universityName.matches(pattern) != true) {
                System.out.println("Please Enter Correct University Name.");
            }


            for (int i = 1 ; i < UinversityType.values().length;i++) {
                System.out.println(i + ". " + UinversityType.values()[i]);
            }

            int universityType = Integer.parseInt(getValidInput(scanner, "Please enter your Type of University: ", input -> input.matches(patternForNumber)));


            LocalDate yearForJoin1 = askForValidDate();


            for (int i = 1 ; i < LoanForStudent.values().length;i++) {
                System.out.println(i + ". " + LoanForStudent.values()[i]);
            }
            String grade = getValidInput(scanner, "Please Enter Your Grade:", input -> input.matches(patternForNumber));


            String randomPassword = generateRandomPassword();
            System.out.println("Random Password: " + randomPassword);


            System.out.println("Do You Have Spouse?  1.NO  2.YES");
            int choice = scanner.nextInt();

            if (choice == 1) {
                userAccountService.registerForWhoWithOutSpouse(name, lastName, fatherName, motherName, idNumber, nationalCode,
                        birthDay1, city,studentId, universityName, universityType, yearForJoin1, grade, randomPassword);

            } else {

                System.out.println("Enter Spouse NationalCode: ");
                String spouseNationalCode = scanner.next();

                if (userAccountService.checkNationalCodeForSpouse(spouseNationalCode) == true) {
                    userAccountService.register(name, lastName, fatherName, motherName, idNumber, nationalCode, birthDay1, city, studentId,
                            universityName, universityType, yearForJoin1, grade, randomPassword, spouseNationalCode);
                } else {

                    try {


                        String name1 = getValidInput(scanner, "Please enter your Name: ", input -> input.matches(pattern));
                        if (name.matches(pattern) != true) {
                            System.out.println("Enter Correct Name.");
                        }


                        String lastName1 = getValidInput(scanner, "Please enter your Last Name: ", input -> input.matches(pattern));
                        if (lastName.matches(pattern) != true) {
                            System.out.println("Enter Correct lastName.");
                        }


                        String fatherName1 = getValidInput(scanner, "Please enter your Father Name: ", input -> input.matches(pattern));
                        if (fatherName.matches(pattern) != true) {
                            System.out.println("Enter Correct fatherName.");
                        }


                        String motherName1 = getValidInput(scanner, "Please enter your Mother Name: ", input -> input.matches(pattern));
                        if (motherName.matches(pattern) != true) {
                            System.out.println("Enter Correct motherName.");
                        }


                        String idNumber1 = getValidInput(scanner, "Please enter your Id Number: ", input -> input.matches("\\d+"));
                        Integer.valueOf(idNumber);


                        String nationalCode1 = getValidInput(scanner, "Please enter your NationalCode: ", input -> input.matches("^(\\d)(?!\\1{9})\\d{9}$"));
                        Integer.valueOf(nationalCode);


                        LocalDate birthDay11 = askForValidBirthdate();


                        for (int i = 1 ; i < BigCity.values().length;i++) {
                            System.out.println(i + ". " + BigCity.values()[i]);
                        }

                        String city1 = getValidInput(scanner, "Please enter your City: ", input -> input.matches(patternForNumber));


                        Long studentId1 = Long.valueOf(getValidInput(scanner, "Please enter your Student Id: ", input -> input.matches("\\d+")));
                        Long.valueOf(studentId);


                        String universityName1 = getValidInput(scanner, "Please enter your University Name: ", input -> input.matches(pattern));


                        for (int i = 1 ; i < UinversityType.values().length;i++) {
                            System.out.println(i + ". " + UinversityType.values()[i]);
                        }

                        int universityType1 = Integer.parseInt(getValidInput(scanner, "Please enter your University Type: ", input -> input.matches(patternForNumber)));


                        LocalDate yearForJoin11 = askForValidDate();


                        for (int i = 1 ; i < LoanForStudent.values().length;i++) {
                            System.out.println(i + ". " + LoanForStudent.values()[i]);
                        }
                        String grade1 = getValidInput(scanner, "Please Enter Your Grade: ", input -> input.matches(patternForNumber));


                        String randomPassword1 = generateRandomPassword();
                        System.out.println("Random Password: " + randomPassword);


                        userAccountService.register(name, lastName, fatherName, motherName, idNumber, nationalCode, birthDay1, city, studentId,
                                universityName, universityType, yearForJoin1, grade, randomPassword, nationalCode1);


                        userAccountService.register(name1, lastName1, fatherName1, motherName1, idNumber1, nationalCode1, birthDay11, city1,
                                studentId1, universityName1, universityType1, yearForJoin11, grade1, randomPassword1, nationalCode);


                    } catch (Exception e) {
                        System.out.println("Information Not true");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
















    private String getValidInput(Scanner scanner, String prompt, ValidationCondition condition) {
        String input;

        do {
            System.out.print(prompt);
            input = scanner.nextLine();
        } while (!condition.isValid(input));
        return input;
    }
    interface ValidationCondition {
        boolean isValid(String input);
    }










    private LocalDate askForValidBirthdate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        while (true) {
            System.out.print("Enter your birthdate (yyyy-MM-dd): ");
            String userInput = scanner.nextLine();

            try {
                LocalDate birthdate = LocalDate.parse(userInput, formatter);
                System.out.println("Valid birthdate: " + birthdate);
                return birthdate;
            } catch (Exception e) {
                System.out.println("Invalid date format. Please enter your birthdate in yyyy-MM-dd format.");
            }
        }
    }










    private LocalDate askForValidDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        while (true) {
            System.out.print("Enter your Date To Join University (yyyy-MM-dd): ");
            String userInput = scanner.nextLine();

            try {
                LocalDate birthdate = LocalDate.parse(userInput, formatter);
                System.out.println("Valid Date: " + birthdate);
                return birthdate;
            } catch (Exception e) {
                System.out.println("Invalid date format. Please enter your Date in yyyy-MM-dd format.");
            }
        }
    }













    private LocalDate askForValidExp() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        while (true) {
            System.out.print("Enter your Date For Card (yyyy-MM-dd): ");
            String userInput = scanner.nextLine();

            try {
                LocalDate birthdate = LocalDate.parse(userInput, formatter);
                System.out.println("Valid Date: " + birthdate);
                return birthdate;
            } catch (Exception e) {
                System.out.println("Invalid date format. Please enter your Exp in yyyy-MM-dd format.");
            }
        }
    }














    public boolean checkIfStudentHasLoan(String number , int userAccount) {
        StudentLoanService studentLoanService = new StudentLoanService();
        if (studentLoanService.addLoan(number,userAccount)) {
            System.out.println("You Can't Got This.");
            return false;
        }
        return true;
    }













    public boolean checkSpouse() {
        UserAccountService userAccountService = new UserAccountService();

        if (userAccountService.checkSpouse(UserAccountService.loggedInUser.getSpouseName(), UserAccountService.loggedInUser.getNationalCode()) == true) {
            System.out.println("Wait...");
            return true;
        } else {
            return false;
        }
    }










    public boolean checkLoanForSpouse(String loanType) {
        StudentLoanService service = new StudentLoanService();

        if (service.checkSpouseLoan(loanType,UserAccountService.loggedInUser.getSpouseName()) == true) {
            System.out.println("Wait...");
            return true;
        } else {
            return false;
        }
    }















    public boolean loanMenuForEducation(String loan) {
        UserAccountService userAccountService = new UserAccountService();


        for (int i = 1 ; i < Grade.values().length;i++) {
            System.out.println(i + ". " + Grade.values()[i] + "           Payment: " + Grade.values()[i].getValues());
        }

        int getLoan = Integer.parseInt(getValidInput(scanner, "Please enter your grade: ", input -> input.matches(patternForNumber)));
        Grade grade = Grade.values()[getLoan];


        if (getLoan == 1) {
            if (userAccountService.checkGrade(String.valueOf(1),UserAccountService.loggedInUser.getNationalCode()) == true) {
                studentLoanService.createLoan(loan,UserAccountService.loggedInUser.getId(), grade.getValues());
                payments(grade.getValues());
            } else {
                return false;
            }

        } else if (getLoan == 2) {
            if (userAccountService.checkGrade(String.valueOf(2),UserAccountService.loggedInUser.getNationalCode()) == true) {
                studentLoanService.createLoan(loan,UserAccountService.loggedInUser.getId(), grade.getValues());
                payments(grade.getValues());
            } else {
                return false;
            }

        } else if (getLoan == 3) {
            if (userAccountService.checkGrade(String.valueOf(3),UserAccountService.loggedInUser.getNationalCode()) == true) {
                studentLoanService.createLoan(loan,UserAccountService.loggedInUser.getId(), grade.getValues());
                payments(grade.getValues());
            } else {
                return false;
            }

        } else if (getLoan == 4) {
            if (userAccountService.checkGrade(String.valueOf(4),UserAccountService.loggedInUser.getNationalCode()) == true) {
                studentLoanService.createLoan(loan,UserAccountService.loggedInUser.getId(), grade.getValues());
                payments(grade.getValues());
            } else {
                return false;
            }

        } else if (getLoan == 5) {
            if (userAccountService.checkGrade(String.valueOf(5),UserAccountService.loggedInUser.getNationalCode()) == true) {
                studentLoanService.createLoan(loan,UserAccountService.loggedInUser.getId(), grade.getValues());
                payments(grade.getValues());
            } else {
                return false;
            }

        } else if (getLoan == 6) {
            if (userAccountService.checkGrade(String.valueOf(6),UserAccountService.loggedInUser.getNationalCode()) == true) {
                studentLoanService.createLoan(loan,UserAccountService.loggedInUser.getId(), grade.getValues());
                payments(grade.getValues());
            } else {
                return false;
            }

        } else if (getLoan == 7) {
            if (userAccountService.checkGrade(String.valueOf(7),UserAccountService.loggedInUser.getNationalCode()) == true) {
                studentLoanService.createLoan(loan,UserAccountService.loggedInUser.getId(), grade.getValues());
                payments(grade.getValues());
            } else {
                return false;
            }

        } else if (getLoan == 8) {
            if (userAccountService.checkGrade(String.valueOf(8),UserAccountService.loggedInUser.getNationalCode()) == true) {
                studentLoanService.createLoan(loan,UserAccountService.loggedInUser.getId(), grade.getValues());
                payments(grade.getValues());
            } else {
                return false;
            }
        }
        return false;
    }













    public boolean checkCity(String loan,Long houseId , String address){
        UserAccountService userAccountService = new UserAccountService();

        for (int i = 1 ; i < BigCity.values().length;i++) {
            System.out.println(i + ". " + BigCity.values()[i] + "                Payment: " + BigCity.values()[i].getValue());
        }


        int city = Integer.parseInt(getValidInput(scanner, "Please enter your city: ", input -> input.matches(patternForNumber)));
        BigCity bigCity = BigCity.values()[city];
        if (city == 1) {
            if (userAccountService.checkCity(String.valueOf(1),UserAccountService.loggedInUser.getNationalCode()) == false) {
                studentLoanService.createLoanForHouse(loan,address, bigCity.getValue(), houseId,UserAccountService.loggedInUser.getNationalCode(), UserAccountService.loggedInUser.getId());
                payments(bigCity.getValue());
            } else {
                return false;
            }

        } else if (city == 2) {
            if (userAccountService.checkCity(String.valueOf(2),UserAccountService.loggedInUser.getNationalCode()) == false) {
                studentLoanService.createLoanForHouse(loan,address, bigCity.getValue(), houseId,UserAccountService.loggedInUser.getNationalCode(), UserAccountService.loggedInUser.getId());                payments(bigCity.getValue());
            } else {
                return false;
            }

        } else if (city == 3) {
            if (userAccountService.checkCity(String.valueOf(3),UserAccountService.loggedInUser.getNationalCode()) == false) {
                studentLoanService.createLoanForHouse(loan,address, bigCity.getValue(), houseId,UserAccountService.loggedInUser.getNationalCode(), UserAccountService.loggedInUser.getId());                payments(bigCity.getValue());
            } else {
                return false;
            }

        } else if (city == 4) {
            if (userAccountService.checkCity(String.valueOf(4),UserAccountService.loggedInUser.getNationalCode()) == false) {
                studentLoanService.createLoanForHouse(loan,address, bigCity.getValue(), houseId,UserAccountService.loggedInUser.getNationalCode(), UserAccountService.loggedInUser.getId());                payments(bigCity.getValue());
            } else {
                return false;
            }

        } else if (city == 5) {
            if (userAccountService.checkCity(String.valueOf(5),UserAccountService.loggedInUser.getNationalCode()) == false) {
                studentLoanService.createLoanForHouse(loan,address, bigCity.getValue(), houseId,UserAccountService.loggedInUser.getNationalCode(), UserAccountService.loggedInUser.getId());                payments(bigCity.getValue());
            } else {
                return false;
            }

        } else if (city == 6) {
            if (userAccountService.checkCity(String.valueOf(6),UserAccountService.loggedInUser.getNationalCode()) == false) {
                studentLoanService.createLoanForHouse(loan,address, bigCity.getValue(), houseId,UserAccountService.loggedInUser.getNationalCode(), UserAccountService.loggedInUser.getId());                payments(bigCity.getValue());
            } else {
                return false;
            }

        } else if (city == 7) {
            if (userAccountService.checkCity(String.valueOf(7),UserAccountService.loggedInUser.getNationalCode()) == false) {
                studentLoanService.createLoanForHouse(loan,address, bigCity.getValue(), houseId,UserAccountService.loggedInUser.getNationalCode(), UserAccountService.loggedInUser.getId());                payments(bigCity.getValue());
            } else {
                return false;
            }

        } else if (city == 8) {
            if (userAccountService.checkCity(String.valueOf(8),UserAccountService.loggedInUser.getNationalCode()) == false) {
                studentLoanService.createLoanForHouse(loan,address, bigCity.getValue(), houseId,UserAccountService.loggedInUser.getNationalCode(), UserAccountService.loggedInUser.getId());                payments(bigCity.getValue());
            } else {
                return false;
            }

        } else if (city == 9) {
            if (userAccountService.checkCity(String.valueOf(9),UserAccountService.loggedInUser.getNationalCode()) == false) {
                studentLoanService.createLoanForHouse(loan,address, bigCity.getValue(), houseId,UserAccountService.loggedInUser.getNationalCode(), UserAccountService.loggedInUser.getId());                payments(bigCity.getValue());
            } else {
                return false;
            }

        } else if (city == 10) {
            if (userAccountService.checkCity(String.valueOf(10),UserAccountService.loggedInUser.getNationalCode()) == false) {
                studentLoanService.createLoanForHouse(loan,address, bigCity.getValue(), houseId,UserAccountService.loggedInUser.getNationalCode(), UserAccountService.loggedInUser.getId());                payments(bigCity.getValue());
            } else {
                return false;
            }
        }
        return false;
    }








    public boolean addStudentLoan(String loan) {
        UserAccountService userAccountService = new UserAccountService();


        for (int i = 1 ; i < LoanForStudent.values().length;i++){
            System.out.println(i + ". " + LoanForStudent.values()[i] + "       Payment: " + LoanForStudent.values()[i].getValues());
        }

        System.out.println("Enter Your Loan: ");
        int addLoan = scanner.nextInt();
        LoanForStudent loanForStudent = LoanForStudent.values()[addLoan];
            if (addLoan == 1) {
                if (userAccountService.checkGrade(String.valueOf(1),UserAccountService.loggedInUser.getNationalCode()) == true) {
                    studentLoanService.createLoan(loan,UserAccountService.loggedInUser.getId(), loanForStudent.getValues());
                    payments(loanForStudent.getValues());
                } else {
                    return false;
                }

            } else if (addLoan == 2) {
                if (userAccountService.checkGrade(String.valueOf(2),UserAccountService.loggedInUser.getNationalCode()) == true) {
                    studentLoanService.createLoan(loan,UserAccountService.loggedInUser.getId(), loanForStudent.getValues());
                    payments(loanForStudent.getValues());
                } else {
                    return false;
                }

            } else if (addLoan == 3) {
                if (userAccountService.checkGrade(String.valueOf(3),UserAccountService.loggedInUser.getNationalCode()) == false) {
                    studentLoanService.createLoan(loan,UserAccountService.loggedInUser.getId(), loanForStudent.getValues());
                    payments(loanForStudent.getValues());
                } else {
                    return false;
                }

            } else if (addLoan == 4) {
                if (userAccountService.checkGrade(String.valueOf(4),UserAccountService.loggedInUser.getNationalCode()) == true) {
                    studentLoanService.createLoan(loan,UserAccountService.loggedInUser.getId(), loanForStudent.getValues());
                    payments(loanForStudent.getValues());
                } else {
                    return false;
                }

            } else if (addLoan == 5) {
                if (userAccountService.checkGrade(String.valueOf(5),UserAccountService.loggedInUser.getNationalCode()) == true) {
                    studentLoanService.createLoan(loan,UserAccountService.loggedInUser.getId(), loanForStudent.getValues());
                    payments(loanForStudent.getValues());
                } else {
                    return false;
                }

            } else if (addLoan == 6) {
            if (userAccountService.checkGrade(String.valueOf(6),UserAccountService.loggedInUser.getNationalCode()) == true){
                studentLoanService.createLoan(loan,UserAccountService.loggedInUser.getId(), loanForStudent.getValues());
                payments(loanForStudent.getValues());
            }else {
                return false;
            }
             }

            else if (addLoan == 7) {
                if (userAccountService.checkGrade(String.valueOf(7),UserAccountService.loggedInUser.getNationalCode()) == true) {
                    studentLoanService.createLoan(loan,UserAccountService.loggedInUser.getId(), loanForStudent.getValues());
                    payments(loanForStudent.getValues());
                } else {
                    return false;
                }

            } else if (addLoan == 8) {
                if (userAccountService.checkGrade(String.valueOf(8),UserAccountService.loggedInUser.getNationalCode()) == true) {
                    studentLoanService.createLoan(loan,UserAccountService.loggedInUser.getId(), loanForStudent.getValues());
                    payments(loanForStudent.getValues());
                } else {
                    return false;
                }
            }
        return true;
    }












    public void addCard(){
        CardInfoService cardInfoService = new CardInfoService();

        Long cardNumber = Long.valueOf(getValidInput(scanner, "Please enter your Card Number : ", input -> input.matches("\\d+")));
        Long.valueOf(cardNumber);

        int cvv = Integer.parseInt((getValidInput(scanner, "Please enter your CVV: ", input -> input.matches("\\d+"))));
        Long.valueOf(cvv);

        LocalDate exp = askForValidExp();

       cardInfoService.createCard(cardNumber,cvv,exp,UserAccountService.loggedInUser.getId());
    }











    public boolean getCardFromUser(){
        CardInfoService cardInfoService = new CardInfoService();

        Long cardNumber = Long.valueOf(getValidInput(scanner, "Please enter your Card Number : ", input -> input.matches("\\d+")));
        Long.valueOf(cardNumber);

        int cvv = Integer.parseInt((getValidInput(scanner, "Please enter your CVV: ", input -> input.matches("\\d+"))));
        Long.valueOf(cvv);

        LocalDate exp = askForValidExp();

        if (cardInfoService.checkCard(cardNumber,cvv,exp,UserAccountService.loggedInUser.getId())){
            System.out.println("Wait...");
            return true;
        }else {
            System.out.println("Your card not correct!");
            return false;
        }
    }














    public void payments(int amount){
        int selectedGrade = amount; // Change this based on user input

        double interestRate = 0.04; // 4% interest rate
        int loanTermYears = 5; // 5 years

        double monthlyInterestRate = interestRate / 12.0;
        int numberOfPayments = loanTermYears * 12;

        List<StudentLoanInstallment> installmentPlan = calculateInstallmentPlan(selectedGrade, monthlyInterestRate, numberOfPayments);

        System.out.println("Installment plan: " + installmentPlan);

        insertStudentLoanPayment(selectedGrade,installmentPlan);
    }











    public static List<StudentLoanInstallment> calculateInstallmentPlan(double monthlyPayment, double monthlyInterestRate, int numberOfPayments) {
        LocalDate month = LocalDate.now();
        List<StudentLoanInstallment> installmentPlan = new ArrayList<>();
        double remainingLoanAmount = monthlyPayment * numberOfPayments;

        for (int i = 0; i < numberOfPayments; i++) {
            double interestPayment = remainingLoanAmount * monthlyInterestRate;
            double principalPayment = monthlyPayment - interestPayment;
            remainingLoanAmount -= principalPayment;

            StudentLoanInstallment installment = new StudentLoanInstallment();
            installment.setInstallmentNumber(i + 1);
            installment.setInterestPayment(interestPayment);
            installment.setPrincipalPayment(principalPayment);
            installment.setTotalPayment(monthlyPayment);
            month = month.plusMonths(1).with(TemporalAdjusters.firstDayOfMonth());
            installment.setPaymentDate(month);
            installment.setUserAccount(UserAccountService.loggedInUser.getId());
//            installment.setPaymentStatus("NO");

            installmentPlan.add(installment);
        }

        return installmentPlan;
    }














    public void checkDate(){

        LocalDate date = LocalDate.of(2023, 6, 13);

        LocalDate maxDate = date.plusDays(7);

        LocalDate secondDate = LocalDate.of(2023,10,13);

        LocalDate MaxDate = secondDate.plusDays(7);


        boolean isValidDate = false;

        LocalDate userDate = null;

        while (!isValidDate) {

            try {
                userDate = LocalDate.now();

                if (userDate.isAfter(date) && userDate.isBefore(maxDate)) {
                    isValidDate = true;

                } else if (userDate.isAfter(secondDate) && userDate.isBefore(MaxDate)){
                    isValidDate = true;

                }else {
                    System.out.println("Error: Date should be between " + date + " and " + maxDate + " or " + secondDate + " and " + MaxDate);
                }
            } catch (Exception e) {
                System.out.println("Error: Invalid date format");
            }
        }

        System.out.println("Valid date entered: " + userDate);
    }










    public boolean checkGraduation(){
        LocalDate date = UserAccountService.loggedInUser.getYearsOfJoinToUniversity();

        LocalDate maxYears = date.plusYears(2);

        LocalDate MaxYears = date.plusYears(4);

        LocalDate Maxyears = date.plusYears(6);

        LocalDate MAXYEARS = date.plusYears(5);

        LocalDate userDate = LocalDate.now();



        if (Integer.parseInt(UserAccountService.loggedInUser.getGarde()) == 1 || Integer.parseInt(UserAccountService.loggedInUser.getGarde()) == 5){
            if (userDate.isAfter(date) && userDate.isBefore(maxYears)){
                System.out.println("Wait...");
                return true;
            }else {
                System.out.println("You Graduatd");
                return false;
            }

        }else if (Integer.parseInt(UserAccountService.loggedInUser.getGarde()) == 2 || Integer.parseInt(UserAccountService.loggedInUser.getGarde()) == 3){
            if (userDate.isAfter(date) && userDate.isBefore(MaxYears)){
                System.out.println("Wait...");
                return true;
            }else {
                System.out.println("You Graduatd");
                return false;
            }

        }else if (Integer.parseInt(UserAccountService.loggedInUser.getGarde()) == 4){
            if (userDate.isAfter(date) && userDate.isBefore(Maxyears)){
                System.out.println("Wait...");
                return true;
            }else {
                System.out.println("You Graduatd");
                return false;
            }

        } else if (Integer.parseInt(UserAccountService.loggedInUser.getGarde()) == 6 || Integer.parseInt(UserAccountService.loggedInUser.getGarde()) == 7 || Integer.parseInt(UserAccountService.loggedInUser.getGarde()) == 8) {
            if (userDate.isAfter(date) && userDate.isBefore(MAXYEARS)){
                System.out.println("Wait...");
                return true;
            }else {
                System.out.println("You Graduatd");
                return false;
            }
        }
        return true;
    }
















    public String  generateRandomPassword() {

        SecureRandom random = new SecureRandom();
        StringBuilder password = new StringBuilder();

        password.append(UPPERCASE_LETTERS.charAt(random.nextInt(UPPERCASE_LETTERS.length())));

        password.append(LOWERCASE_LETTERS.charAt(random.nextInt(LOWERCASE_LETTERS.length())));

        password.append(DIGITS.charAt(random.nextInt(DIGITS.length())));
        password.append(ACTION.charAt(random.nextInt(ACTION.length())));

        for (int i = 4; i < 8; i++) {
            String characters = UPPERCASE_LETTERS + LOWERCASE_LETTERS + DIGITS + ACTION;
            password.append(characters.charAt(random.nextInt(characters.length())));
        }

        return password.toString();
    }



    }