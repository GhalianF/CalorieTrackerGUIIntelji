package GymTrackerApp;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;



public class inputGUI implements ActionListener {
    JFrame frame;

    static public ArrayList<TableEntry> tableEntries;
    static public Color[] colorScheme;

    public static AppInfo appInfo;

    static {
        try {
            appInfo = deSerializeAppInfo();
            tableEntries = deSerialize();

            /** IMPORTANT THIS IS HOW U HCNAGE THE COLOR SCHEME INDEX
            appInfo.colorSchemeIndex = number u want;
             **/
            colorScheme = appInfo.colorSchemes[appInfo.colorSchemeIndex];
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    static public String[][] data;
    public static int lastIndex = inputGUI.tableEntries.size()-1;





    public static AppInfo deSerializeAppInfo() throws IOException, ClassNotFoundException {

        AppInfo info = new AppInfo();
        FileInputStream fileIn = new FileInputStream(FilePathStrings.AppInfoFilePath);
        ObjectInputStream inputStream = new ObjectInputStream(fileIn);
        info = (AppInfo) inputStream.readObject();
        fileIn.close();
        inputStream.close();

        return info;
    }

    inputGUI () {
        frame = new JFrame();
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout());
        topPanel.setBackground(colorScheme[0]);

        topPanel.setPreferredSize(new Dimension(frame.getWidth(), 150));

        JButton addButton = new JButton("Add data to today");
        addButton.setBackground(colorScheme[1]);

        topPanel.add(addButton);

        frame.add(topPanel,BorderLayout.NORTH);
        JButton button = new JButton("Click to go back");

        JButton averageAndGoals = new JButton("View daily goals and average data");
        averageAndGoals.addActionListener(this);
        topPanel.add(averageAndGoals);



        topPanel.setBorder(BorderFactory.createEmptyBorder(100,100,100,100));
        topPanel.add(button);




        JPanel mainPanel = new JPanel();
        frame.add(mainPanel,BorderLayout.CENTER);
        mainPanel.setBackground(colorScheme[1]);

        button.addActionListener(this);
        addButton.addActionListener(this);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // Perform custom operations
                try {
                    serializeData();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                //cleanupResources();

                // Close the JFrame
                frame.dispose();
            }
        });



        frame.setTitle("Table");
        String pattern = "MMM-dd-yyyy";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);


        LocalDate currentDateTime = LocalDate.now();
        // Get the current date and time
        LocalDate lastMeasuredTime = LocalDate.parse(tableEntries.get(tableEntries.size()-1).data[0],formatter);
        LocalDate lockDateTime = lastMeasuredTime.plusDays(1);
        // Create a DateTimeFormatter with the pattern
        // or tommorow beofre 3 am
        if( currentDateTime.isBefore(lockDateTime)) {
            data = matchValues(0);

        } else {
            data = matchValues(1);
        }

        // SCRAP THIS AND GET THE DATE OF THE LAST OBJECT USING WHILE HAS NEXT THEN ACT ACOORIDNGLY

        // DeSerialize -> Display -> edit -> serialize

        //loop through the file and find the last day
            //if the last day is equal to today, or tomorrow before 3 AM, then all changes will happen to that day
                //create a array with the current amount of lines as how much space it has allocated
            //if its beyond that (else) create a array with the current amount + 1 and save the new day there
                //next time you run it, the last day will satistfy the first condition


        //loop through file and deserialize




        String[] columnNames = {"Date","Calories","Protein","Workout rating","Last measured weight"};



        JTable table = new JTable(data,columnNames);
        table.setPreferredScrollableViewportSize(new Dimension(700, 600));

        JScrollPane scroll = new JScrollPane(table,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        mainPanel.add(scroll,BorderLayout.CENTER);

        frame.setLocation(400, 250);
        frame.pack();
        frame.setMinimumSize(new Dimension(800, 800));

        frame.setVisible(true);

    }

    private void serializeData() throws IOException {

        ArrayList<TableEntry> entriesArray = tableEntries;
        FileOutputStream fileOut = new FileOutputStream("TableSerial.ser");
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        out.writeObject(entriesArray);
        out.close();
        fileOut.close();



        AppInfo info = appInfo;
        FileOutputStream fileOut2 = new FileOutputStream("AppInfo.ser");
        ObjectOutputStream out2 = new ObjectOutputStream(fileOut2);
        out2.writeObject(info);
        out2.close();
        fileOut2.close();
    }

    private String[][] matchValues(int freeSpace) {
        if(freeSpace == 1) tableEntries.add(new TableEntry());
        String[][] data = new String[tableEntries.size()][5];
        for(int i = 0; i < tableEntries.size() ;i++) {
            for(int k = 0; k < 5; k++) {
                data[i][k] = tableEntries.get(i).data[k];
            }
        }
        return data;
    }

    public static double goalCalculator(String field)  {
        double retValue = 0;

        double lastMeasuredWeight = 130;
        try {
            lastMeasuredWeight = Double.parseDouble(inputGUI.tableEntries.get(inputGUI.tableEntries.size() - 1).data[4]);
        } catch (Exception e ) {

        }

        switch (field) {
            case "calories":
                /**
                 * CHANGE CALORIES PER POUND HERE
                 * LOWER IT SLIGHTLY AS YOU GAIN WEIGHT TO NOT OVERSHOOT
                 * \/\/\/\/\\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/
                 */
                double calPerPound = 22;
                if(lastMeasuredWeight < 132) {
                    calPerPound = 23;
                } else if (retValue < 133 && retValue > 139) {
                    calPerPound = 22;
                } else if (retValue > 139) {
                    calPerPound = 21.2;
                }
                retValue =  retValue * calPerPound;
                return retValue;

            case "protein":
                retValue = retValue * 0.8;
                return retValue;
            case "weight":
                break;
            case "rating":
                retValue = 7;
                return retValue;
        }
        return retValue;
    }


    public static void main(String[] args) {
    new inputGUI();
    }

    public static ArrayList<TableEntry> deSerialize() throws IOException, ClassNotFoundException {
        ArrayList<TableEntry> entriesArrayList =  new ArrayList<>();
        FileInputStream fileIn = new FileInputStream(FilePathStrings.TableSerialFilePath);
        ObjectInputStream inputStream = new ObjectInputStream(fileIn);
        entriesArrayList = (ArrayList<TableEntry>) inputStream.readObject();
        fileIn.close();
        inputStream.close();

        return entriesArrayList;
    }





    @Override
    public void actionPerformed(ActionEvent e) {

        // SERIALIZE TO SAVE !!!!!


        JButton source = (JButton) e.getSource();
        if(source.getText().equals("Add data to today")) {

            new InputWindow();

        }
         else if(source.getText().equals("Click to go back")) {

            frame.dispose();

            new FrontPage();

        }




    }
}
