/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patienttracker;

import com.sun.javafx.collections.MappingChange.Map;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.TextAttribute;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import javax.swing.table.TableModel;
import static patienttracker.NewAppointment.provideR;
import static patienttracker.NewAppointment2.kinD;

/**
 *
 * @author siarasaylor
 */
class Review extends NewAppointment2 {

    Review() {
        schedNewAppt();
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

    public static void schedNewAppt() {
        System.out.println("Review Appointment");

        //set up items
        JLabel scheduleAppointment = new JLabel("Review Appointment");
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
        patientHome = new JFrame("Patient Tracker - Review Appointment");
        patientHome.setSize(1300, 800);
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
        JLabel header1 = new JLabel("Review Appointment Information");
        JLabel header2 = new JLabel("Review the following:");
        JLabel header3 = new JLabel("Contact Info: ");
        JLabel header4 = new JLabel("Appointment Info: ");

        header1.setFont(new Font("Arial", Font.PLAIN, 30));
        header2.setFont(new Font("Arial", Font.BOLD, 24));
        header3.setFont(new Font("Arial", Font.BOLD, 16));
        header4.setFont(new Font("Arial", Font.BOLD, 16));

        JLabel fname = new JLabel("First Name");
        JLabel mname = new JLabel("Middle Name");
        JLabel lname = new JLabel("Last Name");
        JLabel phone = new JLabel("Phone Number");
        JLabel email = new JLabel("Email Address");
        JLabel location = new JLabel("Location");
        JLabel visitType = new JLabel("Visit Type");
        JLabel kindType = new JLabel("Kind");
        JLabel details = new JLabel("Details");
        JLabel appointment = new JLabel("Appointment");

        location.setFont(new Font("Arial", Font.BOLD, 14));
        visitType.setFont(new Font("Arial", Font.BOLD, 14));
        details.setFont(new Font("Arial", Font.BOLD, 14));
        appointment.setFont(new Font("Arial", Font.BOLD, 14));

        JTextField enterfname = new JTextField(30);
        JTextField entermname = new JTextField(25);
        JTextField enterlname = new JTextField(30);
        JTextField enterphone = new JTextField(13);
        JTextField enteremail = new JTextField(50);

        JTextArea reason = new JTextArea();
        JTextArea apptDetails = new JTextArea();
        JTextField typeVisit = new JTextField();
        JTextField kindVisit = new JTextField();

        Border border = BorderFactory.createLineBorder(Color.BLACK);
        reason.setBorder(BorderFactory.createCompoundBorder(border,
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));

        Border border2 = BorderFactory.createLineBorder(Color.BLACK);
        apptDetails.setBorder(BorderFactory.createCompoundBorder(border2,
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));

        JButton confirmBtn = new JButton("Confirm Appointment");
        JButton backBtn = new JButton("Back");
        JButton confirmBtn2 = new JButton("Confirm Appointment");
        JButton backBtn2 = new JButton("Back");
        //image logo
        JLabel img1 = new JLabel(new ImageIcon("logo3.jpg"));

        //horizontal line 
        MyLine horizontal1 = new MyLine();
        MyLine horizontal2 = new MyLine();
        MyLine horizontal3 = new MyLine();

        //map
        RectDraw rectangleMap = new RectDraw();

        //header
        img1.setBounds(10, 10, img1.getPreferredSize().width, img1.getPreferredSize().height);
        header1.setBounds(125, 100, 450, 40);
        horizontal2.setBounds(0, -250, 1300, 800);
        confirmBtn2.setBounds(1000, 100, confirmBtn.getPreferredSize().width, confirmBtn.getPreferredSize().height);
        backBtn2.setBounds(925, 100, backBtn2.getPreferredSize().width, backBtn2.getPreferredSize().height);
        //section2
        horizontal1.setBounds(0, -150, 1300, 800);
        header2.setBounds(125, 150, 450, 40);
        header3.setBounds(125, 185, 450, 40);

        fname.setBounds(340, 200, 80, 20);
        enterfname.setBounds(340, 235, 150, 20);
        mname.setBounds(530, 200, 125, 20);
        entermname.setBounds(530, 235, 150, 20);
        lname.setBounds(720, 200, 150, 20);
        enterlname.setBounds(720, 235, 150, 20);
        phone.setBounds(340, 270, 150, 20);
        enterphone.setBounds(340, 305, 150, 20);
        email.setBounds(530, 270, 150, 20);
        enteremail.setBounds(530, 305, 200, 20);

        //section3
        horizontal3.setBounds(0, 5, 1300, 800);
        header4.setBounds(125, 405, 450, 40);
        location.setBounds(740, 440, 80, 20);
        visitType.setBounds(500, 440, 80, 20);
        typeVisit.setBounds(500, 465, 150, 20);
        kindType.setBounds(500, 495, 80, 20);
        kindVisit.setBounds(500, 520, 150, 20);
        appointment.setBounds(125, 440, 100, 20);
        apptDetails.setBounds(125, 465, 300, 60);
        details.setBounds(125, 550, 80, 20);
        reason.setBounds(125, 585, 400, 100);
        rectangleMap.setBounds(840, 430, rectangleMap.getPreferredSize().width, rectangleMap.getPreferredSize().height);

        confirmBtn.setBounds(1000, 655, confirmBtn.getPreferredSize().width, confirmBtn.getPreferredSize().height);
        backBtn.setBounds(925, 655, backBtn2.getPreferredSize().width, backBtn2.getPreferredSize().height);

        //add items to menu
        patientexit.add(logout);
        patientexit.add(exit);
        patientMenu.add(profilePage);
        patientMenu.add(scheduleAppt);
        patientMenu.add(refills);
        patientMenu.add(myHealth);
        patientMenu.add(contactUs);
        patientMenu.add(patientexit);

        //add items to panel
        mainPanel2.add(img1);
        mainPanel2.add(header1);
        mainPanel2.add(confirmBtn2);
        mainPanel2.add(backBtn2);

        mainPanel2.add(horizontal2);
        mainPanel2.add(header2);
        mainPanel2.add(header3);
        mainPanel2.add(fname);
        mainPanel2.add(enterfname);

        mainPanel2.add(mname);
        mainPanel2.add(entermname);

        mainPanel2.add(lname);
        mainPanel2.add(enterlname);
        mainPanel2.add(phone);
        mainPanel2.add(enterphone);

        mainPanel2.add(email);
        mainPanel2.add(enteremail);

        mainPanel2.add(horizontal3);

        mainPanel2.add(header4);
        mainPanel2.add(location);
        mainPanel2.add(visitType);
        mainPanel2.add(typeVisit);
        mainPanel2.add(kindType);
        mainPanel2.add(kindVisit);
        mainPanel2.add(appointment);
        mainPanel2.add(apptDetails);
        mainPanel2.add(details);
        mainPanel2.add(reason);
        mainPanel2.add(rectangleMap);
        mainPanel2.add(confirmBtn);
        mainPanel2.add(backBtn);

        //add panels to JFrame
        patientHome.getContentPane().add(BorderLayout.NORTH, patientMenu);
        patientHome.getContentPane().add(BorderLayout.CENTER, mainPanel2);
        patientHome.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        patientHome.setResizable(false);
        patientHome.setVisible(true);

        mainPanel2.setBackground(Color.WHITE);

        //set boxes
        typeVisit.setText(typE);
        typeVisit.setEnabled(false);

        kindVisit.setText(kinD);
        kindVisit.setEnabled(false);

        reason.setText(detailS);
        reason.setEnabled(false);

        //pull information from text file and populate
        try {

            Scanner s = new Scanner(new File("userPass.txt"));
            Boolean found = false;
            while (s.hasNextLine() && !found) {

                String in = s.nextLine();
                String[] sArray = in.split("//");
                if (loginWindow.userInput.equals(sArray[0])) {

                    enterfname.setText(sArray[2]);
                    enterfname.setEditable(false);
                    enterfname.setEnabled(false);

                    entermname.setText(sArray[3]);
                    entermname.setEnabled(false);

                    enterlname.setText(sArray[4]);
                    enterlname.setEnabled(false);

                    enterphone.setText(sArray[10]);
                    enterphone.setEnabled(false);

                    enteremail.setText(sArray[11]);
                    enteremail.setEnabled(false);

                }

            }
            try {
                BufferedReader br = new BufferedReader(new FileReader("apptchoices.txt"));
                StringBuilder sb = new StringBuilder();
                String line = br.readLine();

                while (line != null) {
                    sb.append(line);
                    line = br.readLine();
                }
                String fileAsString = sb.toString();
                System.out.println(fileAsString);
                apptDetails.setText(fileAsString);
                apptDetails.setEnabled(false);

            } catch (IOException exx) {
                // TODO Auto-generated catch block
                exx.printStackTrace();
            }
        } catch (FileNotFoundException d) {
            JOptionPane.showMessageDialog(null, "User Database Not Found.", "Error", JOptionPane.ERROR_MESSAGE);
        }

        //action to go to newAppointment2 page when button is clicked
        confirmBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String filePath = "userPass.txt";
                BufferedReader br;
                String firstname = enterfname.getText();
                String lname = enterlname.getText();
                String userID = "";

                String line = "";

                System.out.println(firstname);
                System.out.println(lname);
                System.out.println(provideR);
                System.out.println(networK);
                System.out.println(locatioN);
                System.out.println(datE);

                System.out.println(kinD);
                System.out.println(typE);
                System.out.println(detailS);

                try {
                    br = new BufferedReader(new FileReader(filePath));
                    Scanner in = new Scanner(new File("userPass.txt"));
                    boolean found = false;
                    while ((line = br.readLine()) != null) {

                        //System.out.println(line);
                        String[] words = line.split("//");

                        for (String word : words) {
                            if (word.equals(firstname)) {
                                System.out.println("Found: " + firstname);
                                System.out.println(words[2]);
                                found = true;
                            }
                            if (word.equals(lname)) {
                                System.out.println("Found: " + lname);
                                System.out.println(words[4]);
                                found = true;
                                userID = words[0].toString();
                                System.out.println(userID);
                                break;
                            }

                        }
                    }

                    BufferedReader br2 = new BufferedReader(new FileReader("apptchoices.txt"));
                    StringBuilder sb = new StringBuilder();
                    String line2 = br2.readLine();

                    while (line2 != null) {
                        sb.append(line2);
                        line2 = br2.readLine();
                    }
                    String fileAsString = sb.toString();

                    if (detailS.isEmpty()) {
                        detailS = "Review during appointment";
                    }
//                    Appointments appt = new Appointments(1, userID, firstname, lname, provideR, networK,
//                            locatioN, datE, fileAsString, kinD, typE, detailS);

                    File inputFile = new File("appointments.txt");
                    Random rand = new Random();
                    int upperbound = 100000;
                    int int_random = rand.nextInt(upperbound);
                    FileWriter fw = new FileWriter(inputFile.getAbsoluteFile(), true);
                    // BufferedWriter bw = new BufferedWriter(new FileWriter("userPass.txt"));
                    fw.write("\n" + int_random + "//" + userID + "//" + firstname + "//" + lname + "//" + networK
                            + "//" + datE + "//" + fileAsString + "//" + kinD + "//" + typE + "//" + detailS);

                    fw.close();

                    //appt.printAppointments(appt);
                    in.close();
                } catch (FileNotFoundException d) {
                    JOptionPane.showMessageDialog(null, "User Database Not Found.", "Error", JOptionPane.ERROR_MESSAGE);
                } catch (IOException exx) {
                    // TODO Auto-generated catch block
                    exx.printStackTrace();
                }
                File myObj = new File("apptchoices.txt");
                if (myObj.delete()) {
                    System.out.println("Deleted the file: " + myObj.getName());
                } else {
                    System.out.println("Failed to delete the file.");
                }
                JOptionPane.showMessageDialog(null, "Confirmed Appointment", "Success", JOptionPane.INFORMATION_MESSAGE);
                patientHome.setVisible(false);
                patientHome.dispose(); //close screen
                PatientMenu pat = new PatientMenu();
            }

        });

        //action to go to newAppointment page when button is clicked
        backBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                patientHome.setVisible(false);
                patientHome.dispose(); //close screen
                NewAppointment2 newApp = new NewAppointment2();
            }
        });
        //action to go to newAppointment2 page when button is clicked
        confirmBtn2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });

        //action to go to newAppointment page when button is clicked
        backBtn2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                patientHome.setVisible(false);
                patientHome.dispose(); //close screen
                NewAppointment2 newApp = new NewAppointment2();
            }
        });
    }

    private static class RectDraw extends JPanel {

        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawRect(0, 0, 250, 250);

            g.setColor(Color.pink);
            g.fillRect(0, 0, 250, 250);
        }

        public Dimension getPreferredSize() {
            return new Dimension(200, 200); // appropriate constants
        }
    }
}
//review 2 for the reschedule phase

class Review2 extends cancelReschedule {

    Review2() throws ParseException {
        schedNewAppt();
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

    public static void schedNewAppt() throws ParseException {
        System.out.println("Review Appointment");

        //set up items
        JLabel scheduleAppointment = new JLabel("Review Appointment");
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
        patientHome = new JFrame("Patient Tracker - Review Appointment");
        patientHome.setSize(1300, 800);
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
        JLabel header1 = new JLabel("Review Appointment Information");
        JLabel header2 = new JLabel("Review the following:");
        JLabel header3 = new JLabel("Contact Info: ");
        JLabel header4 = new JLabel("Appointment Info: ");

        header1.setFont(new Font("Arial", Font.PLAIN, 30));
        header2.setFont(new Font("Arial", Font.BOLD, 24));
        header3.setFont(new Font("Arial", Font.BOLD, 16));
        header4.setFont(new Font("Arial", Font.BOLD, 16));

        JLabel fname = new JLabel("First Name");
        JLabel mname = new JLabel("Middle Name");
        JLabel lname = new JLabel("Last Name");
        JLabel phone = new JLabel("Phone Number");
        JLabel email = new JLabel("Email Address");
        JLabel location = new JLabel("Location");
        JLabel visitType = new JLabel("Visit Type");
        JLabel kindType = new JLabel("Kind");
        JLabel details = new JLabel("Details");
        JLabel appointment = new JLabel("Appointment");

        location.setFont(new Font("Arial", Font.BOLD, 14));
        visitType.setFont(new Font("Arial", Font.BOLD, 14));
        details.setFont(new Font("Arial", Font.BOLD, 14));
        appointment.setFont(new Font("Arial", Font.BOLD, 14));

        JTextField enterfname = new JTextField(30);
        JTextField entermname = new JTextField(25);
        JTextField enterlname = new JTextField(30);
        JTextField enterphone = new JTextField(13);
        JTextField enteremail = new JTextField(50);

        JTextArea reason = new JTextArea();
        JTextArea apptDetails = new JTextArea();
        JTextField typeVisit = new JTextField();
        JTextField kindVisit = new JTextField();

        Border border = BorderFactory.createLineBorder(Color.BLACK);
        reason.setBorder(BorderFactory.createCompoundBorder(border,
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));

        Border border2 = BorderFactory.createLineBorder(Color.BLACK);
        apptDetails.setBorder(BorderFactory.createCompoundBorder(border2,
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));

        JButton confirmBtn = new JButton("Confirm Appointment");
        JButton backBtn = new JButton("Back");
        JButton confirmBtn2 = new JButton("Confirm Appointment");
        JButton backBtn2 = new JButton("Back");
        //image logo
        JLabel img1 = new JLabel(new ImageIcon("logo3.jpg"));

        //horizontal line 
        MyLine horizontal1 = new MyLine();
        MyLine horizontal2 = new MyLine();
        MyLine horizontal3 = new MyLine();

        //map
        RectDraw rectangleMap = new RectDraw();

        //header
        img1.setBounds(10, 10, img1.getPreferredSize().width, img1.getPreferredSize().height);
        header1.setBounds(125, 100, 450, 40);
        horizontal2.setBounds(0, -250, 1300, 800);
        confirmBtn2.setBounds(1000, 100, confirmBtn.getPreferredSize().width, confirmBtn.getPreferredSize().height);
        backBtn2.setBounds(925, 100, backBtn2.getPreferredSize().width, backBtn2.getPreferredSize().height);
        //section2
        horizontal1.setBounds(0, -150, 1300, 800);
        header2.setBounds(125, 150, 450, 40);
        header3.setBounds(125, 185, 450, 40);

        fname.setBounds(340, 200, 80, 20);
        enterfname.setBounds(340, 235, 150, 20);
        mname.setBounds(530, 200, 125, 20);
        entermname.setBounds(530, 235, 150, 20);
        lname.setBounds(720, 200, 150, 20);
        enterlname.setBounds(720, 235, 150, 20);
        phone.setBounds(340, 270, 150, 20);
        enterphone.setBounds(340, 305, 150, 20);
        email.setBounds(530, 270, 150, 20);
        enteremail.setBounds(530, 305, 200, 20);

        //section3
        horizontal3.setBounds(0, 5, 1300, 800);
        header4.setBounds(125, 405, 450, 40);
        location.setBounds(740, 440, 80, 20);
        visitType.setBounds(500, 440, 80, 20);
        typeVisit.setBounds(500, 465, 150, 20);
        kindType.setBounds(500, 495, 80, 20);
        kindVisit.setBounds(500, 520, 150, 20);
        appointment.setBounds(125, 440, 100, 20);
        apptDetails.setBounds(125, 465, 300, 60);
        details.setBounds(125, 550, 80, 20);
        reason.setBounds(125, 585, 400, 100);
        rectangleMap.setBounds(840, 430, rectangleMap.getPreferredSize().width, rectangleMap.getPreferredSize().height);

        confirmBtn.setBounds(1000, 655, confirmBtn.getPreferredSize().width, confirmBtn.getPreferredSize().height);
        backBtn.setBounds(925, 655, backBtn2.getPreferredSize().width, backBtn2.getPreferredSize().height);

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
        mainPanel2.add(confirmBtn2);
        mainPanel2.add(backBtn2);

        mainPanel2.add(horizontal2);
        mainPanel2.add(header2);
        mainPanel2.add(header3);
        mainPanel2.add(fname);
        mainPanel2.add(enterfname);

        mainPanel2.add(mname);
        mainPanel2.add(entermname);

        mainPanel2.add(lname);
        mainPanel2.add(enterlname);
        mainPanel2.add(phone);
        mainPanel2.add(enterphone);

        mainPanel2.add(email);
        mainPanel2.add(enteremail);

        mainPanel2.add(horizontal3);

        mainPanel2.add(header4);
        mainPanel2.add(location);
        mainPanel2.add(visitType);
        mainPanel2.add(typeVisit);
        mainPanel2.add(kindType);
        mainPanel2.add(kindVisit);
        mainPanel2.add(appointment);
        mainPanel2.add(apptDetails);
        mainPanel2.add(details);
        mainPanel2.add(reason);
        mainPanel2.add(rectangleMap);
        mainPanel2.add(confirmBtn);
        mainPanel2.add(backBtn);

        //add panels to JFrame
        patientHome.getContentPane().add(BorderLayout.NORTH, patientMenu);
        patientHome.getContentPane().add(BorderLayout.CENTER, mainPanel2);
        patientHome.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        patientHome.setResizable(false);
        patientHome.setVisible(true);

        mainPanel2.setBackground(Color.WHITE);

        //pull information from text file and populate
        try {

            Scanner s = new Scanner(new File("userPass.txt"));
            Boolean found = false;
            while (s.hasNextLine() && !found) {

                String in = s.nextLine();
                String[] sArray = in.split("//");
                if (loginWindow.userInput.equals(sArray[0])) {

                    enterfname.setText(sArray[2]);
                    enterfname.setEditable(false);
                    enterfname.setEnabled(false);

                    entermname.setText(sArray[3]);
                    entermname.setEnabled(false);

                    enterlname.setText(sArray[4]);
                    enterlname.setEnabled(false);

                    enterphone.setText(sArray[10]);
                    enterphone.setEnabled(false);

                    enteremail.setText(sArray[11]);
                    enteremail.setEnabled(false);

                }

            }
            try {
                BufferedReader br = new BufferedReader(new FileReader("apptchoices.txt"));
                StringBuilder sb = new StringBuilder();
                String line = br.readLine();

                while (line != null) {
                    sb.append(line);
                    line = br.readLine();
                }
                String fileAsString = sb.toString();
                System.out.println(fileAsString);
                apptDetails.setText(fileAsString);
                apptDetails.setEnabled(false);

            } catch (IOException exx) {
                // TODO Auto-generated catch block
                exx.printStackTrace();
            }
        } catch (FileNotFoundException d) {
            JOptionPane.showMessageDialog(null, "User Database Not Found.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        
        try {
                Scanner s = new Scanner(new File("appointments.txt"));
                Boolean found = false;
                
                while (s.hasNextLine() && !found) {
                String in = s.nextLine();
                String[] sArray = in.split("//");
                String day = sArray[5].toString();
                SimpleDateFormat parser = new SimpleDateFormat("EEE MMM d HH:mm:ss zzz yyyy");
                        Date date1 = parser.parse(day);
                        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
                        String formattedDate = formatter.format(date1);
                        
                        
                        String time1 = sArray[6].toString();
                        String time2 = sArray[7].toString();

                        SimpleDateFormat format = new SimpleDateFormat("HH:mm"); // 12 hour format

                        java.util.Date d1 = (java.util.Date) format.parse(time1);

                        timeValue = new java.sql.Time(d1.getTime());

                        java.util.Date d2 = (java.util.Date) format.parse(time2);

                        timeValue2 = new java.sql.Time(d2.getTime());
                        if (date.equals(formattedDate)) {
                //set boxes
                    typeVisit.setText(sArray[10]);
                    typeVisit.setEnabled(false);

                    kindVisit.setText(sArray[11]);
                    kindVisit.setEnabled(false);

                    reason.setText(sArray[12]);
                    reason.setEnabled(false);
                }
                }
            } catch (IOException exx) {
                // TODO Auto-generated catch block
                exx.printStackTrace();
            }
         
        
        //action to go to newAppointment2 page when button is clicked
        confirmBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String filePath = "userPass.txt";
                BufferedReader br;
                String firstname = enterfname.getText();
                String lname = enterlname.getText();
                String userID = "";

                String line = "";


                try {
                    br = new BufferedReader(new FileReader(filePath));
                    Scanner in = new Scanner(new File("userPass.txt"));
                    boolean found = false;
                    while ((line = br.readLine()) != null) {

                        //System.out.println(line);
                        String[] words = line.split("//");

                        for (String word : words) {
                            if (word.equals(firstname)) {
                                System.out.println("Found: " + firstname);
                                System.out.println(words[2]);
                                found = true;
                            }
                            if (word.equals(lname)) {
                                System.out.println("Found: " + lname);
                                System.out.println(words[4]);
                                found = true;
                                userID = words[0].toString();
                                System.out.println(userID);
                                break;
                            }

                        }
                    }

                    BufferedReader br2 = new BufferedReader(new FileReader("apptchoices.txt"));
                    StringBuilder sb = new StringBuilder();
                    String line2 = br2.readLine();

                    while (line2 != null) {
                        sb.append(line2);
                        line2 = br2.readLine();
                    }
                    String fileAsString = sb.toString();

                    
//                    Appointments appt = new Appointments(1, userID, firstname, lname, provideR, networK,
//                            locatioN, datE, fileAsString, kinD, typE, detailS);

                   
                    Scanner s2 = new Scanner(new File("appointments.txt"));
                    Boolean found2 = false;
                    while (s2.hasNextLine() && !found2) {

                        String in1 = s2.nextLine();
                        String[] sArray = in1.split("//");
                        String day = sArray[5].toString();
                        
                        
                    SimpleDateFormat parser = new SimpleDateFormat("EEE MMM d HH:mm:ss zzz yyyy");
                        Date date1 = parser.parse(day);
                        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
                        String formattedDate = formatter.format(date1);
                        
                        
                        String time1 = sArray[6].toString();
                        String time2 = sArray[7].toString();

                        SimpleDateFormat format = new SimpleDateFormat("HH:mm"); // 12 hour format

                        java.util.Date d1 = (java.util.Date) format.parse(time1);

                        timeValue = new java.sql.Time(d1.getTime());

                        java.util.Date d2 = (java.util.Date) format.parse(time2);

                        timeValue2 = new java.sql.Time(d2.getTime());
                        if (date.equals(formattedDate)) {
                            System.out.println("FOUND");
                            System.out.println(date);
                            System.out.println(formattedDate + " " + sArray[2].toString());
                            
                            original = sArray[0].toString() + "//"+ sArray[1].toString() + "//"+ sArray[2].toString() + "//"+ sArray[3].toString() +"//"+ sArray[4].toString() + "//"+ sArray[5].toString()
                            + "//"+ sArray[6].toString() + "//"+ sArray[7].toString() + "//"+ sArray[8].toString() + "//"+ sArray[9].toString() + "//"+ sArray[10].toString() + "//"+ sArray[11].toString()
                            + "//"+ sArray[12].toString();
                            
                            replaceData = sArray[0].toString() + "//" + sArray[1].toString() + "//" + sArray[2].toString() + "//" + sArray[3].toString()  + "//" + networK
                            +"//"+ datE +"//"+ fileAsString +"//"+ sArray[10].toString() +"//"+ sArray[11].toString() +"//"+ sArray[12].toString();
                        
                    
                        }
                
                    }
                replaceSelected(original, replaceData);    
                    
                    s2.close();
                } catch (FileNotFoundException d) {
                    JOptionPane.showMessageDialog(null, "User Database Not Found.", "Error", JOptionPane.ERROR_MESSAGE);
                } catch (ParseException ex) { 
                    Logger.getLogger(Review2.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(Review2.class.getName()).log(Level.SEVERE, null, ex);
                }
                File myObj = new File("apptchoices.txt");
                if (myObj.delete()) {
                    System.out.println("Deleted the file: " + myObj.getName());
                } else {
                    System.out.println("Failed to delete the file.");
                }
                JOptionPane.showMessageDialog(null, "Confirmed Appointment", "Success", JOptionPane.INFORMATION_MESSAGE);
                patientHome.setVisible(false);
                patientHome.dispose(); //close screen
                PatientMenu pat = new PatientMenu();
                
            }

        });

        //action to go to newAppointment page when button is clicked
        backBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                patientHome.setVisible(false);
                patientHome.dispose(); //close screen
                NewAppointment2 newApp = new NewAppointment2();
            }
        });
        //action to go to newAppointment2 page when button is clicked
        confirmBtn2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String filePath = "userPass.txt";
                BufferedReader br;
                String firstname = enterfname.getText();
                String lname = enterlname.getText();
                String userID = "";

                String line = "";


                try {
                    br = new BufferedReader(new FileReader(filePath));
                    Scanner in = new Scanner(new File("userPass.txt"));
                    boolean found = false;
                    while ((line = br.readLine()) != null) {

                        //System.out.println(line);
                        String[] words = line.split("//");

                        for (String word : words) {
                            if (word.equals(firstname)) {
                                System.out.println("Found: " + firstname);
                                System.out.println(words[2]);
                                found = true;
                            }
                            if (word.equals(lname)) {
                                System.out.println("Found: " + lname);
                                System.out.println(words[4]);
                                found = true;
                                userID = words[0].toString();
                                System.out.println(userID);
                                break;
                            }

                        }
                    }

                    BufferedReader br2 = new BufferedReader(new FileReader("apptchoices.txt"));
                    StringBuilder sb = new StringBuilder();
                    String line2 = br2.readLine();

                    while (line2 != null) {
                        sb.append(line2);
                        line2 = br2.readLine();
                    }
                    String fileAsString = sb.toString();

                    
//                    Appointments appt = new Appointments(1, userID, firstname, lname, provideR, networK,
//                            locatioN, datE, fileAsString, kinD, typE, detailS);

                   
                    Scanner s2 = new Scanner(new File("appointments.txt"));
                    Boolean found2 = false;
                    while (s2.hasNextLine() && !found2) {

                        String in1 = s2.nextLine();
                        String[] sArray = in1.split("//");
                        String day = sArray[5].toString();
                        
                        
                    SimpleDateFormat parser = new SimpleDateFormat("EEE MMM d HH:mm:ss zzz yyyy");
                        Date date1 = parser.parse(day);
                        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
                        String formattedDate = formatter.format(date1);
                        
                        
                        String time1 = sArray[6].toString();
                        String time2 = sArray[7].toString();

                        SimpleDateFormat format = new SimpleDateFormat("HH:mm"); // 12 hour format

                        java.util.Date d1 = (java.util.Date) format.parse(time1);

                        timeValue = new java.sql.Time(d1.getTime());

                        java.util.Date d2 = (java.util.Date) format.parse(time2);

                        timeValue2 = new java.sql.Time(d2.getTime());
                        if (date.equals(formattedDate)) {
                            System.out.println("FOUND");
                            System.out.println(date);
                            System.out.println(formattedDate + " " + sArray[2].toString());
                            
                            original = sArray[0].toString() + "//"+ sArray[1].toString() + "//"+ sArray[2].toString() + "//"+ sArray[3].toString() + "//"+ sArray[4].toString() + "//"+ sArray[5].toString()
                            + "//"+ sArray[6].toString() + "//"+ sArray[7].toString() + "//"+ sArray[8].toString() + "//"+ sArray[9].toString() + "//"+ sArray[10].toString() + "//"+ sArray[11].toString()
                            + "//"+ sArray[12].toString();
                            
                            replaceData = sArray[0].toString() + "//" + sArray[1].toString() + "//" + sArray[2].toString() + "//" + sArray[3].toString()  + "//" + networK
                            +"//"+ datE +"//"+ fileAsString +"//"+ sArray[10].toString() +"//"+ sArray[11].toString() +"//"+ sArray[12].toString();
                        
                    
                        }
                
                    }
                replaceSelected(original, replaceData);    
                    
                    s2.close();
                } catch (FileNotFoundException d) {
                    JOptionPane.showMessageDialog(null, "User Database Not Found.", "Error", JOptionPane.ERROR_MESSAGE);
                } catch (ParseException ex) { 
                    Logger.getLogger(Review2.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(Review2.class.getName()).log(Level.SEVERE, null, ex);
                }
                File myObj = new File("apptchoices.txt");
                if (myObj.delete()) {
                    System.out.println("Deleted the file: " + myObj.getName());
                } else {
                    System.out.println("Failed to delete the file.");
                }
                JOptionPane.showMessageDialog(null, "Confirmed Appointment", "Success", JOptionPane.INFORMATION_MESSAGE);
                patientHome.setVisible(false);
                patientHome.dispose(); //close screen
                PatientMenu pat = new PatientMenu();
            }
        });

        //action to go to newAppointment page when button is clicked
        backBtn2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                patientHome.setVisible(false);
                patientHome.dispose(); //close screen
                NewAppointment2 newApp = new NewAppointment2();
            }
        });
    }

    private static class RectDraw extends JPanel {

        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawRect(0, 0, 250, 250);

            g.setColor(Color.pink);
            g.fillRect(0, 0, 250, 250);
        }

        public Dimension getPreferredSize() {
            return new Dimension(200, 200); // appropriate constants
        }
    }
}
