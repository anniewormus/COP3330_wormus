public class ContactItem{
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;

    ContactItem(String firstName, String lastName, String phoneNumber, String email) throws InvalidContactItemException {
        if(firstName.isEmpty() && lastName.isEmpty() && phoneNumber.isEmpty() && email.isEmpty()){
            throw new InvalidContactItemException("ERROR: Your contact must contain at least one field.");
        }else{
            this.firstName = firstName;
            this.lastName = lastName;
            this.phoneNumber = phoneNumber;
            this.email = email;
        }
    }

    public String getFirstName(){
        return this.firstName;
    }
    public void setFirstName(String firstName){
        this.firstName = firstName;
    }

    public String getLastName(){
        return this.lastName;
    }
    public void setLastName(String lastName){
        this.lastName = lastName;
    }

    public String getPhoneNumber(){
        return this.phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
}