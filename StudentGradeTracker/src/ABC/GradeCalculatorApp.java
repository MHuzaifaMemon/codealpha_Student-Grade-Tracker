package ABC;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Collections;

public class GradeCalculatorApp extends JFrame {

    private ArrayList<Integer> grades;
    private JTextField numberOfStudentsField;
    private JTextArea resultsTextArea;
    private JButton calculateButton;

    public GradeCalculatorApp() {
        grades = new ArrayList<>();
        setTitle("Grade Calculator App");
        setSize(500, 400); 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setLayout(new GridLayout(4, 2, 10, 10));

        JLabel numberOfStudentsLabel = new JLabel("Enter the number of students:");
        numberOfStudentsLabel.setForeground(Color.BLACK);
        numberOfStudentsLabel.setFont(new Font("Viner Hand ITC", Font.BOLD, 16)); 
        numberOfStudentsField = new JTextField();
        numberOfStudentsField.setBackground(Color.lightGray); 
        numberOfStudentsField.setFont(new Font("Viner Hand ITC", Font.BOLD, 24));


        resultsTextArea = new JTextArea();
        resultsTextArea.setEditable(false);   
        resultsTextArea.setBackground(Color.LIGHT_GRAY); 
        resultsTextArea.setForeground(Color.black);
        resultsTextArea.setFont(new Font("Viner Hand ITC", Font.BOLD, 13));



        calculateButton = new JButton("Calculate Grades");
        calculateButton.setBackground(Color.black);
        calculateButton.setForeground(Color.WHITE);
        calculateButton.setFont(new Font("Viner Hand ITC", Font.BOLD, 13));
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateButtonClicked();
            }
        });

        mainPanel.add(numberOfStudentsLabel, BorderLayout.NORTH); 
        mainPanel.add(numberOfStudentsField, BorderLayout.CENTER); 
        mainPanel.add(calculateButton, BorderLayout.EAST); 
        mainPanel.add(resultsTextArea, BorderLayout.SOUTH); 
        mainPanel.setBackground(Color.GRAY); 
        add(mainPanel);
        setLocationRelativeTo(null);
        setVisible(true);

        numberOfStudentsField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    calculateButtonClicked();
                }
            }
        });
    }

    private void calculateButtonClicked() {
        grades.clear();
        try {
            int numberOfStudents = Integer.parseInt(numberOfStudentsField.getText());

            for (int i = 0; i < numberOfStudents; i++) {
                String input = JOptionPane.showInputDialog("Enter grade for student (0-100) " + (i + 1) + ":");
                int grade = Integer.parseInt(input);
                if (grade >= 0 && grade <= 100)
                    grades.add(grade);
                else {
                    JOptionPane.showMessageDialog(this, "Invalid marks entered. Results till the correct marks entered.");
                    break;
                }
            }

            double average = calculateAverage(grades);
            int highestScore = findHighestScore(grades);
            int lowestScore = findLowestScore(grades);

            resultsTextArea.setText("Results:\n" +
                    "Average Score: " + average + "\n" +
                    "Highest Score: " + highestScore + "\n" +
                    "Lowest Score: " + lowestScore);

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number of students.");
        }
    }

    private double calculateAverage(ArrayList<Integer> grades) {
        int sum = 0;
        for (int grade : grades) {
            sum += grade;
        }
        return ((double) sum / grades.size());
    }

    private int findHighestScore(ArrayList<Integer> grades) {
        return (Collections.max(grades));
    }

    private int findLowestScore(ArrayList<Integer> grades) {
        return (Collections.min(grades));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GradeCalculatorApp();
            }
        });
    }
}
