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
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author siarasaylor
 */
class patientDetails {

    patientDetails(){
        pdetails();
    }

    public static void pdetails(){
        System.out.println("Patient Details");

        //set up items
        JLabel pSEARCH = new JLabel("Patient Details");

        JPanel mainPanel = new JPanel();
        //set frame size and location
        StaffMenuFDN.staffHome = new JFrame("Patient Tracker - Patient Details");
        StaffMenuFDN.staffHome.setSize(1300, 800);
        StaffMenuFDN.staffHome.setLocationRelativeTo(null);
        mainPanel.setLayout(null);

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
        JButton back = new JButton("Back");

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
        img.setBounds(10, 10, img.getPreferredSize().width, img.getPreferredSize().height);
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

        back.setBounds(800, 655,update.getPreferredSize().width, update.getPreferredSize().height);
        edit.setBounds(900, 655, update.getPreferredSize().width, update.getPreferredSize().height);
        update.setBounds(1000, 655, update.getPreferredSize().width, update.getPreferredSize().height);
        
        //add items to panel
        mainPanel.add(img);
        mainPanel.add(horizontal2);

        mainPanel.add(header1);
        mainPanel.add(fname);
        mainPanel.add(enterfname);

        mainPanel.add(mname);
        mainPanel.add(entermname);

        mainPanel.add(lname);
        mainPanel.add(enterlname);

        mainPanel.add(sex);
        mainPanel.add(legalSex);

        mainPanel.add(dob);
        mainPanel.add(monthBox);
        mainPanel.add(dayBox);
        mainPanel.add(yearBox);

        mainPanel.add(SSN);
        mainPanel.add(enterSSN);

        mainPanel.add(phone);
        mainPanel.add(enterphone);

        mainPanel.add(email);
        mainPanel.add(enteremail);

        mainPanel.add(inprovider);
        mainPanel.add(enterinprovider);

        mainPanel.add(membernum);
        mainPanel.add(entermembernum);

        mainPanel.add(street);
        mainPanel.add(enterstreet);

        mainPanel.add(city);
        mainPanel.add(entercity);

        mainPanel.add(state);
        mainPanel.add(st);

        mainPanel.add(zipcode);
        mainPanel.add(enterzipcode);

        mainPanel.add(horizontal1);
        mainPanel.add(header2);

        mainPanel.add(emfname);
        mainPanel.add(enteremfname);

        mainPanel.add(emlname);
        mainPanel.add(enteremlname);

        mainPanel.add(emphone);
        mainPanel.add(enteremphone);

        mainPanel.add(ememail);
        mainPanel.add(enterememail);

        mainPanel.add(horizontal3);
        mainPanel.add(header3);

        mainPanel.add(username);
        mainPanel.add(enteruname);

        mainPanel.add(tempPass);
        mainPanel.add(entertempPass);

        mainPanel.add(back);
        mainPanel.add(edit);
        mainPanel.add(update);

        if (loginWindow.userInput.startsWith("1") && loginWindow.userInput.length() > 2) {
            System.out.println("Doctor-patient search");
            StaffMenuFDN.staffHome.getContentPane().add(BorderLayout.NORTH, StaffMenuFDN.dstaffMenu);

        }
        if (loginWindow.userInput.startsWith("2") && loginWindow.userInput.length() > 2) {
            System.out.println("Nurse-patient search");
            StaffMenuFDN.staffHome.getContentPane().add(BorderLayout.NORTH, StaffMenuFDN.nstaffMenu);

        }
        if (loginWindow.userInput.startsWith("3") && loginWindow.userInput.length() > 2) {
            System.out.println("Front Desk-patient search");
            StaffMenuFDN.staffHome.getContentPane().add(BorderLayout.NORTH, StaffMenuFDN.fdstaffMenu);

        }
        StaffMenuFDN.staffHome.getContentPane().add(BorderLayout.CENTER, mainPanel);
        StaffMenuFDN.staffHome.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        StaffMenuFDN.staffHome.setResizable(false);
        StaffMenuFDN.staffHome.setVisible(true);

        mainPanel.setBackground(Color.WHITE); //changes background color to white

        //pull information from text file and populate
        String filePath = "userPass.txt";
        BufferedReader br1;
        boolean found1 = false;
        String line1 = "";
        try {
            br1 = new BufferedReader(new FileReader(filePath));
            try {
                while ((line1 = br1.readLine()) != null) {

                    String[] words = line1.split("//");

                    for (String word : words) {
                        if (word.equals(PatientSearch.inputSearch)) {

                            System.out.println(words[2]);
                            found1 = true;
                            enteruname.setText(words[0]);
                            enteruname.setEnabled(false);

                            entertempPass.setText(words[1]);
                            entertempPass.setEnabled(false);

                            enterfname.setText(words[2]);
                            enterfname.setEditable(false);
                            enterfname.setEnabled(false);
                        }

                        if (word.equals(PatientSearch.inputSearch2)) {

                            found1 = true;
                            entermname.setText(words[3]);
                            entermname.setEnabled(false);
                            entermname.setEditable(false);
                            enterlname.setText(words[4]);
                            enterlname.setEditable(false);
                            enterlname.setEnabled(false);
                        }
                        if (word.equals(PatientSearch.monthInput)) {
                            System.out.println(words[7]);
                            found1 = true;
                            String monthOption = words[7];
                            monthBox.setSelectedItem(monthOption);
                            monthBox.setEnabled(false);

                        }
                        if (word.equals(PatientSearch.dayInput)) {
                            System.out.println(words[8]);
                            found1 = true;
                            String dayOption = words[8];
                            dayBox.setSelectedItem(dayOption);
                            dayBox.setEnabled(false);
                        }
                        if (word.equals(PatientSearch.yearInput)) {
                            System.out.println(words[9]);
                            found1 = true;
                            String yearOption = words[9];
                            yearBox.setSelectedItem(yearOption);
                            yearBox.setEnabled(false);

                            String sexOption = words[6];
                            legalSex.setSelectedItem(sexOption); //sets option in text file to selected value
                            legalSex.setEnabled(false);

                            enterphone.setText(words[10]);
                            enterphone.setEnabled(false);

                            enteremail.setText(words[11]);
                            enteremail.setEnabled(false);

                            enterinprovider.setText(words[12]);
                            enterinprovider.setEnabled(false);

                            entermembernum.setText(words[13]);
                            entermembernum.setEnabled(false);

                            enteremfname.setText(words[14]);
                            enteremfname.setEnabled(false);

                            enteremlname.setText(words[15]);
                            enteremlname.setEnabled(false);

                            enteremphone.setText(words[16]);
                            enteremphone.setEnabled(false);

                            enterememail.setText(words[17]);
                            enterememail.setEnabled(false);

                            enterSSN.setText(words[5]);
                            enterSSN.setEnabled(false);

                            enterstreet.setText(words[18]);
                            enterstreet.setEnabled(false);

                            entercity.setText(words[19]);
                            entercity.setEnabled(false);

                            String stateOption = words[20];
                            st.setSelectedItem(stateOption);
                            st.setEnabled(false);

                            enterzipcode.setText(words[21]);
                            enterzipcode.setEnabled(false);
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
        } catch (FileNotFoundException ex) {
            // TODO Auto-generated catch block
            ex.printStackTrace();
        }

        //action to edit text fields
        edit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                
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
                        if (enteruname.getText().equals(sArray[0])) {
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
        
        //action to go to patient Detail page when button is clicked
        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                StaffMenuFDN.staffHome.setVisible(false);
                StaffMenuFDN.staffHome.dispose();
                patientInfo newp = new patientInfo();
            }
        });

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
