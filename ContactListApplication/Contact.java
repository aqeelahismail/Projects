import java.util.*;
import java.time.*;

public class Contact {
    protected String name;
    protected String mobileNumber;
    protected String homeNumber;
    protected String workNumber;
    protected String address;
    protected String email;
    protected LocalDate birthday;

    public Contact(String name, String mobileNumber)
    {
        this.name = name;
        this.mobileNumber = mobileNumber;
        this.homeNumber = "";
        this.workNumber = "";
        this.address = "";
        this.email = "";
        this.birthday = null;
    }

    public Contact(String name, String mobileNumber, String homeNumber, String workNumber, String address, String email, LocalDate birthday)
    {
        this.name = name;
        this.mobileNumber = mobileNumber;
        this.homeNumber = homeNumber;
        this.workNumber = workNumber;
        this.address = address;
        this.email = email;
        this.birthday = birthday;
    }

    public void updateName(String name)
    {
        this.name = name;
    }

    public void updateMobile(String newMobile)
    {
        this.mobileNumber = newMobile;
    }

    public void updateHome(String newHome)
    {
        this.homeNumber = newHome;
    }

    public void updateWork(String newWork)
    {
        this.workNumber = newWork;
    }
    public void updateAddress(String newAddress)
    {
        this.address = newAddress;
    }

    public void updateEmail(String newEmail)
    {
        this.email = newEmail;
    }

    public void updateBirthday(LocalDate newBirthday)
    {
        this.birthday = newBirthday;
    }

    public String toString()
    {
        String returnString = "Name: " + name + "\nMobile: " + mobileNumber;

        if (!this.homeNumber.equals(""))
        {
            returnString = returnString + "\nHome: " + homeNumber;
        }

        if (!this.workNumber.equals(""))
        {
            returnString = returnString + "\nWork: " + workNumber;
        }

        if (!this.email.equals(""))
        {
            returnString = returnString + "\nEmail: " + email;
        }

        if (!this.address.equals(""))
        {
            returnString = returnString + "\nAddress: " + address;
        }

        if (this.birthday != null)
        {
            returnString = returnString + "\nBirthday: " + birthday.toString();
        }

        return returnString;
    }
}
