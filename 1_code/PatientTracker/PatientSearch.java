/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patienttracker;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;
import static patienttracker.ScheduleAppointment.patientHome;

/**
 *
 * @author siarasaylor
 */
class PatientSearch extends StaffMenuFDN {
//--------------------------------------------------------------------------
//doctor view

    PatientSearch() {
        psearch();
    }
    static JFrame staffHome;
    static String inputSearch;
    static String inputSearch2;
    static String monthInput;
    static String dayInput;
    static String yearInput;
    static JTextField enterfname;
    static JTextField enterlname;
    static String userID;
                
    public static void psearch() {
        System.out.println("Patient Search");

        //set up items
        JLabel pSEARCH = new JLabel("Patient Search");

        JPanel mainPanel = new JPanel();
        //set frame size and location
        staffHome = new JFrame("Patient Tracker - Patient Search");
        staffHome.setSize(1300, 800);
        staffHome.setLocationRelativeTo(null);
        mainPanel.setLayout(null);

        //create items for main panel
        JLabel header = new JLabel("Patient Search");
        JLabel fname = new JLabel("First Name");
        JLabel lname = new JLabel("Last Name");
        JLabel dob = new JLabel("Date of Birth");
        enterfname = new JTextField(30);
        enterlname = new JTextField(30);
        JButton searchButton = new JButton("Search");
        JButton newPatient = new JButton("Add New Patient");
        JLabel header2 = new JLabel("Results");
        JLabel img = new JLabel(new ImageIcon("logo3.jpg"));
        header.setFont(new Font("Arial", Font.PLAIN, 30));
        header2.setFont(new Font("Arial", Font.PLAIN, 30));
        JPanel resultTable = new JPanel();

        //table to show results from search
        String[] columnNames = {"First Name", "Last Name", "DOB", "Address", "Phone", "Patient Information"};

        //example data will need to work on being able to populate from text file
        Object[][] data = { };
        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        JTable table = new JTable(model);
        table.getColumn("Patient Information").setCellRenderer(new ButtonRenderer());
        table.getColumn("Patient Information").setCellEditor(new ButtonEditor(new JCheckBox()));
        
        table.setPreferredScrollableViewportSize(new Dimension(200, 400));
        table.setFillsViewportHeight(true);
        table.setBackground(Color.lightGray);

        //Create the scroll pane and add the table to it.
        JScrollPane scrollPane = new JScrollPane(table);

        //combo box for birthday
        String[] month = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};
        String[] day = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12",
            "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26",
            "27", "28", "29", "30", "31"};
        ArrayList<String> years_tmp = new ArrayList<String>();
        for (int years = 1900; years <= Calendar.getInstance().get(Calendar.YEAR); years++) {
            years_tmp.add(years + "");
        }
        JComboBox monthBox = new JComboBox(month);
        JComboBox dayBox = new JComboBox(day);
        JComboBox yearBox = new JComboBox(years_tmp.toArray());

        //horizontal line 
        MyLine horizontal1 = new MyLine();
        MyLine horizontal2 = new MyLine();

        //set location for each item
        img.setBounds(10, 10, img.getPreferredSize().width, img.getPreferredSize().height);
        horizontal1.setBounds(0, 100, 1300, 800);
        horizontal2.setBounds(0, -250, 1300, 800);
        header.setBounds(125, 100, 450, 40);
        fname.setBounds(150, 200, 80, 20);
        enterfname.setBounds(150, 235, 150, 20);
        lname.setBounds(150, 270, 80, 20);
        enterlname.setBounds(150, 305, 150, 20);
        dob.setBounds(150, 340, 80, 20);
        monthBox.setBounds(150, 375, 70, monthBox.getPreferredSize().height);
        dayBox.setBounds(220, 375, 70, dayBox.getPreferredSize().height);
        yearBox.setBounds(290, 375, yearBox.getPreferredSize().width, yearBox.getPreferredSize().height);
        searchButton.setBounds(150, 425, 80, 20);
        newPatient.setBounds(975, 175, 125, 20);

        header2.setBounds(125, 500, 450, 40);
        scrollPane.setBounds(100, 550, 1000, 100);

        //add item to result panel
        //resultTable.add(new JScrollPane(table));
        //add items to main panel
        mainPanel.add(img);
        mainPanel.add(horizontal2);
        mainPanel.add(header);
        mainPanel.add(fname);
        mainPanel.add(enterfname);
        mainPanel.add(lname);

        mainPanel.add(enterlname);
        mainPanel.add(dob);
        mainPanel.add(monthBox);
        mainPanel.add(dayBox);
        mainPanel.add(yearBox);
        mainPanel.add(searchButton);
        mainPanel.add(newPatient);

        mainPanel.add(horizontal1);
        mainPanel.add(header2);
        mainPanel.add(scrollPane);

        if (loginWindow.userInput.startsWith("1") && loginWindow.userInput.length() > 2) {
            System.out.println("Doctor-patient search");
            staffHome.getContentPane().add(BorderLayout.NORTH, dstaffMenu);

        }
        if (loginWindow.userInput.startsWith("2") && loginWindow.userInput.length() > 2) {
            System.out.println("Nurse-patient search");
            staffHome.getContentPane().add(BorderLayout.NORTH, nstaffMenu);

        }
        if (loginWindow.userInput.startsWith("3") && loginWindow.userInput.length() > 2) {
            System.out.println("Front Desk-patient search");
            staffHome.getContentPane().add(BorderLayout.NORTH, fdstaffMenu);

        }
        staffHome.getContentPane().add(BorderLayout.CENTER, mainPanel);
        staffHome.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        staffHome.setResizable(false);
        staffHome.setVisible(true);

        mainPanel.setBackground(Color.WHITE); //changes background color to white

        //action to go to calendar page when button is clicked
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String filePath = "userPass.txt";
                BufferedReader br;
                inputSearch = enterfname.getText();
                inputSearch2 = enterlname.getText();
                monthInput = monthBox.getSelectedItem().toString();
                dayInput = dayBox.getSelectedItem().toString();
                yearInput = yearBox.getSelectedItem().toString();
                boolean found = false;
                String line = "";
                model.setNumRows(0);
                try {
                    br = new BufferedReader(new FileReader(filePath));
                    try {
                        while ((line = br.readLine()) != null) {

                            
                            String[] words = line.split("//");

                            for (String word : words) {
                                if (word.equals(inputSearch)) {
                                    System.out.println("Found: " + inputSearch);
                                    System.out.println(words[2]);
                                    found =true;
                                    
                                }
                                
                                if (word.equals(inputSearch2)) {
                                    System.out.println("Found: " + inputSearch2);
                                    System.out.println(words[4]);
                                    found =true;
                                }
                                if (word.equals(monthInput)) {
                                    System.out.println("Found: " + monthInput);
                                    System.out.println(words[7]);
                                    found =true;
                                }
                                if (word.equals(dayInput)) {
                                    System.out.println("Found: " + dayInput);
                                    System.out.println(words[8]);
                                    found =true;
                                }
                                if (word.equals(yearInput)) {
                                    System.out.println("Found: " + yearInput);
                                    System.out.println(words[9]);
                                    found =true;
                                    model.addRow(new Object[]{words[2], words[4], words[7] + "/" + words[8] + "/" + words[9], words[18], words[10], "Patient Information"});
                                    
                                }
                                if(found = false) {
                                    JOptionPane.showMessageDialog(null, "Patient Not Found", "Error", JOptionPane.ERROR_MESSAGE);
                                    break;
                                }

                                
                                          
                            }
                            
                        }
                        br.close();
                    } catch (IOException exx) {
                        // TODO Auto-generated catch block
                        exx.printStackTrace();
                    }
                } catch (FileNotFoundException ex) {
                    // TODO Auto-generated catch block
                    ex.printStackTrace();
                }

            }
        });
        //action to go to calendar page when button is clicked
        newPatient.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                staffHome.setVisible(false);
                staffHome.dispose();
                NewPatient newp = new NewPatient();
            }
        });
    }
}

    