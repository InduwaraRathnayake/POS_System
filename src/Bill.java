import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Bill implements Serializable{
    private String cashierName;
    private String branch;
    private String customerName;
    private boolean registeredCustomer;
    private double totalDiscount;
    private double totalPrice;
    private double actualCartValue;
    private ArrayList<GloceryItem> cart = new ArrayList<>();

    public Bill(String cashierName, String branch) {
        this.cashierName = cashierName;
        this.branch = branch;
    }

    public void setRegisteredCustomer(boolean registeredCustomer) {
        this.registeredCustomer = registeredCustomer;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    
    public void setCashierName(String cashierName) {
        this.cashierName = cashierName;
    }
    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void addItemToBill(GloceryItem item){
        cart.add(item);
    }

    public void calculateBill(){
        System.out.println("\n##################################################################################");
        System.out.println("                            Super-Saving supermarket");
        System.out.printf("%-65s %s\n", "Cashier             :", cashierName);
        System.out.printf("%-65s %s\n", "Branch              :", branch);
        System.out.printf("%-65s %s\n", "Custormer Name      :", registeredCustomer ? customerName : "Unregistered");
        System.out.println("##################################################################################");
        System.out.println();

        System.out.printf("%-20s %-15s %-15s %-15s %-15s\n", "Item", "Unit Price", "Quantity", "Discount", "Net-Price");

        for(GloceryItem item : cart){
            double itemValue, discount, finalItemValue;
            itemValue = (item.getPrice()*item.getQuantity());
            finalItemValue = itemValue - itemValue*item.getDiscount();
            discount = itemValue - finalItemValue;

            totalDiscount += discount;
            actualCartValue += itemValue;
            totalPrice += finalItemValue;

            System.out.printf("%-20s %-15d %-15.3f %-15.2f %-15.2f\n", item.getName(), item.getPrice(), item.getQuantity(), discount, finalItemValue);

        }
        System.out.println();
        System.out.printf("%-60s %15.2f\n", "Total          :", actualCartValue);
        System.out.printf("%-60s %15.2f\n", "Total Discount :", totalDiscount);
        System.out.printf("%-60s %15.2f\n", "Net Value      :", totalPrice);

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedNow = now.format(formatter);

        System.out.println();
        System.out.printf("%s %s\n", "Date and Time  :", formattedNow);
        System.out.println( "Thank you for shopping with us...");
        System.out.println("##################################################################################");
    }
}
