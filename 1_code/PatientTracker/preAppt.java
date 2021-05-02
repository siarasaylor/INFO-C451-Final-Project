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
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.Border;

/**
 *
 * @author siarasaylor
 */
class preAppt {
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
    preAppt() {
        preapointment();
    }
    static void preapointment() {
        System.out.println("Pre Appointment");

        //set up items
        JLabel scheduleAppointment = new JLabel("Pre Appointment");
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
        patientHome = new JFrame("Patient Tracker - Pre Appointment");
        patientHome.setSize(1300,800);
        patientHome.setLocationRelativeTo(null);
        mainPanel2.setLayout(null);

        //action to go to Profile page when button is clicked
        profilePage.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                patientHome.setVisible(false);
                patientHome.dispose(); //close screen
                PatientMenu pat = new PatientMenu();
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
        //create items for main panel
        JLabel header1 = new JLabel("Pre Appointment");
        JLabel header2 = new JLabel("Insurance Information");
        JLabel header3 = new JLabel("Pre-Medical Conditions");
        JLabel header4 = new JLabel("List any known allergies.");
        JLabel header5 = new JLabel("List any medications you are taking.");
        
        header1.setFont(new Font("Arial", Font.PLAIN, 30));
        header2.setFont(new Font("Arial", Font.BOLD, 24));
        header3.setFont(new Font("Arial", Font.BOLD, 24));
        header4.setFont(new Font("Arial", Font.PLAIN, 14));
        header5.setFont(new Font("Arial", Font.PLAIN, 14));
        
        JLabel inprovider = new JLabel("Insurance Provider");
        JTextField enterinprovider = new JTextField(50);
        JLabel membernum = new JLabel("Membership Number");
        JTextField entermembernum = new JTextField(25);
        
        JTextArea reason1 = new JTextArea();
        Border border = BorderFactory.createLineBorder(Color.BLACK);
        reason1.setBorder(BorderFactory.createCompoundBorder(border,
            BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        
        JTextArea reason2 = new JTextArea();
        Border border2 = BorderFactory.createLineBorder(Color.BLACK);
        reason2.setBorder(BorderFactory.createCompoundBorder(border2,
            BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        
        
        JButton submit = new JButton("Submit");
        
        //image logo
        JLabel img1 = new JLabel(new ImageIcon("logo3.jpg"));
        
        //horizontal line 
        MyLine horizontal1 = new MyLine();
        MyLine horizontal2 = new MyLine();
   
        
        //header
        img1.setBounds(10, 10, img1.getPreferredSize().width, img1.getPreferredSize().height);
        header1.setBounds(125, 100, 450, 40);
        horizontal2.setBounds(0, -250, 1300, 800);
        
        //section1
        header2.setBounds(125,150,450,40);
        inprovider.setBounds(150, 200, 150, 20);
        enterinprovider.setBounds(150, 225, 150,20);
        membernum.setBounds(360, 200,150,20);
        entermembernum.setBounds(360,225,150,20);
        
        //section2
        horizontal1.setBounds(0, -100, 1300, 800);
        header3.setBounds(125,300,450,40 );
        
        header4.setBounds(125, 335, 450,40);
        header5.setBounds(700, 335, 450,40);
        reason1.setBounds(125, 385, 550, 100);
        reason2.setBounds(700, 385, 550, 100);
        submit.setBounds(1000, 655, submit.getPreferredSize().width, submit.getPreferredSize().height);
        //add items to menu
        patientexit.add(logout);
        patientexit.add(exit);
        patientMenu.add(profilePage);
        patientMenu.add(scheduleAppt);
//        patientMenu.add(refills);
//        patientMenu.add(myHealth);
        patientMenu.add(contactUs);
        patientMenu.add(patientexit);

       
        
        //add items to panel
        mainPanel2.add(img1);
        mainPanel2.add(header1);
        mainPanel2.add(horizontal2);
        mainPanel2.add(header2);
        
        mainPanel2.add(horizontal1);
        mainPanel2.add(inprovider);
        mainPanel2.add(enterinprovider);
        
        mainPanel2.add(membernum);
        mainPanel2.add(entermembernum);

        mainPanel2.add(header3);
//
        mainPanel2.add(header4);
        mainPanel2.add(header5);
        mainPanel2.add(reason1);
        mainPanel2.add(reason2);
        mainPanel2.add(submit);
      
        //add panels to JFrame
        patientHome.getContentPane().add(BorderLayout.NORTH, patientMenu);
        patientHome.getContentPane().add(BorderLayout.CENTER,mainPanel2);
        patientHome.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        patientHome.setResizable(false);
        patientHome.setVisible(true);
        
        mainPanel2.setBackground(Color.WHITE);
        
        //action to go to newAppointment2 page when button is clicked
        submit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Pre Appointment Completed.", "Success", JOptionPane.INFORMATION_MESSAGE);
                
                FileWriter fw = null;
                    try {
                        String userID = loginWindow.userInput.toString();
                        File inputFile = new File("preAppt.txt");
                        fw = new FileWriter(inputFile.getAbsoluteFile(), true);

                        fw.write("\n" + userID + "//" + enterinprovider.getText().toString() + "//" + entermembernum.getText().toString() + "//"
                                + reason1.getText().toString() + "//" + reason2.getText().toString());
                        fw.close();
                        
                    } catch (IOException ex) {
                        Logger.getLogger(AppointmentUpdate.class.getName()).log(Level.SEVERE, null, ex);
                    }
                PatientMenu pat = new PatientMenu();
                patientHome.dispose(); //close screen
            }
        });
        
    }
}

// staff view
class preAppt1 {
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
    preAppt1() {
        preapointment();
    }
    static void preapointment() {
        System.out.println("Pre Appointment - Staff View");

        //set up items
        JLabel pSEARCH = new JLabel("Pre Appointment");

        StaffMenuFDN.mainPanel = new JPanel();
        //set frame size and location
        StaffMenuFDN.staffHome = new JFrame("Patient Tracker - Pre Appointment");
        StaffMenuFDN.staffHome.setSize(1300, 800);
        StaffMenuFDN.staffHome.setLocationRelativeTo(null);
        StaffMenuFDN.mainPanel.setLayout(null);

        

        //create items for main panel
        JLabel header1 = new JLabel("Pre Appointment");
        JLabel header2 = new JLabel("Insurance Information");
        JLabel header3 = new JLabel("Pre-Medical Conditions");
        JLabel header4 = new JLabel("List any known allergies.");
        JLabel header5 = new JLabel("List any medications you are taking.");
        
        header1.setFont(new Font("Arial", Font.PLAIN, 30));
        header2.setFont(new Font("Arial", Font.BOLD, 24));
        header3.setFont(new Font("Arial", Font.BOLD, 24));
        header4.setFont(new Font("Arial", Font.PLAIN, 14));
        header5.setFont(new Font("Arial", Font.PLAIN, 14));
        
        JLabel inprovider = new JLabel("Insurance Provider");
        JTextField enterinprovider = new JTextField(50);
        JLabel membernum = new JLabel("Membership Number");
        JTextField entermembernum = new JTextField(25);
        
        JTextArea reason1 = new JTextArea();
        Border border = BorderFactory.createLineBorder(Color.BLACK);
        reason1.setBorder(BorderFactory.createCompoundBorder(border,
            BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        
        JTextArea reason2 = new JTextArea();
        Border border2 = BorderFactory.createLineBorder(Color.BLACK);
        reason2.setBorder(BorderFactory.createCompoundBorder(border2,
            BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        
        
        JButton back = new JButton("Back");
        
        //image logo
        JLabel img1 = new JLabel(new ImageIcon("logo3.jpg"));
        
        //horizontal line 
        MyLine horizontal1 = new MyLine();
        MyLine horizontal2 = new MyLine();

        
        //header
        img1.setBounds(10, 10, img1.getPreferredSize().width, img1.getPreferredSize().height);
        header1.setBounds(125, 100, 450, 40);
        horizontal2.setBounds(0, -250, 1300, 800);
        
        //section1
        header2.setBounds(125,150,450,40);
        inprovider.setBounds(150, 200, 150, 20);
        enterinprovider.setBounds(150, 225, 150,20);
        membernum.setBounds(360, 200,150,20);
        entermembernum.setBounds(360,225,150,20);
        
        //section2
        horizontal1.setBounds(0, -100, 1300, 800);
        header3.setBounds(125,300,450,40 );
        
        header4.setBounds(125, 335, 450,40);
        header5.setBounds(700, 335, 450,40);
        reason1.setBounds(125, 385, 550, 100);
        reason2.setBounds(700, 385, 550, 100);
        back.setBounds(1000, 655, back.getPreferredSize().width, back.getPreferredSize().height);
        
        
        //add items to panel
        StaffMenuFDN.mainPanel.add(img1);
        StaffMenuFDN.mainPanel.add(header1);
        StaffMenuFDN.mainPanel.add(horizontal2);
        StaffMenuFDN.mainPanel.add(header2);
        
        StaffMenuFDN.mainPanel.add(horizontal1);
        StaffMenuFDN.mainPanel.add(inprovider);
        StaffMenuFDN.mainPanel.add(enterinprovider);
        
        StaffMenuFDN.mainPanel.add(membernum);
        StaffMenuFDN.mainPanel.add(entermembernum);

        StaffMenuFDN.mainPanel.add(header3);
//
        StaffMenuFDN.mainPanel.add(header4);
        StaffMenuFDN.mainPanel.add(header5);
        StaffMenuFDN.mainPanel.add(reason1);
        StaffMenuFDN.mainPanel.add(reason2);
        StaffMenuFDN.mainPanel.add(back);
     
        String filePath = "userPass.txt";
        BufferedReader br1;
        boolean found1 = false;
        String line1 = "";
        String userID = "";
        try {
            br1 = new BufferedReader(new FileReader(filePath));
            try {
                while ((line1 = br1.readLine()) != null) {

                    String[] words = line1.split("//");

                    for (String word : words) {
                        if (word.equals(PatientSearch.inputSearch)) {
                            userID = words[0];
                            System.out.println(words[0]);
                            System.out.println(words[2]);
                            found1 = true;
                           
                        }

                        
                        if (found1 = false) {
                            JOptionPane.showMessageDialog(null, "Patient Not Found", "Error", JOptionPane.ERROR_MESSAGE);
                            break;
                        }

                    }
                    
                    

                }
                br1.close();
            } catch (IOException exx) {
                // TODO Auto-generated catch block
                exx.printStackTrace();
            }
            
            Scanner s2 = new Scanner(new File("preAppt.txt"));
                Boolean found2 = false;
                while (s2.hasNextLine() && !found2) {

                    String in1 = s2.nextLine();
                    String[] sArray = in1.split("//");

                    if (userID.equals(sArray[0])) {

                        enterinprovider.setText(sArray[1]);
                        enterinprovider.setEnabled(false);
                        enterinprovider.setEditable(false);

                       
                        entermembernum.setText(sArray[2]);
                        entermembernum.setEnabled(false);
                        entermembernum.setEditable(false);

                        reason1.setText(sArray[3]);
                        reason1.setEnabled(false);
                        reason1.setEditable(false);

                        reason2.setText(sArray[4]);
                        reason2.setEnabled(false);
                        reason2.setEditable(false);
                    }
                }

        } catch (IOException exx) {
                // TODO Auto-generated catch block
                exx.printStackTrace();
            }
        try {

               
                Scanner s2 = new Scanner(new File("preAppt.txt"));
                Boolean found2 = false;
                while (s2.hasNextLine() && !found2) {

                    String in1 = s2.nextLine();
                    String[] sArray = in1.split("//");

                    if (userID.equals(sArray[0])) {

                        enterinprovider.setText(sArray[1]);
                        enterinprovider.setEnabled(false);
                        enterinprovider.setEditable(false);

                       
                        entermembernum.setText(sArray[2]);
                        entermembernum.setEnabled(false);
                        entermembernum.setEditable(false);

                        reason1.setText(sArray[3]);
                        reason1.setEnabled(false);
                        reason1.setEditable(false);

                        reason2.setText(sArray[4]);
                        reason2.setEnabled(false);
                        reason2.setEditable(false);

                        
                    }
                }

            } catch (IOException ex) {
                Logger.getLogger(AppointmentUpdate.class.getName()).log(Level.SEVERE, null, ex);
            }
       
        
        
        if (loginWindow.userInput.startsWith("1") && loginWindow.userInput.length() > 2) {
            System.out.println("Doctor-pre appointment");
            StaffMenuFDN.staffHome.getContentPane().add(BorderLayout.NORTH, StaffMenuFDN.dstaffMenu);
            StaffMenuFDN.staffHome.getContentPane().add(BorderLayout.CENTER, StaffMenuFDN.mainPanel);
        StaffMenuFDN.staffHome.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        StaffMenuFDN.staffHome.setResizable(false);
        StaffMenuFDN.staffHome.setVisible(true);

        StaffMenuFDN.mainPanel.setBackground(Color.WHITE); //changes background color to white
        }
        if (loginWindow.userInput.startsWith("2") && loginWindow.userInput.length() > 2) {
            System.out.println("Nurse-pre appointment");
            StaffMenuFDN.staffHome.getContentPane().add(BorderLayout.NORTH, StaffMenuFDN.nstaffMenu);
StaffMenuFDN.staffHome.getContentPane().add(BorderLayout.CENTER, StaffMenuFDN.mainPanel);
        StaffMenuFDN.staffHome.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        StaffMenuFDN.staffHome.setResizable(false);
        StaffMenuFDN.staffHome.setVisible(true);

        StaffMenuFDN.mainPanel.setBackground(Color.WHITE); //changes background color to white
        }
        if (loginWindow.userInput.startsWith("3") && loginWindow.userInput.length() > 2) {
            System.out.println("Front pre appointment");
            StaffMenuFDN.staffHome.getContentPane().add(BorderLayout.NORTH, StaffMenuFDN.fdstaffMenu);
            StaffMenuFDN.staffHome.getContentPane().add(BorderLayout.CENTER, StaffMenuFDN.mainPanel);
        StaffMenuFDN.staffHome.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        StaffMenuFDN.staffHome.setResizable(false);
        StaffMenuFDN.staffHome.setVisible(true);

        StaffMenuFDN.mainPanel.setBackground(Color.WHITE); //changes background color to white
        }
        
        
        
        

        //action to go to newAppointment2 page when button is clicked
        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                PatientSearch pat = new PatientSearch();
                StaffMenuFDN.staffHome.dispose(); //close screen
            }
        });
        
    }
}

        
        
        
//         