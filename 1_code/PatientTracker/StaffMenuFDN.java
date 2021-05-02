/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patienttracker;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

/**
 *
 * @author siarasaylor
 */
class StaffMenuFDN{

    

    StaffMenuFDN() {
        menuStaff();
    }
    static JFrame staffHome;
    static JPanel mainPanel;
    static JMenuBar fdstaffMenu;
    static JMenuBar nstaffMenu;
    static JMenuBar dstaffMenu;
    static JMenuItem calendar;
    static JMenuItem patientSearch;
    static JMenuItem presApprovals;
    static JMenuItem homeButton;
    static JMenu staffexit;
    static JMenuItem logout;
    static JMenuItem exit;

    public static void menuStaff() {
       if (loginWindow.userInput.matches("[0-9]+") && loginWindow.userInput.length() > 2) {
            if (loginWindow.userInput.startsWith("1") && loginWindow.userInput.length() > 2) {
                
                doctorMenu();

            }
            if (loginWindow.userInput.startsWith("2") && loginWindow.userInput.length() > 2) {
                nurseMenu();

            }
            if (loginWindow.userInput.startsWith("3") && loginWindow.userInput.length() > 2) {
                frontDeskMenu();

            }

        }
    }

    public static void doctorMenu() {
        System.out.println("Doctor View");

        dstaffMenu = new JMenuBar();

        homeButton = new JMenuItem("Home");
        calendar = new JMenuItem("Calendar");
        patientSearch = new JMenuItem("Patient Search");
        presApprovals = new JMenuItem("Prescription Approvals");

        staffexit = new JMenu("Exit");
        logout = new JMenuItem("Log Out");
        exit = new JMenuItem("Exit");

        dstaffMenu.setPreferredSize(new Dimension(dstaffMenu.getPreferredSize().width, 30));

        //action to go to the staff main page when button is clicked
        homeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loginWindow.frame.dispose();
                staffHome.dispose();
                staffHome.setVisible(false);
                DoctorHome dstaff = new DoctorHome();
                    
                    
            }
        });
        //action to go to calendar page when button is clicked
        calendar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                staffHome.setVisible(false);
                staffHome.dispose();
                loginWindow.frame.dispose();
                MainFrame mainFrame = new MainFrame();
            }
        });
        //action to go to the Patient Search Page when button is clicked
        patientSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                staffHome.setVisible(false);
                loginWindow.frame.dispose();
                staffHome.dispose();
                PatientSearch searchP = new PatientSearch();
            }
        });

//        //action to go to the prescriptionApprovals page when button is clicked
//        presApprovals.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                staffHome.setVisible(false);
//                staffHome.dispose();
//                prescriptionApprovals pApprovals = new prescriptionApprovals();
//            }
//        });

        //action to close when button is clicked
        exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                staffHome.dispose();
                System.exit(0);
            }
        });
        //action to close when button is clicked
        logout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                staffHome.setVisible(false);
                staffHome.dispose();
                loginWindow login = new loginWindow();
                //System.exit(0);
            }
        });

        //add items to menu
        staffexit.add(logout);
        staffexit.add(exit);
        dstaffMenu.add(homeButton);
        dstaffMenu.add(calendar);
        dstaffMenu.add(patientSearch);
        //dstaffMenu.add(presApprovals);
        dstaffMenu.add(staffexit);

        
    }

    public static void nurseMenu() {
        System.out.println("Nurse View");

        nstaffMenu = new JMenuBar();
        homeButton = new JMenuItem("Home");
        calendar = new JMenuItem("Calendar");
        patientSearch = new JMenuItem("Patient Search");
        presApprovals = new JMenuItem("Prescription Approvals");

        staffexit = new JMenu("Exit");
        logout = new JMenuItem("Log Out");
        exit = new JMenuItem("Exit");

        nstaffMenu.setPreferredSize(new Dimension(nstaffMenu.getPreferredSize().width, 30));
        //action to go to the staff main page when button is clicked
        homeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loginWindow.frame.setVisible(false);
                loginWindow.frame.dispose();
                NurseHome nstaff = new NurseHome();
            }
        });

        //action to go to calendar page when button is clicked
        calendar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                staffHome.setVisible(false);
                staffHome.dispose();
                MainFrame mainFrame = new MainFrame();
            }
        });
        //action to go to the Patient Search Page when button is clicked
        patientSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                staffHome.setVisible(false);
                staffHome.dispose();
                PatientSearch searchP = new PatientSearch();
            }
        });

//        //action to go to the prescriptionApprovals page when button is clicked
//        presApprovals.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                staffHome.setVisible(false);
//                staffHome.dispose();
//                prescriptionApprovals pApprovals = new prescriptionApprovals();
//                
//            }
//        });

        //action to close when button is clicked
        exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                staffHome.dispose();
                System.exit(0);
            }
        });
        //action to close when button is clicked
        logout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                staffHome.setVisible(false);
                staffHome.dispose();
                loginWindow login = new loginWindow();
                //System.exit(0);
            }
        });

        //add items to menu
        staffexit.add(logout);
        staffexit.add(exit);
        nstaffMenu.add(homeButton);
        nstaffMenu.add(calendar);
        nstaffMenu.add(patientSearch);
        //nstaffMenu.add(presApprovals);
        nstaffMenu.add(staffexit);

        
    }

    public static void frontDeskMenu() {
        System.out.println("Front Desk View");

        fdstaffMenu = new JMenuBar();

        homeButton = new JMenuItem("Home");
        calendar = new JMenuItem("Calendar");
        patientSearch = new JMenuItem("Patient Search");

        staffexit = new JMenu("Exit");
        logout = new JMenuItem("Log Out");
        exit = new JMenuItem("Exit");

        fdstaffMenu.setPreferredSize(new Dimension(fdstaffMenu.getPreferredSize().width, 30));
        //action to go to the staff main page when button is clicked
        homeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loginWindow.frame.setVisible(false);
                loginWindow.frame.dispose();
                FDHome fdstaff = new FDHome();
            }
        });

        //action to go to calendar page when button is clicked
        calendar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                staffHome.setVisible(false);
                staffHome.dispose();
                MainFrame mainFrame = new MainFrame();
            }
        });
        //action to go to the Patient Search Page when button is clicked
        patientSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                staffHome.setVisible(false);
                staffHome.dispose();
                PatientSearch searchP = new PatientSearch();
            }
        });
        //action to close when button is clicked
        exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                staffHome.dispose();
                System.exit(0);
            }
        });
        //action to close when button is clicked
        logout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                staffHome.setVisible(false);
                staffHome.dispose();
                loginWindow login = new loginWindow();
                //System.exit(0);
            }
        });

        //add items to menu
        staffexit.add(logout);
        staffexit.add(exit);

        fdstaffMenu.add(homeButton);
        fdstaffMenu.add(calendar);
        fdstaffMenu.add(patientSearch);
        fdstaffMenu.add(staffexit);

        
    }
}
