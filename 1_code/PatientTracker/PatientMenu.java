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
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

/**
 *
 * @author siarasaylor
 */
//-----------------------------------
class PatientMenu {

    PatientMenu() {
        patient();
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

    public static void patient() {
        System.out.println("Patient");

        //set up items
        JLabel patientMainPage = new JLabel("Patient Home");
        patientMainPage.setFont(new Font("Arial", Font.PLAIN, 30));

        patientMenu = new JMenuBar();
        patientMenu.setBackground(Color.ORANGE);
        profilePage = new JMenuItem("Profile");
        scheduleAppt = new JMenuItem("Schedule Appointment");
        refills = new JMenuItem("Prescription Refills");
        myHealth = new JMenuItem("My Health");
        contactUs = new JMenuItem("Contact Us");

        patientexit = new JMenu("Exit");
        logout = new JMenuItem("Log Out");
        exit = new JMenuItem("Exit");

        //image logo
        JLabel img1 = new JLabel(new ImageIcon("logo3.jpg"));

        mainPanel2 = new JPanel();
        patientMenu.setPreferredSize(new Dimension(patientMenu.getPreferredSize().width, 30));

        //set frame size and location
        patientHome = new JFrame("Patient Tracker - Patient View");
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
        JLabel header1 = new JLabel("Personal Information");
        JLabel fname = new JLabel("First Name");
        JLabel mname = new JLabel("Middle Name");
        JLabel lname = new JLabel("Last Name");
        JLabel dob = new JLabel("Date of Birth");
        JLabel SSN = new JLabel("Social Security Number");
        JLabel phone = new JLabel("Phone Number");
        JLabel email = new JLabel("Email Address");
        JLabel inprovider = new JLabel("Insurance Provider");
        JLabel membernum = new JLabel("Membership Number");
        JLabel header2 = new JLabel("Emergency Contact");
        JLabel emfname = new JLabel("First Name");
        JLabel emlname = new JLabel("Last Name");
        JLabel emphone = new JLabel("Phone Number");
        JLabel ememail = new JLabel("Email Address");
        JLabel username = new JLabel("Username");
        JLabel tempPass = new JLabel("Password");
        JLabel header3 = new JLabel("User Authentication");
        JLabel img = new JLabel(new ImageIcon("logo3.jpg"));
        JLabel street = new JLabel("Street");
        JLabel city = new JLabel("City");
        JLabel state = new JLabel("State");
        JLabel zipcode = new JLabel("Zip Code");

        JTextField enterfname = new JTextField(30);
        JTextField entermname = new JTextField(25);
        JTextField enterlname = new JTextField(30);
        JTextField enterSSN = new JTextField(11);
        JTextField enterphone = new JTextField(13);
        JTextField enteremail = new JTextField(50);
        JTextField enterinprovider = new JTextField(50);
        JTextField entermembernum = new JTextField(25);
        JTextField enteremfname = new JTextField(30);
        JTextField enteremlname = new JTextField(30);
        JTextField enteremphone = new JTextField(13);
        JTextField enterememail = new JTextField(50);
        JTextField enteruname = new JTextField(30);
        JTextField entertempPass = new JTextField(30);
        JTextField enterstreet = new JTextField(50);
        JTextField entercity = new JTextField(30);
        JTextField enterzipcode = new JTextField(30);

        JButton update = new JButton("Update");
        JButton edit = new JButton("Edit");

        header1.setFont(new Font("Arial", Font.PLAIN, 24));
        header2.setFont(new Font("Arial", Font.PLAIN, 24));
        header3.setFont(new Font("Arial", Font.PLAIN, 24));

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

        //combo box for legal sex
        JLabel sex = new JLabel("Legal Sex");
        String[] lsex = {"Female", "Male", "Other"};
        JComboBox legalSex = new JComboBox(lsex);

        //combo box for states
        String[] states = new String[]{
            "Alabama", "Alaska", "Arizona", "Arkansas", "California",
            "Colorado", "Connecticut", "Delaware", "Florida", "Georgia",
            "Hawaii", "Idaho", "Illinois", "Indiana", "Iowa", "Kansas",
            "Kentucky", "Louisiana", "Maine", "Maryland", "Massachusetts",
            "Michigan", "Minnesota", "Mississippi", "Missouri", "Montana",
            "Nebraska", "Nevada", "New Hampshire", "New Jersey", "New Mexico",
            "New York", "North Carolina", "North Dakota", "Ohio", "Oklahoma",
            "Oregon", "Pennsylvania", "Rhode Island", "South Carolina", "South Dakota",
            "Tennessee", "Texas", "Utah", "Vermont", "Virginia", "Washington", "West Virginia",
            "Wisconsin", "Wyoming"
        };
        JComboBox st = new JComboBox(states);
        //horizontal line 
        MyLine horizontal1 = new MyLine();
        MyLine horizontal2 = new MyLine();
        MyLine horizontal3 = new MyLine();

        //set location for each item
        //header
        img1.setBounds(10, 10, img1.getPreferredSize().width, img1.getPreferredSize().height);
        patientMainPage.setBounds(125, 100, 450, 40);
        horizontal2.setBounds(0, -250, 1300, 800);

        //section1
        header1.setBounds(125, 150, 450, 40);
        fname.setBounds(150, 200, 80, 20);
        enterfname.setBounds(150, 235, 150, 20);
        mname.setBounds(340, 200, 125, 20);
        entermname.setBounds(340, 235, 150, 20);
        lname.setBounds(530, 200, 150, 20);
        enterlname.setBounds(530, 235, 150, 20);
        sex.setBounds(770, 200, 150, 20);
        legalSex.setBounds(770, 235, legalSex.getPreferredSize().width, 20);
        dob.setBounds(960, 200, 80, 20);
        monthBox.setBounds(960, 235, 70, monthBox.getPreferredSize().height);
        dayBox.setBounds(1030, 235, 70, dayBox.getPreferredSize().height);
        yearBox.setBounds(1100, 235, yearBox.getPreferredSize().width, yearBox.getPreferredSize().height);
        SSN.setBounds(150, 270, 150, 20);
        enterSSN.setBounds(150, 305, 150, 20);
        phone.setBounds(340, 270, 150, 20);
        enterphone.setBounds(340, 305, 150, 20);
        email.setBounds(530, 270, 150, 20);
        enteremail.setBounds(530, 305, 200, 20);
        inprovider.setBounds(770, 270, 1500, 20);
        enterinprovider.setBounds(770, 305, 150, 20);
        membernum.setBounds(960, 270, 200, 20);
        entermembernum.setBounds(960, 305, 150, 20);
        street.setBounds(150, 340, 80, 20);
        enterstreet.setBounds(150, 375, 150, 20);
        city.setBounds(340, 340, 80, 20);
        entercity.setBounds(340, 375, 150, 20);
        state.setBounds(530, 340, 80, 20);
        st.setBounds(530, 375, st.getPreferredSize().width, 20);
        zipcode.setBounds(770, 340, 80, 20);
        enterzipcode.setBounds(770, 375, 150, 20);

        //section2
        horizontal1.setBounds(0, 25, 1300, 800);
        header2.setBounds(125, 440, 450, 40);
        emfname.setBounds(150, 480, 80, 20);
        enteremfname.setBounds(150, 515, 150, 20);
        emlname.setBounds(340, 480, 150, 20);
        enteremlname.setBounds(340, 515, 150, 20);
        emphone.setBounds(530, 480, 150, 20);
        enteremphone.setBounds(530, 515, 150, 20);
        ememail.setBounds(720, 480, 150, 20);
        enterememail.setBounds(720, 515, 200, 20);

        //section3
        horizontal3.setBounds(0, 160, 1300, 800);
        header3.setBounds(125, 560, 450, 40);

        username.setBounds(150, 600, 80, 20);
        enteruname.setBounds(150, 635, 150, 20);
        tempPass.setBounds(340, 600, 150, 20);
        entertempPass.setBounds(340, 635, 150, 20);

        edit.setBounds(900, 655, update.getPreferredSize().width, update.getPreferredSize().height);
        update.setBounds(1000, 655, update.getPreferredSize().width, update.getPreferredSize().height);

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
        mainPanel2.add(patientMainPage);
        mainPanel2.add(img1);
        mainPanel2.add(horizontal2);

        mainPanel2.add(header1);
        mainPanel2.add(fname);
        mainPanel2.add(enterfname);

        mainPanel2.add(mname);
        mainPanel2.add(entermname);

        mainPanel2.add(lname);
        mainPanel2.add(enterlname);

        mainPanel2.add(sex);
        mainPanel2.add(legalSex);

        mainPanel2.add(dob);
        mainPanel2.add(monthBox);
        mainPanel2.add(dayBox);
        mainPanel2.add(yearBox);

        mainPanel2.add(SSN);
        mainPanel2.add(enterSSN);

        mainPanel2.add(phone);
        mainPanel2.add(enterphone);

        mainPanel2.add(email);
        mainPanel2.add(enteremail);

        mainPanel2.add(inprovider);
        mainPanel2.add(enterinprovider);

        mainPanel2.add(membernum);
        mainPanel2.add(entermembernum);

        mainPanel2.add(street);
        mainPanel2.add(enterstreet);

        mainPanel2.add(city);
        mainPanel2.add(entercity);

        mainPanel2.add(state);
        mainPanel2.add(st);

        mainPanel2.add(zipcode);
        mainPanel2.add(enterzipcode);

        mainPanel2.add(horizontal1);
        mainPanel2.add(header2);

        mainPanel2.add(emfname);
        mainPanel2.add(enteremfname);

        mainPanel2.add(emlname);
        mainPanel2.add(enteremlname);

        mainPanel2.add(emphone);
        mainPanel2.add(enteremphone);

        mainPanel2.add(ememail);
        mainPanel2.add(enterememail);

        mainPanel2.add(horizontal3);
        mainPanel2.add(header3);

        mainPanel2.add(username);
        mainPanel2.add(enteruname);

        mainPanel2.add(tempPass);
        mainPanel2.add(entertempPass);

        mainPanel2.add(edit);
        mainPanel2.add(update);

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
                    enteruname.setText(sArray[0]);
                    enteruname.setEnabled(false);

                    entertempPass.setText(sArray[1]);
                    entertempPass.setEnabled(false);

                    enterfname.setText(sArray[2]);
                    enterfname.setEditable(false);
                    enterfname.setEnabled(false);

                    entermname.setText(sArray[3]);
                    entermname.setEnabled(false);

                    enterlname.setText(sArray[4]);
                    enterlname.setEnabled(false);

                    String sexOption = sArray[6];
                    legalSex.setSelectedItem(sexOption); //sets option in text file to selected value
                    legalSex.setEnabled(false);

                    String monthOption = sArray[7];
                    monthBox.setSelectedItem(monthOption);
                    monthBox.setEnabled(false);

                    String dayOption = sArray[8];
                    dayBox.setSelectedItem(dayOption);
                    dayBox.setEnabled(false);

                    String yearOption = sArray[9];
                    yearBox.setSelectedItem(yearOption);
                    yearBox.setEnabled(false);

                    enterphone.setText(sArray[10]);
                    enterphone.setEnabled(false);

                    enteremail.setText(sArray[11]);
                    enteremail.setEnabled(false);

                    enterinprovider.setText(sArray[12]);
                    enterinprovider.setEnabled(false);

                    entermembernum.setText(sArray[13]);
                    entermembernum.setEnabled(false);

                    enteremfname.setText(sArray[14]);
                    enteremfname.setEnabled(false);

                    enteremlname.setText(sArray[15]);
                    enteremlname.setEnabled(false);

                    enteremphone.setText(sArray[16]);
                    enteremphone.setEnabled(false);

                    enterememail.setText(sArray[17]);
                    enterememail.setEnabled(false);

                    enterSSN.setText(sArray[5]);
                    enterSSN.setEnabled(false);

                    enterstreet.setText(sArray[18]);
                    enterstreet.setEnabled(false);

                    entercity.setText(sArray[19]);
                    entercity.setEnabled(false);

                    String stateOption = sArray[20];
                    st.setSelectedItem(stateOption);
                    st.setEnabled(false);

                    enterzipcode.setText(sArray[21]);
                    enterzipcode.setEnabled(false);
                }

            }

            s.close();
        } catch (FileNotFoundException d) {
            JOptionPane.showMessageDialog(null, "User Database Not Found.", "Error", JOptionPane.ERROR_MESSAGE);
        }

        //action to edit text fields
        edit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loginWindow.frame.setVisible(false);
                loginWindow.frame.dispose(); //close screen
                PatientMenu pat = new PatientMenu();
                enterfname.setEditable(true);
                enterfname.setEnabled(true);

                entermname.setEditable(true);
                entermname.setEnabled(true);

                enterlname.setEditable(true);
                enterlname.setEnabled(true);

                legalSex.setEnabled(true);

                monthBox.setEnabled(true);

                dayBox.setEnabled(true);

                yearBox.setEnabled(true);

                enterphone.setEditable(true);
                enterphone.setEnabled(true);

                enteremail.setEditable(true);
                enteremail.setEnabled(true);

                enterinprovider.setEditable(true);
                enterinprovider.setEnabled(true);

                entermembernum.setEditable(true);
                entermembernum.setEnabled(true);

                enterSSN.setEditable(true);
                enterSSN.setEnabled(true);

                enteremfname.setEditable(true);
                enteremfname.setEnabled(true);

                enteremlname.setEditable(true);
                enteremlname.setEnabled(true);

                enteremphone.setEditable(true);
                enteremphone.setEnabled(true);

                enterememail.setEditable(true);
                enterememail.setEnabled(true);

                enterstreet.setEditable(true);
                enterstreet.setEnabled(true);

                entercity.setEditable(true);
                entercity.setEnabled(true);

                st.setEnabled(true);

                enterzipcode.setEditable(true);
                enterzipcode.setEnabled(true);
            }
        });

        //action to go to the staff main page when button is clicked
        update.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Updated", "Success", JOptionPane.INFORMATION_MESSAGE);
                String replaceData = enteruname.getText() + "//" + entertempPass.getText() + "//" + enterfname.getText() + "//" + entermname.getText() + "//" + enterlname.getText()
                        + "//" + enterSSN.getText() + "//" + legalSex.getSelectedItem().toString() + "//" + monthBox.getSelectedItem().toString() + "//" + dayBox.getSelectedItem().toString() + "//" + yearBox.getSelectedItem().toString()
                        + "//" + enterphone.getText() + "//" + enteremail.getText() + "//" + enterinprovider.getText()
                        + "//" + entermembernum.getText() + "//" + enteremfname.getText() + "//" + enteremlname.getText() + "//" + enteremphone.getText() + "//" + enterememail.getText()
                        + "//" + enterstreet.getText() + "//" + entercity.getText() + "//" + st.getSelectedItem().toString() + "//" + enterzipcode.getText();
                String original = "";
                try {

                    Scanner s = new Scanner(new File("userPass.txt"));
                    Boolean found = false;
                    while (s.hasNextLine() && !found) {

                        String in = s.nextLine();
                        String[] sArray = in.split("//");
                        if (loginWindow.userInput.equals(sArray[0])) {
                            original = sArray[0].toString() + "//" + sArray[1].toString() + "//" + sArray[2].toString() + "//" + sArray[3].toString() + "//" + sArray[4].toString() + "//" + sArray[5].toString()
                                    + "//" + sArray[6].toString() + "//" + sArray[7].toString() + "//" + sArray[8].toString() + "//" + sArray[9].toString() + "//" + sArray[10].toString() + "//" + sArray[11].toString()
                                    + "//" + sArray[12].toString() + "//" + sArray[13].toString() + "//" + sArray[14].toString() + "//" + sArray[15].toString() + "//" + sArray[16].toString() + "//" + sArray[17].toString()
                                    + "//" + sArray[18].toString() + "//" + sArray[19].toString() + "//" + sArray[20].toString() + "//" + sArray[21].toString();

                        }

                    }
                    System.out.println("ORIGINAL: " + original);
                    System.out.println("NEW: " + replaceData);

                    replaceSelected(original, replaceData);
                    s.close();
                } catch (FileNotFoundException d) {
                    JOptionPane.showMessageDialog(null, "User Database Not Found.", "Error", JOptionPane.ERROR_MESSAGE);
                }

                enteruname.setEnabled(false);
                entertempPass.setEnabled(false);
                enterfname.setEnabled(false);
                entermname.setEnabled(false);
                enterlname.setEnabled(false);
                enterSSN.setEnabled(false);
                legalSex.setEnabled(false);
                monthBox.setEnabled(false);
                dayBox.setEnabled(false);
                yearBox.setEnabled(false);
                enterphone.setEnabled(false);
                enteremail.setEnabled(false);
                enterinprovider.setEnabled(false);
                entermembernum.setEnabled(false);
                enteremfname.setEnabled(false);
                enteremlname.setEnabled(false);
                enteremphone.setEnabled(false);
                enterememail.setEnabled(false);
                enterstreet.setEnabled(false);
                entercity.setEnabled(false);
                st.setEnabled(false);

                enterzipcode.setEnabled(false);
                //fw.close();

            }

        }
        );

    }

    public static void replaceSelected(String replaceWith, String type) {
        try {
            // input the file content to the StringBuffer "input"
            BufferedReader file = new BufferedReader(new FileReader("userPass.txt"));
            StringBuffer inputBuffer = new StringBuffer();
            String line;

            while ((line = file.readLine()) != null) {
                inputBuffer.append(line);
                inputBuffer.append('\n');
            }
            file.close();
            String inputStr = inputBuffer.toString();

            System.out.println(inputStr); // display the original file for debugging

            inputStr = inputStr.replace(replaceWith, type);

            // display the new file for debugging
            System.out.println("----------------------------------\n" + inputStr);

            // write the new string with the replaced line OVER the same file
            FileOutputStream fileOut = new FileOutputStream("userPass.txt");
            fileOut.write(inputStr.getBytes());
            fileOut.close();

        } catch (Exception e) {
            System.out.println("Problem reading file.");
        }
    }
}
