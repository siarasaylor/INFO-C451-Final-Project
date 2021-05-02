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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
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
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;


/**
 *
 * @author siarasaylor
 */
class patientInfo {

    static JTable table2;
    static DefaultTableModel dm2;
    static JTable table;
    static DefaultTableModel dm;
    static String dateCR;
    static TableModel model1;
    static TableRowSorter<TableModel> sorter;
    static JTextField enterfname1;
    patientInfo() {
        pinfo();
    }

    public static void pinfo() {
        System.out.println("Patient Info");

        //set up items
        JLabel pSEARCH = new JLabel("Patient Info");

        JPanel mainPanel = new JPanel();
        //set frame size and location
        StaffMenuFDN.staffHome = new JFrame("Patient Tracker - Patient Info");
        StaffMenuFDN.staffHome.setSize(1300, 800);
        StaffMenuFDN.staffHome.setLocationRelativeTo(null);
        mainPanel.setLayout(null);

        //create items for main panel
        JLabel header = new JLabel("Patient Info");
        JLabel fname = new JLabel("First Name");
        JLabel lname = new JLabel("Last Name");
        JLabel dob = new JLabel("Date of Birth");

        enterfname1 = new JTextField(30);
        JTextField enterlname1 = new JTextField(30);

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

        JButton searchButton = new JButton("Patient Details");
        JButton view = new JButton ("View");
        JButton submit = new JButton("View");

        JButton backButton = new JButton("Back");
        JLabel header1 = new JLabel("Upcoming Appointments");
        JLabel header3 = new JLabel("Previous Appointments");

        JLabel img = new JLabel(new ImageIcon("logo3.jpg"));
        header.setFont(new Font("Arial", Font.PLAIN, 30));
        header1.setFont(new Font("Arial", Font.PLAIN, 24));
        header3.setFont(new Font("Arial", Font.PLAIN, 24));
        JPanel resultTable = new JPanel();

        //horizontal line 
        MyLine horizontal1 = new MyLine();
        MyLine horizontal2 = new MyLine();
        MyLine horizontal3 = new MyLine();
        //pull information from text file and populate

        System.out.println("UPDATED: " + PatientSearch.inputSearch);
        String filePath = "userPass.txt";
        BufferedReader br1;
        boolean found = false;
        String line1 = "";
        try {
            br1 = new BufferedReader(new FileReader(filePath));
            try {
                while ((line1 = br1.readLine()) != null) {

                    String[] words = line1.split("//");

                    for (String word : words) {
                        if (word.equals(PatientSearch.inputSearch)) {

                            System.out.println(words[2]);
                            found = true;
                            enterfname1.setText(words[2]);
                            enterfname1.setEditable(false);
                            enterfname1.setEnabled(false);
                        }

                        if (word.equals(PatientSearch.inputSearch2)) {

                            found = true;
                            enterlname1.setText(words[4]);
                            enterlname1.setEditable(false);
                            enterlname1.setEnabled(false);
                        }
                        if (word.equals(PatientSearch.monthInput)) {
                            System.out.println(words[7]);
                            found = true;
                            String monthOption = words[7];
                            monthBox.setSelectedItem(monthOption);
                            monthBox.setEnabled(false);

                        }
                        if (word.equals(PatientSearch.dayInput)) {
                            System.out.println(words[8]);
                            found = true;
                            String dayOption = words[8];
                            dayBox.setSelectedItem(dayOption);
                            dayBox.setEnabled(false);
                        }
                        if (word.equals(PatientSearch.yearInput)) {
                            System.out.println(words[9]);
                            found = true;
                            String yearOption = words[9];
                            yearBox.setSelectedItem(yearOption);
                            yearBox.setEnabled(false);

                        }
                        if (found = false) {
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
        //pull information from text file and populate
        try {
            BufferedReader br = new BufferedReader(new FileReader("appointments.txt"));
            Scanner s = new Scanner(new File("appointments.txt"));

            String line = "";

            Object[][] data = {};
            String[] columnNames = {"Date", "Time", "Doctor", "Location", "Pre-Appointment"};

            dm2 = new DefaultTableModel(data, columnNames);
            table2 = new JTable(dm2){

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

            sorter = new TableRowSorter<>(table2.getModel());
            sorter.setComparator(0, new Comparator<String>() {
                @Override
                public int compare(String o1, String o2) {
                    return o1.compareTo(o2);
                }
            });
            sorter.setSortsOnUpdates(true);
            List<RowSorter.SortKey> sortKeys = new ArrayList<RowSorter.SortKey>();
            sortKeys.add(new RowSorter.SortKey(0, SortOrder.ASCENDING));
            sorter.setSortKeys(sortKeys);
            table2.setRowSorter(sorter);
            
            
            
            Object[][] data2 = {};
            String[] columnNames1 = {"Date", "Time", "Doctor", "Location", "View Details"};
            dm = new DefaultTableModel(data2, columnNames1);
            table = new JTable(dm){

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
            TableRowSorter<TableModel> sorter2 = new TableRowSorter<>(table.getModel());
            sorter2.setComparator(0, new Comparator<String>() {
                @Override
                public int compare(String o1, String o2) {
                    return o1.compareTo(o2);
                }
            });
            sorter2.setSortsOnUpdates(true);
            List<RowSorter.SortKey> sortKeys2 = new ArrayList<RowSorter.SortKey>();
            sortKeys2.add(new RowSorter.SortKey(0, SortOrder.ASCENDING));
            sorter2.setSortKeys(sortKeys2);
            table.setRowSorter(sorter2);
            while ((line = br.readLine()) != null) {

                String in = s.nextLine();
                String[] sArray = in.split("//");
                String day1 = sArray[5].toString();
                SimpleDateFormat parser = new SimpleDateFormat("EEE MMM d HH:mm:ss zzz yyyy");
                Date date = parser.parse(day1);
                Date todayDate = new Date();
                SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
                String formattedDate = formatter.format(date);
                String formattedDate1 = formatter.format(todayDate);

                for (String word : sArray) {
                    if (word.equals(PatientSearch.inputSearch)) {

                        //for (int x = 0; x < count; ++x) {
                        System.out.println(formattedDate);
                        System.out.println(todayDate);
                        int result = formattedDate.compareTo(formattedDate1);
                        System.out.println("result: " + result);

                        if (result == 0) {
                            dm2.addRow(new Object[]{formattedDate, sArray[6].toString() + "-" + sArray[7].toString(),
                                sArray[8].toString(),
                                sArray[9].toString() + " / " + sArray[11].toString(),
                                false});

                        } else if (result > 0) {
                            dm2.addRow(new Object[]{formattedDate, sArray[6].toString() + "-" + sArray[7].toString(),
                                sArray[8].toString(),
                                sArray[9].toString() + " / " + sArray[11].toString(),
                                false});

                        } else if (result < 0) {
                            dm.addRow(new Object[]{formattedDate, sArray[6].toString() + "-" + sArray[7].toString(),
                                sArray[8].toString(),
                                sArray[9].toString() + " / " + sArray[11].toString(),
                                false});

                        }

                        //}
                    }

                }
            }

        } catch (FileNotFoundException d) {
            JOptionPane.showMessageDialog(null, "User Database Not Found.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (ParseException ex) {
            Logger.getLogger(ScheduleAppointment.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException exx) {
            // TODO Auto-generated catch block
            exx.printStackTrace();
        }
        TableCellRenderer rendererFromHeader = table2.getTableHeader().getDefaultRenderer();
            JLabel headerLabel = (JLabel) rendererFromHeader;
            headerLabel.setHorizontalAlignment(JLabel.CENTER);

            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(JLabel.CENTER);
            table2.setDefaultRenderer(String.class, centerRenderer);

            table2.setPreferredScrollableViewportSize(table2.getPreferredSize());
            JScrollPane scrollPane = new JScrollPane(table2);
            
            TableCellRenderer rendererFromHeader2 = table.getTableHeader().getDefaultRenderer();
            JLabel headerLabel2 = (JLabel) rendererFromHeader2;
            headerLabel2.setHorizontalAlignment(JLabel.CENTER);

            DefaultTableCellRenderer centerRenderer2 = new DefaultTableCellRenderer();
            centerRenderer2.setHorizontalAlignment(JLabel.CENTER);
            table.setDefaultRenderer(String.class, centerRenderer);

            table.setPreferredScrollableViewportSize(table.getPreferredSize());
            JScrollPane scrollPane2 = new JScrollPane(table);
        //set location for each item
        img.setBounds(10, 10, img.getPreferredSize().width, img.getPreferredSize().height);

        horizontal2.setBounds(0, -250, 1300, 800);
        header.setBounds(125, 100, 450, 40);
        fname.setBounds(150, 175, 80, 20);
        enterfname1.setBounds(150, 200, 150, 20);
        lname.setBounds(340, 175, 150, 20);
        enterlname1.setBounds(340, 200, 150, 20);
        dob.setBounds(530, 175, 80, 20);
        monthBox.setBounds(530, 200, 70, monthBox.getPreferredSize().height);
        dayBox.setBounds(600, 200, 70, dayBox.getPreferredSize().height);
        yearBox.setBounds(670, 200, yearBox.getPreferredSize().width, yearBox.getPreferredSize().height);
        searchButton.setBounds(900, 200, searchButton.getPreferredSize().width, 20);

        //section2
        //horizontal1.setBounds(0, 100, 1300, 800);
        horizontal1.setBounds(0, -150, 1300, 800);
        header1.setBounds(125, 250, 450, 40);
        scrollPane.setBounds(150, 290, 1000, 100);
        submit.setBounds(1200, 370, 80, 20);
        //section3
        horizontal3.setBounds(0, 5, 1300, 800);
        header3.setBounds(125, 405, 450, 40);
        scrollPane2.setBounds(150, 445, 1000, 100);
        backButton.setBounds(900, 600, backButton.getPreferredSize().width, 20);
        view.setBounds(1200, 525, 80, 20);
        //add item to result panel
        //resultTable.add(new JScrollPane(table));
        //add items to main panel
        mainPanel.add(img);
        mainPanel.add(horizontal2);
        mainPanel.add(header);
        mainPanel.add(fname);
        mainPanel.add(enterfname1);
        mainPanel.add(lname);

        mainPanel.add(enterlname1);
        mainPanel.add(dob);
        mainPanel.add(monthBox);
        mainPanel.add(dayBox);
        mainPanel.add(yearBox);
        mainPanel.add(searchButton);

        mainPanel.add(horizontal1);
        mainPanel.add(header1);
        mainPanel.add(scrollPane);
        
        mainPanel.add(horizontal3);
        mainPanel.add(header3);
        mainPanel.add(scrollPane2);
        //mainPanel.add(view);
        mainPanel.add(backButton);

        if (loginWindow.userInput.startsWith("1") && loginWindow.userInput.length() > 2) {
            System.out.println("Doctor-patient search");
            mainPanel.add(submit);
            mainPanel.add(view);
            StaffMenuFDN.staffHome.getContentPane().add(BorderLayout.NORTH, StaffMenuFDN.dstaffMenu);

        }
        if (loginWindow.userInput.startsWith("2") && loginWindow.userInput.length() > 2) {
            System.out.println("Nurse-patient search");
            mainPanel.add(submit);
            mainPanel.add(view);
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

        //action to go to patient Detail page when button is clicked
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                StaffMenuFDN.staffHome.setVisible(false);
                StaffMenuFDN.staffHome.dispose();
                patientDetails newp = new patientDetails();
            }
        });
        //action to go to patient Detail page when button is clicked
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                StaffMenuFDN.staffHome.setVisible(false);
                StaffMenuFDN.staffHome.dispose();
                PatientSearch newp = new PatientSearch();
            }
        });
        
         //action to go to newAppointment2 page when button is clicked
        submit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                
                try {
                    File inputFile = new File("apptcr.txt");

                    FileWriter fw = new FileWriter(inputFile.getAbsoluteFile(), true);
                    model1 = table2.getModel();
                    for (int i = 0; i < model1.getRowCount() ; i++) {

                        if ((Boolean) model1.getValueAt(i, 4)) {
                            System.out.println(model1.getValueAt(i, 0));
                            System.out.println(model1.getValueAt(i, 1));
                            System.out.println(model1.getValueAt(i, 2));
                            System.out.println(model1.getValueAt(i, 3));
                            fw.write("\n" + model1.getValueAt(i, 0) + "//" + model1.getValueAt(i, 1) + "//" + model1.getValueAt(i, 2)+ "//" + model1.getValueAt(i, 3));
                            
                        }
                        
                    }
                    fw.close();
                    
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                
               
               StaffMenuFDN.staffHome.setVisible(false);
               StaffMenuFDN.staffHome.dispose();
               preAppt1 preappt = new preAppt1();
            }
        });
        //action to go to newAppointment2 page when button is clicked
        view.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                
                try {
                    File inputFile = new File("apptpa.txt");

                    FileWriter fw = new FileWriter(inputFile.getAbsoluteFile(), true);
                    model1 = table.getModel();
                    for (int i = 0; i < model1.getRowCount() ; i++) {

                        if ((Boolean) model1.getValueAt(i, 4)) {
                            System.out.println(model1.getValueAt(i, 0));
                            System.out.println(model1.getValueAt(i, 1));
                            System.out.println(model1.getValueAt(i, 2));
                            System.out.println(model1.getValueAt(i, 3));
                            fw.write(model1.getValueAt(i, 0) + "//" + model1.getValueAt(i, 1) + "//" + model1.getValueAt(i, 2)+ "//" + model1.getValueAt(i, 3));

                            
                        }
                        
                    }
                    fw.close();
                    
                   
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                StaffMenuFDN.staffHome.setVisible(false);
                StaffMenuFDN.staffHome.dispose();
                 try {
                    prevAppointment prevApp = new prevAppointment();
                } catch (IOException ex) {
                    Logger.getLogger(ButtonEditor.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ParseException ex) {
                    Logger.getLogger(ScheduleAppointment.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
}
