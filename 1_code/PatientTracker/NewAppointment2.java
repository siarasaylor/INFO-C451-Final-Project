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
import java.util.Enumeration;
import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author siarasaylor
 */
class NewAppointment2 extends NewAppointment{
    NewAppointment2() {
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
    static JTextArea reason;
    static JRadioButton prev;
    static JRadioButton labs;
    static JRadioButton phone;
    static JRadioButton zoom;
    static JRadioButton inperson;
    static ButtonGroup bg1;
    static ButtonGroup bg2;
    static String kinD; 
    static String typE;
    static String detailS;

    public static void schedNewAppt() {
        System.out.println("Schedule Appointment Page 2");

        //set up items
        JLabel scheduleAppointment = new JLabel("Schedule New Appointment - Page 2");
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
        patientHome = new JFrame("Patient Tracker - Schedule New Appointment - Page 2");
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
        JLabel header1 = new JLabel("Schedule New Appointment");
        JLabel header2 = new JLabel("Schedule Appointment - Part 2");
        JLabel header3 = new JLabel("What kind of appointment would you like?");
        JLabel header4 = new JLabel("What type of appointment would you like?");
        JLabel header5 = new JLabel("What is the reason for the appointment?");
        
        header1.setFont(new Font("Arial", Font.PLAIN, 30));
        header2.setFont(new Font("Arial", Font.PLAIN, 24));
        header3.setFont(new Font("Arial", Font.BOLD, 14));
        header4.setFont(new Font("Arial", Font.BOLD, 14));
        header5.setFont(new Font("Arial", Font.BOLD, 14));
        
        reason = new JTextArea("Review during appointment", 10, 20);
        reason.setEditable(true);
        reason.setEnabled(true);
        
        Border border = BorderFactory.createLineBorder(Color.BLACK);
        reason.setBorder(BorderFactory.createCompoundBorder(border,
            BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        
        
        //kind of appointment buttons
        prev = new JRadioButton("Preventitive");
        prev.setActionCommand("Preventitive");
        labs = new JRadioButton("Labs");
        labs.setActionCommand("Labs");
        bg1=new ButtonGroup();    
        bg1.add(prev);
        bg1.add(labs);  
        
        //Menu for type of appointment
        phone = new JRadioButton("Phone");
        phone.setActionCommand("Phone");
        zoom = new JRadioButton("Zoom");
        zoom.setActionCommand("Zoom");
        inperson = new JRadioButton("In Person");
        inperson.setActionCommand("In Person");
        bg2=new ButtonGroup();    
        bg2.add(phone);
        bg2.add(zoom);
        bg2.add(inperson);
        
        JButton nextBtn = new JButton("Next");
        JButton backBtn = new JButton("Back");
        
        //image logo
        JLabel img1 = new JLabel(new ImageIcon("logo3.jpg"));
        
        //horizontal line 
        MyLine horizontal1 = new MyLine();
        MyLine horizontal2 = new MyLine();
        MyLine horizontal3 = new MyLine();
        
        
        
        
        //header
        img1.setBounds(10, 10, img1.getPreferredSize().width, img1.getPreferredSize().height);
        header1.setBounds(125, 100, 450, 40);
        horizontal2.setBounds(0, -250, 1300, 800);
       
        //section1
        horizontal1.setBounds(0, -100, 1300, 800);
        header3.setBounds(125,150,450,40 );
        prev.setBounds(125, 195, prev.getPreferredSize().width, 50);
        labs.setBounds(260, 195, prev.getPreferredSize().width, 50);
        //section2
        horizontal3.setBounds(0, 100, 1300,800);
        header4.setBounds(125, 350, 450,40);
        phone.setBounds(125, 395, phone.getPreferredSize().width, phone.getPreferredSize().height);
        zoom.setBounds(230, 395, zoom.getPreferredSize().width, zoom.getPreferredSize().height);
        inperson.setBounds(335, 395, inperson.getPreferredSize().width, inperson.getPreferredSize().height);
        
        //section3
        header5.setBounds(125, 500, 450,40);
        reason.setBounds(125, 535, reason.getPreferredSize().width, reason.getPreferredSize().height);
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
        mainPanel2.add(prev);
        mainPanel2.add(labs);
        
        mainPanel2.add(phone);
        mainPanel2.add(zoom);
        mainPanel2.add(inperson);
        
        mainPanel2.add(horizontal3);
        mainPanel2.add(header3);

        mainPanel2.add(header4);
        mainPanel2.add(header5);
        mainPanel2.add(reason);
        mainPanel2.add(nextBtn);
        mainPanel2.add(backBtn);
      
        //add panels to JFrame
        patientHome.getContentPane().add(BorderLayout.NORTH, patientMenu);
        patientHome.getContentPane().add(BorderLayout.CENTER,mainPanel2);
        patientHome.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        patientHome.setResizable(false);
        patientHome.setVisible(true);
        
        mainPanel2.setBackground(Color.WHITE);
        
        //action to go to newAppointment2 page when button is clicked
        nextBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                kinD = bg1.getSelection().getActionCommand();
                System.out.println(kinD);
                typE = bg2.getSelection().getActionCommand();
                System.out.println(typE);
                detailS = reason.getText();
                Review newApp = new Review();
                patientHome.dispose(); //close screen
            }
        });
        //action to go to newAppointment page when button is clicked
        backBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                NewAppointment newApp = new NewAppointment();
                patientHome.dispose(); //close screen
            }
        });
    }
    public String getSelectedButtonText(ButtonGroup buttonGroup) {
        for (Enumeration<AbstractButton> buttons = bg1.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();

            if (button.isSelected()) {
                return button.getText();
            }
        }

        return null;
    }
}
