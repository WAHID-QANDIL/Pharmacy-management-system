package Pharmacy;

import java.util.ArrayList;
import java.util.List;

public class Pharmacy {
    private String PharmacyName;
    private String PharmacyLocation;
    private List<Employee> Employees; //Uninitialized ArrayList
    public Pharmacy()
    {
       this.Employees = new ArrayList<Employee>();

    }
}
