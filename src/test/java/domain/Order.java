package domain;

/**
 * @author Ritesh Kadu
 */
public class Order {

    private int subTotal;
    private Float taxes;
    private Float total;

    public Order(final int orderTotal, final Float stateTax, final Float total2)
    {
        this.subTotal = orderTotal;
        this.taxes = stateTax;
        this.total = total2;
    }

    public int getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(final int subTotal) {
        this.subTotal = subTotal;
    }
    
    public Float getTaxes() {
        return taxes;
    }

    public void setTaxes(final Float taxes) {
        this.taxes = taxes;
    }
    
    public Float getTotal() {
        return total;
    }

    public void setTotal(final Float total) {
        this.total = total;
    }
}