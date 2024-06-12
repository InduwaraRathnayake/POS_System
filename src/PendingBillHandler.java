import java.io.*;

public class PendingBillHandler {
    public static void saveBill(Bill bill){
        String fileName = bill.getCustomerName() + ".ser";
        File outFile = new File(fileName);
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(outFile) )){
            oos.writeObject(bill);
        }catch (IOException ioe){
            System.out.println("Cannot save the bill");
        }
    }

    public static Bill retrieveBill(String customerName){
        
        Bill retrievedBill = null;
        String fileName = "C:\\Users\\induw\\OneDrive - University of Moratuwa\\Desktop\\CS 1040 PC\\POS\\"+customerName + ".ser";
        File outFile = new File(fileName);

        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(outFile))){
            retrievedBill = (Bill) ois.readObject();
            System.out.println("Bill retrieve successfulll");

        }catch (ClassNotFoundException cnfe){
            System.out.println("Cannot retrieve the bill");
        }catch(FileNotFoundException fnfe){
            System.out.println("No any pending bills");
        }catch (IOException ioe){
            System.out.println("Cannot retrieve the bill");
        }

        if (outFile.delete()) {
            System.out.println("Remove from the pending bill section.");
        }else{
            System.out.println("Cannot remove from the pending bill section.");
        }
        

        return retrievedBill;
    }
}
