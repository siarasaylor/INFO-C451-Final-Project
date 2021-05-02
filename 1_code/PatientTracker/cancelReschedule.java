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
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;

/**
 *
 * @author siarasaylor
 */
class cancelReschedule1 {

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
    static JComboBox providerS;
    static JComboBox netWork;
    static JComboBox locations;
    static DateTextField cal;
    static DefaultTableModel model;
    static JTable table;
    static String provideR;
    static String networK;
    static String locatioN;
    static Date datE;
    static TableModel model1;
    static String date;
    static java.sql.Time timeValue;
    static java.sql.Time timeValue2;
    static String original = "";
    static String replaceData = "";

    cancelReschedule1() {
        crapointment();
    }

    static void crapointment() {
System.out.println("Cancel/Reschedule - Staff View");

        //set up items
        JLabel pSEARCH = new JLabel("Cancel/Reschedule");

        JPanel mainPanel = new JPanel();
        //set frame size and location
        StaffMenuFDN.staffHome = new JFrame("Patient Tracker - Cancel/Reschedule");
        StaffMenuFDN.staffHome.setSize(1300, 800);
        StaffMenuFDN.staffHome.setLocationRelativeTo(null);
        mainPanel.setLayout(null);
        //create items for main panel
        JLabel header1 = new JLabel("Cancel/Reschedule Appointment");

        JLabel header2 = new JLabel("Cancel Appointment");
        JLabel header3 = new JLabel("Please provide a reason for cancelling your appointment.");
        JLabel header4 = new JLabel("Reschedule");

        header1.setFont(new Font("Arial", Font.PLAIN, 30));
        header2.setFont(new Font("Arial", Font.BOLD, 24));

        header3.setFont(new Font("Arial", Font.PLAIN, 14));
        header4.setFont(new Font("Arial", Font.BOLD, 24));

        JTextArea reason = new JTextArea();
        Border border = BorderFactory.createLineBorder(Color.BLACK);
        reason.setBorder(BorderFactory.createCompoundBorder(border,
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));

        //kind of appointment buttons
        JButton cancel = new JButton("Cancel");
        JButton labs = new JButton("Labs");

        //Menu for type of appointment
        JButton phone = new JButton("Phone");
        JButton zoom = new JButton("Zoom");
        JButton inperson = new JButton("In Person");
        JButton search = new JButton("Search");
        JButton nextBtn = new JButton("Next");
        JButton backBtn = new JButton("Back");

        //image logo
        JLabel img1 = new JLabel(new ImageIcon("logo3.jpg"));

        //horizontal line 
        MyLine horizontal1 = new MyLine();
        MyLine horizontal2 = new MyLine();
        MyLine horizontal3 = new MyLine();

        //combo box for Provider
        JLabel providers = new JLabel("Provider");
        providers.setFont(new Font("Arial", Font.BOLD, 14));
        String[] providerNames = {"Dr. K Smith", "Dr. C Jones", "Dr. G Ward"};
        providerS = new JComboBox(providerNames);

        //combo box for Network
        JLabel network = new JLabel("Network");
        network.setFont(new Font("Arial", Font.BOLD, 14));
        String[] net = {"Anthem", "CareSource", "Celtic Insurance", "Ambetter"};
        netWork = new JComboBox(net);

        //combo box for Location
        JLabel location = new JLabel("Location");
        location.setFont(new Font("Arial", Font.BOLD, 14));
        String[] loc = {"Indianapolis", "Washington St", "Fishers", "Avon"};
        locations = new JComboBox(loc);

        //table to show results from search
        String[] columnNames = {"Start Time", "End Time", "Doctor", "Location", "Schedule"};

        //example data will need to work on being able to populate from text file
        Object[][] data = {};

        //calendar
        JLabel calendar = new JLabel("Pick a Date: ");
        calendar.setFont(new Font("Arial", Font.BOLD, 14));
        cal = new DateTextField();

        model = new DefaultTableModel(data, columnNames);
        table = new JTable(model) {

            private static final long serialVersionUID = 1L;

            /*@Override
            public Class getColumnClass(int column) {
            return getValueAt(0, column).getClass();
            }*/
            @Override
            public Class getColumnClass(int column) {
                switch (column) {
                    case 0:
                        return String.class;
                    case 1:
                        return String.class;
                    case 2:
                        return String.class;
                    case 3:
                        return String.class;
                    default:
                        return Boolean.class;
                }
            }
        };
        TableCellRenderer rendererFromHeader = table.getTableHeader().getDefaultRenderer();
        JLabel headerLabel = (JLabel) rendererFromHeader;
        headerLabel.setHorizontalAlignment(JLabel.CENTER);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(String.class, centerRenderer);

        table.setPreferredScrollableViewportSize(table.getPreferredSize());
        JScrollPane scrollPane = new JScrollPane(table);
        table.setBackground(Color.lightGray);

        //header
        img1.setBounds(10, 10, img1.getPreferredSize().width, img1.getPreferredSize().height);
        header1.setBounds(125, 100, 450, 40);
        horizontal2.setBounds(0, -250, 1300, 800);
        header2.setBounds(125, 150, 450, 40);
        //section1
        horizontal1.setBounds(0, -100, 1300, 800);
        header3.setBounds(125, 175, 450, 40);
        reason.setBounds(125, 225, 650, 50);
        cancel.setBounds(800, 255, 80, 20);
        //section2
        horizontal3.setBounds(0, 100, 1300, 800);
        header4.setBounds(125, 300, 450, 40);
        providers.setBounds(150, 335, 80, 20);
        providerS.setBounds(150, 355, providerS.getPreferredSize().width, providerS.getPreferredSize().height);
        network.setBounds(360, 335, 80, 20);
        netWork.setBounds(360, 355, netWork.getPreferredSize().width, netWork.getPreferredSize().height);
        location.setBounds(550, 335, 80, 20);
        locations.setBounds(550, 355, locations.getPreferredSize().width, locations.getPreferredSize().height);
        calendar.setBounds(150, 395, 100, 20);
        cal.setBounds(255, 395, cal.getPreferredSize().width, cal.getPreferredSize().height);
        search.setBounds(360, 395, 100, 20);
        scrollPane.setBounds(150, 495, 1000, 100);

        //section3
        nextBtn.setBounds(1000, 655, nextBtn.getPreferredSize().width, nextBtn.getPreferredSize().height);
        backBtn.setBounds(900, 655, nextBtn.getPreferredSize().width, nextBtn.getPreferredSize().height);
        

        //add items to panel
        mainPanel.add(img1);
        mainPanel.add(header1);
        mainPanel.add(horizontal1);

        mainPanel.add(horizontal2);
        mainPanel.add(header2);

        mainPanel.add(header3);
        mainPanel.add(reason);
        mainPanel.add(cancel);

        mainPanel.add(header4);
        mainPanel.add(providers);
        mainPanel.add(providerS);

        mainPanel.add(network);
        mainPanel.add(netWork);

        mainPanel.add(location);
        mainPanel.add(locations);

        mainPanel.add(scrollPane);
        mainPanel.add(calendar);
        mainPanel.add(cal);
        mainPanel.add(search);

        mainPanel.add(nextBtn);
        mainPanel.add(backBtn);

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

        //action to go to search for appointment times
        search.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                model.addRow(new Object[]{"9:00", "9:45", providerS.getSelectedItem().toString(), locations.getSelectedItem().toString(), false});
                model.addRow(new Object[]{"11:00", "11:45", providerS.getSelectedItem().toString(), locations.getSelectedItem().toString(), false});
                model.addRow(new Object[]{"1:30", "2:15", providerS.getSelectedItem().toString(), locations.getSelectedItem().toString(), false});

            }
        });

        //action to go to newAppointment2 page when button is clicked
        nextBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                provideR = providerS.getSelectedItem().toString();
                networK = netWork.getSelectedItem().toString();
                locatioN = locations.getSelectedItem().toString();
                datE = cal.getDate();

                try {
                    File inputFile = new File("apptchoices.txt");

                    FileWriter fw = new FileWriter(inputFile.getAbsoluteFile(), true);
                    model1 = table.getModel();
                    for (int i = 0; i < model1.getRowCount(); i++) {

                        if ((Boolean) model1.getValueAt(i, 4)) {
                            System.out.println(model1.getValueAt(i, 0));
                            System.out.println(model1.getValueAt(i, 1));
                            System.out.println(model1.getValueAt(i, 2));
                            System.out.println(model1.getValueAt(i, 3));
                            fw.write("\n" + model1.getValueAt(i, 0) + "//" + model1.getValueAt(i, 1) + "//" + model1.getValueAt(i, 2) + "//" + model1.getValueAt(i, 3));

                        }
                    }
                    fw.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                
                    
                    
                try {

                    Scanner s = new Scanner(new File("apptcr.txt"));
                    Boolean found = false;
                    while (s.hasNextLine() && !found) {

                        String in = s.nextLine();
                        String[] sArray = in.split("//");
                        date = sArray[0];

                    }
                    BufferedReader br2 = new BufferedReader(new FileReader("apptchoices.txt"));
                    StringBuilder sb = new StringBuilder();
                    String line2 = br2.readLine();

                    while (line2 != null) {
                        sb.append(line2);
                        line2 = br2.readLine();
                    }
                    String fileAsString = sb.toString();
                    
                    Scanner s2 = new Scanner(new File("appointments.txt"));
                    Boolean found2 = false;
                    while (s2.hasNextLine() && !found2) {

                        String in = s2.nextLine();
                        String[] sArray = in.split("//");
                        String day = sArray[5].toString();
                        SimpleDateFormat parser = new SimpleDateFormat("EEE MMM d HH:mm:ss zzz yyyy");
                        Date date1 = parser.parse(day);
                        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
                        String formattedDate = formatter.format(date1);
                        replaceData = sArray[0].toString() + "//" + sArray[1].toString() + "//" + sArray[2].toString() + "//" + sArray[3].toString()  + "//" + networK
                            +"//"+ datE +"//"+ fileAsString +"//"+ sArray[10].toString()+ "//"+ sArray[11].toString() +"//"+ sArray[12].toString();
                        
                        
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
                            + "//"+ sArray[6].toString() + "//"+ sArray[7].toString() +"//"+ sArray[8].toString() + "//"+ sArray[9].toString() + "//"+ sArray[10].toString() + "//"+ sArray[11].toString()
                            + "//"+ sArray[12].toString();
                    
                        }
                
                    }
                //replaceSelected(original, replaceData);    
                    s.close();
                    s2.close();
                } catch (FileNotFoundException d) {
                    JOptionPane.showMessageDialog(null, "User Database Not Found.", "Error", JOptionPane.ERROR_MESSAGE);
                } catch (ParseException ex) {
                    Logger.getLogger(cancelReschedule.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(cancelReschedule.class.getName()).log(Level.SEVERE, null, ex);
                }

                
                File myObj = new File("apptcr.txt");
                if (myObj.delete()) {
                    System.out.println("Deleted the file: " + myObj.getName());
                } else {
                    System.out.println("Failed to delete the file.");
                }
                try {
                    Review2 newApp = new Review2();
                } catch (ParseException ex) {
                    Logger.getLogger(cancelReschedule.class.getName()).log(Level.SEVERE, null, ex);
                }
                StaffMenuFDN.staffHome.dispose(); //close screen
            }
        });
        //action to go to newAppointment page when button is clicked
        backBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                File myObj = new File("apptcr.txt");
                if (myObj.delete()) {
                    System.out.println("Deleted the file: " + myObj.getName());
                } else {
                    System.out.println("Failed to delete the file.");
                }
                StaffMenuFDN.staffHome.setVisible(false);
                StaffMenuFDN.staffHome.dispose(); //close screen
                PatientSearch sch = new PatientSearch();
            }
        });

        //action to go to newAppointment2 page when button is clicked
        cancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {

                    Scanner s = new Scanner(new File("apptcr.txt"));
                    Boolean found = false;
                    while (s.hasNextLine() && !found) {

                        String in = s.nextLine();
                        String[] sArray = in.split("//");
                        date = sArray[0];

                    }
                    Scanner s2 = new Scanner(new File("appointments.txt"));
                    Boolean found2 = false;
                    while (s2.hasNextLine() && !found2) {

                        String in = s2.nextLine();
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
                            System.out.println("FOUND");
                            System.out.println(date);
                            System.out.println(formattedDate + " " + sArray[2].toString());

                            File inputFile = new File("appointments.txt");
                            File tempFile = new File("myTempFile.txt");

                            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
                            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
                            

                            String lineToRemove = sArray[0] + "//" + sArray[1] + "//" + sArray[2] + "//" + sArray[3] + "//" + sArray[4] + "//" + sArray[5] + "//" + sArray[6] + "//" + sArray[7] + "//" + sArray[8] + "//" + sArray[9] + "//" + sArray[10] + "//" + sArray[11] + "//" + sArray[12];

                            String currentLine;

                            while ((currentLine = reader.readLine()) != null) {
                                // trim newline when comparing with lineToRemove
                                String trimmedLine = currentLine.trim();
                                if (trimmedLine.contains(sArray[5])) {
                                    continue;
                                }
                                writer.write(currentLine + System.getProperty("line.separator"));
                            }
                            boolean delete = inputFile.delete();
                            boolean b = tempFile.renameTo(inputFile);
                            writer.close();
                            reader.close();
                        }

                    }
                    s.close();
                    s2.close();
                } catch (FileNotFoundException d) {
                    JOptionPane.showMessageDialog(null, "User Database Not Found.", "Error", JOptionPane.ERROR_MESSAGE);
                } catch (ParseException ex) {
                    Logger.getLogger(cancelReschedule.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(cancelReschedule.class.getName()).log(Level.SEVERE, null, ex);
                }

                String message = "Are you sure you want to cancel your " + date + " appointment from " + timeValue + " to " + timeValue2 + "?";
                Object[] params = {message};
                int n = JOptionPane.showConfirmDialog(null, params, "Disconnect Products", JOptionPane.YES_NO_OPTION);

                JOptionPane.showMessageDialog(null, "Appointment Cancelled.", "Success", JOptionPane.INFORMATION_MESSAGE);
                File myObj = new File("apptcr.txt");
                if (myObj.delete()) {
                    System.out.println("Deleted the file: " + myObj.getName());
                } else {
                    System.out.println("Failed to delete the file.");
                }
                StaffMenuFDN.staffHome.setVisible(false);
                StaffMenuFDN.staffHome.dispose(); //close screen
                PatientSearch pat = new PatientSearch();
            }
        });
    }
    public static void replaceSelected(String replaceWith, String type) {
        try {
            // input the file content to the StringBuffer "input"
            BufferedReader file = new BufferedReader(new FileReader("appointments.txt"));
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
            FileOutputStream fileOut = new FileOutputStream("appointments.txt");
            fileOut.write(inputStr.getBytes());
            fileOut.close();

        } catch (Exception e) {
            System.out.println("Problem reading file.");
        }
    }
}

// staff view
class cancelReschedule {

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
    static JComboBox providerS;
    static JComboBox netWork;
    static JComboBox locations;
    static DateTextField cal;
    static DefaultTableModel model;
    static JTable table;
    static String provideR;
    static String networK;
    static String locatioN;
    static Date datE;
    static TableModel model1;
    static String date;
    static java.sql.Time timeValue;
    static java.sql.Time timeValue2;
    static String original = "";
    static String replaceData = "";

    cancelReschedule() {
        crapointment();
    }

    static void crapointment() {
        System.out.println("Cancel/Reschedule Appointment");

        //set up items
        JLabel scheduleAppointment = new JLabel("Cancel/Reschedule Appointment");
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
        patientHome = new JFrame("Patient Tracker - Cancel/Reschedule Appointment");
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
        JLabel header1 = new JLabel("Cancel/Reschedule Appointment");

        JLabel header2 = new JLabel("Cancel Appointment");
        JLabel header3 = new JLabel("Please provide a reason for cancelling your appointment.");
        JLabel header4 = new JLabel("Reschedule");

        header1.setFont(new Font("Arial", Font.PLAIN, 30));
        header2.setFont(new Font("Arial", Font.BOLD, 24));

        header3.setFont(new Font("Arial", Font.PLAIN, 14));
        header4.setFont(new Font("Arial", Font.BOLD, 24));

        JTextArea reason = new JTextArea();
        Border border = BorderFactory.createLineBorder(Color.BLACK);
        reason.setBorder(BorderFactory.createCompoundBorder(border,
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));

        //kind of appointment buttons
        JButton cancel = new JButton("Cancel");
        JButton labs = new JButton("Labs");

        //Menu for type of appointment
        JButton phone = new JButton("Phone");
        JButton zoom = new JButton("Zoom");
        JButton inperson = new JButton("In Person");
        JButton search = new JButton("Search");
        JButton nextBtn = new JButton("Next");
        JButton backBtn = new JButton("Back");

        //image logo
        JLabel img1 = new JLabel(new ImageIcon("logo3.jpg"));

        //horizontal line 
        MyLine horizontal1 = new MyLine();
        MyLine horizontal2 = new MyLine();
        MyLine horizontal3 = new MyLine();

        //combo box for Provider
        JLabel providers = new JLabel("Provider");
        providers.setFont(new Font("Arial", Font.BOLD, 14));
        String[] providerNames = {"Dr. K Smith", "Dr. C Jones", "Dr. G Ward"};
        providerS = new JComboBox(providerNames);

        //combo box for Network
        JLabel network = new JLabel("Network");
        network.setFont(new Font("Arial", Font.BOLD, 14));
        String[] net = {"Anthem", "CareSource", "Celtic Insurance", "Ambetter"};
        netWork = new JComboBox(net);

        //combo box for Location
        JLabel location = new JLabel("Location");
        location.setFont(new Font("Arial", Font.BOLD, 14));
        String[] loc = {"Indianapolis", "Washington St", "Fishers", "Avon"};
        locations = new JComboBox(loc);

        //table to show results from search
        String[] columnNames = {"Start Time", "End Time", "Doctor", "Location", "Schedule"};

        //example data will need to work on being able to populate from text file
        Object[][] data = {};

        //calendar
        JLabel calendar = new JLabel("Pick a Date: ");
        calendar.setFont(new Font("Arial", Font.BOLD, 14));
        cal = new DateTextField();

        model = new DefaultTableModel(data, columnNames);
        table = new JTable(model) {

            private static final long serialVersionUID = 1L;

            /*@Override
            public Class getColumnClass(int column) {
            return getValueAt(0, column).getClass();
            }*/
            @Override
            public Class getColumnClass(int column) {
                switch (column) {
                    case 0:
                        return String.class;
                    case 1:
                        return String.class;
                    case 2:
                        return String.class;
                    case 3:
                        return String.class;
                    default:
                        return Boolean.class;
                }
            }
        };
        TableCellRenderer rendererFromHeader = table.getTableHeader().getDefaultRenderer();
        JLabel headerLabel = (JLabel) rendererFromHeader;
        headerLabel.setHorizontalAlignment(JLabel.CENTER);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(String.class, centerRenderer);

        table.setPreferredScrollableViewportSize(table.getPreferredSize());
        JScrollPane scrollPane = new JScrollPane(table);
        table.setBackground(Color.lightGray);

        //header
        img1.setBounds(10, 10, img1.getPreferredSize().width, img1.getPreferredSize().height);
        header1.setBounds(125, 100, 450, 40);
        horizontal2.setBounds(0, -250, 1300, 800);
        header2.setBounds(125, 150, 450, 40);
        //section1
        horizontal1.setBounds(0, -100, 1300, 800);
        header3.setBounds(125, 175, 450, 40);
        reason.setBounds(125, 225, 650, 50);
        cancel.setBounds(800, 255, 80, 20);
        //section2
        horizontal3.setBounds(0, 100, 1300, 800);
        header4.setBounds(125, 300, 450, 40);
        providers.setBounds(150, 335, 80, 20);
        providerS.setBounds(150, 355, providerS.getPreferredSize().width, providerS.getPreferredSize().height);
        network.setBounds(360, 335, 80, 20);
        netWork.setBounds(360, 355, netWork.getPreferredSize().width, netWork.getPreferredSize().height);
        location.setBounds(550, 335, 80, 20);
        locations.setBounds(550, 355, locations.getPreferredSize().width, locations.getPreferredSize().height);
        calendar.setBounds(150, 395, 100, 20);
        cal.setBounds(255, 395, cal.getPreferredSize().width, cal.getPreferredSize().height);
        search.setBounds(360, 395, 100, 20);
        scrollPane.setBounds(150, 495, 1000, 100);

        //section3
        nextBtn.setBounds(1000, 655, nextBtn.getPreferredSize().width, nextBtn.getPreferredSize().height);
        backBtn.setBounds(900, 655, nextBtn.getPreferredSize().width, nextBtn.getPreferredSize().height);
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
        mainPanel2.add(horizontal1);

        mainPanel2.add(horizontal2);
        mainPanel2.add(header2);

        mainPanel2.add(header3);
        mainPanel2.add(reason);
        mainPanel2.add(cancel);

        mainPanel2.add(header4);
        mainPanel2.add(providers);
        mainPanel2.add(providerS);

        mainPanel2.add(network);
        mainPanel2.add(netWork);

        mainPanel2.add(location);
        mainPanel2.add(locations);

        mainPanel2.add(scrollPane);
        mainPanel2.add(calendar);
        mainPanel2.add(cal);
        mainPanel2.add(search);

        mainPanel2.add(nextBtn);
        mainPanel2.add(backBtn);

        //add panels to JFrame
        patientHome.getContentPane().add(BorderLayout.NORTH, patientMenu);
        patientHome.getContentPane().add(BorderLayout.CENTER, mainPanel2);
        patientHome.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        patientHome.setResizable(false);
        patientHome.setVisible(true);

        mainPanel2.setBackground(Color.WHITE);
        
        //action to go to search for appointment times
        search.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                model.addRow(new Object[]{"9:00", "9:45", providerS.getSelectedItem().toString(), locations.getSelectedItem().toString(), false});
                model.addRow(new Object[]{"11:00", "11:45", providerS.getSelectedItem().toString(), locations.getSelectedItem().toString(), false});
                model.addRow(new Object[]{"1:30", "2:15", providerS.getSelectedItem().toString(), locations.getSelectedItem().toString(), false});

            }
        });

        //action to go to newAppointment2 page when button is clicked
        nextBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                provideR = providerS.getSelectedItem().toString();
                networK = netWork.getSelectedItem().toString();
                locatioN = locations.getSelectedItem().toString();
                datE = cal.getDate();

                try {
                    File inputFile = new File("apptchoices.txt");

                    FileWriter fw = new FileWriter(inputFile.getAbsoluteFile(), true);
                    model1 = table.getModel();
                    for (int i = 0; i < model1.getRowCount(); i++) {

                        if ((Boolean) model1.getValueAt(i, 4)) {
                            System.out.println(model1.getValueAt(i, 0));
                            System.out.println(model1.getValueAt(i, 1));
                            System.out.println(model1.getValueAt(i, 2));
                            System.out.println(model1.getValueAt(i, 3));
                            fw.write("\n" + model1.getValueAt(i, 0) + "//" + model1.getValueAt(i, 1) + "//" + model1.getValueAt(i, 2) + "//" + model1.getValueAt(i, 3));

                        }
                    }
                    fw.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                
                    
                    
                try {

                    Scanner s = new Scanner(new File("apptcr.txt"));
                    Boolean found = false;
                    while (s.hasNextLine() && !found) {

                        String in = s.nextLine();
                        String[] sArray = in.split("//");
                        date = sArray[0];

                    }
                    BufferedReader br2 = new BufferedReader(new FileReader("apptchoices.txt"));
                    StringBuilder sb = new StringBuilder();
                    String line2 = br2.readLine();

                    while (line2 != null) {
                        sb.append(line2);
                        line2 = br2.readLine();
                    }
                    String fileAsString = sb.toString();
                    
                    Scanner s2 = new Scanner(new File("appointments.txt"));
                    Boolean found2 = false;
                    while (s2.hasNextLine() && !found2) {

                        String in = s2.nextLine();
                        String[] sArray = in.split("//");
                        String day = sArray[5].toString();
                        SimpleDateFormat parser = new SimpleDateFormat("EEE MMM d HH:mm:ss zzz yyyy");
                        Date date1 = parser.parse(day);
                        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
                        String formattedDate = formatter.format(date1);
                        replaceData = sArray[0].toString() + "//" + sArray[1].toString() + "//" + sArray[2].toString() + "//" + sArray[3].toString()  + "//" + networK +"//"+ datE +"//"+ fileAsString +"//"+ sArray[10].toString()+ "//"+ sArray[11].toString() +"//"+ sArray[12].toString();
                        
                        
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
                            + "//"+ sArray[6].toString() + "//"+ sArray[7].toString() +"//"+ sArray[8].toString() + "//"+ sArray[9].toString() + "//"+ sArray[10].toString() + "//"+ sArray[11].toString()
                            + "//"+ sArray[12].toString();
                    
                        }
                
                    }
                //replaceSelected(original, replaceData);    
                    s.close();
                    s2.close();
                } catch (FileNotFoundException d) {
                    JOptionPane.showMessageDialog(null, "User Database Not Found.", "Error", JOptionPane.ERROR_MESSAGE);
                } catch (ParseException ex) {
                    Logger.getLogger(cancelReschedule.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(cancelReschedule.class.getName()).log(Level.SEVERE, null, ex);
                }

                
                File myObj = new File("apptcr.txt");
                if (myObj.delete()) {
                    System.out.println("Deleted the file: " + myObj.getName());
                } else {
                    System.out.println("Failed to delete the file.");
                }
                try {
                    Review2 newApp = new Review2();
                } catch (ParseException ex) {
                    Logger.getLogger(cancelReschedule.class.getName()).log(Level.SEVERE, null, ex);
                }
                patientHome.dispose(); //close screen
            }
        });
        //action to go to newAppointment page when button is clicked
        backBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                File myObj = new File("apptcr.txt");
                

                    patientHome.dispose(); //close screen
                    ScheduleAppointment sch = new ScheduleAppointment();
                
                
            }
        });

        //action to go to newAppointment2 page when button is clicked
        cancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {

                    Scanner s = new Scanner(new File("apptcr.txt"));
                    Boolean found = false;
                    while (s.hasNextLine() && !found) {

                        String in = s.nextLine();
                        String[] sArray = in.split("//");
                        date = sArray[0];

                    }
                    Scanner s2 = new Scanner(new File("appointments.txt"));
                    Boolean found2 = false;
                    while (s2.hasNextLine() && !found2) {

                        String in = s2.nextLine();
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
                            System.out.println("FOUND");
                            System.out.println(date);
                            System.out.println(formattedDate + " " + sArray[2].toString());

                            File inputFile = new File("appointments.txt");
                            File tempFile = new File("myTempFile.txt");

                            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
                            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
                            

                            String lineToRemove = sArray[0] + "//" + sArray[1] + "//" + sArray[2] + "//" + sArray[3] + "//" + sArray[4] + "//" + sArray[5] + "//" + sArray[6] + "//" + sArray[7] + "//" + sArray[8] + "//" + sArray[9] + "//" + sArray[10] + "//" + sArray[11] + "//" + sArray[12];

                            String currentLine;

                            while ((currentLine = reader.readLine()) != null) {
                                // trim newline when comparing with lineToRemove
                                String trimmedLine = currentLine.trim();
                                if (trimmedLine.contains(sArray[5])) {
                                    continue;
                                }
                                writer.write(currentLine + System.getProperty("line.separator"));
                            }
                            boolean delete = inputFile.delete();
                            boolean b = tempFile.renameTo(inputFile);
                            writer.close();
                            reader.close();
                        }

                    }
                    s.close();
                    s2.close();
                } catch (FileNotFoundException d) {
                    JOptionPane.showMessageDialog(null, "User Database Not Found.", "Error", JOptionPane.ERROR_MESSAGE);
                } catch (ParseException ex) {
                    Logger.getLogger(cancelReschedule.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(cancelReschedule.class.getName()).log(Level.SEVERE, null, ex);
                }

                String message = "Are you sure you want to cancel your " + date + " appointment from " + timeValue + " to " + timeValue2 + "?";
                Object[] params = {message};
                int n = JOptionPane.showConfirmDialog(null, params, "Disconnect Products", JOptionPane.YES_NO_OPTION);

                JOptionPane.showMessageDialog(null, "Appointment Cancelled.", "Success", JOptionPane.INFORMATION_MESSAGE);
                File myObj = new File("apptcr.txt");
                if (myObj.delete()) {
                    System.out.println("Deleted the file: " + myObj.getName());
                } else {
                    System.out.println("Failed to delete the file.");
                }
                patientHome.setVisible(false);
                patientHome.dispose(); //close screen
                PatientMenu pat = new PatientMenu();
            }
        });
    }
    public static void replaceSelected(String replaceWith, String type) {
        try {
            // input the file content to the StringBuffer "input"
            BufferedReader file = new BufferedReader(new FileReader("appointments.txt"));
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
            FileOutputStream fileOut = new FileOutputStream("appointments.txt");
            fileOut.write(inputStr.getBytes());
            fileOut.close();

        } catch (Exception e) {
            System.out.println("Problem reading file.");
        }
    }
}
