package com.example.surya.safeindia;

/**
 * Created by surya.solanki on 01-12-2016.
 */

import com.google.firebase.database.IgnoreExtraProperties;


@IgnoreExtraProperties
public class UserPOJO {

    private String FirstName;
    private String LastName;
    private String Gender;

    public UserPOJO(String firstName, String lastName, String gender, String DOB, String phoneNumber) {
        FirstName = firstName;
        LastName = lastName;
        Gender = gender;
        this.DOB = DOB;
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {

        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    private String DOB;
    private String phoneNumber;

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public UserPOJO() {
    }

    public UserPOJO(String firstName, String lastName, String gender, String DOB) {

        FirstName = firstName;
        LastName = lastName;
        Gender = gender;
        this.DOB = DOB;
    }

    public String getGender() {
        return Gender;

    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    @Override
    public String toString() {
        return "UserPOJO{" +
                "FirstName='" + FirstName + '\'' +
                ", LastName='" + LastName + '\'' +
                ", Gender='" + Gender + '\'' +
                ", DOB='" + DOB + '\'' +
                '}';
    }
}


