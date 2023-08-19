package org.example.model;

import jakarta.persistence.*;
import org.hibernate.annotations.NaturalId;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "user_account")
public class UserAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "father_name")
    private String fatherName;
    @Column(name = "mother_name")
    private String motherName;
    @Column(name = "id_number")
    private String idNumber;
    @Column(name = "national_code")
    private String nationalCode;
    @Column(name = "birth_day")
    private LocalDate birthDay;
    @Column(name = "ciy")
    private String city;
    @Column(name = "student_id")
    private Long studentId;
    @Column(name = "university_name")
    private String universityName;
    @Column(name = "university_type")
    private int universityType;
    @Column(name = "dorm")
    private String dorm;
    @Column(name = "yearsOfJoinToUniversity")
    private LocalDate yearsOfJoinToUniversity;
    @Column(name = "garde")
    private String garde;
    @Column(name = "password")
    private String password;
    @Column(name = "spouse_name")
    private String spouseName;
    @OneToMany(fetch = FetchType.LAZY , cascade = CascadeType.ALL)
    private List<StudentLoan> studentLoans;

    public UserAccount() {

    }

    public UserAccount(String name, String lastName, String fatherName, String motherName, String idNumber, String nationalCode,
                       LocalDate birthDay, String city, Long studentId, String universityName, int universityType,
                       LocalDate yearsOfJoinToUniversity, String garde, String password, String spouseName) {
        this.name = name;
        this.lastName = lastName;
        this.fatherName = fatherName;
        this.motherName = motherName;
        this.idNumber = idNumber;
        this.nationalCode = nationalCode;
        this.birthDay = birthDay;
        this.city = city;
        this.studentId = studentId;
        this.universityName = universityName;
        this.universityType = universityType;
        this.yearsOfJoinToUniversity = yearsOfJoinToUniversity;
        this.garde = garde;
        this.password = password;
        this.spouseName = spouseName;
    }

    public UserAccount(String name, String lastName, String fatherName, String motherName, String idNumber, String nationalCode,
                       LocalDate birthDay, String city, Long studentId, String universityName, int universityType,
                       LocalDate yearsOfJoinToUniversity, String garde, String password) {
        this.name = name;
        this.lastName = lastName;
        this.fatherName = fatherName;
        this.motherName = motherName;
        this.idNumber = idNumber;
        this.nationalCode = nationalCode;
        this.birthDay = birthDay;
        this.city = city;
        this.studentId = studentId;
        this.universityName = universityName;
        this.universityType = universityType;
        this.yearsOfJoinToUniversity = yearsOfJoinToUniversity;
        this.garde = garde;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getNationalCode() {
        return nationalCode;
    }

    public int getUniversityType() {
        return universityType;
    }

    public LocalDate getYearsOfJoinToUniversity() {
        return yearsOfJoinToUniversity;
    }

    public String getGarde() {
        return garde;
    }

    public String getPassword() {
        return password;
    }

    public String getCity() {
        return city;
    }

    public String getSpouseName() {
        return spouseName;
    }

    public String getDorm() {
        return dorm;
    }

    public void setDorm(String dorm) {
        this.dorm = dorm;
    }

    public void add(StudentLoan studentLoan){
        if (studentLoans == null){
            studentLoans = new ArrayList<>();
        }
        studentLoans.add(studentLoan);
        studentLoan.setUserAccount(this.getId());
    }
}
