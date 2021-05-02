/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patienttracker;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

/**
 *
 * @author siarasaylor
 */
class FDHome extends StaffMenuFDN{
    FDHome() {
        frontdeskHome();
        
    }
    static void frontdeskHome() {
        JPanel mainPanel = new JPanel();
        //set frame size and location
        staffHome = new JFrame("Patient Tracker - Front Desk View");
        staffHome.setSize(1300,800);
        staffHome.setLocationRelativeTo(null);
        mainPanel.setLayout(null);
        
        //set up items
        JLabel staffPage = new JLabel("Front Desk Home");
        staffPage.setFont(new Font("Arial", Font.PLAIN, 30));
        
        //image logo
        JLabel img1 = new JLabel(new ImageIcon("logo3.jpg"));
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
        
        //add to frame
        staffHome.getContentPane().add(BorderLayout.NORTH, fdstaffMenu);
        staffHome.getContentPane().add(BorderLayout.CENTER, mainPanel);
        staffHome.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        staffHome.setResizable(false);
        staffHome.setVisible(true);
        
        //panel background color
        mainPanel.setBackground(Color.WHITE);
    }
}
