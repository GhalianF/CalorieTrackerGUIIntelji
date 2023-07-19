package GymTrackerApp;

import java.io.FileNotFoundException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class TableEntry implements Serializable {
    String[] data;
     transient Date today = new Date();
    transient SimpleDateFormat formatter1 = new SimpleDateFormat("MMM/dd/YY");

    transient LocalDate date = LocalDate.now();
    transient String pattern = "MMM-dd-yyyy";

    // Create a DateTimeFormatter with the pattern
    transient DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);

    public TableEntry() {
        String lastMeasuredWeight = "130";
            try {
                lastMeasuredWeight = inputGUI.tableEntries.get(inputGUI.tableEntries.size() - 1).data[4];
            } catch (Exception e ) {

            }

        this.data = new String[]{date.format(formatter) ,"0","0","0",lastMeasuredWeight };
    }
    public TableEntry(String[] data) {
        this.data = data;
    }


}
