/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patienttracker;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import static patienttracker.ScheduleAppointment.patientHome;
import static patienttracker.ScheduleAppointment.table;

/**
 *
 * @author siarasaylor
 */
//-----------------------------------
class ScheduleAppointment {

    ScheduleAppointment() {
        schedAppt();
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
    static JTable table2;
    static DefaultTableModel dm2;
    static JTable table;
    static DefaultTableModel dm;
    static String dateCR;
    static TableModel model1;
    static TableModel model2;
    static TableRowSorter<TableModel> sorter;
    static TableRowSorter<TableModel> sorter2;

    public static void schedAppt() {
        System.out.println("Schedule Appointment");

        //set up items
        JLabel scheduleAppointment = new JLabel("Schedule Appointment");
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
        patientHome = new JFrame("Patient Tracker - Schedule Appointment");
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
        JLabel header1 = new JLabel("Appointments");
        JLabel header2 = new JLabel("Upcoming Appointments");
        JLabel header3 = new JLabel("Previous Appointments");

        header1.setFont(new Font("Arial", Font.PLAIN, 30));
        header2.setFont(new Font("Arial", Font.PLAIN, 24));
        header3.setFont(new Font("Arial", Font.PLAIN, 24));

        JButton newAppointment = new JButton("Schedule New Appointment");
        JButton view = new JButton ("View");
        JButton submit = new JButton("Submit");

        //image logo
        JLabel img1 = new JLabel(new ImageIcon("logo3.jpg"));

        //horizontal line
        MyLine horizontal1 = new MyLine();
        MyLine horizontal2 = new MyLine();
        MyLine horizontal3 = new MyLine();

        //pull information from text file and populate
        try {
            BufferedReader br = new BufferedReader(new FileReader("appointments.txt"));
            Scanner s = new Scanner(new File("appointments.txt"));

            String line = "";
//TABLE 1 =============================================================================================================================
            Object[][] data = {};
            String[] columnNames = {"Date", "Time", "Doctor", "Location", "PreAppointment", "Cancel/Reschedule"};

            dm2 = new DefaultTableModel(data, columnNames);
            table2 = new JTable(dm2) {

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
                        case 4:
                            return Boolean.class;
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

//            table2.addMouseListener(new java.awt.event.MouseAdapter() {
//                @Override
//                public void mouseClicked(java.awt.event.MouseEvent evt) {
//
//                    try {
//
//                        model1 = table2.getModel();
//
//                        int row = table2.rowAtPoint(evt.getPoint());
//                        int col = table2.columnAtPoint(evt.getPoint());
//
//                        System.out.println("ROW: " + row);
//                        System.out.println("COLUMN: " + col);
//
//                        if (row >= 0 && col >= 5) {
//                            File inputFile = new File("apptcr.txt");
//
//                            FileWriter fw = new FileWriter(inputFile.getAbsoluteFile(), true);
//
//                            System.out.println("TEST");
//                            System.out.println(model1.getValueAt(sorter.convertRowIndexToModel(row), 0) + "//" + model1.getValueAt(sorter.convertRowIndexToModel(row), 1) + "//" + model1.getValueAt(sorter.convertRowIndexToModel(row), 2) + "//" + model1.getValueAt(sorter.convertRowIndexToModel(row), 3));
//                            fw.write("\n" + model1.getValueAt(sorter.convertRowIndexToModel(row), 0) + "//" + model1.getValueAt(sorter.convertRowIndexToModel(row), 1) + "//" + model1.getValueAt(sorter.convertRowIndexToModel(row), 2) + "//" + model1.getValueAt(sorter.convertRowIndexToModel(row), 3));
//
//                            fw.close();
//                        }
//
//                    } catch (Exception ex) {
//                        ex.printStackTrace();
//                    }
//
//                }
//
//            });
//TABLE 2 =========================================================================================
            Object[][] data2 = {};
            String[] columnNames1 = {"Date", "Time", "Doctor", "Location", "View Details"};
            dm = new DefaultTableModel(data2, columnNames1);
            table = new JTable(dm) {

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
            
            sorter2 = new TableRowSorter<>(table.getModel());
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
                String day = sArray[5].toString();
                SimpleDateFormat parser = new SimpleDateFormat("EEE MMM d HH:mm:ss zzz yyyy");
                Date date = parser.parse(day);
                Date todayDate = new Date();
                SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
                String formattedDate = formatter.format(date);
                String formattedDate1 = formatter.format(todayDate);

                for (String word : sArray) {
                    if (word.equals(loginWindow.userInput)) {

                        //for (int x = 0; x < count; ++x) {
                        System.out.println(formattedDate);
                        System.out.println(todayDate);
                        int result = formattedDate.compareTo(formattedDate1);
                        System.out.println("result: " + result);

                        if (result == 0) {
                            dm2.addRow(new Object[]{formattedDate, sArray[6].toString() + "-" + sArray[7].toString(),
                                sArray[8].toString(),
                                sArray[9].toString() + " / " + sArray[11].toString(),
                                false,
                                false});

                        } else if (result > 0) {
                            dm2.addRow(new Object[]{formattedDate, sArray[6].toString() + "-" + sArray[7].toString(),
                                sArray[8].toString(),
                                sArray[9].toString() + " / " + sArray[11].toString(),
                                false,
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
            
//        table2.getColumn("PreAppointment").setCellRenderer(new ButtonRenderer());
//        table2.getColumn("PreAppointment").setCellEditor(new ButtonEditor(new JCheckBox()));
//        table2.getColumn("Cancel/Reschedule").setCellRenderer(new ButtonRenderer());
//        table2.getColumn("Cancel/Reschedule").setCellEditor(new ButtonEditor(new JCheckBox()));
//
//        JScrollPane scroll2 = new JScrollPane(table2);
//
//        table2.setPreferredScrollableViewportSize(table2.getPreferredSize());//thanks mKorbel +1 http://stackoverflow.com/questions/10551995/how-to-set-jscrollpane-layout-to-be-the-same-as-jtable
//
//        table2.getColumnModel().getColumn(0).setPreferredWidth(100);
//
//        table.getColumn("View Details").setCellRenderer(new ButtonRenderer());
//        table.getColumn("View Details").setCellEditor(new ButtonEditor(new JCheckBox()));
//
//        JScrollPane scroll = new JScrollPane(table);
//
//        table.setPreferredScrollableViewportSize(table.getPreferredSize());//thanks mKorbel +1 http://stackoverflow.com/questions/10551995/how-to-set-jscrollpane-layout-to-be-the-same-as-jtable
//
//        table.getColumnModel().getColumn(0).setPreferredWidth(100);
        //header
        img1.setBounds(10, 10, img1.getPreferredSize().width, img1.getPreferredSize().height);
        header1.setBounds(125, 100, 450, 40);
        horizontal2.setBounds(0, -250, 1300, 800);

        //Section1
        newAppointment.setBounds(150, 185, newAppointment.getPreferredSize().width, newAppointment.getPreferredSize().height);

        //section2
        horizontal1.setBounds(0, -150, 1300, 800);
        header2.setBounds(125, 250, 450, 40);
        scrollPane.setBounds(150, 290, 1000, 100);
        submit.setBounds(1200, 370, 80, 20);
        //section3
        horizontal3.setBounds(0, 5, 1300, 800);
        header3.setBounds(125, 405, 450, 40);
        scrollPane2.setBounds(150, 445, 1000, 100);
        view.setBounds(1200, 525, 80, 20);

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

        mainPanel2.add(newAppointment);

        mainPanel2.add(horizontal2);
        mainPanel2.add(header2);
        mainPanel2.add(scrollPane);
        mainPanel2.add(submit);

        mainPanel2.add(horizontal3);
        mainPanel2.add(header3);
        mainPanel2.add(scrollPane2);
        mainPanel2.add(view);

//add panels to JFrame
        patientHome.getContentPane().add(BorderLayout.NORTH, patientMenu);
        patientHome.getContentPane().add(BorderLayout.CENTER, mainPanel2);
        patientHome.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        patientHome.setResizable(false);
        patientHome.setVisible(true);

        mainPanel2.setBackground(Color.WHITE);

        //action to go to new appointment page when button is clicked
        newAppointment.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                NewAppointment newApp = new NewAppointment();
                patientHome.dispose(); //close screen
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
                            patientHome.dispose(); //close screen
                            patientHome.setVisible(false);
                            
                            preAppt preappt = new preAppt();
                            
                        }
                        if ((Boolean) model1.getValueAt(i, 5)) {
                            System.out.println(model1.getValueAt(i, 0));
                            System.out.println(model1.getValueAt(i, 1));
                            System.out.println(model1.getValueAt(i, 2));
                            System.out.println(model1.getValueAt(i, 3));
                            fw.write("\n" + model1.getValueAt(i, 0) + "//" + model1.getValueAt(i, 1) + "//" + model1.getValueAt(i, 2)+ "//" + model1.getValueAt(i, 3));
                            
                            patientHome.dispose(); //close screen
                            patientHome.setVisible(false);
                            cancelReschedule canres = new cancelReschedule();
                            
                        }
                    }
                    fw.close();
                    
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                
               
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
                patientHome.dispose(); //close screen
                patientHome.setVisible(false);
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

class dates extends ScheduleAppointment {

    public dates(int row, int col) {
        model1 = table2.getModel();
        model1.getValueAt(row, col);

    }
}

class ButtonRenderer extends JButton implements TableCellRenderer {

    public ButtonRenderer() {
        setOpaque(true);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
        if (isSelected) {
            setForeground(table.getSelectionForeground());
            setBackground(table.getSelectionBackground());
        } else {
            setForeground(table.getForeground());
            setBackground(UIManager.getColor("Button.background"));
        }
        setText((value == null) ? "" : value.toString());
        return this;
    }
}

class ButtonEditor extends DefaultCellEditor {

    protected JButton details;
    protected JButton preAppt;
    protected JButton canresch;
    private String label;
    private boolean isPushed;
    static TableModel model1;
    static TableModel model2;

    public ButtonEditor(JCheckBox checkBox) {
        super(checkBox);
        details = new JButton();
        details.setOpaque(true);
        details.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                fireEditingStopped();
            }
        });

    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value,
            boolean isSelected, int row, int column) {
        if (isSelected) {
            details.setForeground(table.getSelectionForeground());
            details.setBackground(table.getSelectionBackground());
        } else {
            details.setForeground(table.getForeground());
            details.setBackground(table.getBackground());
        }
        label = (value == null) ? "" : value.toString();
        details.setText(label);
        isPushed = true;
        return details;
    }

    @Override
    public Object getCellEditorValue() {
        if (isPushed) {
//            if (label.equals("Details")) {
////                table.addMouseListener(new java.awt.event.MouseAdapter() {
////                    @Override
////                    public void mouseClicked(java.awt.event.MouseEvent evt2) {
////
////                        try {
////                            System.out.println("READING TABLE");
////                            model2 = ScheduleAppointment.table.getModel();
////
////                            int row = ScheduleAppointment.table.rowAtPoint(evt2.getPoint());
////                            int col = ScheduleAppointment.table.columnAtPoint(evt2.getPoint());
////
////                            System.out.println("ROW: " + row);
////                            System.out.println("COLUMN: " + col);
////
////                            if (row >= 0 && col >= 4) {
////                                File inputFile = new File("apptpa.txt");
////
////                                FileWriter fw2 = new FileWriter(inputFile.getAbsoluteFile(), true);
////
////                                System.out.println("TEST");
////                                System.out.println(model2.getValueAt(ScheduleAppointment.sorter2.convertRowIndexToModel(row), 0) + "//" + model2.getValueAt(ScheduleAppointment.sorter2.convertRowIndexToModel(row), 1) + "//" + model2.getValueAt(ScheduleAppointment.sorter2.convertRowIndexToModel(row), 2) + "//" + model2.getValueAt(ScheduleAppointment.sorter2.convertRowIndexToModel(row), 3));
////                                fw2.write("\n" + model2.getValueAt(ScheduleAppointment.sorter2.convertRowIndexToModel(row), 0) + "//" + model2.getValueAt(ScheduleAppointment.sorter2.convertRowIndexToModel(row), 1) + "//" + model2.getValueAt(ScheduleAppointment.sorter2.convertRowIndexToModel(row), 2) + "//" + model2.getValueAt(ScheduleAppointment.sorter2.convertRowIndexToModel(row), 3));
////
////                                fw2.close();
////                            }
////                            prevAppointment prevApp = new prevAppointment();
////                        } catch (Exception ex) {
////                            ex.printStackTrace();
////                        }
////                    }
////
////                });
////                
//                try {
//                    prevAppointment prevApp = new prevAppointment();
//                } catch (IOException ex) {
//                    Logger.getLogger(ButtonEditor.class.getName()).log(Level.SEVERE, null, ex);
//                }
////                        
//                patientHome.dispose(); //close screen
//            }
//            if (label.equals("PreAppointment")) {
////                ScheduleAppointment.table2.addMouseListener(new java.awt.event.MouseAdapter() {
////                    @Override
////                    public void mouseClicked(java.awt.event.MouseEvent evt1) {
////
////                        try {
////
////                            model1 = ScheduleAppointment.table2.getModel();
////
////                            int row = ScheduleAppointment.table2.rowAtPoint(evt1.getPoint());
////                            int col = ScheduleAppointment.table2.columnAtPoint(evt1.getPoint());
////
////                            System.out.println("ROW: " + row);
////                            System.out.println("COLUMN: " + col);
////
////                            if (row >= 0 && col >= 4) {
////                                File inputFile = new File("apptcr.txt");
////
////                                FileWriter fw = new FileWriter(inputFile.getAbsoluteFile(), true);
////
////                                System.out.println("TEST");
////                                System.out.println(model1.getValueAt(ScheduleAppointment.sorter.convertRowIndexToModel(row), 0) + "//" + model1.getValueAt(ScheduleAppointment.sorter.convertRowIndexToModel(row), 1) + "//" + model1.getValueAt(ScheduleAppointment.sorter.convertRowIndexToModel(row), 2) + "//" + model1.getValueAt(ScheduleAppointment.sorter.convertRowIndexToModel(row), 3));
////                                fw.write("\n" + model1.getValueAt(ScheduleAppointment.sorter.convertRowIndexToModel(row), 0) + "//" + model1.getValueAt(ScheduleAppointment.sorter.convertRowIndexToModel(row), 1) + "//" + model1.getValueAt(ScheduleAppointment.sorter.convertRowIndexToModel(row), 2) + "//" + model1.getValueAt(ScheduleAppointment.sorter.convertRowIndexToModel(row), 3));
////
////                                fw.close();
////                            }
////
////                        } catch (Exception ex) {
////                            ex.printStackTrace();
////                        }
////                    }
////                    preAppt preappt = new preAppt();
////
////                });
//                preAppt preappt = new preAppt();
//                patientHome.dispose(); //close screen
//            }
//            if (label.equals("Pre-Appointment")) {
////                ScheduleAppointment.table2.addMouseListener(new java.awt.event.MouseAdapter() {
////                    @Override
////                    public void mouseClicked(java.awt.event.MouseEvent evt1) {
////
////                        try {
////
////                            model1 = ScheduleAppointment.table2.getModel();
////
////                            int row = ScheduleAppointment.table2.rowAtPoint(evt1.getPoint());
////                            int col = ScheduleAppointment.table2.columnAtPoint(evt1.getPoint());
////
////                            System.out.println("ROW: " + row);
////                            System.out.println("COLUMN: " + col);
////
////                            if (row >= 0 && col >= 4) {
////                                File inputFile = new File("apptcr.txt");
////
////                                FileWriter fw = new FileWriter(inputFile.getAbsoluteFile(), true);
////
////                                System.out.println("TEST");
////                                System.out.println(model1.getValueAt(ScheduleAppointment.sorter.convertRowIndexToModel(row), 0) + "//" + model1.getValueAt(ScheduleAppointment.sorter.convertRowIndexToModel(row), 1) + "//" + model1.getValueAt(ScheduleAppointment.sorter.convertRowIndexToModel(row), 2) + "//" + model1.getValueAt(ScheduleAppointment.sorter.convertRowIndexToModel(row), 3));
////                                fw.write("\n" + model1.getValueAt(ScheduleAppointment.sorter.convertRowIndexToModel(row), 0) + "//" + model1.getValueAt(ScheduleAppointment.sorter.convertRowIndexToModel(row), 1) + "//" + model1.getValueAt(ScheduleAppointment.sorter.convertRowIndexToModel(row), 2) + "//" + model1.getValueAt(ScheduleAppointment.sorter.convertRowIndexToModel(row), 3));
////
////                                fw.close();
////                            }
////
////                        } catch (Exception ex) {
////                            ex.printStackTrace();
////                        }
////                    }
////                    preAppt1 preappt = new preAppt1();
////
////                });
//                preAppt1 preappt = new preAppt1();
//                StaffMenuFDN.staffHome.dispose(); //close screen
//            }
//            if (label.equals("Cancel/Reschedule")) {
////                ScheduleAppointment.table2.addMouseListener(new java.awt.event.MouseAdapter() {
////                    @Override
////                    public void mouseClicked(java.awt.event.MouseEvent evt1) {
////
////                        try {
////
////                            model1 = ScheduleAppointment.table2.getModel();
////
////                            int row = ScheduleAppointment.table2.rowAtPoint(evt1.getPoint());
////                            int col = ScheduleAppointment.table2.columnAtPoint(evt1.getPoint());
////
////                            System.out.println("ROW: " + row);
////                            System.out.println("COLUMN: " + col);
////
////                            if (row >= 0 && col >= 4) {
////                                File inputFile = new File("apptcr.txt");
////
////                                FileWriter fw = new FileWriter(inputFile.getAbsoluteFile(), true);
////
////                                System.out.println("TEST");
////                                System.out.println(model1.getValueAt(ScheduleAppointment.sorter.convertRowIndexToModel(row), 0) + "//" + model1.getValueAt(ScheduleAppointment.sorter.convertRowIndexToModel(row), 1) + "//" + model1.getValueAt(ScheduleAppointment.sorter.convertRowIndexToModel(row), 2) + "//" + model1.getValueAt(ScheduleAppointment.sorter.convertRowIndexToModel(row), 3));
////                                fw.write("\n" + model1.getValueAt(ScheduleAppointment.sorter.convertRowIndexToModel(row), 0) + "//" + model1.getValueAt(ScheduleAppointment.sorter.convertRowIndexToModel(row), 1) + "//" + model1.getValueAt(ScheduleAppointment.sorter.convertRowIndexToModel(row), 2) + "//" + model1.getValueAt(ScheduleAppointment.sorter.convertRowIndexToModel(row), 3));
////
////                                fw.close();
////                            }
////
////                        } catch (Exception ex) {
////                            ex.printStackTrace();
////                        }
////                    }
////                    cancelReschedule canres = new cancelReschedule();
////
////                });
//                cancelReschedule canres = new cancelReschedule();
//                patientHome.dispose(); //close screen
//            }

            if (label.equals("Patient Information")) {

                StaffMenuFDN.staffHome.dispose(); //close screen
                patientInfo ptinfo = new patientInfo();

            }
        }

        isPushed = false;
        return label;
    }

    @Override
    public boolean stopCellEditing() {
        isPushed = false;
        return super.stopCellEditing();
    }
}
