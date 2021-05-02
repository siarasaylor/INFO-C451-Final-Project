/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patienttracker;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author siarasaylor
 */
//-----------------------------------
class ContactUs {

    ContactUs() {
        contact();
    }

    static JFrame patientHome;
    static JPanel mainPanel2;
    static JMenuBar patientMenu;
    static JMenuItem scheduleAppt;
    static JMenuItem refills;
    static JMenuItem myHealth;
    static JMenuItem profilePage;
    static JMenuItem contactUs;
    static JMenu patientexit;
    static JMenuItem logout;
    static JMenuItem exit;

    public static void contact() {
        System.out.println("Contact Us");

        //set up items
        JLabel patientContact = new JLabel("Contact Us");
        patientMenu = new JMenuBar();
        UIManager.put("MenuBar.background", Color.ORANGE);
        profilePage = new JMenuItem("Profile");
        scheduleAppt = new JMenuItem("Schedule Appointment");
        refills = new JMenuItem("Prescription Refills");
        myHealth = new JMenuItem("My Health");
        contactUs = new JMenuItem("Contact Us");

        patientexit = new JMenu("Exit");
        logout = new JMenuItem("Log Out");
        exit = new JMenuItem("Exit");

        mainPanel2 = new JPanel();
        patientMenu.setPreferredSize(new Dimension(patientMenu.getPreferredSize().width, 30));

        //set frame size and location
        patientHome = new JFrame("Patient Tracker - My Health");
        patientHome.setSize(1300, 800);
        patientHome.setLocationRelativeTo(null);
        mainPanel2.setLayout(null);

        //action to go to Profile page when button is clicked
        profilePage.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                PatientMenu pat = new PatientMenu();
                patientHome.dispose(); //close screen
            }
        });
        //action to go to Schedule Appointment page when button is clicked
        scheduleAppt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                patientHome.setVisible(false);
                patientHome.dispose(); //close screen
                ScheduleAppointment sched = new ScheduleAppointment();
            }
        });
//        //action to go to My Health page when button is clicked
//        myHealth.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                patientHome.setVisible(false);
//                patientHome.dispose(); //close screen
//                MyHealth health = new MyHealth();
//            }
//        });
        //action to go to Contact page when button is clicked
        contactUs.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                patientHome.setVisible(false);
                patientHome.dispose(); //close screen
                ContactUs con = new ContactUs();
            }
        });
//        //action to go to Refills page when button is clicked
//        refills.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                patientHome.setVisible(false);
//                patientHome.dispose(); //close screen
//                PresRefills refill = new PresRefills();
//            }
//        });
        //action to close when button is clicked
        exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                patientHome.dispose(); //close screen
                System.exit(0);
            }
        });
        //action to close when button is clicked
        logout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                patientHome.setVisible(false);
                patientHome.dispose(); //close screen
                loginWindow login = new loginWindow();
                //System.exit(0);
            }
        });
        //add items to menu
        patientexit.add(logout);
        patientexit.add(exit);
        patientMenu.add(profilePage);
        patientMenu.add(scheduleAppt);
//        patientMenu.add(refills);
//        patientMenu.add(myHealth);
        patientMenu.add(contactUs);
        patientMenu.add(patientexit);

        //create items for main panel
        JLabel header = new JLabel("Locations");
        JLabel zip = new JLabel("Zip Code");
        JLabel city = new JLabel("City");

        JTextField ezip = new JTextField(30);
        JTextField ecity = new JTextField(30);
        JButton searchButton = new JButton("Search");
        JLabel header2 = new JLabel("Results");
        JLabel img = new JLabel(new ImageIcon("logo3.jpg"));
        header.setFont(new Font("Arial", Font.PLAIN, 30));
        header2.setFont(new Font("Arial", Font.PLAIN, 30));
        JPanel resultTable = new JPanel();

        //table to show results from search
        String[] columnNames = {"Name", "Phone", "Address", "City", "State", "Zip", "Hours"};

        Object[][] data = {};
        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        JTable table = new JTable(model);

        table.setPreferredScrollableViewportSize(new Dimension(200, 400));
        table.setFillsViewportHeight(true);
        table.setBackground(Color.lightGray);

        //Create the scroll pane and add the table to it.
        JScrollPane scrollPane = new JScrollPane(table);

        //horizontal line 
        MyLine horizontal1 = new MyLine();
        MyLine horizontal2 = new MyLine();

        //set location for each item
        img.setBounds(10, 10, img.getPreferredSize().width, img.getPreferredSize().height);
        horizontal1.setBounds(0, 100, 1300, 800);
        horizontal2.setBounds(0, -250, 1300, 800);
        header.setBounds(125, 100, 450, 40);
        zip.setBounds(150, 200, 80, 20);
        ezip.setBounds(150, 235, 150, 20);
        city.setBounds(150, 270, 80, 20);
        ecity.setBounds(150, 305, 150, 20);

        searchButton.setBounds(150, 375, 80, 20);

        header2.setBounds(125, 500, 450, 40);
        scrollPane.setBounds(100, 550, 1000, 100);

        //add items to panel
        mainPanel2.add(patientContact);
        mainPanel2.add(img);
        mainPanel2.add(horizontal1);
        mainPanel2.add(horizontal2);
        mainPanel2.add(header);
        mainPanel2.add(zip);
        mainPanel2.add(ezip);
        mainPanel2.add(city);
        mainPanel2.add(ecity);
        mainPanel2.add(searchButton);
        mainPanel2.add(header2);
        mainPanel2.add(scrollPane);

        //add panels to JFrame
        patientHome.getContentPane().add(BorderLayout.NORTH, patientMenu);
        patientHome.getContentPane().add(BorderLayout.CENTER, mainPanel2);
        patientHome.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        patientHome.setResizable(false);
        patientHome.setVisible(true);

        mainPanel2.setBackground(Color.WHITE);

        //action to go to search for location
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String filePath = "locations.txt";
                BufferedReader br;
                String inputSearch = ezip.getText();
                String inputSearch2 = ecity.getText();

                boolean found = false;
                String line = "";
                model.setNumRows(0);
                try {
                    br = new BufferedReader(new FileReader(filePath));
                    try {
                        while ((line = br.readLine()) != null) {

                            String[] words = line.split("//");

                            for (String word : words) {
                                if (word.equals(inputSearch2)) {
                                    System.out.println("Found: " + inputSearch2);
                                    System.out.println(words[4]);
                                    found = true;

                                }

                                if (word.equals(inputSearch)) {
                                    System.out.println("Found: " + inputSearch);
                                    System.out.println(words[6]);
                                    found = true;
                                    if(found = true) {
                                    model.addRow(new Object[]{words[1], words[2], words[3], words[4], words[5], words[6], words[7]});
                                }

                                }
                                
                                if (found = false) {
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
    }
}
