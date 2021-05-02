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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
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
import static patienttracker.NewAppointment2.patientMenu;

/**
 *
 * @author siarasaylor
 */
class prevAppointment {

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
    static String replace;
    static String fileAsString = "";
    static DefaultTableModel dm;

    prevAppointment() throws IOException, ParseException {
        paapointment();
    }

    static void paapointment() throws IOException, ParseException {
        System.out.println("Previous Appointment");

        //set up items
        JLabel scheduleAppointment = new JLabel("Previous Appointment");
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
        patientHome = new JFrame("Patient Tracker - Previous Appointment");
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
        JLabel header1 = new JLabel("Previous Appointment");
        header1.setFont(new Font("Arial", Font.PLAIN, 30));
        JLabel header2 = new JLabel("Date/Time");
        header2.setFont(new Font("Arial", Font.BOLD, 14));
        JLabel header3 = new JLabel("Provider");
        header3.setFont(new Font("Arial", Font.BOLD, 14));
        JLabel header4 = new JLabel("Patient Name: ");
        JTextArea header5 = new JTextArea("Appointment Reason: ");
        header5.setLineWrap(true);
        header5.setWrapStyleWord(true);
        header4.setFont(new Font("Arial", Font.BOLD, 14));

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
        notes.setLineWrap(true);
        header5.setWrapStyleWord(false);
        Border border = BorderFactory.createLineBorder(Color.BLACK);
        notes.setBorder(BorderFactory.createCompoundBorder(border,
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));

        JButton backBtn = new JButton("Back");

        //image logo
        JLabel img1 = new JLabel(new ImageIcon("logo3.jpg"));

        //horizontal line 
        MyLine horizontal2 = new MyLine();

        //medication table
        dm = new DefaultTableModel();
        dm.setDataVector(new Object[][]{},
                new Object[]{"Name", "Purpose", "Directions", "Duration", "Refills", "Doctor"});

        JTable table = new JTable(dm);

        JScrollPane scroll = new JScrollPane(table);

        table.setPreferredScrollableViewportSize(table.getPreferredSize());//thanks mKorbel +1 http://stackoverflow.com/questions/10551995/how-to-set-jscrollpane-layout-to-be-the-same-as-jtable

        table.getColumnModel().getColumn(0).setPreferredWidth(100);

        //header
        img1.setBounds(10, 10, img1.getPreferredSize().width, img1.getPreferredSize().height);
        header1.setBounds(125, 100, 450, 40);
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

        medications.setBounds(530, 450, 150, 20);
        scroll.setBounds(530, 475, 550, 150);
        backBtn.setBounds(1000, 655, backBtn.getPreferredSize().width, backBtn.getPreferredSize().height);

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
        mainPanel2.add(header3);
        mainPanel2.add(header4);

        mainPanel2.add(weight);
        mainPanel2.add(wht);

        mainPanel2.add(height);
        mainPanel2.add(feet);
        mainPanel2.add(inches);

        mainPanel2.add(bmi);
        mainPanel2.add(bMI);

        mainPanel2.add(bp);
        mainPanel2.add(bloodP);

        mainPanel2.add(cholesterol);
        mainPanel2.add(chol);

        mainPanel2.add(waistSize);
        mainPanel2.add(wSize);

        mainPanel2.add(header5);
        mainPanel2.add(addnotes);
        mainPanel2.add(notes);

        mainPanel2.add(medications);
        mainPanel2.add(scroll);

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

            System.out.println("READING APPTPA.TXT FILE");
            BufferedReader br2 = new BufferedReader(new FileReader("apptpa.txt"));
            StringBuilder sb = new StringBuilder();
            Scanner s = new Scanner(new File("apptpa.txt"));
            String line2, last;

//            while ((line2 = br2.readLine()) != null) {
//                if (line2 != null) {
//                    last = line2;
//                    fileAsString = last;
//                }
            String out = s.nextLine();
            String[] sArray1 = out.split("//");
            fileAsString = sArray1[0];
//            }

            System.out.println(fileAsString);
            String apptID = "";
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
                String formattedDate2 = formatter.format(date1);

                String time1 = sArray[6];
                String time2 = sArray[7];

                if (fileAsString.equals(formattedDate2)) {
                    System.out.println("FOUND");
                    System.out.println(fileAsString + " FILE: " + formattedDate2);
                    //System.out.println(formattedDate);
                    System.out.println(time1 + " - " + time2);
                    String dtTime = formattedDate2 + " " + time1 + " - " + time2;
                    header2.setText("Date/Time: " + dtTime);
                    header3.setText("Provider: " + sArray[8]);
                    header4.setText("Patient Name: " + sArray[2] + " " + sArray[3]);
                    header5.setText("Appointment Reason: " + sArray[12]);
                    apptID = sArray[0];
                }
            }

            Scanner s3 = new Scanner(new File("finalappointmentUpdates.txt"));
            Boolean found3 = false;
            while (s3.hasNextLine() && !found3) {

                String in3 = s3.nextLine();
                String[] sArray3 = in3.split("//");

                if (apptID.equals(sArray3[0])) {

                    wht.setText(sArray3[1]);
                    wht.setEnabled(false);
                    wht.setEditable(false);

                    String ft = sArray3[2];
                    feet.setSelectedItem(ft);
                    feet.setEditable(false);
                    feet.setEnabled(false);

                    String i = sArray3[3];
                    inches.setSelectedItem(i);
                    inches.setEditable(false);
                    inches.setEnabled(false);

                    bMI.setText(sArray3[4]);
                    bMI.setEnabled(false);
                    bMI.setEditable(false);

                    bloodP.setText(sArray3[5]);
                    bloodP.setEnabled(false);
                    bloodP.setEditable(false);

                    chol.setText(sArray3[6]);
                    chol.setEnabled(false);
                    chol.setEditable(false);

                    wSize.setText(sArray3[7]);
                    wSize.setEnabled(false);
                    wSize.setEditable(false);

                    notes.setText(sArray3[8]);
                    notes.setEnabled(true);
                    notes.setEditable(true);

                    dm.addRow(new Object[]{sArray3[9], sArray3[10], sArray3[11], sArray3[12], sArray3[13], sArray3[14]});
                    table.setEnabled(false);
                }
            }
            //sc.close();
            s2.close();
        } catch (FileNotFoundException d) {
            JOptionPane.showMessageDialog(null, "User Database Not Found.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (ParseException ex) {
            Logger.getLogger(AppointmentUpdate.class.getName()).log(Level.SEVERE, null, ex);
        }

        //action to go to newAppointment page when button is clicked
        backBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (loginWindow.userInput.startsWith("1") && loginWindow.userInput.length() > 2) {
                     StaffMenuFDN.staffHome.dispose();
                    PatientSearch newp = new PatientSearch();
                } else if (loginWindow.userInput.startsWith("2") && loginWindow.userInput.length() > 2) {
                    
                    StaffMenuFDN.staffHome.dispose();
                    PatientSearch newp = new PatientSearch();

                } else if (loginWindow.userInput.startsWith("3") && loginWindow.userInput.length() > 2) {
                    
                    StaffMenuFDN.staffHome.dispose();
                    PatientSearch newp = new PatientSearch();

                } else {

                    patientHome.dispose(); //close screen
                    ScheduleAppointment sch = new ScheduleAppointment();
                }

//delete ApptID.txt
                File myObj = new File("apptpa.txt");
                if (myObj.delete()) {
                    System.out.println("Deleted the file: " + myObj.getName());
                } else {
                    System.out.println("Failed to delete the file.");
                }
//                ScheduleAppointment sch = new ScheduleAppointment();
//                patientHome.dispose(); //close screen
            }
        });
    }
}
