import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class POS {
    private Scanner prompt = new Scanner(System.in);;
    private String cashierName;
    private String branch;

    public void startSystem() {
        init();
        int option;
        do {
            option = cashierOption1();
            MainMenu(option);
        } while (option != 0);
        System.out.println("\nSystem shutdown...");
    }

    private GloceryItem getItemDetails() throws ItemCodeNotFound {
        BufferedReader br = null;
        GloceryItem item = null;
        try {
            System.out.print("Enter the Item code : ");
            br = new BufferedReader(new InputStreamReader(System.in));
            String itemCode = br.readLine();

            if (DataBase.getDataBase().containsKey(itemCode)) {
                item = DataBase.getDataBase().get(itemCode);
            } else {
                throw new ItemCodeNotFound("Invalid Item Code.");
            }
        } catch (IOException ime) {
            System.err.println("Please Retry");
        }
        return item;
    }

    private void MainMenu(int option) {
        Bill bill = null;
        switch (option) {
            case 1:
                bill = billing(true, bill);
                int choice = cashierOption3();
                if (choice == 1) {
                    bill.calculateBill();
                } else {
                    PendingBillHandler.saveBill(bill);
                }
                break;
            case 2:
                System.out.print("Enter the customer name : ");
                String customerName = prompt.nextLine();
                bill = PendingBillHandler.retrieveBill(customerName);
                if (bill != null) {
                    bill = billing(false, bill);
                    bill.calculateBill();
                }
                break;
        }
    }

    private void init() {
        System.out.println("###############################################################################");
        System.out.println("###############################    WELCOME!   #################################");
        System.out.println("                            Super-Saving supermarket");
        System.out.println("###############################################################################\n");

        System.out.print("Enter cashier name : ");
        cashierName = prompt.nextLine();

        System.out.print("Enter branch name  : ");
        branch = prompt.nextLine();
    }

    private int cashierOption1() {
        int option = -1;
        while (true) {
            System.out.println("\nDo you want to Start a new Bill    : press 1. ");
            System.out.println("Do you want retrieve the savedBill : press 2. ");
            System.out.println("Do you want to Exit                : press 0. ");
            System.out.print("Enter your option: ");
            try {

                option = prompt.nextInt();
                prompt.nextLine();
                if (0 <= option && option <= 2) {
                    break;
                } else {
                    System.out.println("Invalid input. Please enter 0, 1 or 2.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter an integer.");
                prompt.next();
                continue;
            }
        }
        return option;
    }

    private int cashierOption2() {
        int option = -1;
        while (true) {
            System.out.println("\nDo you want to add any items : press 1. ");
            System.out.println("Do you want to end this bill : press 2. ");
            System.out.print("Enter your option: ");
            try {
                option = prompt.nextInt();
                prompt.nextLine();
                if (option == 1 || option == 2) {
                    break;
                } else {
                    System.out.println("Invalid input. Please enter 1 or 2.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please Re-enter.");
                prompt.next();
                continue;
            }
        }
        return option;
    }
  
    private int cashierOption3() {
        int option = -1;
            while (true) {
                try {
                    System.out.println("\nDo you want to close this Bill   : press 1. ");
                    System.out.println("Do you want save the currentBill : press 2. ");
                    System.out.print("Enter your option: ");
                    option = prompt.nextInt();
                    if (option == 1 || option == 2) {
                        break;
                    } else {
                        System.out.println("Invalid input. Please enter 1 or 2.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter an integer.");
                    prompt.next();
                    continue;
                }
            }
        return option;
    }

    private Bill billing(boolean newBill, Bill bill) {
        if (newBill) {
            bill = new Bill(cashierName, branch);

            System.out.print("\nEnter the Customer name: ");
            String customerName = prompt.nextLine();

            for (String name : DataBase.getRegCustomerList()) {
                if (name.equals(customerName)) {
                    System.out.println("Hello, " + name);
                    bill.setRegisteredCustomer(true);
                    break;
                }
            }
            bill.setCustomerName(customerName);
        }
        while (true) {
            int option = cashierOption2();
            if (option == 1) {
                GloceryItem fetchedItem = null;
                try {
                    fetchedItem = getItemDetails();
                    if (fetchedItem != null) {
                        GloceryItem item = new GloceryItem(fetchedItem);
                        System.out.print("Add quantity: ");
                        double qty = prompt.nextDouble();
                        item.setQuantity(qty);
                        bill.addItemToBill(item);
                    }
                } catch (ItemCodeNotFound icnf) {
                    System.err.println(icnf.getMessage());
                } catch(InputMismatchException ime) {
                    System.err.println("Invalid input. Please Re-enter.");
                    prompt.next();
                    continue;
                }
            } else if (option == 2) {
                return bill;
            }
        }
    }
}
