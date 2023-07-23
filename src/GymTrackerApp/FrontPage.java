package GymTrackerApp;


import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Objects;
//comment

public class FrontPage implements ActionListener {

    JFrame frame;
    JComboBox settings;
    JButton button;

    public static void main(String[] args) {
        new FrontPage();
    }

     public FrontPage () {
        frame = new JFrame();
        JPanel panel = new JPanel();
         button = new JButton("data");
        button.setBounds(new Rectangle(40,20));
         frame.setMaximumSize(new Dimension(800, 500));
         frame.setMinimumSize(new Dimension(800, 500));
         frame.setLocation(400, 250);
         panel.setBorder(BorderFactory.createEmptyBorder(100,100,100,100));

        panel.setLayout(new BorderLayout(0,0));
        frame.setLayout(new BorderLayout(0,0));

        frame.add(panel,BorderLayout.CENTER);

         button.addActionListener(this);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Workout Tracker");

        JPanel panel1 = new JPanel();

        panel1.setLayout(new FlowLayout());
        frame.add(panel1,BorderLayout.NORTH);



        panel1.setBackground(inputGUI.colorScheme[0]);
        panel1.setPreferredSize(new Dimension(frame.getWidth(), 150));


         JLabel label = new JLabel("Welcome to the workout and nutrition tracker           change color scheme");
         label.setForeground(inputGUI.colorScheme[2]);

         panel1.setLayout(new BorderLayout(10,10));
         JPanel comboBoxPanel = new JPanel();
         comboBoxPanel.setBackground(inputGUI.colorScheme[0]);
         comboBoxPanel.setPreferredSize(new Dimension(790,150));

         panel1.add(comboBoxPanel,BorderLayout.WEST);
        label.setFont(new Font(Font.DIALOG,  Font.BOLD, 15));
        label.setBackground(inputGUI.colorScheme[0]);
        panel.setBackground(inputGUI.colorScheme[1]);
        comboBoxPanel.add(label);

        panel.add(button,BorderLayout.SOUTH);



         settings = new JComboBox(new String[] {"Black and Blue","Old Money", "Strawberry", "USA"});
        settings.addActionListener(this);
         //settings.setPreferredSize(new Dimension(800,10));

         comboBoxPanel.add(settings);


         frame.pack();
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e)  {

        if(e.getSource() == button) {
            frame.dispose();
            new inputGUI();
        }

        if(e.getSource() == settings) {
            String selected = (String) settings.getSelectedItem();

            if(Objects.equals(selected, "Black and Blue")) {
                inputGUI.appInfo.colorSchemeIndex = 0;
            }
            if(Objects.equals(selected, "Old Money")) {
                inputGUI.appInfo.colorSchemeIndex = 1;
            }
            if(Objects.equals(selected, "Strawberry")) {
                inputGUI.appInfo.colorSchemeIndex = 2;
            }
            if(Objects.equals(selected, "USA")) {
                inputGUI.appInfo.colorSchemeIndex = 3;
            }

            AppInfo info = inputGUI.appInfo;

            try {
                FileOutputStream fileOut2 = new FileOutputStream("AppInfo.ser");
                ObjectOutputStream out2 = new ObjectOutputStream(fileOut2);
                out2.writeObject(info);
                out2.close();
                fileOut2.close();
            } catch (Exception e1) {

            }
            JOptionPane.showMessageDialog(null,"Color scheme changed successfully, it will be displayed the next time the app is opened","",JOptionPane.PLAIN_MESSAGE);


        }


    }


}
