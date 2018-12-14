package com.bluementors;

import com.bluementors.admin.Admin;
import com.bluementors.mentor.Mentor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "USERS")
public class User implements Serializable {
    @Id
    private Long id;
    @Email
    private String email;
    @NotNull
    private String authenticationString;
    @OneToOne
    private Mentor mentor;
    @OneToOne
    private Admin admin;
    private String firstName;
    private String lastName;
    private String contactNo;
    private LocalDateTime registrationDate;
    private String registrationCode;
    private Boolean active;

    public boolean isMentor(){
        return this.mentor != null;
    }

    public boolean isAdmin(){
        return this.admin !=null;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAuthenticationString() {
        return authenticationString;
    }

    public void setAuthenticationString(String authenticationString) {
        this.authenticationString = authenticationString;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getRegistrationCode() {
        return registrationCode;
    }

    public void setRegistrationCode(String registrationCode) {
        this.registrationCode = registrationCode;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", authenticationString='" + authenticationString + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", contactNo='" + contactNo + '\'' +
                ", registrationDate=" + registrationDate +
                ", registrationCode='" + registrationCode + '\'' +
                ", active=" + active +
                '}';
    }

    public static class Builder{
        private User user;

        Builder(){
            this.user = new User();
        }

        public Builder firstName(String firstName){
            this.user.firstName = firstName;
            return this;
        }

        public Builder lastName(String lastName){
            this.user.lastName = lastName;
            return this;
        }

        public Builder email(String email){
            this.user.email = email;
            return this;
        }

        public Builder authenticationString(String authenticationString){
            this.user.authenticationString = authenticationString;
            return this;
        }

        public Builder mentor(Mentor mentor){
            this.user.mentor = mentor;
            return this;
        }

        public Builder admin(Admin admin){
            this.user.admin = admin;
            return this;
        }

        public Builder contactNo(String contactNo){
            this.user.contactNo = contactNo;
            return this;
        }

        public Builder registrationCode(String registrationCode){
            this.user.registrationCode = registrationCode;
            return this;
        }

        public Builder active(Boolean active){
            this.user.active = active;
            return this;
        }

        public Builder registrationDate(LocalDateTime registrationDate){
            this.user.registrationDate = registrationDate;
            return this;
        }

        public User build(){
            return this.user;
        }
    }

}
