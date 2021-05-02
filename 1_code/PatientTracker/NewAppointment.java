/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patienttracker;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;

/**
 *
 * @author siarasaylor
 */
class NewAppointment {

    NewAppointment() {
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

    public static void schedNewAppt() {
        System.out.println("Schedule Appointment");

        //set up items
        JLabel scheduleAppointment = new JLabel("Schedule New Appointment");
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
        patientHome = new JFrame("Patient Tracker - Schedule New Appointment");
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
        JLabel header1 = new JLabel("Schedule New Appointment");
        JLabel header2 = new JLabel("Schedule Appointment");
        JLabel header3 = new JLabel("Available Appointments");

        header1.setFont(new Font("Arial", Font.PLAIN, 30));
        header2.setFont(new Font("Arial", Font.PLAIN, 24));
        header3.setFont(new Font("Arial", Font.PLAIN, 24));

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

        JButton nextBtn = new JButton("Next");
        JButton search = new JButton("Search");
        //image logo
        JLabel img1 = new JLabel(new ImageIcon("logo3.jpg"));

        //horizontal line 
        MyLine horizontal1 = new MyLine();
        MyLine horizontal2 = new MyLine();
        MyLine horizontal3 = new MyLine();

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

        //section2
        horizontal1.setBounds(0, -150, 1300, 800);
        providers.setBounds(150, 160, 80, 20);
        providerS.setBounds(150, 185, providerS.getPreferredSize().width, providerS.getPreferredSize().height);
        network.setBounds(150, 220, 80, 20);
        netWork.setBounds(150, 245, netWork.getPreferredSize().width, netWork.getPreferredSize().height);
        location.setBounds(150, 280, 80, 20);
        locations.setBounds(150, 305, locations.getPreferredSize().width, locations.getPreferredSize().height);
        search.setBounds(400, 350, 100, 20);
        calendar.setBounds(150, 350, 100, 20);
        cal.setBounds(255, 350, cal.getPreferredSize().width, cal.getPreferredSize().height);
        //section3
        horizontal3.setBounds(0, 5, 1300, 800);
        header3.setBounds(125, 405, 450, 40);
        scrollPane.setBounds(150, 445, 1000, 100);

        nextBtn.setBounds(1000, 655, nextBtn.getPreferredSize().width, nextBtn.getPreferredSize().height);

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

        mainPanel2.add(providers);
        mainPanel2.add(providerS);

        mainPanel2.add(network);
        mainPanel2.add(netWork);

        mainPanel2.add(location);
        mainPanel2.add(locations);
        mainPanel2.add(search);
        mainPanel2.add(horizontal3);
        mainPanel2.add(header3);
        mainPanel2.add(scrollPane);
        mainPanel2.add(nextBtn);

        mainPanel2.add(calendar);
        mainPanel2.add(cal);
        //add panels to JFrame
        patientHome.getContentPane().add(BorderLayout.NORTH, patientMenu);
        patientHome.getContentPane().add(BorderLayout.CENTER, mainPanel2);
        patientHome.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        patientHome.setResizable(false);
        patientHome.setVisible(true);

        mainPanel2.setBackground(Color.WHITE);
        
        //
        //action to go to search for appointment times
        search.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                model.addRow(new Object[]{"9:00", "9:45", providerS.getSelectedItem().toString(), locations.getSelectedItem().toString(), false});
                model.addRow(new Object[]{"11:00","11:45", providerS.getSelectedItem().toString(), locations.getSelectedItem().toString(), false});
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
                    for (int i = 0; i < model1.getRowCount() ; i++) {

                        if ((Boolean) model1.getValueAt(i, 4)) {
                            System.out.println(model1.getValueAt(i, 0));
                            System.out.println(model1.getValueAt(i, 1));
                            System.out.println(model1.getValueAt(i, 2));
                            System.out.println(model1.getValueAt(i, 3));
                            fw.write("\n" + model1.getValueAt(i, 0) + "//" + model1.getValueAt(i, 1) + "//" + model1.getValueAt(i, 2) + "//"+ model1.getValueAt(i, 3));

                            
                        }
                    }
                    fw.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                patientHome.dispose(); //close screen
                patientHome.setVisible(false);
                NewAppointment2 newApp = new NewAppointment2();
                
            }
        });
    }
}

//calendar
class DateTextField extends JTextField {

    private static String DEFAULT_DATE_FORMAT = "MM/dd/yyyy";
    private static final int DIALOG_WIDTH = 200;
    private static final int DIALOG_HEIGHT = 200;

    private SimpleDateFormat dateFormat;
    private DatePanel datePanel = null;
    private JDialog dateDialog = null;

    public DateTextField() {
        this(new Date());
    }

    public DateTextField(String dateFormatPattern, Date date) {
        this(date);
        DEFAULT_DATE_FORMAT = dateFormatPattern;
    }

    public DateTextField(Date date) {
        setDate(date);
        setEditable(false);
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        addListeners();
    }

    private void addListeners() {
        addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent paramMouseEvent) {
                if (datePanel == null) {
                    datePanel = new DatePanel();
                }
                Point point = getLocationOnScreen();
                point.y = point.y + 30;
                showDateDialog(datePanel, point);
            }
        });
    }

    private void showDateDialog(DatePanel dateChooser, Point position) {
        Frame owner = (Frame) SwingUtilities
                .getWindowAncestor(DateTextField.this);
        if (dateDialog == null || dateDialog.getOwner() != owner) {
            dateDialog = createDateDialog(owner, dateChooser);
        }
        dateDialog.setLocation(getAppropriateLocation(owner, position));
        dateDialog.setVisible(true);
    }

    private JDialog createDateDialog(Frame owner, JPanel contentPanel) {
        JDialog dialog = new JDialog(owner, "Date Selected", true);
        dialog.setUndecorated(true);
        dialog.getContentPane().add(contentPanel, BorderLayout.CENTER);
        dialog.pack();
        dialog.setSize(DIALOG_WIDTH, DIALOG_HEIGHT);
        return dialog;
    }

    private Point getAppropriateLocation(Frame owner, Point position) {
        Point result = new Point(position);
        Point p = owner.getLocation();
        int offsetX = (position.x + DIALOG_WIDTH) - (p.x + owner.getWidth());
        int offsetY = (position.y + DIALOG_HEIGHT) - (p.y + owner.getHeight());

        if (offsetX > 0) {
            result.x -= offsetX;
        }

        if (offsetY > 0) {
            result.y -= offsetY;
        }

        return result;
    }

    private SimpleDateFormat getDefaultDateFormat() {
        if (dateFormat == null) {
            dateFormat = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
        }
        return dateFormat;
    }

    public void setText(Date date) {
        setDate(date);
    }

    public void setDate(Date date) {
        super.setText(getDefaultDateFormat().format(date));
    }

    public Date getDate() {
        try {
            return getDefaultDateFormat().parse(getText());
        } catch (ParseException e) {
            return new Date();
        }
    }

    private class DatePanel extends JPanel implements ChangeListener {

        int startYear = 1980;
        int lastYear = 2050;

        Color backGroundColor = Color.gray;
        Color palletTableColor = Color.white;
        Color todayBackColor = Color.orange;
        Color weekFontColor = Color.blue;
        Color dateFontColor = Color.black;
        Color weekendFontColor = Color.red;

        Color controlLineColor = Color.pink;
        Color controlTextColor = Color.white;

        JSpinner yearSpin;
        JSpinner monthSpin;
        JButton[][] daysButton = new JButton[6][7];

        DatePanel() {
            setLayout(new BorderLayout());
            setBorder(new LineBorder(backGroundColor, 2));
            setBackground(backGroundColor);

            JPanel topYearAndMonth = createYearAndMonthPanal();
            add(topYearAndMonth, BorderLayout.NORTH);
            JPanel centerWeekAndDay = createWeekAndDayPanal();
            add(centerWeekAndDay, BorderLayout.CENTER);

            reflushWeekAndDay();
        }

        private JPanel createYearAndMonthPanal() {
            Calendar cal = getCalendar();
            int currentYear = cal.get(Calendar.YEAR);
            int currentMonth = cal.get(Calendar.MONTH) + 1;

            JPanel panel = new JPanel();
            panel.setLayout(new FlowLayout());
            panel.setBackground(controlLineColor);

            yearSpin = new JSpinner(new SpinnerNumberModel(currentYear,
                    startYear, lastYear, 1));
            yearSpin.setPreferredSize(new Dimension(56, 20));
            yearSpin.setName("Year");
            yearSpin.setEditor(new JSpinner.NumberEditor(yearSpin, "####"));
            yearSpin.addChangeListener(this);
            panel.add(yearSpin);

            JLabel yearLabel = new JLabel("Year");
            yearLabel.setForeground(controlTextColor);
            panel.add(yearLabel);

            monthSpin = new JSpinner(new SpinnerNumberModel(currentMonth, 1,
                    12, 1));
            monthSpin.setPreferredSize(new Dimension(35, 20));
            monthSpin.setName("Month");
            monthSpin.addChangeListener((ChangeListener) this);
            panel.add(monthSpin);

            JLabel monthLabel = new JLabel("Month");
            monthLabel.setForeground(controlTextColor);
            panel.add(monthLabel);

            return panel;
        }

        private JPanel createWeekAndDayPanal() {
            String colname[] = {"S", "M", "T", "W", "T", "F", "S"};
            JPanel panel = new JPanel();
            panel.setFont(new Font("Arial", Font.PLAIN, 10));
            panel.setLayout(new GridLayout(7, 7));
            panel.setBackground(Color.white);

            for (int i = 0; i < 7; i++) {
                JLabel cell = new JLabel(colname[i]);
                cell.setHorizontalAlignment(JLabel.RIGHT);
                if (i == 0 || i == 6) {
                    cell.setForeground(weekendFontColor);
                } else {
                    cell.setForeground(weekFontColor);
                }
                panel.add(cell);
            }

            int actionCommandId = 0;
            for (int i = 0; i < 6; i++) {
                for (int j = 0; j < 7; j++) {
                    JButton numBtn = new JButton();
                    numBtn.setBorder(null);
                    numBtn.setHorizontalAlignment(SwingConstants.RIGHT);
                    numBtn.setActionCommand(String
                            .valueOf(actionCommandId));
                    numBtn.setBackground(palletTableColor);
                    numBtn.setForeground(dateFontColor);
                    numBtn.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent event) {
                            JButton source = (JButton) event.getSource();
                            if (source.getText().length() == 0) {
                                return;
                            }
                            dayColorUpdate(true);
                            source.setForeground(todayBackColor);
                            int newDay = Integer.parseInt(source.getText());
                            Calendar cal = getCalendar();
                            cal.set(Calendar.DAY_OF_MONTH, newDay);
                            setDate(cal.getTime());

                            dateDialog.setVisible(false);
                        }
                    });

                    if (j == 0 || j == 6) {
                        numBtn.setForeground(weekendFontColor);
                    } else {
                        numBtn.setForeground(dateFontColor);
                    }
                    daysButton[i][j] = numBtn;
                    panel.add(numBtn);
                    actionCommandId++;
                }
            }

            return panel;
        }

        private Calendar getCalendar() {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(getDate());
            return calendar;
        }

        private int getSelectedYear() {
            return ((Integer) yearSpin.getValue()).intValue();
        }

        private int getSelectedMonth() {
            return ((Integer) monthSpin.getValue()).intValue();
        }

        private void dayColorUpdate(boolean isOldDay) {
            Calendar cal = getCalendar();
            int day = cal.get(Calendar.DAY_OF_MONTH);
            cal.set(Calendar.DAY_OF_MONTH, 1);
            int actionCommandId = day - 2 + cal.get(Calendar.DAY_OF_WEEK);
            int i = actionCommandId / 7;
            int j = actionCommandId % 7;
            if (isOldDay) {
                daysButton[i][j].setForeground(dateFontColor);
            } else {
                daysButton[i][j].setForeground(todayBackColor);
            }
        }

        private void reflushWeekAndDay() {
            Calendar cal = getCalendar();
            cal.set(Calendar.DAY_OF_MONTH, 1);
            int maxDayNo = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
            int dayNo = 2 - cal.get(Calendar.DAY_OF_WEEK);
            for (int i = 0; i < 6; i++) {
                for (int j = 0; j < 7; j++) {
                    String s = "";
                    if (dayNo >= 1 && dayNo <= maxDayNo) {
                        s = String.valueOf(dayNo);
                    }
                    daysButton[i][j].setText(s);
                    dayNo++;
                }
            }
            dayColorUpdate(false);
        }

        public void stateChanged(ChangeEvent e) {
            dayColorUpdate(true);

            JSpinner source = (JSpinner) e.getSource();
            Calendar cal = getCalendar();
            if (source.getName().equals("Year")) {
                cal.set(Calendar.YEAR, getSelectedYear());
            } else {
                cal.set(Calendar.MONTH, getSelectedMonth() - 1);
            }
            setDate(cal.getTime());
            reflushWeekAndDay();
        }
    }
}
