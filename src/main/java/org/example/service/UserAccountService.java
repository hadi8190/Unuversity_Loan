package org.example.service;

import org.example.model.UserAccount;
import org.example.repository.UserAccountRepo;

import java.time.LocalDate;

public class UserAccountService {
    public static UserAccount loggedInUser = null;

    public boolean
    register(String name, String lastName, String fatherName, String motherName,
             String idNumber, String nationalCode, LocalDate birthDay, String city, Long studentId,
             String universityName, int universityType, LocalDate yearsOfJoinToUniversity, String garde, String password, String spouseName) {
        UserAccountRepo userAccountRepo = new UserAccountRepo();
        if (userAccountRepo.findUserAccountByPassword(nationalCode,password) == null) {
            UserAccount userAccount = new UserAccount(name, lastName, fatherName, motherName, idNumber, nationalCode, birthDay, city, studentId, universityName,
                    universityType, yearsOfJoinToUniversity, garde, password, spouseName);
            userAccountRepo.createAdmin(userAccount);
            System.out.println("Account Created Successfully!");
            return true;
        }//catch
        System.out.println("You Signed up with this password Before!");
        return false;
    }

    public boolean registerForWhoWithOutSpouse(String name, String lastName, String fatherName, String motherName,
                                               String idNumber, String nationalCode, LocalDate birthDay, String city, Long studentId,
                                               String universityName, int universityType, LocalDate yearsOfJoinToUniversity, String garde, String password) {
        UserAccountRepo userAccountRepo = new UserAccountRepo();
        if (userAccountRepo.findUserAccountByPassword(nationalCode,password) == null) {
            UserAccount userAccount = new UserAccount(name, lastName, fatherName, motherName, idNumber, nationalCode, birthDay, city, studentId, universityName,
                    universityType, yearsOfJoinToUniversity, garde, password);
            userAccountRepo.createAdmin(userAccount);
            System.out.println("Account Created Successfully!");
            return true;
        }//catch
        System.out.println("You Signed up with this password Before!");
        return false;
    }

    public boolean login(String nationalcode , String password) {
        UserAccountRepo userAccountRepo = new UserAccountRepo();
        UserAccount userAccount = userAccountRepo.findUserAccountByPassword(nationalcode,password);
        if (userAccount != null) {
            if (userAccount.getPassword().equals(password) && userAccount.getNationalCode().equals(nationalcode)) {
                this.loggedInUser = userAccount;
                System.out.println(userAccount.getNationalCode() + " with [YOU: " + userAccount.getName() + userAccount.getLastName()
                        + "] Logged in Successfully");
                return true;
            }
        } //catch
            System.out.println("Password is Incorrect!");
        return false;
        }



    public boolean checkNationalCodeForSpouse(String nationalCode) {
        UserAccountRepo userAccountRepo = new UserAccountRepo();
        UserAccount userAccount = userAccountRepo.findUserAccountByNationalCode(nationalCode);
        if (userAccount != null) {
            if (userAccount.getNationalCode().equals(nationalCode)) {
                this.loggedInUser = userAccount;
                System.out.println("NationalCode Exist.");
                return true;
            }
        } else {
            System.out.println("NationalCode Does Not Exist.");
        }
        return false;
    }


    public boolean checkGrade(String grade,String nationalCode) {
        UserAccountRepo userAccountRepo = new UserAccountRepo();
        UserAccount userAccount = userAccountRepo.findUserAccountByGrade(grade,nationalCode);
        if (userAccount != null) {
            if (userAccount.getGarde().equals(grade)) {
                this.loggedInUser = userAccount;
                System.out.println(userAccount);
                return true;
            }
        }//catch
        System.out.println("Incorrect!");
        return false;
    }

    public boolean checkCity(String city,String nationalCode) {
        UserAccountRepo userAccountRepo = new UserAccountRepo();
        UserAccount userAccount = userAccountRepo.findUserAccountByCity(city,nationalCode);
        if (userAccount != null) {
            if (userAccount.getCity().equals(city)) {
                this.loggedInUser = userAccount;
                System.out.println(userAccount);
                return false;
            }
        }//catch
        System.out.println("Incorrect city!");
        return true;
    }


    public boolean checkSpouse(String nationalCode, String spouse) {
        UserAccountRepo userAccountRepo = new UserAccountRepo();
        UserAccount userAccount = userAccountRepo.findSpouse(spouse,nationalCode);
        if (userAccount == null) {
            System.out.println("You don't have a spouse.");
            return false;
        } else {
            System.out.println("Your Spouse is: " + UserAccountService.loggedInUser.getSpouseName());
            return true;
        }
    }

}
