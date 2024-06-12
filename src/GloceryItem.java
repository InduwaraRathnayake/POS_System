import java.io.Serializable;

public class GloceryItem implements Serializable {
    private String itemCode;
    private int price;
    private double weightORsize;
    private String manufacDate;
    private String expireDate;
    private String manufacturerName;
    private double discount;
    private String name;
    private double quantity;

    public GloceryItem(String itemCode, int price, double weightORsize, String manufacDate, String expireDate, 
    String manufacturerName, double discount, String name) 
    {
        this.itemCode = itemCode;
        this.price = price;
        this.weightORsize = weightORsize;
        this.manufacDate = manufacDate;
        this.expireDate = expireDate;
        this.manufacturerName = manufacturerName;
        this.discount = discount;
        this.name = name;
    }
    
    public GloceryItem(GloceryItem other) {
        this.itemCode = other.itemCode;
        this.price = other.price;
        this.weightORsize = other.weightORsize;
        this.manufacDate = other.manufacDate;
        this.expireDate = other.expireDate;
        this.manufacturerName = other.manufacturerName;
        this.discount = other.discount;
        this.name = other.name;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }
    
    public double getQuantity() {
        return quantity;
    }

    public String getName() {
        return name;
    }
        
    public String getItemCode() {
        return itemCode;
    }
        
    public void setDiscount(double discount) {
        this.discount = discount;
        }
        
        public int getPrice() {
        return price;
    }

    public double getWeightORsize() {
        return weightORsize;
    }

    public String getManufacDate() {
        return manufacDate;
    }

    public String getExpireDate() {
        return expireDate;
    }

    public String getManufacturerName() {
        return manufacturerName;
    }

    public double getDiscount() {
        return discount;
    }

}
