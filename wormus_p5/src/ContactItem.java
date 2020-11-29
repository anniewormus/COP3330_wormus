public class ContactItem{
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;

    ContactItem(String firstName, String lastName, String phoneNumber, String email) {
        if(firstName.isEmpty() && lastName.isEmpty() && phoneNumber.isEmpty() && email.isEmpty()){
            throw new invalidContactItemException("ERROR: Your contact must contain at least one field.");
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
    public String setFirstName(String firstName){
        this.firstName = firstName;
    }

    public String getLastName(){
        return this.lastName;
    }
    public String setLastName(String lastName){
        this.lastName = lastName;
    }

    public String getPhoneNumber(){
        return this.phoneNumber;
    }
    public String setPhoneNumber(){
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
}