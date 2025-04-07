package Pharmacy;

abstract class  Human {
    protected String Name;
    protected String Location;
    protected String IdentificationNumber;
    protected String Gender;
    protected int Age;

    public void setName(String name) {
        Name = name;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public void setIdentificationNumber(String identificationNumber) {
        IdentificationNumber = identificationNumber;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public void setAge(int age) {
        Age = age;
    }


    public String getName() {
        return Name;
    }

    public Human(String name, String location, String identificationNumber, String gender, int age) {
        Name = name;
        Location = location;
        IdentificationNumber = identificationNumber;
        Gender = gender;
        Age = age;
    }
    public Human(){}

    public String getLocation() {
        return Location;
    }

    public String getIdentificationNumber() {
        return IdentificationNumber;
    }

    public String getGender() {
        return Gender;
    }

    public int getAge() {
        return Age;
    }
}
