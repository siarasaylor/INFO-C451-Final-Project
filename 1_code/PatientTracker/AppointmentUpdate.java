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
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;
import javafx.scene.layout.Border;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import static patienttracker.StaffMenuFDN.mainPanel;
import static patienttracker.loginWindow.userInput;

/**
 *
 * @author siarasaylor
 */
class AppointmentUpdate extends StaffMenuFDN {

    AppointmentUpdate() throws IOException {
        System.out.println("APPOINTMENT UPDATE");

        apptUpdate();
    }

    static String replace;
    static String fileAsString;

    public static void apptUpdate() throws IOException {
        //set up items

        JLabel staffPage = new JLabel("Staff Appointment Update");
        staffPage.setFont(new Font("Arial", Font.PLAIN, 30));
        JLabel header2 = new JLabel("Date/Time");
        JLabel header4 = new JLabel("Patient Name: ");
        JTextArea header5 = new JTextArea("Appointment Reason: ");
        header5.setLineWrap(true);
        header5.setWrapStyleWord(true);
        //image logo
        JLabel img1 = new JLabel(new ImageIcon("logo3.jpg"));

        //mainPanel = new JPanel();
        StaffMenuFDN.mainPanel = new JPanel();
        staffHome.repaint();
        //set frame size and location
        StaffMenuFDN.staffHome = new JFrame("Patient Tracker - Doctor View");
        StaffMenuFDN.staffHome.setSize(1300, 800);
        StaffMenuFDN.staffHome.setLocationRelativeTo(null);
        mainPanel.setLayout(null);

        //horizontal line
        MyLine horizontal2 = new MyLine();
        //set location for each item
        img1.setBounds(10, 10, img1.getPreferredSize().width, img1.getPreferredSize().height);
        staffPage.setBounds(125, 100, 450, 40);
        horizontal2.setBounds(0, -250, 1300, 800);
        //create items for main panel

        //JLabel header2 = new JLabel("Date/Time");
        header2.setFont(new Font("Arial", Font.BOLD, 14));
        JLabel header3 = new JLabel("Provider");
        header3.setFont(new Font("Arial", Font.BOLD, 14));
        header4.setFont(new Font("Arial", Font.BOLD, 14));
        //header5.setFont(new Font("Arial", Font.PLAIN, 14));

        //labels
        JLabel weight = new JLabel("Weight");
        JLabel height = new JLabel("Height (Feet/Inches)");
        JLabel bmi = new JLabel("BMI");
        JLabel bp = new JLabel("Blood Pressure");
        JLabel cholesterol = new JLabel("Cholesterol");
        JLabel waistSize = new JLabel("Waist Size");
        JLabel addnotes = new JLabel("Additional Notes");
        JLabel medications = new JLabel("Medications Prescribed");

        //text boxes to be auto filled
        JTextField wht = new JTextField();
        
        JTextField bMI = new JTextField();
        JTextField bloodP = new JTextField();
        JTextField chol = new JTextField();
        JTextField wSize = new JTextField();
        
        String[] ht = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
        JComboBox feet = new JComboBox(ht);
        String[] in = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11"};
        JComboBox inches = new JComboBox(in);

        //notes left from doctor/nurse
        JTextArea notes = new JTextArea();
        javax.swing.border.Border border = BorderFactory.createLineBorder(Color.BLACK);
        notes.setBorder(BorderFactory.createCompoundBorder(border,
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        notes.setLineWrap(true);
        notes.setWrapStyleWord(true);
        JButton backBtn = new JButton("Back");
        JButton updateBtn = new JButton("Update");

        //medication table
        DefaultTableModel dm = new DefaultTableModel();
        dm.setDataVector(new Object[][]{},
                new Object[]{"Name", "Purpose", "Directions", "Duration", "Refills", "Doctor"});

        JTable table = new JTable(dm);

        JScrollPane scroll = new JScrollPane(table);

        table.setPreferredScrollableViewportSize(table.getPreferredSize());//thanks mKorbel +1 http://stackoverflow.com/questions/10551995/how-to-set-jscrollpane-layout-to-be-the-same-as-jtable

        table.getColumnModel().getColumn(0).setPreferredWidth(100);
        //header
        img1.setBounds(10, 10, img1.getPreferredSize().width, img1.getPreferredSize().height);
        horizontal2.setBounds(0, -250, 1300, 800);

        //section1
        header4.setBounds(150, 175, 300, 20);
        header2.setBounds(150, 200, 300, 20);
        header3.setBounds(150, 225, 150, 20);

        weight.setBounds(150, 250, 150, 20);
        wht.setBounds(150, 275, 150, 20);

        height.setBounds(150, 300, 150, 20);
        feet.setBounds(150, 325, feet.getPreferredSize().width, feet.getPreferredSize().height);
        inches.setBounds(215, 325, inches.getPreferredSize().width, inches.getPreferredSize().height);
        
        bmi.setBounds(150, 350, 150, 20);
        bMI.setBounds(150, 375, 150, 20);

        bp.setBounds(150, 400, 150, 20);
        bloodP.setBounds(150, 425, 150, 20);

        cholesterol.setBounds(150, 450, 150, 20);
        chol.setBounds(150, 475, 150, 20);

        waistSize.setBounds(150, 500, 150, 20);
        wSize.setBounds(150, 525, 150, 20);

        header5.setBounds(530, 170, 550, 60);
        addnotes.setBounds(530, 250, 150, 20);
        notes.setBounds(530, 275, 550, 150);

//        medications.setBounds(530, 450, 150, 20);
//        scroll.setBounds(530, 475, 550, 150);
        backBtn.setBounds(900, 655, backBtn.getPreferredSize().width, backBtn.getPreferredSize().height);
        updateBtn.setBounds(1000, 655, updateBtn.getPreferredSize().width, updateBtn.getPreferredSize().height);

        //add items to panel
        mainPanel.add(staffPage);
        mainPanel.add(img1);

        mainPanel.add(horizontal2);
        mainPanel.add(header4);
        mainPanel.add(header2);
        mainPanel.add(header3);

        mainPanel.add(weight);
        mainPanel.add(wht);

        mainPanel.add(height);
        mainPanel.add(feet);
        mainPanel.add(inches);

        mainPanel.add(bmi);
        mainPanel.add(bMI);

        mainPanel.add(bp);
        mainPanel.add(bloodP);

        mainPanel.add(cholesterol);
        mainPanel.add(chol);

        mainPanel.add(waistSize);
        mainPanel.add(wSize);

        mainPanel.add(header5);
        mainPanel.add(addnotes);
        mainPanel.add(notes);

        mainPanel.add(backBtn);
        mainPanel.add(updateBtn);

        //add items to panel
        mainPanel.add(img1);
        mainPanel.add(horizontal2);

        //pull information from text file and populate
        try {
             
            System.out.println("READING APPTID.TXT FILE");
            BufferedReader br2 = new BufferedReader(new FileReader("apptID.txt"));
            StringBuilder sb = new StringBuilder();
            String line2, last;

            while ((line2 = br2.readLine()) != null) {
                if (line2 != null) {
                    last = line2;
                    fileAsString = last;
                }
            }

            System.out.println(fileAsString);

            System.out.println("READING APPOINTMENTS.TXT FILE");
            Scanner s2 = new Scanner(new File("appointments.txt"));
            Boolean found2 = false;
            while (s2.hasNextLine() && !found2) {

                String in1 = s2.nextLine();
                String[] sArray = in1.split("//");
                String day = sArray[5];

                SimpleDateFormat parser = new SimpleDateFormat("EEE MMM d HH:mm:ss zzz yyyy");
                Date date1 = parser.parse(day);
                SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
                String formattedDate = formatter.format(date1);

                String time1 = sArray[6];
                String time2 = sArray[7];

                if (fileAsString.contains(sArray[0])) {
                    System.out.println("FOUND");
                    System.out.println(fileAsString + " FILE: " + sArray[0]);
                    System.out.println(formattedDate);
                    System.out.println(time1 + " - " + time2);
                    String dtTime = formattedDate + " " + time1 + " - " + time2;
                    header2.setText("Date/Time: " + dtTime);
                    header3.setText("Provider: " + sArray[8]);
                    header4.setText("Patient Name: " + sArray[2] + " " + sArray[3]);
                    header5.setText("Appointment Reason: " + sArray[12]);
                }
            }
            //sc.close();
            s2.close();
        } catch (FileNotFoundException d) {
            JOptionPane.showMessageDialog(null, "User Database Not Found.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (ParseException ex) {
            Logger.getLogger(AppointmentUpdate.class.getName()).log(Level.SEVERE, null, ex);
        }

        //======================================================================
        //NURSE VIEW
        if (loginWindow.userInput.startsWith("2") && loginWindow.userInput.length() > 2) {

            updateBtn.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    FileWriter fw = null;
                    try {
                        Scanner s2 = new Scanner(new File("apptID.txt"));
                        Boolean found2 = false;
                        String userID = "";
                        while (s2.hasNextLine() && !found2) {

                            String in1 = s2.nextLine();
                            String[] sArray = in1.split("//");
                            userID = sArray[0];

                        }
                        File inputFile = new File("appointmentUpdates.txt");
                        fw = new FileWriter(inputFile.getAbsoluteFile(), true);

                        fw.write("\n" + userID + "//" + wht.getText().toString() + "//" + feet.getSelectedItem().toString() + "//" + inches.getSelectedItem().toString() + "//" + bMI.getText().toString() + "//"
                                + bloodP.getText().toString() + "//" + chol.getText().toString() + "//" + wSize.getText().toString() + "//" + notes.getText().toString());
                        fw.close();
                        //delete ApptID.txt
                        File myObj = new File("apptID.txt");
                        if (myObj.delete()) {
                            System.out.println("Deleted the file: " + myObj.getName());
                        } else {
                            System.out.println("Failed to delete the file.");
                        }

                        MainFrame mainFrame = new MainFrame();
                        StaffMenuFDN.staffHome.dispose();
                    } catch (IOException ex) {
                        Logger.getLogger(AppointmentUpdate.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
            JOptionPane.showMessageDialog(null, "Updated", "Success", JOptionPane.INFORMATION_MESSAGE);
            StaffMenuFDN.staffHome.getContentPane().add(BorderLayout.NORTH, StaffMenuFDN.nstaffMenu);
        }

        //======================================================================
        //DOCTOR VIEW
        if (loginWindow.userInput.startsWith("1") && loginWindow.userInput.length() > 2) {
            FileWriter fw = null;
            try {

                Scanner s = new Scanner(new File("apptID.txt"));
                Boolean found = false;
                String userID = "";
                while (s.hasNextLine() && !found) {

                    String in1 = s.nextLine();
                    String[] sArray = in1.split("//");
                    userID = sArray[0];

                }
                Scanner s2 = new Scanner(new File("appointmentUpdates.txt"));
                Boolean found2 = false;
                while (s2.hasNextLine() && !found2) {

                    String in1 = s2.nextLine();
                    String[] sArray = in1.split("//");

                    if (userID.equals(sArray[0])) {

                        wht.setText(sArray[1]);
                        wht.setEnabled(false);
                        wht.setEditable(false);

                        String ft = sArray[2];
                        feet.setSelectedItem(ft);
                        feet.setEditable(false);
                        feet.setEnabled(false);

                        String i = sArray[3];
                        inches.setSelectedItem(i);
                        inches.setEditable(false);  
                        inches.setEnabled(false);
                       
                        bMI.setText(sArray[4]);
                        bMI.setEnabled(false);
                        bMI.setEditable(false);

                        bloodP.setText(sArray[5]);
                        bloodP.setEnabled(false);
                        bloodP.setEditable(false);

                        chol.setText(sArray[6]);
                        chol.setEnabled(false);
                        chol.setEditable(false);

                        wSize.setText(sArray[7]);
                        wSize.setEnabled(false);
                        wSize.setEditable(false);

                        notes.setText(sArray[8]);
                        notes.setEnabled(true);
                        notes.setEditable(true);
                    }
                }

            } catch (IOException ex) {
                Logger.getLogger(AppointmentUpdate.class.getName()).log(Level.SEVERE, null, ex);
            }
            JButton add = new JButton("Add Medication");
            add.setBounds(530, 625, add.getPreferredSize().width, add.getPreferredSize().height);
            mainPanel.add(add);

            add.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    JFrame frame = new JFrame();
                    JPanel medPanel = new JPanel();
                    frame.setSize(300, 400);
                    frame.setLocationRelativeTo(null);
                    medPanel.setLayout(null);

                    JLabel medName = new JLabel("Medication Name: ");
                    JLabel medPurpose = new JLabel("Purpose: ");
                    JLabel directions = new JLabel("Directions: ");
                    JLabel duration = new JLabel("Duration: ");
                    JLabel refills = new JLabel("Refills: ");
                    JLabel doctor = new JLabel("Provider: ");

                    JTextField name = new JTextField();
                    JTextField purp = new JTextField();
                    JTextField dir = new JTextField();
                    JTextField dur = new JTextField();

                    JButton submit = new JButton("Submit");

                    String[] option = {"Yes", "No"};
                    JComboBox fills = new JComboBox(option);

                    String[] providerNames = {"Dr. K Smith", "Dr. C Jones", "Dr. G Ward"};
                    JComboBox providerS = new JComboBox(providerNames);

                    medName.setBounds(25, 25, 150, 20);
                    name.setBounds(150, 25, 100, 20);

                    medPurpose.setBounds(25, 55, 100, 20);
                    purp.setBounds(150, 55, 100, 20);

                    directions.setBounds(25, 85, 100, 20);
                    dir.setBounds(150, 85, 100, 20);

                    duration.setBounds(25, 115, 100, 20);
                    dur.setBounds(150, 115, 100, 20);

                    refills.setBounds(25, 145, 100, 20);
                    fills.setBounds(150, 145, fills.getPreferredSize().width, fills.getPreferredSize().height);

                    doctor.setBounds(25, 175, 100, 20);
                    providerS.setBounds(150, 175, providerS.getPreferredSize().width, providerS.getPreferredSize().height);

                    submit.setBounds(100, 205, 100, 20);

                    medPanel.add(medName);
                    medPanel.add(name);

                    medPanel.add(medPurpose);
                    medPanel.add(purp);

                    medPanel.add(directions);
                    medPanel.add(dir);

                    medPanel.add(duration);
                    medPanel.add(dur);

                    medPanel.add(refills);
                    medPanel.add(fills);

                    medPanel.add(doctor);
                    medPanel.add(providerS);

                    medPanel.add(submit);

//"Name", "Purpose", "Directions", "Duration", "Refills", "Doctor"
                    frame.getContentPane().add(BorderLayout.CENTER, medPanel);

                    frame.setResizable(false);
                    frame.setVisible(true);

                    submit.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            try {

                                File inputFile = new File("medchoices.txt");

                                FileWriter fw = new FileWriter(inputFile.getAbsoluteFile(), true);

                                if (inputFile.length() == 0) {
                                    System.out.println("NEW FILE");
                                    fw.write(name.getText() + "//" + purp.getText() + "//"
                                            + dir.getText() + "//" + dur.getText() + "//" + fills.getSelectedItem().toString() + "//"
                                            + providerS.getSelectedItem().toString());
                                }
                                if (inputFile.length() != 0) {
                                    System.out.println("ADDING TO FILE");
                                    fw.write("\n" + name.getText() + "//" + purp.getText() + "//"
                                            + dir.getText() + "//" + dur.getText() + "//" + fills.getSelectedItem().toString() + "//"
                                            + providerS.getSelectedItem().toString());
                                }
                                fw.close();
                                //                      
                                BufferedReader br = new BufferedReader(new FileReader("medchoices.txt"));
                                Scanner s = new Scanner(new File("medchoices.txt"));
                                String last;
                                String line = "";
                                while ((line = br.readLine()) != null) {
                                    if (line != null) {
                                        last = line;
                                        String[] sArray = last.split("//");

                                        dm.addRow(new Object[]{sArray[0], sArray[1], sArray[2], sArray[3], sArray[4], sArray[5]});
                                    }

                                }
                                frame.dispose();
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        }
                    });

                }
            });

            updateBtn.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    FileWriter fw = null;
                    try {
                        Scanner s2 = new Scanner(new File("apptID.txt"));
                        Boolean found2 = false;
                        String userID = "";
                        while (s2.hasNextLine() && !found2) {

                            String in1 = s2.nextLine();
                            String[] sArray = in1.split("//");
                            userID = sArray[0];

                        }
                        File inputFile = new File("finalappointmentUpdates.txt");
                        FileWriter fw2 = new FileWriter(inputFile.getAbsoluteFile(), true);
                        String r = "";
                        //GET ROWS FROM TABLE AND ADD TO END OF FW.WRITE
                        for (int i = 0; i < dm.getRowCount(); i++) {

                            r = dm.getValueAt(i, 0) + "//" + dm.getValueAt(i, 1) + "//" + dm.getValueAt(i, 2) + "//" + dm.getValueAt(i, 3) + "//" + dm.getValueAt(i, 4)
                                    + "//" + dm.getValueAt(i, 5);

                        }
                        System.out.println(r);
                        fw2.write("\n" + userID + "//" + wht.getText().toString() + "//" + feet.getSelectedItem().toString() + "//" + inches.getSelectedItem().toString() + "//" + bMI.getText().toString() + "//"
                                + bloodP.getText().toString() + "//" + chol.getText().toString() + "//" + wSize.getText().toString() + "//" + notes.getText().toString() + "//" + r);
                        fw2.close();
                        
                        
                        File myObj = new File("apptID.txt");
                        if (myObj.delete()) {
                            System.out.println("Deleted the file: " + myObj.getName());
                        } else {
                            System.out.println("Failed to delete the file.");
                        }
                        //delete ApptID.txt
                        File myObj2 = new File("medchoices.txt");
                        if (myObj2.delete()) {
                            System.out.println("Deleted the file: " + myObj2.getName());
                        } else {
                            System.out.println("Failed to delete the file.");
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    JOptionPane.showMessageDialog(null, "Updated", "Success", JOptionPane.INFORMATION_MESSAGE);
                    MainFrame mainFrame = new MainFrame();

                    StaffMenuFDN.staffHome.dispose();
                }
            });
            
            StaffMenuFDN.staffHome.getContentPane().add(BorderLayout.NORTH, StaffMenuFDN.dstaffMenu);
            medications.setBounds(530, 450, 150, 20);
            scroll.setBounds(530, 475, 550, 150);
            mainPanel.add(medications);
            mainPanel.add(scroll);
            
            
        }
        //======================================================================
        //BOTH VIEWS
        backBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                File myObj = new File("apptID.txt");
                if (myObj.delete()) {
                    System.out.println("Deleted the file: " + myObj.getName());
                } else {
                    System.out.println("Failed to delete the file.");
                }

                File myObj2 = new File("medchoices.txt");
                if (myObj2.delete()) {
                    System.out.println("Deleted the file: " + myObj2.getName());
                } else {
                    System.out.println("Failed to delete the file.");
                }
                MainFrame mainFrame = new MainFrame();
                StaffMenuFDN.staffHome.dispose();

            }
        });
        StaffMenuFDN.staffHome.getContentPane().add(BorderLayout.CENTER, mainPanel);
        StaffMenuFDN.staffHome.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        StaffMenuFDN.staffHome.setResizable(false);
        StaffMenuFDN.staffHome.setVisible(true);
        StaffMenuFDN.staffHome.repaint();
        mainPanel.setBackground(Color.WHITE);

    }

}
