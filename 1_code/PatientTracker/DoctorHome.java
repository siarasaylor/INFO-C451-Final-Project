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
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


/**
 *
 * @author siarasaylor
 */
class DoctorHome extends StaffMenuFDN{
    DoctorHome() {
        dhomepage();
    }
    static void dhomepage() {
        //set up items
        JLabel staffPage = new JLabel("Doctor Home");
        staffPage.setFont(new Font("Arial", Font.PLAIN, 30));
        
        //image logo
        JLabel img1 = new JLabel(new ImageIcon("logo3.jpg"));
        
        mainPanel = new JPanel();

        //set frame size and location
        staffHome = new JFrame("Patient Tracker - Doctor View");
        staffHome.setSize(1300,800);
        staffHome.setLocationRelativeTo(null);
        mainPanel.setLayout(null);
        
        //horizontal line
        MyLine horizontal2 = new MyLine();
        //set location for each item
        img1.setBounds(10, 10, img1.getPreferredSize().width, img1.getPreferredSize().height);
        staffPage.setBounds(125, 100, 450, 40);
        horizontal2.setBounds(0, -250, 1300, 800);
        
        //add items to panel
        mainPanel.add(staffPage);
        mainPanel.add(img1);
        mainPanel.add(horizontal2);
        
        
        staffHome.getContentPane().add(BorderLayout.NORTH, dstaffMenu);
        staffHome.getContentPane().add(BorderLayout.CENTER, mainPanel);
        staffHome.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        staffHome.setResizable(false);
        staffHome.setVisible(true);
        mainPanel.setBackground(Color.WHITE);
    }
}
