package App.Domain;

public class Customer {

    private int Customer_ID;   // Should match the database column name
    private String First_Name;
    private String Last_Name;
    private String Phone_Number;
    private String Email;

    // Getters and Setters

    public int getCustomerId() {
        return Customer_ID;
    }

    public void setCustomerId(int customerId) {
        this.Customer_ID = customerId;
    }

    public String getFirstName() {
        return First_Name;
    }

    public void setFirstName(String firstName) {
        this.First_Name = firstName;
    }

    public String getLastName() {
        return Last_Name;
    }

    public void setLastName(String lastName) {
        this.Last_Name = lastName;
    }

    public String getPhoneNumber() {
        return Phone_Number;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.Phone_Number = phoneNumber;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        this.Email = email;
    }
}
