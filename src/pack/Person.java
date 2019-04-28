package pack;

/**
 * @author Victtor da Silva Mendes
 */
public class Person {

    public Person(String firstName, String lastName) {
        setFirstName(firstName);
        setLastName(lastName);
    }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        if (firstName == null || firstName.equals("") || firstName.equals(" ")) {
            throw new IllegalArgumentException("first name should not be null");
        }
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        if (lastName == null || lastName.equals("") || lastName.equals(" ")) {
            throw new IllegalArgumentException("last name should not be null");
        }
        this.lastName = lastName;
    }

    private String firstName;
    private String lastName;
}
