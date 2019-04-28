package pack;

/**
 * Person class
 *
 * @author Victtor da Silva Mendes
 */
public class Person {

    /**
     *
     * @param firstName first name of the person
     * @param lastName last name of the person
     */
    public Person(String firstName, String lastName) {
        setFirstName(firstName);
        setLastName(lastName);
    }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }

    /**
     * Getter firstName
     *
     * @return firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Setter firstName
     *
     * @param firstName first name of the person
     */
    public void setFirstName(String firstName) {
        if (firstName == null || firstName.equals("") || firstName.equals(" ")) {
            throw new IllegalArgumentException("first name should not be null");
        }
        this.firstName = firstName;
    }

    /**
     * Getter lastName
     *
     * @return lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Setter lastName
     *
     * @param lastName last name of the person
     */
    public void setLastName(String lastName) {
        if (lastName == null || lastName.equals("") || lastName.equals(" ")) {
            throw new IllegalArgumentException("last name should not be null");
        }
        this.lastName = lastName;
    }

    private String firstName;
    private String lastName;
}
