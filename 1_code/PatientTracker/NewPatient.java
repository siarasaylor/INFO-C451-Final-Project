/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patienttracker;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


/**
 *
 * @author siarasaylor
 */
class NewPatient extends StaffMenuFDN {

    NewPatient() {
        newwPat();
    }

    public static void newwPat() {
        System.out.println("NEW PATIENT");

        //set up items
        JLabel staffPage = new JLabel("New Patient - Front Desk");

        JPanel mainPanel = new JPanel();

        //set frame size and location
        staffHome = new JFrame("Patient Tracker - New Patient");
        staffHome.setSize(1300, 800);
        staffHome.setLocationRelativeTo(null);
        mainPanel.setLayout(null);

        //create items for main panel
        JLabel headerNP = new JLabel("New Patient");
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
        JLabel tempPass = new JLabel("Temporary Password");
        JLabel header3 = new JLabel("User Authentication");
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
        JButton addPatient = new JButton("Add Patient");
        JLabel img = new JLabel(new ImageIcon("logo3.jpg"));
        headerNP.setFont(new Font("Arial", Font.PLAIN, 30));
        header2.setFont(new Font("Arial", Font.PLAIN, 24));
        header3.setFont(new Font("Arial", Font.PLAIN, 24));
        
        JLabel street = new JLabel("Street");
        JLabel city = new JLabel("City");
        JLabel state = new JLabel("State");
        JLabel zipcode = new JLabel("Zip Code");
        JTextField enterstreet = new JTextField(50);
        JTextField entercity = new JTextField(30);
        JTextField enterzipcode = new JTextField(30);
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
        
        //horizontal line 
        MyLine horizontal1 = new MyLine();
        MyLine horizontal2 = new MyLine();
        MyLine horizontal3 = new MyLine();

        //set location for each item
        //header
        img.setBounds(10, 10, img.getPreferredSize().width, img.getPreferredSize().height);
        horizontal2.setBounds(0, -250, 1300, 800);
        headerNP.setBounds(125, 100, 450, 40);

        //section1
        fname.setBounds(150, 200, 80, 20);
        enterfname.setBounds(150, 235, 150, 20);
        mname.setBounds(340, 200, 125, 20);
        entermname.setBounds(340, 235, 150, 20);
        lname.setBounds(580, 200, 150, 20);
        enterlname.setBounds(580, 235, 150, 20);
        sex.setBounds(770, 200, 150, 20);
        legalSex.setBounds(770, 235, 150, 20);
        dob.setBounds(960, 200, 80, 20);
        monthBox.setBounds(960, 235, 70, monthBox.getPreferredSize().height);
        dayBox.setBounds(1030, 235, 70, dayBox.getPreferredSize().height);
        yearBox.setBounds(1100, 235, yearBox.getPreferredSize().width, yearBox.getPreferredSize().height);
        phone.setBounds(150, 270, 150, 20);
        enterphone.setBounds(150, 305, 150, 20);
        email.setBounds(340, 270, 150, 20);
        enteremail.setBounds(340, 305, 200, 20);
        inprovider.setBounds(580, 270, 1500, 20);
        enterinprovider.setBounds(580, 305, 150, 20);
        membernum.setBounds(770, 270, 200, 20);
        entermembernum.setBounds(770, 305, 150, 20);
        SSN.setBounds(960, 270, 150, 20);
        enterSSN.setBounds(960, 305, 150, 20);
        street.setBounds(150, 340, 80, 20);
        enterstreet.setBounds(150, 375, 150, 20);
        city.setBounds(340, 340, 80, 20);
        entercity.setBounds(340, 375, 150, 20);
        state.setBounds(530, 340, 80, 20);
        st.setBounds(530, 375, st.getPreferredSize().width, 20);
        zipcode.setBounds(770, 340, 80, 20);
        enterzipcode.setBounds(770, 375, 150, 20);
        
        //section2
        horizontal1.setBounds(0, 5, 1300, 800);
        header2.setBounds(125, 410, 450, 40);
        emfname.setBounds(150, 480, 80, 20);
        enteremfname.setBounds(150, 515, 150, 20);
        emlname.setBounds(340, 480, 150, 20);
        enteremlname.setBounds(340, 515, 150, 20);
        emphone.setBounds(530, 480, 150, 20);
        enteremphone.setBounds(530, 515, 150, 20);
        ememail.setBounds(770, 480, 150, 20);
        enterememail.setBounds(770, 515, 200, 20);

        horizontal3.setBounds(0, 160, 1300, 800);
        header3.setBounds(125, 560, 450, 40);

        username.setBounds(150, 620, 80, 20);
        enteruname.setBounds(150, 655, 150, 20);
        tempPass.setBounds(340, 620, 150, 20);
        entertempPass.setBounds(340, 655, 150, 20);

        addPatient.setBounds(1000, 655, addPatient.getPreferredSize().width, addPatient.getPreferredSize().height);

        //add items to main panel
        mainPanel.add(img);
        mainPanel.add(horizontal2);
        mainPanel.add(headerNP);

        mainPanel.add(fname);
        mainPanel.add(enterfname);

        mainPanel.add(mname);
        mainPanel.add(entermname);

        mainPanel.add(lname);
        mainPanel.add(enterlname);

        mainPanel.add(dob);
        mainPanel.add(monthBox);
        mainPanel.add(dayBox);
        mainPanel.add(yearBox);

        mainPanel.add(SSN);
        mainPanel.add(enterSSN);
        
        mainPanel.add(sex);
        mainPanel.add(legalSex);

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

        mainPanel.add(addPatient);

        if (loginWindow.userInput.startsWith("1") && loginWindow.userInput.length() > 2) {
            System.out.println("Doctor-New Patient");
            staffHome.getContentPane().add(BorderLayout.NORTH, dstaffMenu);

        }
        if (loginWindow.userInput.startsWith("2") && loginWindow.userInput.length() > 2) {
            System.out.println("Nurse-New Patient");
            staffHome.getContentPane().add(BorderLayout.NORTH, nstaffMenu);

        }
        if (loginWindow.userInput.startsWith("3") && loginWindow.userInput.length() > 2) {
            System.out.println("Front Desk-New Patient");
            staffHome.getContentPane().add(BorderLayout.NORTH, fdstaffMenu);

        }
        staffHome.getContentPane().add(BorderLayout.CENTER, mainPanel);
        staffHome.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        staffHome.setResizable(false);
        staffHome.setVisible(true);
        mainPanel.setBackground(Color.WHITE);

        //action to go to the staff main page when button is clicked
        addPatient.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Patient Added", "Success", JOptionPane.INFORMATION_MESSAGE);
                
                try {
                    File inputFile = new File("userPass.txt");
                    
                    FileWriter fw = new FileWriter(inputFile.getAbsoluteFile(),true);
                   // BufferedWriter bw = new BufferedWriter(new FileWriter("userPass.txt"));
                    fw.write("\n" + enteruname.getText() + "//"+ entertempPass.getText() + "//"+ enterfname.getText() + "//"+ entermname.getText() + "//"+ enterlname.getText()
                    + "//"+ enterSSN.getText() + "//"+ legalSex.getSelectedItem().toString() +"//"+ monthBox.getSelectedItem().toString() + "//"+ dayBox.getSelectedItem().toString()+ "//"+ yearBox.getSelectedItem().toString() +"//"+ enterphone.getText() + "//"+ enteremail.getText()+ "//"+ enterinprovider.getText()
                    + "//"+ entermembernum.getText() + "//"+ enteremfname.getText()+"//"+ enteremlname.getText()+ "//"+ enteremphone.getText()+ "//"+ enterememail.getText()
                    + "//" + enterstreet.getText() + "//" + entercity.getText() + "//"+ st.getSelectedItem().toString() + "//" + enterzipcode.getText());
                    
                    fw.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                if (loginWindow.userInput.startsWith("1") && loginWindow.userInput.length() > 2) {
                    DoctorHome dstaff = new DoctorHome();
                    loginWindow.frame.dispose();

                }
                if (loginWindow.userInput.startsWith("2") && loginWindow.userInput.length() > 2) {
                    NurseHome nstaff = new NurseHome();
                    loginWindow.frame.dispose();
                }
                if (loginWindow.userInput.startsWith("3") && loginWindow.userInput.length() > 2) {
                    FDHome fdstaff = new FDHome();
                    loginWindow.frame.dispose();
                }
            }
        });
    }
}
