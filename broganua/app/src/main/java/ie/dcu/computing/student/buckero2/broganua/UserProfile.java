package ie.dcu.computing.student.buckero2.broganua;

import java.lang.reflect.Constructor;

public class UserProfile {
    public String userAge;
    public String userEmail;
    public String userName;
    public String userShoeSize;

    public UserProfile() {

    }

    public UserProfile(String userAge, String userEmail, String userName, String userShoeSize) {
        this.userAge = userAge;
        this.userEmail = userEmail;
        this.userName = userName;
        this.userShoeSize = userShoeSize;
    }

    public String getUserAge() {
        return userAge;
    }

    public void setUserAge(String userAge) {
        this.userAge = userAge;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserShoeSize() {
        return userShoeSize;
    }

    public void setUserShoeSize(String userShoeSize) {
        this.userShoeSize = userShoeSize;
    }
}
