package GymTrackerApp;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Date;
import java.text.SimpleDateFormat;


public class FrontPage implements ActionListener {

    JFrame frame;

    public static void main(String[] args) {
        new FrontPage();
    }

     public FrontPage () {
        frame = new JFrame();
        JPanel panel = new JPanel();
        JButton button = new JButton("data");
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
        frame.add(panel1,BorderLayout.NORTH);


        panel1.setBackground(inputGUI.colorScheme[0]);
        panel1.setPreferredSize(new Dimension(frame.getWidth(), 100));


         JLabel label = new JLabel("Welcome to the workout and nutrition tracker");
         label.setForeground(inputGUI.colorScheme[2]);

         panel1.setLayout(new GridLayout());
        label.setFont(new Font(Font.DIALOG,  Font.BOLD, 20));
        label.setBackground(inputGUI.colorScheme[0]);
        panel.setBackground(inputGUI.colorScheme[1]);
        panel1.add(label);

        panel.add(button,BorderLayout.SOUTH);



         frame.pack();
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton source = (JButton) e.getSource();

        if(source.getText().equals("data")) {
            frame.dispose();
            new inputGUI();
        }
    }


}
