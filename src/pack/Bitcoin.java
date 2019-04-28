package pack;

/**
 * Bitcoin class, it stores the value and the id of a bitcoin
 *
 * @author Victtor da Silva Mendes
 */
public class Bitcoin {

    /**
     *
     * @param id id of the coin
     * @param value value of the coin
     */
    public Bitcoin(String id, double value) {
        setId(id);
        setValue(value);
    }

    /**
     * Getter id
     *
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * Setter id
     *
     * @param id id of the coin
     */
    public void setId(String id) {
        if (id == null || id.equals("") || id.equals(" ")) {
            throw new IllegalArgumentException("bitcoin id should not be null");
        }
        this.id = id;
    }

    /**
     * Getter value
     *
     * @return value
     */
    public double getValue() {
        return value;
    }

    /**
     * Setter value
     *
     * @param value value of the coin
     */
    public void setValue(double value) {
        if (value <= 0) {
            throw new IllegalArgumentException("value of bitcoin must be > 0");
        }
        this.value = value;
    }

    private String id;
    private double value;
}
