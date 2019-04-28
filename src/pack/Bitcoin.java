package pack;

/**
 * @author Victtor da Silva Mendes
 */
public class Bitcoin {

    public Bitcoin(String id, double value) {
        setId(id);
        setValue(value);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        if (id == null || id.equals("") || id.equals(" ")) {
            throw new IllegalArgumentException("bitcoin id should not be null");
        }
        this.id = id;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        if (value <= 0) {
            throw new IllegalArgumentException("value of bitcoin must be > 0");
        }
        this.value = value;
    }

    private String id;
    private double value;
}
