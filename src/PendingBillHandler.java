import java.io.*;

public class PendingBillHandler {
    private static String dirPath = System.getProperty("user.dir") + File.separator + "PendingBills";

    static{
        File dir = new File(dirPath);
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }
    
    public static void saveBill(Bill bill){
        String fileName = dirPath + File.separator + bill.getCustomerName() + ".ser";
        File outFile = new File(fileName);

        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(outFile) )){
            oos.writeObject(bill);
        }catch (IOException ioe){
            System.out.println("Cannot save the bill");
        }
    }

    public static Bill retrieveBill(String customerName){        
        Bill retrievedBill = null;
        String fileName = dirPath + File.separator + customerName + ".ser";
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
            System.out.println("Removed from the pending bill section.");
        } else {
            System.out.println("Cannot remove from the pending bill section.");
        }

        return retrievedBill;
    }
}
