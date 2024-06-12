import java.util.ArrayList;
import java.util.HashMap;

public class DataBase {
    private static HashMap<String,GloceryItem> dataBase = new HashMap<>();
    private static ArrayList<String> regCustomer = new ArrayList<>();

    static {
        dataBase.put("1000",new GloceryItem("1000", 750, 1, "2023-04-05", "2023-10-05", "MD", 0.255, "Apple"));
        dataBase.put("1001",new GloceryItem("1001", 1250, 1, "2023-03-19", "2023-08-01", "Willson", 0.5, "Tennis Ball"));
        dataBase.put("1002",new GloceryItem("1002", 375, 1, "2023-03-13", "2023-12-24", "Sang", 0.1, "Carrot"));
        dataBase.put("1003",new GloceryItem("1003", 100, 1, "2023-02-01", "2024-05-01", "Atlas", 0.2, "NoteBook"));
        dataBase.put("1004",new GloceryItem("1004", 845, 1, "2022-09-17", "2023-10-30", "MD", 0.45, "Orange"));
        dataBase.put("1005",new GloceryItem("1005", 2000, 1, "2022-10-08", "2023-11-09", "Keels", 0.75, "Hammer"));
        dataBase.put("1006",new GloceryItem("1006", 90, 1, "2023-01-20", "2024-01-20", "Atlas", 0, "Marker Pen"));
        dataBase.put("1007",new GloceryItem("1007", 170, 1, "2022-11-30", "2024-11-30", "Keels", 0.1, "Suger"));
    }

    static {
        regCustomer.add("Induwara");
        regCustomer.add("Kevin");
        regCustomer.add("Jenny");
        regCustomer.add("Alex");
        regCustomer.add("John");
    }

    public static HashMap<String,GloceryItem> getDataBase(){
        return dataBase;
    }

    public static ArrayList<String> getRegCustomerList(){
        return regCustomer;
    }

}
