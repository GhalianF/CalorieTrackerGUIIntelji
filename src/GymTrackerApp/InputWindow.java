package GymTrackerApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.HashMap;

public class InputWindow implements ActionListener {
    JFrame frame;
    JTextField proteinField;
    JTextField calorieField;
    JTextField workoutField;
    JButton addButton;
    JButton setButton;
    JComboBox foods;
    JButton secondAdd;
    JTextField quantityField;

    JTextField snackNameField;

    JTextField calorieQuickAdd;

    JTextField proteinQuickAdd;

    JTextField weightField;

    JButton setWeight;
    JButton addFood;

    JButton exit;



    InputWindow() {
        frame = new JFrame();
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        JPanel panel1 = new JPanel();
        panel1.setBackground(inputGUI.colorScheme[0]);

        JPanel repeatedFoodsPanel = new JPanel();
        repeatedFoodsPanel.setPreferredSize(new Dimension(frame.getWidth(), 180));
        repeatedFoodsPanel.setBackground(inputGUI.colorScheme[1]);
        repeatedFoodsPanel.setLayout(new FlowLayout());

        panel1.setLayout(new FlowLayout());
        mainPanel.add(repeatedFoodsPanel,BorderLayout.SOUTH);

        mainPanel.add(panel1,BorderLayout.CENTER);
        JButton button = new JButton("exit");
        //frame.setMaximumSize(new Dimension(800, 300));
        frame.setMinimumSize(new Dimension(800, 300));
        frame.setLocation(570, 200);
        button.addActionListener(this);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.add(mainPanel);

        panel1.setLayout(new FlowLayout());

        JLabel JLabel1 = new JLabel("Calories");
        JLabel1.setForeground(inputGUI.colorScheme[2]);
        panel1.add(JLabel1);

        calorieField = new JTextField();
        calorieField.setPreferredSize(new Dimension(100,20));
        panel1.add(calorieField);

        JLabel JLabel2 = new JLabel("Protein");
        JLabel2.setForeground(inputGUI.colorScheme[2]);
        panel1.add(JLabel2);

        proteinField = new JTextField();
        proteinField.setPreferredSize(new Dimension(100,20));
        panel1.add(proteinField);

        calorieField.setText("0");
        proteinField.setText("0");

        addButton = new JButton("add");
        panel1.add(addButton);

        JLabel JLabel3 = new JLabel("Workout rating(0-10, 7 = average");
        JLabel3.setForeground(inputGUI.colorScheme[2]);
        panel1.add(JLabel3);


        addButton.addActionListener(this);

        workoutField = new JTextField();
        workoutField.setPreferredSize(new Dimension(40,20));
        panel1.add(workoutField);

        setButton = new JButton("set");
        panel1.add(setButton);
        setButton.addActionListener(this);

        JLabel label1 = new JLabel("QuickAdd food");
         label1.setForeground(inputGUI.colorScheme[2]);
        repeatedFoodsPanel.add(label1);

        //drop down menu (combobox in java swing terms) that allows a selection of already saved foods

        //QuickAddFoods[] foodsForNow = (QuickAddFoods[]) inputGUI.appInfo.quickAddFoods.toArray();
        foods = new JComboBox(inputGUI.appInfo.quickAddFoods.toArray());







        foods.setPreferredSize(new Dimension(140,18));
        foods.setEditable(true);
        foods.addActionListener(this);

        repeatedFoodsPanel.add(foods);



        JLabel label2 = new JLabel("Quantity");
        label2.setForeground(inputGUI.colorScheme[2]);
        repeatedFoodsPanel.add(label2);

        quantityField = new JTextField();
        quantityField.setPreferredSize(new Dimension(40,20));
        quantityField.setText("1");
        repeatedFoodsPanel.add(quantityField);

        secondAdd = new JButton("add");
        secondAdd.addActionListener(this);
        repeatedFoodsPanel.add(secondAdd);


        JLabel label3 = new JLabel("Add a food that you eat regularly to the drop down menu below for QuickAdd");
        label3.setForeground(inputGUI.colorScheme[2]);
        repeatedFoodsPanel.add(label3);

        repeatedFoodsPanel.add(new JLabel("                                                      "));

        JLabel l = new JLabel("Name of the snack");
        l.setForeground(inputGUI.colorScheme[2]);
        repeatedFoodsPanel.add(l);

        snackNameField = new JTextField();
        snackNameField.setPreferredSize(new Dimension(140,20));
        repeatedFoodsPanel.add(snackNameField);

        weightField = new JTextField();
        weightField.setPreferredSize(new Dimension(60,20));
        panel1.add(weightField);

        setWeight = new JButton("set Weight");
        setWeight.addActionListener(this);
        panel1.add(setWeight);

        JLabel l1 = new JLabel("Calorie");
        l1.setForeground(inputGUI.colorScheme[2]);
        repeatedFoodsPanel.add(l1);


        calorieQuickAdd = new JTextField();
        calorieQuickAdd.setPreferredSize(new Dimension(100,20));
        repeatedFoodsPanel.add(calorieQuickAdd);

        JLabel l3 = new JLabel("Protein");
        l3.setForeground(inputGUI.colorScheme[2]);
        repeatedFoodsPanel.add(l3);


        proteinQuickAdd = new JTextField();
        proteinQuickAdd.setPreferredSize(new Dimension(60,20));
        repeatedFoodsPanel.add(proteinQuickAdd);

        addFood = new JButton("add to QuickAdd");
        addFood.addActionListener(this);
        repeatedFoodsPanel.add(addFood);

        JPanel lastPanel = new JPanel();
        lastPanel.setBackground(inputGUI.colorScheme[1]);

        exit = new JButton("Save");
        exit.addActionListener(this);
        lastPanel.add(exit);
        lastPanel.setPreferredSize(new Dimension(frame.getWidth(), 60));
        repeatedFoodsPanel.add(lastPanel);



        frame.setTitle("Input");
        frame.pack();
        frame.setVisible(true);

    }
    public static void main(String[] args) {
        InputWindow window = new InputWindow();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        TableEntry currentRow = inputGUI.tableEntries.get(inputGUI.tableEntries.size()-1);


        if(e.getSource() == addButton) {

            currentRow.data[1] = String.valueOf(Double.parseDouble(currentRow.data[1]) + Double.parseDouble(calorieField.getText()));
            currentRow.data[2] = String.valueOf(Double.parseDouble(currentRow.data[2]) + Double.parseDouble(proteinField.getText()));
            inputGUI.data[inputGUI.tableEntries.size()-1][1] =  currentRow.data[1];
            inputGUI.data[inputGUI.tableEntries.size()-1][2] =  currentRow.data[2];


            calorieField.setText("0");
            proteinField.setText("0");
        }

        if(e.getSource() == setButton) {
            currentRow.data[3] = workoutField.getText();
            inputGUI.data[inputGUI.tableEntries.size()-1][3] =  currentRow.data[3];
          }
        if(e.getSource() == foods) {
        }



        if(e.getSource() == setWeight) {

            currentRow.data[4] = weightField.getText();
            inputGUI.data[inputGUI.tableEntries.size()-1][4] =  currentRow.data[4];
        }

        if(e.getSource() == exit) {
            frame.dispose();
        }

        if(e.getSource() == secondAdd) {
            double quantity = Double.parseDouble(quantityField.getText());
            QuickAddFoods selectedFood = (QuickAddFoods) foods.getSelectedItem();

            double calories = Double.parseDouble(selectedFood.calories) * quantity;
            double protein = Double.parseDouble(selectedFood.protein) * quantity;
            calorieField.setText(calories + "");
            proteinField.setText(protein + "");
            addButton.doClick();

            quantityField.setText("1");

            //addButton.doClick();
        }

        if(e.getSource() == addFood) {
            QuickAddFoods food = new QuickAddFoods(snackNameField.getText(),calorieQuickAdd.getText(),proteinQuickAdd.getText());
            inputGUI.appInfo.quickAddFoods.add(food);
            snackNameField.setText("");
            calorieQuickAdd.setText("");
            proteinQuickAdd.setText("");
        }
    }
}

