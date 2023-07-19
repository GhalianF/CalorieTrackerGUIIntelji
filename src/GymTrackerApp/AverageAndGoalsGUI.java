package GymTrackerApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AverageAndGoalsGUI implements ActionListener {
    JFrame frame;

    AverageAndGoalsGUI() {
        frame = new JFrame("Daily goals");

        JPanel mainPanel = new JPanel(new BorderLayout());



        frame.setMinimumSize(new Dimension(800, 300));
        frame.setLocation(570, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        // Set the frame visible
        frame.setVisible(true);

    }

    public static void main(String[] args) {
        AverageAndGoalsGUI gui = new AverageAndGoalsGUI();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
