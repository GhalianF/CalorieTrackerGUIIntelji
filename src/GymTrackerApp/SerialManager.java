package GymTrackerApp;

import java.io.*;
import java.util.ArrayList;

public class SerialManager implements Serializable {
    public static void main(String[] args) throws IOException {

        ArrayList<TableEntry> entriesArray = new ArrayList<>();
        entriesArray.add(new TableEntry());
        FileOutputStream fileOut = new FileOutputStream("TableSerial.ser");
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        out.writeObject(entriesArray);
        out.close();
        fileOut.close();



        AppInfo appInfo = new AppInfo();
        FileOutputStream fileOut2 = new FileOutputStream("AppInfo.ser");
        ObjectOutputStream out2 = new ObjectOutputStream(fileOut2);
        out2.writeObject(appInfo);
        out2.close();
        fileOut2.close();
    }

}
