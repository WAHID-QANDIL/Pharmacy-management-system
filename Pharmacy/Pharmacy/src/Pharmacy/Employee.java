package Pharmacy;
public class Employee extends Human {
    private String Email;
    private String Password;
    private String Position;
    public void setEmail(String email) {
        Email = email;
    }
    public void setPassword(String password) {
        Password = password;
    }

    @Override
    public void setGender(String gender) {
        super.setGender(gender);
    }
    @Override
    public String getGender() {
        return super.getGender();
    }
    public String getEmail() {
        return Email;
    }
    public String getPassword() {
        return Password;
    }
    public Employee(String name, String location, String identificationNumber, String gender, int age, String email, String password, String position) {
        super(name, location, identificationNumber, gender, age);
        Email = email;
        Password = password;
        Position = position;
    }
    public Employee(){}

    @Override
    public String toString()
    {
        return (Name + " " + Email + " " + Password + " " + Gender + " ");
    }

    public String getPosition() {
        return Position;
    }

    public void setPosition(String position) {
        Position = position;
    }
}
