import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.javafaker.Faker;

import java.util.Random;

public class UserRequestModel
{
    @JsonIgnore
    private String id;

    private String gender;

    private String name;

    private String email;

    private String status;

    public UserRequestModel(){
        this.gender = randomGender();
        this.name = Faker.instance().name().fullName();
        this.email = Faker.instance().name().username() + "@gmail.com";
        this.status = randomStatus();
    }

    public UserRequestModel(String name, String gender, String email, String status) {
        this.gender = gender;
        this.name = name;
        this.email = email;
        this.status = status;
    }

    public String getGender ()
    {
        return gender;
    }

    public void setGender (String gender)
    {
        this.gender = gender;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public String getEmail ()
    {
        return email;
    }

    public void setEmail (String email)
    {
        this.email = email;
    }

    public String getStatus ()
    {
        return status;
    }

    public void setStatus (String status)
    {
        this.status = status;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [gender = "+gender+", name = "+name+", email = "+email+", status = "+status+"]";
    }

    public String randomGender(){
        String[] gender = {
                "Male",
                "Female"
        } ;
        int idx = new Random().nextInt(gender.length);
        return gender[idx];
    }

    public String randomStatus() {
        String[] status = {
                "Active",
                "Inactive"
        } ;
        int idx = new Random().nextInt(status.length);
        return status[idx];
    }
}