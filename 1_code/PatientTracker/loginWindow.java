package patienttracker;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author siarasaylor
 */
class loginWindow {
    loginWindow() {
        login();
    }
    public static String userInput;
    public static String passInput;
    static JButton login;
    static JPanel loginPanel;
    static JTextField enterusername;
    static JPasswordField enterpassword;
    static JLabel username;
    static JLabel password;
    static JFrame frame;
    static JMenuBar loginmenu;
    static JMenuItem help;
    static JMenuItem close;
    //add links to pages like forgot username and password

    public static void login() {
        
        //set up each item
        login = new JButton("Login");
        username = new JLabel("Username: \t");
        password = new JLabel("Password: \t");
        enterusername = new JTextField(30);
        enterpassword = new JPasswordField(25);
        loginPanel = new JPanel();
        frame = new JFrame("Patient Tracker");
        loginmenu = new JMenuBar();
        help = new JMenuItem("Help");
        close = new JMenuItem("Close");
        JLabel img = new JLabel(new ImageIcon("logo2.jpg"));
        //set frame size and location
        frame.setSize(1300,800);
        frame.setMinimumSize(new Dimension(1280, 800));
        
        frame.setLocationRelativeTo(null);
        loginPanel.setLayout(null);

        //set location for each item
        img.setBounds(575, 100, img.getPreferredSize().width, img.getPreferredSize().height);
        enterusername.setBounds(655, 380, 150, 20);
        enterpassword.setBounds(655, 415, 150, 20);
        login.setBounds(655, 450, 80, 20);
        username.setBounds(500, 378, 80, 20);
        password.setBounds(500, 413, 80, 20);
        help.setPreferredSize(new Dimension(100, help.getPreferredSize().height));
        close.setPreferredSize(new Dimension(100, close.getPreferredSize().height));

        //action to close when button is clicked
        close.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                System.exit(0);
            }
        });

        //add items to login panel
        loginPanel.add(img);
        loginPanel.add(login);
        loginPanel.add(enterusername);
        loginPanel.add(enterpassword);
        loginPanel.add(username);
        loginPanel.add(password);

        //add items to the menu
        loginmenu.add(help);
        loginmenu.add(close);

        frame.getContentPane().add(BorderLayout.NORTH, loginmenu);
        frame.getContentPane().add(BorderLayout.CENTER, loginPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);
        
        loginPanel.setBackground(Color.WHITE);

        //press the enter key in the password field and it will log in
        enterpassword.addKeyListener(new KeyListener() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    //System.out.println("Hello");
                    File inputFile = new File("userPass.txt");

                    userInput = enterusername.getText();
                    passInput = enterpassword.getText();

                    try {

                        Scanner in = new Scanner(new File("userPass.txt"));
                        boolean found = false;
                        while (in.hasNextLine() && !found) {

                            String s = in.nextLine();
                            String[] sArray = s.split("//");


                            if (userInput.equals(sArray[0])) {
                                if (userInput.equals(sArray[0]) && passInput.equals(sArray[1])) {
                                    JOptionPane.showMessageDialog(null, "Login Successful", "Success", JOptionPane.INFORMATION_MESSAGE);
                                    //Person person = new Person(userInput, passInput);
                                    //Person.personView(userInput, passInput);
                                    if (userInput.matches("[0-9]+") && userInput.length() > 2) {
                                        if (userInput.startsWith("1") && userInput.length() > 2) {
                                            found = true;
                                            frame.setVisible(false);
                                            frame.dispose(); //Destroy the JFrame object
                                            DoctorHome dstaff = new DoctorHome();
                                            
                                            
                                            break;
                                        }
                                        if (userInput.startsWith("2") && userInput.length() > 2) {
                                            found = true;
                                            frame.setVisible(false);
                                            frame.dispose();
                                            NurseHome nstaff = new NurseHome();
                                            break;
                                        }if (userInput.startsWith("3") && userInput.length() > 2) {
                                            found = true;
                                            frame.setVisible(false);
                                            frame.dispose();
                                            FDHome fdstaff = new FDHome();
                                            
                                            break;
                                        } else {
                                            JOptionPane.showMessageDialog(null, "Invalid Login");
                                            enterusername.setText("");
                                            enterpassword.setText("");
                                            enterusername.requestFocus();
                                        }
                                    } else {
                                        found = true;
                                        frame.setVisible(false);
                                        frame.dispose();
                                        PatientMenu menu = new PatientMenu();
                                        
                                        break;
                                    }
                                }

                            }
                        }
                        if (found == false) {
                            if (userInput.equals("") && passInput.equals("")) {
                                JOptionPane.showMessageDialog(null, "Please insert Username and Password");

                            } else {
                                JOptionPane.showMessageDialog(null, "Invalid Login");
                                enterusername.setText("");
                                enterpassword.setText("");
                                enterusername.requestFocus();
                            }

                        }
                        in.close();
                    } catch (FileNotFoundException d) {
                        JOptionPane.showMessageDialog(null, "User Database Not Found.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }

            }

            public void keyReleased(KeyEvent arg0) {
                // TODO Auto-generated method stub

            }

            public void keyTyped(KeyEvent arg0) {

            }
        });
        login.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                File inputFile = new File("userPass.txt");

                userInput = enterusername.getText();
                passInput = enterpassword.getText();

                try {

                    Scanner in = new Scanner(new File("userPass.txt"));
                    boolean found = false;
                    while (in.hasNextLine() && !found) {

                        String s = in.nextLine();
                        String[] sArray = s.split("//");

                        //verify that file is being read
                        //System.out.println(sArray[0]);
                        //System.out.println(sArray[1]);
                        if (userInput.equals(sArray[0])) {
                            if (userInput.equals(sArray[0]) && passInput.equals(sArray[1])) {
                                JOptionPane.showMessageDialog(null, "Login Successful", "Success", JOptionPane.INFORMATION_MESSAGE);
                                //Person person = new Person(userInput, passInput);
                                if (userInput.matches("[0-9]+") && userInput.length() > 2) {
                                        if (userInput.startsWith("1") && userInput.length() > 2) {
                                            found = true;
                                            frame.setVisible(false);
                                            frame.dispose();
                                            DoctorHome dstaff = new DoctorHome();
                                            break;
                                        }
                                        if (userInput.startsWith("2") && userInput.length() > 2) {
                                            found = true;
                                            frame.setVisible(false);
                                            frame.dispose();
                                            NurseHome nstaff = new NurseHome();
                                            break;
                                        }if (userInput.startsWith("3") && userInput.length() > 2) {
                                            found = true;
                                            frame.setVisible(false);
                                            frame.dispose();
                                            FDHome fdstaff = new FDHome();
                                            break;
                                        } else {
                                            JOptionPane.showMessageDialog(null, "Invalid Login");
                                            enterusername.setText("");
                                            enterpassword.setText("");
                                            enterusername.requestFocus();
                                        }
                                    } else {
                                        found = true;
                                        frame.setVisible(false);
                                        frame.dispose();
                                        PatientMenu menu = new PatientMenu();
                                        break;
                                    }
                            }

                        }
                    }
                    if (found == false) {
                        if (userInput.equals("") && passInput.equals("")) {
                            JOptionPane.showMessageDialog(null, "Please insert Username and Password");

                        } else {
                            JOptionPane.showMessageDialog(null, "Invalid Login");
                            enterusername.setText("");
                            enterpassword.setText("");
                            enterusername.requestFocus();
                        }

                    }
                    in.close();
                } catch (FileNotFoundException d) {
                    JOptionPane.showMessageDialog(null, "User Database Not Found.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
    
    
}
//------------------------------------------------------------------------------

