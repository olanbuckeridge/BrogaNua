package ie.dcu.computing.student.buckero2.broganua;

import java.lang.reflect.Constructor;

public class UserProfile {
    public String Age;
    public String Email;
    public String Name;
    public String ShoeSize;

    public UserProfile(String userAge, String userEmail, String userName, String userShoeSize) {
        this.Age = userAge;
        this.Email = userEmail;
        this.Name = userName;
        this.ShoeSize = userShoeSize;
    }
}
