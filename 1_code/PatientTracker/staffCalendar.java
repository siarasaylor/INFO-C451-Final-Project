/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patienttracker;

import java.awt.BorderLayout;
import static java.awt.BorderLayout.NORTH;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.ItemEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.Spring;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import static patienttracker.StaffMenuFDN.dstaffMenu;
import static patienttracker.loginWindow.userInput;

/**
 *
 * @author siarasaylor
 */
//CALENDAR CODE Front Desk
class MainFrame extends JFrame {

    public Integer frameWidth, frameHeight;
    public MainPanel mainPanel;
    public CCalendar calendar;
    public CalendarManager manager;

    /**
     * Constructor. Calls the initialization of the frame, calendar and manager.
     */
    public MainFrame() {
        calendar = new CCalendar();
        manager = new CalendarManager();
        initFrame();
    }

    /**
     * Inits the frame.
     */
    private void initFrame() {
        new JFrame();
        setFrameDimension(false);
        setTitle("Patient Calendar");
        //setSize(frameWidth,frameHeight);
        setSize(1300, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(1280, 800));

        // add content to frame
        mainPanel = new MainPanel(MainFrame.this);
        setContentPane(mainPanel);
        setLocationRelativeTo(null);

        // add menubar
        setJMenuBar(getCustomMenuBar());

        setResizable(false);
        pack();
        setVisible(true);
    }

    /**
     * Gets the height of the frame.
     *
     * @return the height of the frame
     */
    public Integer getMainFrameHeight() {
        return frameHeight;
    }

    /**
     * Gets the width of the frame.
     *
     * @return the width of the frame
     */
    public Integer getMainFrameWidth() {
        return frameWidth;
    }

    /**
     * Sets the frame dimension variables.
     *
     * @param resized decides whether the frame dimensions are those of the
     * users screen, or needed to be requested from the frame itself.
     */
    public void setFrameDimension(boolean resized) {
        if (resized) {
            // window is being resized
            Dimension windowSize = getBounds().getSize();
            frameWidth = (int) windowSize.getWidth();
            frameHeight = (int) windowSize.getHeight();
        } else {
            // first time startup
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            frameWidth = (int) screenSize.getWidth();
            frameHeight = (int) screenSize.getHeight();
        }
    }

    /**
     * Draws a menubar in the <code>MainFrame</code>.
     *
     * @return
     */
    private JMenuBar getCustomMenuBar() {

        MenuBar menuBar = new MenuBar();

        return menuBar.menu();
    }
}

class MenuBar extends StaffMenuFDN {

    MenuBar() {
        menu();
    }

    JMenuBar menu() {
        if (loginWindow.userInput.startsWith("1") && loginWindow.userInput.length() > 2) {

            return dstaffMenu;

        }
        if (loginWindow.userInput.startsWith("2") && loginWindow.userInput.length() > 2) {
            return nstaffMenu;

        } else {
            return fdstaffMenu;

        }

    }
}

class MainPanel extends JPanel {

    private static Integer TOP_PANEL_HEIGHT = 75;
    private Integer topPanelWidth;
    private JButton prevMonthButton, nextMonthButton, currentMonthButton;
    private JPanel topPanel, navigationButtonPanel; // for now private
    public CalendarPanel calendarPanel;
    public DayDetailPanel dayDetailPanel;
    public MainFrame mainFrame;
    private JLabel sundayLabel, mondayLabel, tuesdayLabel, wednesdayLabel, thursdayLabel, fridayLabel, saturdayLabel, monthYearLabel;
    public JTextField dateField;

    /**
     * Constructor. Sets the dimensions and content of the main-panel.
     *
     * @param mainFrame is passed to have access to it's methods and variables.
     */
    public MainPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        topPanelWidth = mainFrame.getMainFrameWidth();
        setLayout(null);
        addComponentListener(new resizeListener());
        drawPanels();
    }

    /**
     * Draws the panels that are part of the main-panel.
     */
    private void drawPanels() {
        drawTopPanel();
        drawDayDetailPanel();
        drawCalendarPanel();
    }

    /**
     * Draws the top-panel.
     */
    private void drawTopPanel() {
        topPanel = new JPanel();
        topPanel.setLayout(null);
        topPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.decode("#E2E2E2")));

        // buttons
        prevMonthButton = new JButton("<");
        prevMonthButton.addActionListener(new prevMonthButtonHandler());
        currentMonthButton = new JButton("Today");
        currentMonthButton.addActionListener(new currentMonthButtonHandler());
        nextMonthButton = new JButton(">");
        nextMonthButton.addActionListener(new nextMonthButtonHandler());
        dateField = new JTextField();
        dateField.setHorizontalAlignment(JTextField.CENTER);
        setDateFieldText();
        dateField.addActionListener(new dateFieldHandler());

        navigationButtonPanel = new JPanel();
        navigationButtonPanel.setBackground(Color.WHITE);
        navigationButtonPanel.setLayout(new GridLayout());

        // weekday labels
        sundayLabel = new JLabel("Sun", SwingConstants.RIGHT);
        sundayLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        mondayLabel = new JLabel("Mon", SwingConstants.RIGHT);
        mondayLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        tuesdayLabel = new JLabel("Tue", SwingConstants.RIGHT);
        tuesdayLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        wednesdayLabel = new JLabel("Wed", SwingConstants.RIGHT);
        wednesdayLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        thursdayLabel = new JLabel("Thu", SwingConstants.RIGHT);
        thursdayLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        fridayLabel = new JLabel("Fri", SwingConstants.RIGHT);
        fridayLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        saturdayLabel = new JLabel("Sat", SwingConstants.RIGHT);
        saturdayLabel.setFont(new Font("Arial", Font.PLAIN, 16));

        // draw the active month and year label
        monthYearLabel = new JLabel();
        monthYearLabel.setForeground(Color.decode("#333333"));
        monthYearLabel.setFont(new Font("Arial", Font.PLAIN, 30));
        setMonthYearLabelText();

        topPanel.setBackground(Color.WHITE);
        setTopPanelBounds();

        // add components
        navigationButtonPanel.add(dateField);
        navigationButtonPanel.add(prevMonthButton);
        navigationButtonPanel.add(currentMonthButton);
        navigationButtonPanel.add(nextMonthButton);
        topPanel.add(navigationButtonPanel);

        topPanel.add(sundayLabel);
        topPanel.add(mondayLabel);
        topPanel.add(tuesdayLabel);
        topPanel.add(wednesdayLabel);
        topPanel.add(thursdayLabel);
        topPanel.add(fridayLabel);
        topPanel.add(saturdayLabel);

        topPanel.add(monthYearLabel);
        add(topPanel);
    }

    /**
     * Resizes the panels. Is called by an <code>resizeListener</code>.
     */
    private void resizePanels() {
        resizeTopPanel();
        calendarPanel.resizeCalendarPanel();
        dayDetailPanel.resizeDayDetailPanel();
    }

    /**
     * Updates the top-panel dimensions and sets it's new bounds.
     */
    private void resizeTopPanel() {
        topPanelWidth = (int) (mainFrame.getContentPane().getWidth() * 0.8);
        setTopPanelBounds();
    }

    /**
     * Draws the calendar-panel.
     */
    private void drawCalendarPanel() {
        calendarPanel = new CalendarPanel(MainPanel.this);
        add(calendarPanel);
    }

    /**
     * Draws the day-detail panel.
     */
    private void drawDayDetailPanel() {
        dayDetailPanel = new DayDetailPanel(MainPanel.this);
        add(dayDetailPanel);
    }

    /**
     * Gets the width of the top-panel.
     *
     * @return the width of the top-panel
     */
    public Integer getTopPanelWidth() {
        return topPanelWidth;
    }

    /**
     * Gets the height of the top-panel.
     *
     * @return the height of the top-panel
     */
    public Integer getTopPanelHeight() {
        return TOP_PANEL_HEIGHT;
    }

    /**
     * Sets the top-panel's bounds with the known dimensions.
     */
    private void setTopPanelBounds() {
        topPanel.setBounds(0, 0, topPanelWidth, TOP_PANEL_HEIGHT);
        Integer dayLabelWidth = topPanelWidth / 7;
        sundayLabel.setBounds(-5, 50, dayLabelWidth, 25);
        sundayLabel.setForeground(Color.decode("#BBBBBB"));
        mondayLabel.setBounds(dayLabelWidth - 5, 50, dayLabelWidth, 25);
        tuesdayLabel.setBounds(2 * dayLabelWidth - 5, 50, dayLabelWidth, 25);
        wednesdayLabel.setBounds(3 * dayLabelWidth - 5, 50, dayLabelWidth, 25);
        thursdayLabel.setBounds(4 * dayLabelWidth - 5, 50, dayLabelWidth, 25);
        fridayLabel.setBounds(5 * dayLabelWidth - 5, 50, dayLabelWidth, 25);
        saturdayLabel.setBounds(6 * dayLabelWidth - 5, 50, dayLabelWidth, 25);
        saturdayLabel.setForeground(Color.decode("#BBBBBB"));
        monthYearLabel.setBounds(15, 10, topPanelWidth / 2, 50);
        navigationButtonPanel.setBounds(topPanelWidth - 455, 12, 450, 30);
    }

    /**
     * Sets the text of <code>monthYearLabel</code> to the active month / year.
     */
    public void setMonthYearLabelText() {
        monthYearLabel.setText(mainFrame.calendar.month.getMonthName(mainFrame.calendar.month.getActiveMonth()) + " " + mainFrame.calendar.year.getActiveYear());
    }

    /**
     * Sets the date field with new values.
     */
    public void setDateFieldText() {
        dateField.setText(String.format("%02d", mainFrame.calendar.month.getActiveMonth() + 1) + "/" + String.format("%02d", mainFrame.calendar.day.getActiveDay()) + "/" + mainFrame.calendar.year.getActiveYear());
    }

    /**
     * Inner class. Triggers an resizeListener when the window is resized.
     */
    class resizeListener extends ComponentAdapter {

        /**
         * Sets new frame dimensions when the window is resized.
         *
         * @param e
         */
        public void componentResized(ComponentEvent e) {
            mainFrame.setFrameDimension(true);
            resizePanels();
        }
    }

    /**
     * Inner class. Triggers an actionlistener when previous button is clicked.
     */
    class prevMonthButtonHandler implements ActionListener {

        /**
         * Updates the month to the previous one.
         *
         * @param e
         */
        public void actionPerformed(ActionEvent e) {
            mainFrame.calendar.toPrevMonth();
            setMonthYearLabelText();
            setDateFieldText();
            calendarPanel.monthPanel.redrawMonthPanel();
            dayDetailPanel.redrawDayDetailPanel();
        }
    }

    /**
     * Inner class. Triggers an actionlistener when next button is clicked.
     */
    class nextMonthButtonHandler implements ActionListener {

        /**
         * Updates the month to the next one.
         *
         * @param e
         */
        public void actionPerformed(ActionEvent e) {
            mainFrame.calendar.toNextMonth();
            setMonthYearLabelText();
            setDateFieldText();
            calendarPanel.monthPanel.redrawMonthPanel();
            dayDetailPanel.redrawDayDetailPanel();
        }
    }

    /**
     * Inner class. Triggers an actionlistener when current button is clicked.
     */
    class currentMonthButtonHandler implements ActionListener {

        /**
         * Updates the month to the current one.
         *
         * @param e
         */
        public void actionPerformed(ActionEvent e) {
            mainFrame.calendar.toCurrentMonth();
            setMonthYearLabelText();
            setDateFieldText();
            calendarPanel.monthPanel.redrawMonthPanel();
            dayDetailPanel.redrawDayDetailPanel();
        }
    }

    /**
     * Inner class. Triggers an actionlistener when current datefield is
     * entered.
     */
    class dateFieldHandler implements ActionListener {

        /**
         * Updates the year, month and day to the given one.
         *
         * @param e
         */
        public void actionPerformed(ActionEvent e) {
            String dateInput = dateField.getText();
            if (dateInput.matches("([0-9]{2})/([0-9]{2})/([0-9]{4})")) {
                String[] dateInputs = dateInput.split("/");
                Integer month = Integer.parseInt(dateInputs[0]);
                Integer day = Integer.parseInt(dateInputs[1]);
                Integer year = Integer.parseInt(dateInputs[2]);

                mainFrame.calendar.toDate(month, day, year);
                setMonthYearLabelText();
                calendarPanel.monthPanel.redrawMonthPanel();
                dayDetailPanel.redrawDayDetailPanel();

                validate();
                repaint();
            } else {
                // show message dialog
                JOptionPane.showMessageDialog(null,
                        "The entered date invalid.\n"
                        + "Allowed format: mm/dd/yyyy.",
                        "Invalid date", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}

class CalendarPanel extends JPanel {

    private Integer calendarPanelWidth, calendarPanelHeight;
    private MainFrame mainFrame; // for now private
    public MainPanel mainPanel;
    public MonthPanel monthPanel;
    public CCalendar calendar;

    /**
     * Constructor. Creates an calendar object and inits the calendar-panel.
     *
     * @param mainPanel is passed to have access to it's methods.
     */
    public CalendarPanel(MainPanel mainPanel) {
        mainFrame = mainPanel.mainFrame;
        this.mainPanel = mainPanel;
        setCalendarPanelDimensions();
        initCalendarPanel();
    }

    /**
     * Inits the calendar-panel.
     */
    private void initCalendarPanel() {
        setLayout(null);
        setCalendarPanelBounds();
        drawMonthPanel();
    }

    /**
     * Resizes the panels. Is called by an <code>resizeListener</code> inside
     * <code>MainPanel</code>.
     */
    public void resizeCalendarPanel() {
        setCalendarPanelDimensions();
        setCalendarPanelBounds();
        monthPanel.resizeMonthPanel();
    }

    /**
     * Sets the calendar-panel's dimensions.
     */
    private void setCalendarPanelDimensions() {
        calendarPanelWidth = mainFrame.getMainFrameWidth();
        calendarPanelHeight = mainFrame.getMainFrameHeight() - mainPanel.getTopPanelHeight();
    }

    /**
     * Sets the calendar-panel's bounds with the known dimensions.
     */
    private void setCalendarPanelBounds() {
        setBounds(0, mainPanel.getTopPanelHeight(), calendarPanelWidth, calendarPanelHeight);
    }

    /**
     * Gets the width of the calendar-panel.
     *
     * @return the width of the calendar-panel
     */
    public Integer getCalendarPanelWidth() {
        return calendarPanelWidth;
    }

    /**
     * Gets the height of the calendar panel.
     *
     * @return the height of the calendar panel
     */
    public Integer getCalendarPanelHeight() {
        return calendarPanelHeight;
    }

    /**
     * Draws the month-panel.
     */
    public void drawMonthPanel() {
        monthPanel = new MonthPanel(CalendarPanel.this);
        add(monthPanel);
    }
}

class CWeek {

    private Integer activeWeek, prevWeek, nextWeek, currentWeek; // for now private
    private Calendar cal = Calendar.getInstance();

    /**
     * Contstructor. Sets the global month-variables.
     */
    public CWeek() {
        setWeeks();
    }

    /**
     * Sets the global week-variables.
     */
    private void setWeeks() {
        setCurrentWeek();
        setActiveWeek(currentWeek);
        setPreviousWeek();
        setNextWeek();
    }

    /**
     * Gets the previous week.
     *
     * @return the previous week
     */
    public Integer getPreviousWeek() {
        return prevWeek;
    }

    /**
     * Gets the next week.
     *
     * @return the next week
     */
    public Integer getNextWeek() {
        return nextWeek;
    }

    /**
     * Gets the current week.
     *
     * @return the current week
     */
    public Integer getCurrentWeek() {
        return currentWeek;
    }

    /**
     * Gets the active week.
     *
     * @return the active week
     */
    public Integer getActiveWeek() {
        return activeWeek;
    }

    /**
     * Sets the previous week, based on the current week.
     */
    public void setPreviousWeek() {
        prevWeek = activeWeek - 1;
    }

    /**
     * Sets the next week, based on the active week.
     */
    public void setNextWeek() {
        nextWeek = activeWeek + 1;
    }

    /**
     * Sets the current week.
     */
    public void setCurrentWeek() {
        currentWeek = Calendar.getInstance().get(Calendar.WEEK_OF_YEAR);
    }

    /**
     * Sets the active week.
     *
     * @param week the week that needs to be active
     */
    public void setActiveWeek(Integer week) {
        activeWeek = week;
    }

    /**
     * Returns the weeknumber based on a given date.
     *
     * @param date the date from which its week number needs to be requested
     * @return the weeknumber of the given date
     */
    public Integer getWeekNumber(Date date) {
        cal.setTime(date);
        int week = cal.get(Calendar.WEEK_OF_YEAR);

        return week;
    }

    /**
     * Gets the weekday name.
     *
     * @param date the date the weekday needs to be retrieved
     * @return the weekday as a string
     */
    public String getWeekDayName(Date date) {
        return new SimpleDateFormat("EEEE").format(date);
    }

}

class CalendarManager {

    //private AppointmentDAO appointment = new AppointmentDAO();
    /**
     * Gets all appointments of a given date.
     *
     * @param date the date the appointments needs to be retrieved from
     * @return arraylist of appointments
     */
    public ArrayList<Appointment> getAppointments(Date date) {
        ArrayList<Appointment> appointments = new ArrayList<Appointment>();
        String dateString = new SimpleDateFormat("yyyy-MM-dd").format(date);
        //System.out.println(dateString);
        try {
            BufferedReader br = new BufferedReader(new FileReader("appointments.txt"));
            Scanner s = new Scanner(new File("appointments.txt"));
            Boolean found = false;
            String line = "";

            while ((line = br.readLine()) != null) {

                String in = s.nextLine();
                String[] sArray = in.split("//");
                String day = sArray[5].toString();
                SimpleDateFormat parser = new SimpleDateFormat("EEE MMM d HH:mm:ss zzz yyyy");
                Date date1 = parser.parse(day);
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                String formattedDate = formatter.format(date1);

                String time1 = sArray[6].toString();
                String time2 = sArray[7].toString();

                SimpleDateFormat format = new SimpleDateFormat("HH:mm"); // 12 hour format

                java.util.Date d1 = (java.util.Date) format.parse(time1);

                java.sql.Time timeValue = new java.sql.Time(d1.getTime());

                java.util.Date d2 = (java.util.Date) format.parse(time2);

                java.sql.Time timeValue2 = new java.sql.Time(d2.getTime());

                //System.out.println(formattedDate);
                String apptID = sArray[0].toString();
                int apID = Integer.parseInt(apptID);

//                System.out.println(apID);
//                System.out.println(sArray[2].toString() + " " + sArray[3].toString());
//                System.out.println(sArray[6].toString() + " / " + sArray[12].toString());
//                System.out.println(formattedDate);
//                System.out.println(timeValue);
                int result = date.compareTo(date1);
                //add appointment to list
                if (result == 0) {
                    Appointment appointment = new Appointment(apID, sArray[2].toString() + " " + sArray[3].toString(), sArray[12].toString(), sArray[9].toString() + " / " + sArray[11].toString(), date, timeValue, timeValue2);
                    appointments.add(appointment);
                } else {
                    //System.out.println("SKIP");
                }

            }

        } catch (FileNotFoundException d) {
            JOptionPane.showMessageDialog(null, "User Database Not Found.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (IOException exx) {
            // TODO Auto-generated catch block
            exx.printStackTrace();
        } catch (ParseException ex) {
            Logger.getLogger(CalendarManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        //return appointment.getAppointments(date);
        return appointments;
    }

    /**
     * Inserts a new appointment in the database.
     *
     * @param date the date the appointment needs to be added to
     * @param title the title of the appointment
     * @param location the location of the appointment
     * @param description a description of the appointment
     * @param startTime the starttime of the appointment
     * @param endTime the endtime of the appointment
     * @return boolean
     */
    public boolean addAppointment(Date date, String title, String description, String location, Time startTime, Time endTime) {
        List<Integer> resultIds = new ArrayList<Integer>();
        boolean result = false;

        return result;
//return appointment.addAppointment(date, title, description, location, startTime, endTime);

    }

    /**
     * Deletes an appointment from the database.
     *
     * @param appointmentId the id of the appointment that needs to be removed
     * @return boolean
     */
    public boolean deleteAppointment(Integer appointmentId) {
        boolean result = false;
        //return appointment.deleteAppointment(appointmentId);

        return result;
    }

    /**
     * Edits an appointment from the database.
     *
     * @param appointmentId the id of the appointment that needs to be updated
     * @return boolean
     */
    public boolean editAppointment(Integer appointmentId) {
        boolean result = false;

        return result;
    }
}

class AppointmentPanel extends JPanel {

    private Date date;
    private JFrame appointmentFrame;
    private CalendarPanel calendarPanel;
    private JTextField fnameTextField, lnameTextField, notesTextField; //providerTextField, networkTextField, locationTextField, startTimeTextField, endTimeTextField, notesTextField;
    private Time formattedStartTime, formattedEndTime;
    private JComboBox netWork, providerS, locations, startT;
    private CalendarManager manager = new CalendarManager();
    private String endT;
    static DateTextField cal;
    private JFrame frame2;

    /**
     * Constructor. Sets the global variables and calls the draw method.
     *
     * @param month the month of the clicked daypanel
     * @param day the day of the clicked daypanel
     * @param year the year of the clicked daypanel
     * @param calendarPanel the calendarpanel the clicked daypabel is part of,
     * to have access to its (parents) methods
     */
    public AppointmentPanel(Integer month, Integer day, Integer year, CalendarPanel calendarPanel, JFrame appointmentFrame) {
        this.calendarPanel = calendarPanel;
        this.appointmentFrame = appointmentFrame;
        this.date = calendarPanel.mainPanel.mainFrame.calendar.getDate(month, day, year);

        drawAppointmentPanel();
    }

    /**
     * Draws the appointment panel.
     */
    public void drawAppointmentPanel() {

        frame2 = new JFrame();
        JPanel calPanel2 = new JPanel();
        frame2.setSize(300, 400);
        frame2.setLocationRelativeTo(null);
        calPanel2.setLayout(null);
        //calPanel2.setLayout(new SpringLayout());

        JLabel fname = new JLabel("First Name");
        JLabel lname = new JLabel("Last Name");
        JLabel provider = new JLabel("Provider");
        JLabel network = new JLabel("Network");
        JLabel location = new JLabel("Location");
        JLabel startTime = new JLabel("Start Time");
        JLabel note = new JLabel("Notes");

        JButton saveButton = new JButton("Save");
        saveButton.setPreferredSize(new Dimension(200, 40));
        saveButton.addActionListener(new saveAppointmentHandler());

        fnameTextField = new JTextField();
        lnameTextField = new JTextField();
        notesTextField = new JTextField();
        String[] net = {"Anthem", "CareSource", "Celtic Insurance", "Ambetter"};
        netWork = new JComboBox(net);

        String[] providerNames = {"Dr. K Smith", "Dr. C Jones", "Dr. G Ward"};
        providerS = new JComboBox(providerNames);

        String[] loc = {"Indianapolis", "Washington St", "Fishers", "Avon"};
        locations = new JComboBox(loc);

        String[] st = {"9:00", "11:30", "1:15"};
        startT = new JComboBox(st);

        fname.setBounds(50, 55, 80, 20);
        fnameTextField.setBounds(150, 55, 100, 20);

        lname.setBounds(50, 85, 80, 20);
        lnameTextField.setBounds(150, 85, 100, 20);

        provider.setBounds(50, 115, 80, 20);
        providerS.setBounds(150, 115, providerS.getPreferredSize().width, providerS.getPreferredSize().height);

        network.setBounds(50, 145, 80, 20);
        netWork.setBounds(150, 145, netWork.getPreferredSize().width, netWork.getPreferredSize().height);

        location.setBounds(50, 175, 80, 20);
        locations.setBounds(150, 175, locations.getPreferredSize().width, locations.getPreferredSize().height);

        startTime.setBounds(50, 205, 80, 20);
        startT.setBounds(150, 205, startT.getPreferredSize().width, startT.getPreferredSize().height);

        note.setBounds(50, 265, 80, 20);
        notesTextField.setBounds(100, 265, 150, 60);

        saveButton.setBounds(100, 350, 100, 20);

        calPanel2.add(fname);
        calPanel2.add(fnameTextField);

        calPanel2.add(lname);
        calPanel2.add(lnameTextField);

        calPanel2.add(provider);
        calPanel2.add(providerS);

        calPanel2.add(network);
        calPanel2.add(netWork);

        calPanel2.add(location);
        calPanel2.add(locations);

        calPanel2.add(startTime);
        calPanel2.add(startT);

        calPanel2.add(note);
        calPanel2.add(notesTextField);

        calPanel2.add(saveButton);

        //
        frame2.getContentPane().add(BorderLayout.CENTER, calPanel2);

        frame2.setResizable(false);
        frame2.setVisible(true);
//        setLayout(new SpringLayout());
//        String[] labels = {"First Name", "Last Name", "Provider", "Network", "Location", "Start time", "End time", "Notes", ""};
//        int numPairs = labels.length;
//
//        JButton saveButton = new JButton("Save");
//        saveButton.setPreferredSize(new Dimension(200, 40));
//        saveButton.addActionListener(new saveAppointmentHandler());
//
//        ArrayList<JTextField> textFieldList = listTextFields();
//
//        // fill the panel
//        for (int i = 0; i < numPairs; i++) {
//            JLabel l = new JLabel(labels[i], JLabel.TRAILING);
//            add(l);
//            if (i + 1 < numPairs) {
//                add(textFieldList.get(i));
//            } else {
//                add(saveButton);
//            }
//        }
//
//        // lay out the panel
//        SpringUtilities.makeCompactGrid(this,
//                numPairs, 2, //rows, cols
//                10, 10, //initX, initY
//                10, 10 //xPad, yPad
//        );
    }

    /**
     * List the textfields for use with the for-loop in
     * <code>drawAppointments</code>.
     *
     * @return ArrayList of textfields
     */
//    private ArrayList<JTextField> listTextFields() {
//        ArrayList<JTextField> textFieldList = new ArrayList<>();
//        textFieldList.add(fnameTextField = new JTextField());
//        textFieldList.add(lnameTextField = new JTextField());
//        textFieldList.add(providerTextField = new JTextField());
//        textFieldList.add(networkTextField = new JTextField());
//        textFieldList.add(locationTextField = new JTextField());
//        textFieldList.add(startTimeTextField = new JTextField());
//        textFieldList.add(endTimeTextField = new JTextField());
//        textFieldList.add(notesTextField = new JTextField());
//
//        return textFieldList;
//    }
    /**
     * Shows an message dialog when the name of an event isn't filled in.
     */
    private void showNameError() {
        JOptionPane.showMessageDialog(null, "The name of the event must be filled in.", "Invalid name", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Show an message dialog when the filled in times aren't valid.
     */
    private void showTimeError() {
        JOptionPane.showMessageDialog(null,
                "The start time or end time are invalid.\n"
                + "Allowed format: (00 through 23) : (00 through 59).\n"
                + "End time must be greater than start time.", "Invalid times",
                JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Shows an message dialog when an event is succesfully added.
     *
     * @param name the name of the event.
     */
    private void showSuccesMessage(String name) {
        JOptionPane.showMessageDialog(null, "Your event \"" + name + "\" is succesfully added.", "Event added", JOptionPane.PLAIN_MESSAGE);
        
    }

    /**
     * Sets the global formatted time variables, based on a time string
     *
     * @param time 4 digit time as a string
     * @param timeType 0 or 1; startTime or endTime
     * @return true or false; validated and setted or not
     */
    private Boolean setFormattedTime(String time, Integer timeType) {
        Boolean validated = true;
        Time formattedTime = new Time(new Date().getTime());

        // format time
        DateFormat formatter = new SimpleDateFormat("HH:mm");

        try {
            new SimpleDateFormat("HH:mm").parse(time);
            // good format
            formattedTime = new Time(formatter.parse(time).getTime());
        } catch (ParseException e) {
            // bad format
            validated = false;
        } finally {
            if (validated) {
                if (timeType == 0) {
                    // start time
                    formattedStartTime = formattedTime;
                } else if (timeType == 1) {
                    // end time
                    formattedEndTime = formattedTime;
                }
            }
        }

        return validated;
    }

    /**
     * Inner class. Triggers an actionlistener when the
     * <code>addAppointmentButton</code> is clicked.
     */
    class saveAppointmentHandler implements ActionListener {

        /**
         * Opens new frame where a new appointment can be added.
         *
         * @param e
         */
        public void actionPerformed(ActionEvent e) {
            Boolean validName = true;
            Boolean validTimes = true;
            updateTextBox();
            frame2.dispose();
            // get values
            String fname = fnameTextField.getText();
            String lname = lnameTextField.getText();
            String provider = providerS.getSelectedItem().toString();
            String network = netWork.getSelectedItem().toString();
            String notes = notesTextField.getText();
            String location = locations.getSelectedItem().toString();
            String startTime = startT.getSelectedItem().toString().replaceAll("\\s+", ""); // remove whitespace
            String endTime = endT.replaceAll("\\s+", ""); // remove whitespace

            // fields to null of not filled in
            if (notes.isEmpty()) {
                notes = null;
            }
            if (location.isEmpty()) {
                location = null;
            }
            if (provider.isEmpty()) {
                provider = null;
            }
            if (network.isEmpty()) {
                network = null;
            }
            // validate name
            if (fname == null || fname.isEmpty()) {
                validName = false;
            }// validate name
            if (lname == null || lname.isEmpty()) {
                validName = false;
            }
            // validate times
            if (!setFormattedTime(startTime, 0) || !setFormattedTime(endT, 1)) {
                validTimes = false;
            }
            if (validTimes) {
                // is end time greater then start time
                if (Integer.parseInt(startTime.replaceAll("[^\\d]", "")) > Integer.parseInt(endT.replaceAll("[^\\d]", ""))) {
                    validTimes = false;
                }
            }

            if (validName && validTimes) {
                // add appointment
                manager.addAppointment(date, fname, notes, location, formattedStartTime, formattedEndTime);
                // close frame
                appointmentFrame.setVisible(false);
                appointmentFrame.dispose();
                // repaint panels and show succes message
                calendarPanel.monthPanel.redrawMonthPanel();
                showSuccesMessage(fname);
            } else {
                // show errors
                if (!validName) {
                    showNameError();
                }
                if (!validTimes) {
                    showTimeError();
                }
            }
            String filePath = "userPass.txt";
            BufferedReader br;
            String kind = "Preventative";
            String type = "In person";
            String userID = "";

            String line = "";
            try {
                br = new BufferedReader(new FileReader(filePath));
                Scanner in = new Scanner(new File("userPass.txt"));
                boolean found = false;
                while ((line = br.readLine()) != null) {

                    //System.out.println(line);
                    String[] words = line.split("//");

                    for (String word : words) {
                        if (word.equals(fname)) {
                            System.out.println("Found: " + fname);
                            System.out.println(words[2]);
                            found = true;
                        }
                        if (word.equals(lname)) {
                            System.out.println("Found: " + lname);
                            System.out.println(words[4]);
                            found = true;
                            userID = words[0].toString();
                            System.out.println(userID);
                            break;
                        }

                    }
                }

                File inputFile = new File("appointments.txt");
                Random rand = new Random();
                int upperbound = 100000;
                int int_random = rand.nextInt(upperbound);
                FileWriter fw = new FileWriter(inputFile.getAbsoluteFile(), true);
                // BufferedWriter bw = new BufferedWriter(new FileWriter("userPass.txt"));
                fw.write("\n" + int_random + "//" + userID + "//" + fname + "//" + lname + "//" + network + "//"
                        + date + "//" + startTime + "//" + endTime + "//" + provider + "//" + location + "//" + kind + "//" + type + "//" + notes);

                fw.close();

                //appt.printAppointments(appt);
                in.close();
            } catch (FileNotFoundException d) {
                JOptionPane.showMessageDialog(null, "User Database Not Found.", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (IOException exx) {
                // TODO Auto-generated catch block
                exx.printStackTrace();
            }
        }

        public void updateTextBox() {
            if (startT.getSelectedItem().toString().equals("9:00")) {
                endT = "9:45";
            } else if (startT.getSelectedItem().toString().equals("11:30")) {
                endT = "12:15";
            } else {
                endT = "2:00";
            }

        }
    }
}

class SpringUtilities {

    /**
     * A debugging utility that prints to stdout the component's minimum,
     * preferred, and maximum sizes.
     */
    public static void printSizes(Component c) {
        System.out.println("minimumSize = " + c.getMinimumSize());
        System.out.println("preferredSize = " + c.getPreferredSize());
        System.out.println("maximumSize = " + c.getMaximumSize());
    }

    /**
     * Aligns the first <code>rows</code> * <code>cols</code> components of
     * <code>parent</code> in a grid. Each component is as big as the maximum
     * preferred width and height of the components. The parent is made just big
     * enough to fit them all.
     *
     * @param rows number of rows
     * @param cols number of columns
     * @param initialX x location to start the grid at
     * @param initialY y location to start the grid at
     * @param xPad x padding between cells
     * @param yPad y padding between cells
     */
    public static void makeGrid(Container parent,
            int rows, int cols,
            int initialX, int initialY,
            int xPad, int yPad) {
        SpringLayout layout;
        try {
            layout = (SpringLayout) parent.getLayout();
        } catch (ClassCastException exc) {
            System.err.println("The first argument to makeGrid must use SpringLayout.");
            return;
        }

        Spring xPadSpring = Spring.constant(xPad);
        Spring yPadSpring = Spring.constant(yPad);
        Spring initialXSpring = Spring.constant(initialX);
        Spring initialYSpring = Spring.constant(initialY);
        int max = rows * cols;

        //Calculate Springs that are the max of the width/height so that all
        //cells have the same size.
        Spring maxWidthSpring = layout.getConstraints(parent.getComponent(0)).
                getWidth();
        Spring maxHeightSpring = layout.getConstraints(parent.getComponent(0)).
                getHeight();
        for (int i = 1; i < max; i++) {
            SpringLayout.Constraints cons = layout.getConstraints(
                    parent.getComponent(i));

            maxWidthSpring = Spring.max(maxWidthSpring, cons.getWidth());
            maxHeightSpring = Spring.max(maxHeightSpring, cons.getHeight());
        }

        //Apply the new width/height Spring. This forces all the
        //components to have the same size.
        for (int i = 0; i < max; i++) {
            SpringLayout.Constraints cons = layout.getConstraints(
                    parent.getComponent(i));

            cons.setWidth(maxWidthSpring);
            cons.setHeight(maxHeightSpring);
        }

        //Then adjust the x/y constraints of all the cells so that they
        //are aligned in a grid.
        SpringLayout.Constraints lastCons = null;
        SpringLayout.Constraints lastRowCons = null;
        for (int i = 0; i < max; i++) {
            SpringLayout.Constraints cons = layout.getConstraints(
                    parent.getComponent(i));
            if (i % cols == 0) { //start of new row
                lastRowCons = lastCons;
                cons.setX(initialXSpring);
            } else { //x position depends on previous component
                cons.setX(Spring.sum(lastCons.getConstraint(SpringLayout.EAST),
                        xPadSpring));
            }

            if (i / cols == 0) { //first row
                cons.setY(initialYSpring);
            } else { //y position depends on previous row
                cons.setY(Spring.sum(lastRowCons.getConstraint(SpringLayout.SOUTH),
                        yPadSpring));
            }
            lastCons = cons;
        }

        //Set the parent's size.
        SpringLayout.Constraints pCons = layout.getConstraints(parent);
        pCons.setConstraint(SpringLayout.SOUTH,
                Spring.sum(
                        Spring.constant(yPad),
                        lastCons.getConstraint(SpringLayout.SOUTH)));
        pCons.setConstraint(SpringLayout.EAST,
                Spring.sum(
                        Spring.constant(xPad),
                        lastCons.getConstraint(SpringLayout.EAST)));
    }

    /* Used by makeCompactGrid. */
    private static SpringLayout.Constraints getConstraintsForCell(
            int row, int col,
            Container parent,
            int cols) {
        SpringLayout layout = (SpringLayout) parent.getLayout();
        Component c = parent.getComponent(row * cols + col);
        return layout.getConstraints(c);
    }

    /**
     * Aligns the first <code>rows</code> * <code>cols</code> components of
     * <code>parent</code> in a grid. Each component in a column is as wide as
     * the maximum preferred width of the components in that column; height is
     * similarly determined for each row. The parent is made just big enough to
     * fit them all.
     *
     * @param rows number of rows
     * @param cols number of columns
     * @param initialX x location to start the grid at
     * @param initialY y location to start the grid at
     * @param xPad x padding between cells
     * @param yPad y padding between cells
     */
    public static void makeCompactGrid(Container parent,
            int rows, int cols,
            int initialX, int initialY,
            int xPad, int yPad) {
        SpringLayout layout;
        try {
            layout = (SpringLayout) parent.getLayout();
        } catch (ClassCastException exc) {
            System.err.println("The first argument to makeCompactGrid must use SpringLayout.");
            return;
        }

        //Align all cells in each column and make them the same width.
        Spring x = Spring.constant(initialX);
        for (int c = 0; c < cols; c++) {
            Spring width = Spring.constant(0);
            for (int r = 0; r < rows; r++) {
                width = Spring.max(width,
                        getConstraintsForCell(r, c, parent, cols).
                                getWidth());
            }
            for (int r = 0; r < rows; r++) {
                SpringLayout.Constraints constraints
                        = getConstraintsForCell(r, c, parent, cols);
                constraints.setX(x);
                constraints.setWidth(width);
            }
            x = Spring.sum(x, Spring.sum(width, Spring.constant(xPad)));
        }

        //Align all cells in each row and make them the same height.
        Spring y = Spring.constant(initialY);
        for (int r = 0; r < rows; r++) {
            Spring height = Spring.constant(0);
            for (int c = 0; c < cols; c++) {
                height = Spring.max(height,
                        getConstraintsForCell(r, c, parent, cols).
                                getHeight());
            }
            for (int c = 0; c < cols; c++) {
                SpringLayout.Constraints constraints
                        = getConstraintsForCell(r, c, parent, cols);
                constraints.setY(y);
                constraints.setHeight(height);
            }
            y = Spring.sum(y, Spring.sum(height, Spring.constant(yPad)));
        }

        //Set the parent's size.
        SpringLayout.Constraints pCons = layout.getConstraints(parent);
        pCons.setConstraint(SpringLayout.SOUTH, y);
        pCons.setConstraint(SpringLayout.EAST, x);
    }
}

class Appointment {

    public Integer appointmentId;
    public String title, description, location;
    private Date date;
    public Time startTime, endTime;

    /**
     * Constructor. Sets the given variables/
     */
    public Appointment(Integer appointmentId, String title, String description, String location, Date date, Time startTime, Time endTime) {
        this.appointmentId = appointmentId;
        this.title = title;
        this.description = description;
        this.location = location;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }

}

class MonthPanel extends JPanel {

    private static Integer DAYS_IN_MONTH_PANEL = 42;
    private Integer monthPanelWidth, monthPanelHeight;
    private CalendarPanel calendarPanel;
    private ArrayList<DayPanel> dayPanelList = new ArrayList<DayPanel>();

    /**
     * Constructor. Sets the dimensions and content of the month-panel.
     *
     * @param calendarPanel is passed to have access to it's methods and
     * variables.
     */
    public MonthPanel(CalendarPanel calendarPanel) {
        this.calendarPanel = calendarPanel;
        setMonthPanelDimensions();
        setDayPanelList();
        initMonthPanel();
    }

    /**
     * Redraws the month-panel
     */
    public void redrawMonthPanel() {
        emptyDayPanelList();
        setDayPanelList();
        removeAll();
        initDayPanels();

        validate();
        repaint();
    }

    /**
     * Emptys the day panel list
     */
    public void emptyDayPanelList() {
        dayPanelList.clear();
    }

    /**
     * Inits the month-panel.
     */
    public void initMonthPanel() {
        setLayout(new GridLayout(6, 7));
        setBackground(Color.decode("#EEEEEE"));
        setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.decode("#E2E2E2")));
        setMonthPanelBounds();
        initDayPanels();
    }

    /**
     * Resizes the mont-panel. Is called by an <code>resizeListener</code>
     * inside <code>MainPanel</code>.
     */
    public void resizeMonthPanel() {
        setMonthPanelDimensions();
        setMonthPanelBounds();
    }

    /**
     * Sets the month-panel's dimensions.
     */
    private void setMonthPanelDimensions() {
        monthPanelWidth = (int) (calendarPanel.getCalendarPanelWidth() * 0.8);
        monthPanelHeight = calendarPanel.mainPanel.mainFrame.getContentPane().getHeight() - calendarPanel.mainPanel.getTopPanelHeight();
    }

    /**
     * Sets the month-panel's bounds with the known dimensions.
     */
    private void setMonthPanelBounds() {
        setBounds(0, 0, monthPanelWidth, monthPanelHeight);
    }

    /**
     * Gets the width of the month-panel.
     *
     * @return the width of the month-panel
     */
    public Integer getMonthPanelWidth() {
        return monthPanelWidth;
    }

    /**
     * Gets the height of the month-panel.
     *
     * @return the height of the month-panel
     */
    public Integer getMonthPanelHeight() {
        return monthPanelHeight;
    }

    /**
     * Generates a list of days that needs to be displayed in the mont-panel.
     * May include some days of the previous and the next month.
     */
    public void setDayPanelList() {

        // get first weekday of current month
        Integer firstWeekDay = calendarPanel.mainPanel.mainFrame.calendar.month.getFirstWeekDay(
                calendarPanel.mainPanel.mainFrame.calendar.month.getActiveMonth(),
                calendarPanel.mainPanel.mainFrame.calendar.year.getActiveYear()
        );

        // get the number of days to be pre-added, based on the first weekday of the current month
        Integer previousCount = getDaysBefore(firstWeekDay);
        // get the number of days in the active month
        Integer mainCount = calendarPanel.mainPanel.mainFrame.calendar.month.getDayCount(
                calendarPanel.mainPanel.mainFrame.calendar.month.getActiveMonth(),
                calendarPanel.mainPanel.mainFrame.calendar.year.getActiveYear()
        );
        // get the rest number of days to be added
        Integer restCount = getDaysAfter(previousCount, mainCount);

        // get the number of days in the previous month
        if (previousCount > 0) {
            Integer year = calendarPanel.mainPanel.mainFrame.calendar.year.getActiveYear();
            Integer month = calendarPanel.mainPanel.mainFrame.calendar.month.getPreviousMonth();
            Integer daysInPreviousMonth = 0;

            if (calendarPanel.mainPanel.mainFrame.calendar.month.getActiveMonth() == 0) {
                // active month is januari, previous month has also a previous year
                year = calendarPanel.mainPanel.mainFrame.calendar.year.getPreviousYear();
                daysInPreviousMonth = calendarPanel.mainPanel.mainFrame.calendar.month.getDayCount(month, year);
            } else {
                daysInPreviousMonth = calendarPanel.mainPanel.mainFrame.calendar.month.getDayCount(month, year);
            }

            // add days of previous month in arraylist
            Integer index = 1;
            for (int day = daysInPreviousMonth - previousCount + 1; day <= daysInPreviousMonth; day++) {
                dayPanelList.add(new DayPanel(day, month, year, calendarPanel, index));
                index++;
            }
        }

        // add days of active month in arraylist
        for (int day = 1; day <= mainCount; day++) {
            dayPanelList.add(new DayPanel(
                    day,
                    calendarPanel.mainPanel.mainFrame.calendar.month.getActiveMonth(),
                    calendarPanel.mainPanel.mainFrame.calendar.year.getActiveYear(),
                    calendarPanel,
                    previousCount + day
            ));
        }

        // add days of rest month in arraylist
        if (restCount > 0) {
            Integer year = calendarPanel.mainPanel.mainFrame.calendar.year.getActiveYear();
            Integer month = calendarPanel.mainPanel.mainFrame.calendar.month.getNextMonth();
            if (calendarPanel.mainPanel.mainFrame.calendar.month.getActiveMonth() == 11) {
                // active month is december, next month has also a next year
                year = calendarPanel.mainPanel.mainFrame.calendar.year.getNextYear();
            }
            for (int day = 1; day <= restCount; day++) {
                dayPanelList.add(new DayPanel(day, month, year, calendarPanel, previousCount + mainCount + day));
            }
        }
    }

    /**
     * Returns the daypanels of the month-panel view
     *
     * @return list of daypanels
     */
    private ArrayList<DayPanel> getDayPanels() {
        return dayPanelList;
    }

    /**
     * Places every daypanel listed in the daypanel arralist in the monthpanel
     */
    private void initDayPanels() {
        Integer listSize = dayPanelList.size();
        for (int i = 0; i < listSize; i++) {
            add(dayPanelList.get(i));
        }
    }

    /**
     * Calculates the number of days in the previous month that need to be
     * pre-added in the <code>dayPanelList</code>
     *
     * @oaram the start weekday of the current month
     * @return the number of days to be pre-added
     */
    private Integer getDaysBefore(Integer firstWeekDay) {
        return firstWeekDay - 1;
    }

    /**
     * Calculates the number of days in the next month that need to be added in
     * the <code>dayPanelList</code>
     *
     * @param previousMonthDays the number of days of the previous month
     * @param currentMonthDays the number of days of the current month
     * @return the number of days to be added
     */
    private Integer getDaysAfter(Integer previousMonthDays, Integer currentMonthDays) {
        return DAYS_IN_MONTH_PANEL - previousMonthDays - currentMonthDays;
    }

}

class DayPanel extends JPanel {

    private CalendarPanel calendarPanel;
    public Integer day, month, year, index;
    private JButton viewAppointmentsButton, addAppointmentsButton;
    public ArrayList<Appointment> appointments;
    private Date date;
    private CalendarManager manager = new CalendarManager();
    private Integer appointmentsCount;

    /**
     * Constructor. Initializes a day-panel.
     *
     * @param day the day number
     * @param month the month number
     * @param year the year number
     * @param calendarPanel the calendarPanel it is part of
     * @param index the panel's index inside the month-panel
     */
    public DayPanel(Integer day, Integer month, Integer year, CalendarPanel calendarPanel, Integer index) {
        this.day = day;
        this.month = month;
        this.year = year;
        this.calendarPanel = calendarPanel;
        this.index = index;
        date = calendarPanel.mainPanel.mainFrame.calendar.getDate(month, day, year);
        appointments = manager.getAppointments(date);
        appointmentsCount = appointments.size();

        drawDayPanel();
    }

    /**
     * Draws the day panel.
     */
    private void drawDayPanel() {
        setLayout(new GridLayout(3, 1));
        setBackgroundColor();
        setBorder();

        JPanel dayTopPanel = new JPanel();
        dayTopPanel.setBackground(new Color(0, 0, 0, 0));
        dayTopPanel.setLayout(new GridLayout(1, 3));

        // week number
        if (index % 7 == 1) {
            // first day of the week, show the weeknumber
            dayTopPanel.add(getStyledWeekNumber(getWeekNumber()));
        } else {
            dayTopPanel.add(new JLabel(""));
        }

        if (appointmentsCount > 0) {
            JLabel appointmentsCountLabel = new JLabel(appointmentsCount.toString());
            appointmentsCountLabel.setFont(new Font("Arial", Font.BOLD, 16));
            appointmentsCountLabel.setHorizontalAlignment(SwingConstants.CENTER);
            appointmentsCountLabel.setForeground(Color.WHITE);
            appointmentsCountLabel.setBackground(new Color(0, 0, 0, 50));
            appointmentsCountLabel.setOpaque(true);
            dayTopPanel.add(appointmentsCountLabel);
        } else {
            dayTopPanel.add(new JLabel(""));
        }

        // day number
        dayTopPanel.add(getStyledDayNumber(getDayNumber()));

        viewAppointmentsButton = new JButton("View");
        viewAppointmentsButton.addActionListener(new viewAppointmentsButtonHandler());
        addAppointmentsButton = new JButton("Add Appointment");
        addAppointmentsButton.addActionListener(new addAppointmentsButtonHandler());
        viewAppointmentsButton.setOpaque(false);
        addAppointmentsButton.setOpaque(false);

        add(dayTopPanel);
        add(addAppointmentsButton);
        add(viewAppointmentsButton);
    }

    /**
     * Returns a color code based on the appointments counts.
     *
     * @param appointmentsCount the total appointments of a day
     * @return the color code
     */
    private String getAppointmentsBackground(Integer appointmentsCount) {
        String backgroundHash = "#FFB312";
        if (appointmentsCount > 5) {
            backgroundHash = "#FF6600";
            if (appointmentsCount > 10) {
                backgroundHash = "#EE3230";
            }
        }

        return backgroundHash;
    }

    /**
     * Sets the background color based on the week day.
     */
    public void setBackgroundColor() {
        setBackground(Color.WHITE);

        // weekend day
        if (index % 7 == 0 || index % 7 == 1) {
            setBackground(Color.decode("#EEEEEE"));
        }

        // current day
        if (month == calendarPanel.mainPanel.mainFrame.calendar.month.getCurrentMonth()
                && day == calendarPanel.mainPanel.mainFrame.calendar.day.getCurrentDay()
                && year == calendarPanel.mainPanel.mainFrame.calendar.year.getCurrentYear()) {
            setBackground(Color.decode("#1DA04A"));
        }

        // appointments
        if (appointmentsCount > 0) {
            setBackground(Color.decode(getAppointmentsBackground(appointmentsCount)));
        }

        // active day
        if (month == calendarPanel.mainPanel.mainFrame.calendar.month.getActiveMonth()
                && day == calendarPanel.mainPanel.mainFrame.calendar.day.getActiveDay()
                && year == calendarPanel.mainPanel.mainFrame.calendar.year.getActiveYear()) {
            setBackground(Color.decode("#2E78F2"));
        }

        setOpaque(true);
    }

    /**
     * Gets the day number.
     *
     * @return the day number
     */
    public Integer getDayNumber() {
        return day;
    }

    /**
     * Styles the day number based on the month it is part of.
     *
     * @param dayNumber the day number that needs to be styled
     * @return the styled day number as a label
     */
    private JLabel getStyledDayNumber(Integer dayNumber) {
        JLabel dayLabel = new JLabel(this.day.toString());
        dayLabel.setBorder(new EmptyBorder(10, 10, 10, 10));
        dayLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        dayLabel.setHorizontalAlignment(SwingConstants.RIGHT);

        // days that aren't in the active month
        if (month != calendarPanel.mainPanel.mainFrame.calendar.month.getActiveMonth()) {
            dayLabel.setForeground(Color.decode("#B5B5B5"));
        }

        // current day, active day and days with appointments
        if (month == calendarPanel.mainPanel.mainFrame.calendar.month.getCurrentMonth()
                && day == calendarPanel.mainPanel.mainFrame.calendar.day.getCurrentDay()
                && year == calendarPanel.mainPanel.mainFrame.calendar.year.getCurrentYear()
                || month == calendarPanel.mainPanel.mainFrame.calendar.month.getActiveMonth()
                && day == calendarPanel.mainPanel.mainFrame.calendar.day.getActiveDay()
                && year == calendarPanel.mainPanel.mainFrame.calendar.year.getActiveYear()
                || appointmentsCount > 0) {
            dayLabel.setForeground(Color.WHITE);
        }

        return dayLabel;
    }

    /**
     * Gets the week number the current day is part of.
     *
     * @return the week number
     */
    public Integer getWeekNumber() {
        return calendarPanel.mainPanel.mainFrame.calendar.week.getWeekNumber(calendarPanel.mainPanel.mainFrame.calendar.getDate(month, day, year));
    }

    /**
     * Styles the week number based on the week it is part of.
     *
     * @param weekNumber the week number that needs to be styled
     * @return the styles week number as a label
     */
    private JLabel getStyledWeekNumber(Integer weekNumber) {
        JLabel weekLabel = new JLabel(weekNumber.toString());
        weekLabel.setBorder(new EmptyBorder(10, 10, 10, 10));
        weekLabel.setFont(new Font("Arial", Font.BOLD, 16));
        weekLabel.setHorizontalAlignment(SwingConstants.LEFT);
        weekLabel.setForeground(Color.decode("#EF4A4A"));

        // styling if has appointments
        if (!appointments.isEmpty()) {
            weekLabel.setForeground(Color.WHITE);
        }

        // current day styling
        if (month == calendarPanel.mainPanel.mainFrame.calendar.month.getCurrentMonth()
                && day == calendarPanel.mainPanel.mainFrame.calendar.day.getCurrentDay()
                && year == calendarPanel.mainPanel.mainFrame.calendar.year.getCurrentYear()) {
            weekLabel.setForeground(Color.WHITE);
        }

        // active day styling
        if (month == calendarPanel.mainPanel.mainFrame.calendar.month.getActiveMonth()
                && day == calendarPanel.mainPanel.mainFrame.calendar.day.getActiveDay()
                && year == calendarPanel.mainPanel.mainFrame.calendar.year.getActiveYear()) {
            weekLabel.setForeground(Color.WHITE);
        }

        return weekLabel;
    }

    /**
     * Sets the border of the daypanel.
     */
    public void setBorder() {
        setBorder(BorderFactory.createMatteBorder(1, 1, 0, 0, Color.decode("#E2E2E2")));
        if (index % 7 == 1) {
            setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.decode("#E2E2E2")));
        }
    }

    /**
     * Inner class. Triggers an actionlistener when the
     * <code>viewAppointmentsButton</code> is clicked.
     */
    class viewAppointmentsButtonHandler implements ActionListener {

        /**
         * Updates the active date to the daypanel's one.
         *
         * @param e
         */
        public void actionPerformed(ActionEvent e) {
            // update active date
            calendarPanel.mainPanel.mainFrame.calendar.toDate(month + 1, day, year);

            // redraw panels
            calendarPanel.mainPanel.setMonthYearLabelText();
            calendarPanel.mainPanel.setDateFieldText();
            calendarPanel.monthPanel.redrawMonthPanel();
            calendarPanel.mainPanel.dayDetailPanel.redrawDayDetailPanel();
        }
    }

    /**
     * Inner class. Triggers an actionlistener when the
     * <code>addAppointmentButton</code> is clicked.
     */
    class addAppointmentsButtonHandler implements ActionListener {

        /**
         * Opens new frame where a new appointment can be added.
         *
         * @param e
         */
        public void actionPerformed(ActionEvent e) {
            Integer offsetX = getLocationOnScreen().x;
            Integer offsetY = getLocationOnScreen().y;

            new AppointmentFrame(month, day, year, calendarPanel, offsetX, offsetY);
        }
    }
}

class DayDetailPanel extends JPanel {

    private Integer day, month, year;
    private Integer dayDetailPanelWidth, dayDetailPanelHeight;
    private MainPanel mainPanel;
    private CalendarManager manager = new CalendarManager();
    private ArrayList<Appointment> appointments;
    private JScrollPane scrollPane;
    private Date date;
    static DateTextField cal;
    private JFrame appointmentFrame;
    private CalendarPanel calendarPanel;
    private JTextField fnameTextField, lnameTextField, providerTextField, networkTextField, locationTextField, startTimeTextField, endTimeTextField, notesTextField, dateTextField;
    private Time formattedStartTime, formattedEndTime;
    static java.sql.Time timeValue;
    static java.sql.Time timeValue2;
    static String original = "";
    static String replaceData = "";
    static JComboBox startT = null;
    static String endT;
    public Integer appointmentId;
    private String appointmentName;

    /**
     * Constructor. Creates an calendar object and inits the calendar-panel.
     */
    public DayDetailPanel(MainPanel mainPanel) {
        this.mainPanel = mainPanel;
        month = mainPanel.mainFrame.calendar.month.getActiveMonth();
        day = mainPanel.mainFrame.calendar.day.getActiveDay();
        year = mainPanel.mainFrame.calendar.year.getActiveYear();
        appointments = manager.getAppointments(mainPanel.mainFrame.calendar.getDate(month, day, year));

        drawDayDetailPanel();
    }

    /**
     * Draws the day-detail panel.
     */
    private void drawDayDetailPanel() {
        setLayout(null);
        setBackground(Color.WHITE);
        setDayDetailPanelDimensions();
        setDayDetailPanelBounds();

        drawDayLabel();
        drawAppointments();
    }

    /**
     * Updates the day-detail panel dimensions and sets it's new bounds.
     */
    public void resizeDayDetailPanel() {
        setDayDetailPanelDimensions();
        setDayDetailPanelBounds();
    }

    /**
     * Sets the day-detail panel's dimensions.
     */
    private void setDayDetailPanelDimensions() {
        dayDetailPanelWidth = (int) (mainPanel.mainFrame.getContentPane().getWidth() * 0.2);
        dayDetailPanelHeight = mainPanel.mainFrame.getContentPane().getHeight();
    }

    /**
     * Sets the day-detail panel's bounds with the known dimensions.
     */
    private void setDayDetailPanelBounds() {
        setBounds((int) mainPanel.mainFrame.getContentPane().getWidth() - dayDetailPanelWidth, 0, dayDetailPanelWidth, dayDetailPanelHeight);
        //scrollPane.setBounds(0,mainPanel.getTopPanelHeight(),dayDetailPanelWidth, dayDetailPanelHeight - mainPanel.getTopPanelHeight());
    }

    /**
     * Draws the weekday-name and month day heading.
     */
    private void drawDayLabel() {
        String weekDayName = mainPanel.mainFrame.calendar.week.getWeekDayName(mainPanel.mainFrame.calendar.getDate(month, day, year));
        JLabel dayLabel = new JLabel(weekDayName + " " + day);
        dayLabel.setBounds(15, 10, dayDetailPanelWidth, 50);
        dayLabel.setForeground(Color.decode("#333333"));
        dayLabel.setFont(new Font("Arial", Font.PLAIN, 30));

        add(dayLabel);
    }

    /**
     * Redraws the day detail-panel
     */
    public void redrawDayDetailPanel() {
        month = mainPanel.mainFrame.calendar.month.getActiveMonth();
        day = mainPanel.mainFrame.calendar.day.getActiveDay();
        year = mainPanel.mainFrame.calendar.year.getActiveYear();
        appointments = manager.getAppointments(mainPanel.mainFrame.calendar.getDate(month, day, year));

        removeAll();
        drawDayDetailPanel();
        validate();
        repaint();
    }

    /**
     * Draws each appointment with it's contents in the panel.
     */
    private void drawAppointments() {
        JPanel appointmentsPanel = new JPanel();
        appointmentsPanel.setLayout(new BoxLayout(appointmentsPanel, BoxLayout.Y_AXIS));
        appointmentsPanel.setBackground(Color.WHITE);
        appointmentsPanel.setOpaque(true);

        scrollPane = new JScrollPane(appointmentsPanel);
        scrollPane.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.decode("#E2E2E2")));
        scrollPane.setOpaque(true);
        // set bounds
        scrollPane.setBounds(0, mainPanel.getTopPanelHeight(), dayDetailPanelWidth, dayDetailPanelHeight - mainPanel.getTopPanelHeight());
        scrollPane.getVerticalScrollBar().setUnitIncrement(25);

        Integer appointmentsSize = appointments.size();

        if (appointmentsSize > 0) {
            for (Integer i = 0; i < appointments.size(); i++) {
                Appointment appointment = appointments.get(i);

                Boolean hasLocation = true;
                Boolean hasDescription = true;
                if (appointment.location == null) {
                    hasLocation = false;
                }
                if (appointment.description == null) {
                    hasDescription = false;
                }

                // components
                Border spacingBorder = new EmptyBorder(0, 6, 0, 6);
                String startTime = appointment.startTime.toString();
                startTime = startTime.substring(0, startTime.length() - 3);
                String endTime = appointment.endTime.toString();
                endTime = endTime.substring(0, endTime.length() - 3);
                JLabel time = new JLabel(startTime + " - " + endTime);
                time.setBorder(spacingBorder);
                JLabel title = new JLabel(appointment.title);
                title.setBorder(spacingBorder);
                title.setFont(new Font("Arial", Font.BOLD, 14));

                JButton deleteButton = new JButton("Delete");
                deleteButton.addActionListener(new deleteAppointmentHandler(appointment.appointmentId, appointment.title));

                JButton editButton = new JButton("Edit");
                editButton.addActionListener(new editAppointmentHandler(appointment.appointmentId, appointment.title));

                JButton patientButton = new JButton("Patient Information");
                patientButton.addActionListener(new patientAppointmentHandler(appointment.appointmentId, appointment.title));

                // create panel and add components
                JPanel appointmentPanel = new JPanel();
                appointmentPanel.setLayout(new BoxLayout(appointmentPanel, BoxLayout.Y_AXIS));
                appointmentPanel.add(title);
                appointmentPanel.add(time);
                if (hasLocation) {
                    JLabel location = new JLabel("Location: " + appointment.location);
                    location.setBorder(spacingBorder);
                    appointmentPanel.add(location);
                }
                if (hasDescription) {
                    JLabel description = new JLabel("Notes: " + appointment.description);
                    description.setBorder(spacingBorder);
                    appointmentPanel.add(description);
                }

                if (userInput.startsWith("1") && userInput.length() > 2) {
                    JButton viewButton = new JButton("View Appointment");
                    viewButton.addActionListener(new viewAppointmentHandler(appointment.appointmentId, appointment.title));
                    appointmentPanel.add(viewButton);

                }
                if (userInput.startsWith("2") && userInput.length() > 2) {
                    JButton viewButton = new JButton("View Appointment");
                    viewButton.addActionListener(new viewAppointmentHandler(appointment.appointmentId, appointment.title));

                    appointmentPanel.add(viewButton);

                } else {

                }
                //appointmentPanel.add(patientButton);
                appointmentPanel.add(editButton);
                appointmentPanel.add(deleteButton);

                appointmentPanel.setOpaque(false);
                appointmentPanel.setBorder(new EmptyBorder(10, 12, 10, 12));
                appointmentsPanel.add(appointmentPanel);
            }
        } else {
            JLabel noResultsLabel = new JLabel("No appointments found");
            noResultsLabel.setFont(new Font("Arial", Font.PLAIN, 14));
            noResultsLabel.setBorder(new EmptyBorder(10, 12, 10, 12));
            appointmentsPanel.add(noResultsLabel);
        }

        add(scrollPane);

    }

    /**
     * Inner class. Triggers an actionlistener when a delete button is clicked.
     */
    class deleteAppointmentHandler implements ActionListener {

        public Integer appointmentId;
        private String appointmentName;

        /**
         * Constructor, stores the appointment ID.
         *
         * @param appointmentId
         */
        public deleteAppointmentHandler(Integer appointmentId, String appointmentName) {
            this.appointmentId = appointmentId;
            this.appointmentName = appointmentName;

        }

        /**
         * Deletes an appointment based on an appointment id
         *
         * @param e
         */
        public void actionPerformed(ActionEvent e) {
            int dialogResult = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete the appointment \"" + appointmentName + "\"?", "Delete appointment", JOptionPane.YES_NO_OPTION);

            if (dialogResult == JOptionPane.YES_OPTION) {
                System.out.println("APPOINTMENT:" + appointmentId);
                try {
                    Scanner s2 = new Scanner(new File("appointments.txt"));
                    Boolean found2 = false;
                    while (s2.hasNextLine() && !found2) {

                        String in = s2.nextLine();
                        String[] sArray = in.split("//");
                        System.out.println(sArray[0]);
                        if (appointmentId.equals(Integer.parseInt(sArray[0]))) {
                            System.out.println("FOUND");
                            System.out.println("FROM CALENDAR: " + appointmentId);
                            System.out.println("FROM FILE: " + sArray[0]);

                            File inputFile = new File("appointments.txt");
                            File tempFile = new File("myTempFile.txt");

                            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
                            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

                            //String lineToRemove = sArray[0] + "," + sArray[1] + "," + sArray[2] + "," + sArray[3] + "," + sArray[4] + "," + sArray[5] + "," + sArray[6] + "," + sArray[7] + "," + sArray[8] + "," + sArray[9] + "," + sArray[10] + "," + sArray[11] + "," + sArray[12];
                            String currentLine;

                            while ((currentLine = reader.readLine()) != null) {
                                // trim newline when comparing with lineToRemove
                                String trimmedLine = currentLine.trim();
                                if (trimmedLine.contains(sArray[0])) {
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

                    s2.close();
                } catch (FileNotFoundException d) {
                    JOptionPane.showMessageDialog(null, "User Database Not Found.", "Error", JOptionPane.ERROR_MESSAGE);
                } catch (IOException ex) {
                    Logger.getLogger(cancelReschedule.class.getName()).log(Level.SEVERE, null, ex);
                }
                manager.deleteAppointment(appointmentId);
                // redraw panels
                mainPanel.calendarPanel.monthPanel.redrawMonthPanel();
                mainPanel.dayDetailPanel.redrawDayDetailPanel();
            }
        }
    }

    /**
     * Inner class. Triggers an actionlistener when a delete button is clicked.
     */
    class viewAppointmentHandler implements ActionListener {

        public Integer appointmentId;
        private String appointmentName;

        /**
         * Constructor, stores the appointment ID.
         *
         * @param appointmentId
         */
        public viewAppointmentHandler(Integer appointmentId, String appointmentName) {
            this.appointmentId = appointmentId;
            this.appointmentName = appointmentName;

        }

        /**
         * Deletes an appointment based on an appointment id
         *
         * @param e
         */
        public void actionPerformed(ActionEvent e) {
            try {
                File inputFile = new File("apptID.txt");

                FileWriter fw = new FileWriter(inputFile.getAbsoluteFile(), true);

                fw.write("\n" + appointmentId.toString() + "//" + appointmentName);
                fw.close();

                AppointmentUpdate apptUpdate = new AppointmentUpdate();
                mainPanel.mainFrame.dispose();
                //                      

            } catch (Exception ex) {
                ex.printStackTrace();
            }

//               
        }
    }

    class patientAppointmentHandler implements ActionListener {

        public Integer appointmentId;
        private String appointmentName;

        /**
         * Constructor, stores the appointment ID.
         *
         * @param appointmentId
         */
        public patientAppointmentHandler(Integer appointmentId, String appointmentName) {
            this.appointmentId = appointmentId;
            this.appointmentName = appointmentName;

        }

        /**
         * Deletes an appointment based on an appointment id
         *
         * @param e
         */
        public void actionPerformed(ActionEvent e) {
            try {
                File inputFile = new File("apptID.txt");

                FileWriter fw = new FileWriter(inputFile.getAbsoluteFile(), true);

                fw.write("\n" + appointmentId.toString() + "//" + appointmentName);
                fw.close();

                patientInfo pt = new patientInfo();
                mainPanel.mainFrame.dispose();
                //                      

            } catch (Exception ex) {
                ex.printStackTrace();
            }

//               
        }
    }

    class editAppointmentHandler implements ActionListener {

        private Integer appointmentId;
        private String appointmentName;

        /**
         * Constructor, stores the appointment ID.
         *
         * @param appointmentId
         */
        public editAppointmentHandler(Integer appointmentId, String appointmentName) {
            this.appointmentId = appointmentId;
            this.appointmentName = appointmentName;

        }

        /**
         * Edits an appointment based on an appointment id
         *
         * @param e
         */
        public void actionPerformed(ActionEvent e) {
            int dialogResult = JOptionPane.showConfirmDialog(null, "Are you sure you want to edit the appointment \"" + appointmentName + "\"?", "Edit appointment", JOptionPane.YES_NO_OPTION);

            if (dialogResult == JOptionPane.YES_OPTION) {
                System.out.println("UPDATE APPOINTMENT:" + appointmentId);
                JFrame frame = new JFrame();
                JPanel calPanel = new JPanel();
                frame.setSize(300, 150);
                frame.setLocationRelativeTo(null);
                calPanel.setLayout(null);

                JLabel chooseDate = new JLabel("Please select a new date.");
                cal = new DateTextField();
                JButton select = new JButton("Select");

                chooseDate.setBounds(75, 25, 300, 20);
                cal.setBounds(100, 50, cal.getPreferredSize().width, cal.getPreferredSize().height);
                select.setBounds(100, 100, 100, 20);
                calPanel.add(chooseDate);
                calPanel.add(cal);
                calPanel.add(select);

                //
                frame.getContentPane().add(BorderLayout.CENTER, calPanel);

                frame.setResizable(false);
                frame.setVisible(true);

                //action to go to newAppointment page when button is clicked
                select.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        try {
                            frame.dispose(); //close screen

                            JFrame frame2 = new JFrame();
                            JPanel calPanel2 = new JPanel();
                            frame2.setSize(300, 400);
                            frame2.setLocationRelativeTo(null);
                            calPanel2.setLayout(null);

                            JLabel date1 = new JLabel("Date");
                            JLabel fname = new JLabel("First Name");
                            JLabel lname = new JLabel("Last Name");
                            JLabel provider = new JLabel("Provider");
                            JLabel network = new JLabel("Network");
                            JLabel location = new JLabel("Location");
                            JLabel startTime = new JLabel("Start Time");
                            JLabel note = new JLabel("Notes");
                            JButton save = new JButton("Save");

                            Date dt = cal.getDate();

                            SimpleDateFormat parser = new SimpleDateFormat("EEE MMM d HH:mm:ss zzz yyyy");
                            Date Ddate = parser.parse(dt.toString());
                            SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
                            String formattedDate = formatter.format(Ddate);

                            JTextField fdate1 = new JTextField();
                            fdate1.setText(formattedDate);
                            fdate1.setEditable(false);

                            JTextField ffname = new JTextField();
                            JTextField flname = new JTextField();
                            JTextField fnote = new JTextField();

                            String[] net = {"Anthem", "CareSource", "Celtic Insurance", "Ambetter"};
                            JComboBox netWork = new JComboBox(net);

                            String[] providerNames = {"Dr. K Smith", "Dr. C Jones", "Dr. G Ward"};
                            JComboBox providerS = new JComboBox(providerNames);

                            String[] loc = {"Indianapolis", "Washington St", "Fishers", "Avon"};
                            JComboBox locations = new JComboBox(loc);

                            String[] st = {"9:00", "11:30", "1:15"};
                            startT = new JComboBox(st);

                            try {
                                Scanner s = new Scanner(new File("appointments.txt"));
                                Boolean found = false;

                                while (s.hasNextLine() && !found) {
                                    String in = s.nextLine();
                                    String[] sArray = in.split("//");

                                    if (appointmentId.equals(Integer.parseInt(sArray[0]))) {
                                        //set boxes
                                        ffname.setText(sArray[2]);
                                        ffname.setEditable(false);

                                        flname.setText(sArray[3]);
                                        flname.setEditable(false);

                                        String optionProvider = sArray[8];
                                        providerS.setSelectedItem(optionProvider);

                                        String optionNetwork = sArray[4];
                                        netWork.setSelectedItem(optionNetwork);

                                        String optionLocation = sArray[9];
                                        locations.setSelectedItem(optionLocation);

                                        fnote.setText(sArray[12]);

                                    }
                                }
                            } catch (IOException exx) {
                                // TODO Auto-generated catch block
                                exx.printStackTrace();
                            }

                            date1.setBounds(50, 25, 80, 20);
                            fdate1.setBounds(150, 25, 100, 20);

                            fname.setBounds(50, 55, 80, 20);
                            ffname.setBounds(150, 55, 100, 20);

                            lname.setBounds(50, 85, 80, 20);
                            flname.setBounds(150, 85, 100, 20);

                            provider.setBounds(50, 115, 80, 20);
                            providerS.setBounds(150, 115, providerS.getPreferredSize().width, providerS.getPreferredSize().height);

                            network.setBounds(50, 145, 80, 20);
                            netWork.setBounds(150, 145, netWork.getPreferredSize().width, netWork.getPreferredSize().height);

                            location.setBounds(50, 175, 80, 20);
                            locations.setBounds(150, 175, locations.getPreferredSize().width, locations.getPreferredSize().height);

                            startTime.setBounds(50, 205, 80, 20);
                            startT.setBounds(150, 205, startT.getPreferredSize().width, startT.getPreferredSize().height);

                            note.setBounds(50, 265, 80, 20);
                            fnote.setBounds(100, 265, 150, 60);

                            save.setBounds(100, 350, 100, 20);

                            calPanel2.add(date1);
                            calPanel2.add(fdate1);

                            calPanel2.add(fname);
                            calPanel2.add(ffname);

                            calPanel2.add(lname);
                            calPanel2.add(flname);

                            calPanel2.add(provider);
                            calPanel2.add(providerS);

                            calPanel2.add(network);
                            calPanel2.add(netWork);

                            calPanel2.add(location);
                            calPanel2.add(locations);

                            calPanel2.add(startTime);
                            calPanel2.add(startT);

//                            calPanel2.add(endTime);
//                            calPanel2.add(endT);
                            calPanel2.add(note);
                            calPanel2.add(fnote);

                            calPanel2.add(save);

                            //
                            frame2.getContentPane().add(BorderLayout.CENTER, calPanel2);

                            frame2.setResizable(false);
                            frame2.setVisible(true);
                            //AppointmentPanel newappt = new AppointmentPanel();

                            save.addActionListener(new ActionListener() {
                                public void actionPerformed(ActionEvent e) {
                                    frame2.dispose();
                                    updateTextBox();
                                    String filePath = "userPass.txt";
                                    BufferedReader br;
                                    String firstname = ffname.getText();
                                    String lname = flname.getText();
                                    String userID = "";

                                    String line = "";

                                    try {
                                        br = new BufferedReader(new FileReader(filePath));
                                        Scanner in = new Scanner(new File("userPass.txt"));
                                        boolean found = false;
                                        while ((line = br.readLine()) != null) {

                                            //System.out.println(line);
                                            String[] words = line.split("//");

                                            for (String word : words) {
                                                if (word.equals(firstname)) {
                                                    System.out.println("Found: " + firstname);
                                                    System.out.println(words[2]);
                                                    found = true;
                                                }
                                                if (word.equals(lname)) {
                                                    System.out.println("Found: " + lname);
                                                    System.out.println(words[4]);
                                                    found = true;
                                                    userID = words[0].toString();
                                                    System.out.println(userID);
                                                    break;
                                                }

                                            }
                                        }

                                        Scanner s2 = new Scanner(new File("appointments.txt"));
                                        Boolean found2 = false;
                                        while (s2.hasNextLine() && !found2) {

                                            String in1 = s2.nextLine();
                                            String[] sArray = in1.split("//");
                                            String day = sArray[5];

                                            SimpleDateFormat parser = new SimpleDateFormat("EEE MMM d HH:mm:ss zzz yyyy");
                                            Date date1 = parser.parse(day);
                                            SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
                                            String formattedDate = formatter.format(date1);

                                            String time1 = sArray[6];
                                            String time2 = sArray[7];

                                            SimpleDateFormat format = new SimpleDateFormat("HH:mm"); // 12 hour format

                                            java.util.Date d1 = (java.util.Date) format.parse(time1);

                                            timeValue = new java.sql.Time(d1.getTime());

                                            java.util.Date d2 = (java.util.Date) format.parse(time2);

                                            timeValue2 = new java.sql.Time(d2.getTime());

                                            if (appointmentId.equals(Integer.parseInt(sArray[0]))) {
                                                System.out.println("MATCH");

                                                original = sArray[0].toString() + "//" + sArray[1].toString() + "//" + sArray[2].toString() + "//" + sArray[3].toString() + "//" + sArray[4].toString() + "//" + sArray[5].toString()
                                                        + "//" + sArray[6].toString() + "//" + sArray[7].toString() + "//" + sArray[8].toString() + "//" + sArray[9].toString() + "//" + sArray[10].toString() + "//" + sArray[11].toString()
                                                        + "//" + sArray[12].toString();

                                                replaceData = sArray[0].toString() + "//" + sArray[1].toString() + "//" + sArray[2].toString() + "//" + sArray[3].toString() + "//" + netWork.getSelectedItem().toString()
                                                        + "//" + dt + "//" + startT.getSelectedItem().toString() + "//" + endT + "//" + providerS.getSelectedItem().toString() + "//" + locations.getSelectedItem().toString()
                                                        + "//" + sArray[10].toString() + "//" + sArray[11].toString() + "//" + fnote.getText().toString();

                                            }

                                        }
                                        replaceSelected(original, replaceData);

                                        s2.close();
                                    } catch (FileNotFoundException d) {
                                        JOptionPane.showMessageDialog(null, "User Database Not Found.", "Error", JOptionPane.ERROR_MESSAGE);
                                    } 
                                    catch (ParseException ex) {
                                        Logger.getLogger(DayDetailPanel.class.getName()).log(Level.SEVERE, null, ex);
                                    } 
                                    catch (IOException ex) {
                                        Logger.getLogger(DayDetailPanel.class.getName()).log(Level.SEVERE, null, ex);
                                    }

                                }
                            });
                        } catch (ParseException ex) {
                            Logger.getLogger(DayDetailPanel.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    }
                });

                manager.editAppointment(appointmentId);
                // redraw panels
                mainPanel.calendarPanel.monthPanel.redrawMonthPanel();
                mainPanel.dayDetailPanel.redrawDayDetailPanel();
            }

        }

        public void updateTextBox() {
            if (startT.getSelectedItem().toString().equals("9:00")) {
                endT = "9:45";
            } else if (startT.getSelectedItem().toString().equals("11:30")) {
                endT = "12:15";
            } else {
                endT = "2:00";
            }

        }

        public void replaceSelected(String replaceWith, String type) {
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

}

class AppointmentFrame extends JFrame {

    public Integer frameWidth = 292, frameHeight = 352;
    private AppointmentPanel appointmentPanel; // for now private
    private CalendarPanel calendarPanel;
    private Integer month, day, year;

    /**
     * Constructor. Calls the initialization of the frame.
     */
    public AppointmentFrame(Integer month, Integer day, Integer year, CalendarPanel calendarPanel, Integer offsetX, Integer offsetY) {
        this.month = month;
        this.day = day;
        this.year = year;
        this.calendarPanel = calendarPanel;
        initFrame(offsetX, offsetY);
    }

    /**
     * Inits the frame.
     */
    private void initFrame(Integer offsetX, Integer offsetY) {
        new JFrame();
        setTitle("Add Appointment - " + String.format("%02d", (month + 1)) + "/" + String.format("%02d", day) + "/" + year);
        setResizable(false);
        setSize(frameWidth, frameHeight);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocation(offsetX, offsetY);
        //setAlwaysOnTop(true);

        // add content to frame
        appointmentPanel = new AppointmentPanel(month, day, year, calendarPanel, this);
        setContentPane(appointmentPanel);
        pack();
        setVisible(true);
    }
}
//current calendar ================================================================

class CCalendar {

    public CYear year;
    public CMonth month;
    public CWeek week;
    public CDay day;

    /**
     * Constructor of the CCalendar object.
     */
    public CCalendar() {
        initCalendar();
    }

    /**
     * Inits a new calendar
     */
    void initCalendar() {
        year = new CYear();
        month = new CMonth();
        week = new CWeek();
        day = new CDay();
    }

    /**
     * Updates the active date by moving one month earlier.
     */
    public void toPrevMonth() {
        if (month.getActiveMonth() == 0) {
            // active month is januari, set new one to december
            month.setActiveMonth(11);
            // set new year
            year.setActiveYear(year.getPreviousYear());
        } else {
            // set previous month to active month
            month.setActiveMonth(month.getPreviousMonth());
        }

        day.setActiveDay(1, month.getDayCount(month.getActiveMonth(), year.getActiveYear()));
        week.setActiveWeek(week.getWeekNumber(getDate(month.getActiveMonth(), day.getActiveDay(), year.getActiveYear())));

        day.setPreviousDay();
        day.setNextDay();
        week.setPreviousWeek();
        week.setNextWeek();
        month.setPreviousMonth();
        month.setNextMonth();
        year.setPreviousYear();
        year.setNextYear();
    }

    /**
     * Updates the active date by moving to the current month.
     */
    public void toCurrentMonth() {
        day.setActiveDay(1, month.getDayCount(month.getActiveMonth(), year.getActiveYear()));
        day.setPreviousDay();
        day.setNextDay();
        week.setActiveWeek(week.getCurrentWeek());
        week.setPreviousWeek();
        week.setNextWeek();
        month.setActiveMonth(month.getCurrentMonth());
        month.setPreviousMonth();
        month.setNextMonth();
        year.setActiveYear(year.getCurrentYear());
        year.setPreviousYear();
        year.setNextYear();
    }

    /**
     * Updates the active date by moving one month later.
     */
    public void toNextMonth() {
        if (month.getActiveMonth() == 11) {
            // active month is december, set new one to januari
            month.setActiveMonth(0);
            // set new year
            year.setActiveYear(year.getNextYear());
        } else {
            // set next month to active month
            month.setActiveMonth(month.getNextMonth());
        }

        day.setActiveDay(1, month.getDayCount(month.getActiveMonth(), year.getActiveYear()));
        week.setActiveWeek(week.getWeekNumber(getDate(month.getActiveMonth(), day.getActiveDay(), year.getActiveYear())));

        day.setPreviousDay();
        day.setNextDay();
        week.setPreviousWeek();
        week.setNextWeek();
        month.setPreviousMonth();
        month.setNextMonth();
        year.setPreviousYear();
        year.setNextYear();
    }

    /**
     * Updates the active date by overriding month, day, year and week.
     */
    public void toDate(Integer month, Integer day, Integer year) {
        if (month > 12) {
            month = 1;
        }

        this.year.setActiveYear(year);
        this.month.setActiveMonth(month - 1); // zero based
        this.day.setActiveDay(day, this.month.getDayCount(month - 1, year));
        this.week.setActiveWeek(this.week.getWeekNumber(getDate(month, day, year)));
        this.day.setPreviousDay();
        this.day.setNextDay();
        this.month.setPreviousMonth();
        this.month.setNextMonth();
        this.year.setPreviousYear();
        this.year.setNextYear();
        this.week.setPreviousWeek();
        this.week.setNextWeek();
    }

    /**
     * Returns the weeknumber based on a given date.
     *
     * @param month the month of the given date
     * @param day the day of the given date
     * @param year the year of the given date
     * @return
     */
    public Date getDate(Integer month, Integer day, Integer year) {
        SimpleDateFormat formatter = new SimpleDateFormat("M/d/yyyy");
        String dateString = (month + 1) + "/" + day + "/" + year;
        Date date = new Date();

        try {
            date = formatter.parse(dateString);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return date;
    }
}
//current day ================================================================

class CDay {

    private Integer activeDay, prevDay, nextDay, currentDay;

    /**
     * Constructor. Sets the global day-variables.
     */
    public CDay() {
        setDays();
    }

    /**
     * Sets the global day-variables.
     */
    private void setDays() {
        setCurrentDay();
        setActiveDay(getCurrentDay(), null);
        setPreviousDay();
        setNextDay();
    }

    /**
     * Gets the previous day.
     *
     * @return the previous day
     */
    public Integer getPreviousDay() {
        return prevDay;
    }

    /**
     * Gets the next day.
     *
     * @return the next day
     */
    public Integer getNextDay() {
        return nextDay;
    }

    /**
     * Gets the current day.
     *
     * @return the current day
     */
    public Integer getCurrentDay() {
        return currentDay;
    }

    /**
     * Gets the active day.
     *
     * @return the active day
     */
    public Integer getActiveDay() {
        return activeDay;
    }

    /**
     * Sets the active day.
     *
     * @param day the day that needs to be active
     * @param monthDays the total days of the month
     */
    public void setActiveDay(Integer day, Integer monthDays) {
        activeDay = day;
        if (monthDays != null) {
            if (activeDay > monthDays) {
                activeDay = monthDays;
            }
        }
        if (activeDay < 1) {
            activeDay = 1;
        }
    }

    /**
     * Sets the previous day, based on the active day.
     */
    public void setPreviousDay() {
        prevDay = activeDay - 1;
    }

    /**
     * Sets the next daym based on the active day.
     */
    public void setNextDay() {
        nextDay = activeDay + 1;
    }

    /**
     * Sets the current day.
     */
    public void setCurrentDay() {
        currentDay = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
    }

}
//current month ================================================================

class CMonth {

    private Integer activeMonth, prevMonth, nextMonth, currentMonth; // for now private

    /**
     * Contstructor. Sets the global month-variables.
     */
    public CMonth() {
        setMonths();
    }

    /**
     * Sets the global month-variables.
     */
    private void setMonths() {
        setCurrentMonth();
        setActiveMonth(currentMonth);
        setPreviousMonth();
        setNextMonth();
    }

    /**
     * Gets the previous month.
     *
     * @return the previous month
     */
    public Integer getPreviousMonth() {
        return prevMonth;
    }

    /**
     * Gets the next month.
     *
     * @return the next month
     */
    public Integer getNextMonth() {
        return nextMonth;
    }

    /**
     * Gets the current month.
     *
     * @return the current month
     */
    public Integer getCurrentMonth() {
        return currentMonth;
    }

    /**
     * Gets the active month.
     *
     * @return the active month
     */
    public Integer getActiveMonth() {
        return activeMonth;
    }

    /**
     * Sets the previous month, based on the current month.
     */
    public void setPreviousMonth() {
        if (activeMonth > 0) {
            prevMonth = activeMonth - 1;
        } else {
            prevMonth = 11;
        }
    }

    /**
     * Sets the next month, based on the active month.
     */
    public void setNextMonth() {
        if (activeMonth < 11) {
            nextMonth = activeMonth + 1;
        } else {
            nextMonth = 0;
        }

    }

    /**
     * Sets the current month.
     */
    public void setCurrentMonth() {
        currentMonth = Calendar.getInstance().get(Calendar.MONTH);
    }

    /**
     * Sets the active month.
     *
     * @param month the month that needs to be active
     */
    public void setActiveMonth(Integer month) {
        if (month > 11) {
            activeMonth = 11;
        } else if (month < 0) {
            activeMonth = 0;
        } else {
            activeMonth = month;
        }
    }

    /**
     * Gets to month name.
     *
     * @param month the month as an integer (zero-based)
     * @return the month name as a string
     */
    public String getMonthName(Integer month) {
        return new DateFormatSymbols().getMonths()[month];
    }

    /**
     * Gets the sum of days in a given month.
     *
     * @param month the month
     * @param year the year the month is in
     * @return
     */
    public Integer getDayCount(Integer month, Integer year) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DATE, 1);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.YEAR, year);
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    /**
     * Gets the first weekday of the month.
     *
     * @param month the month
     * @param year the year the month is in
     * @return the first weekday of the month, zero based
     */
    public Integer getFirstWeekDay(Integer month, Integer year) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DATE, 1);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.YEAR, year);
        return calendar.get(Calendar.DAY_OF_WEEK);
    }
}
//current year ================================================================

class CYear {

    private Integer activeYear, prevYear, nextYear, currentYear; // for now private

    /**
     * Constructor. Sets the global year-variables.
     */
    public CYear() {
        setYears();
    }

    /**
     * Sets the global year-variables.
     */
    private void setYears() {
        setCurrentYear();
        setActiveYear(getCurrentYear());
        setPreviousYear();
        setNextYear();
    }

    /**
     * Gets the previous year, based on the active year.
     *
     * @return the previous year
     */
    public Integer getPreviousYear() {
        return prevYear;
    }

    /**
     * Gets the next year, based on the active year.
     *
     * @return the next year
     */
    public Integer getNextYear() {
        return nextYear;
    }

    /**
     * Gets the current year.
     *
     * @return the current year
     */
    public Integer getCurrentYear() {
        return currentYear;
    }

    /**
     * Gets the active year.
     *
     * @return the active year
     */
    public Integer getActiveYear() {
        return activeYear;
    }

    /**
     * Sets the active year.
     *
     * @param year the year that needs to be active
     */
    public void setActiveYear(Integer year) {
        activeYear = year;
    }

    /**
     * Sets the previous year, based on the active year.
     */
    public void setPreviousYear() {
        prevYear = activeYear - 1;
    }

    /**
     * Sets the next year, based on the active year.
     */
    public void setNextYear() {
        nextYear = activeYear + 1;
    }

    /**
     * Sets the current year.
     */
    public void setCurrentYear() {
        currentYear = Calendar.getInstance().get(Calendar.YEAR);
    }

}
//class DatabaseConnection {
//
//    private static final Logger LOGGER;
//
//    static {
//        LOGGER = Logger.getLogger(DatabaseConnection.class.getName());
//    }
//
//    private final String connectionString;
//    private final String user;
//    private final String password;
//    private Connection connection;
//
//    /**
//     * Initializes a new DatabaseConnection instance using the specified
//     * connection string.
//     *
//     * @param connectionString The connection string to create the connection
//     * with.
//     * @param user The user to create the connection with.
//     * @param password The password to create the connection with.
//     */
//    public DatabaseConnection(String connectionString, String user, String password) {
//        this.connectionString = connectionString;
//        this.user = user;
//        this.password = password;
//    }
//
//    /**
//     * Opens the connection.
//     *
//     * @return true if successful; otherwise, false.
//     */
//    public boolean open() {
//        if (isOpen()) {
//            return true;
//        }
//
//        try {
//            connection = DriverManager.getConnection(connectionString, user, password);
//            return true;
//        } catch (Exception e) {
//            LOGGER.log(Level.SEVERE, "An exception occurred while trying to open the database connection.", e);
//            return false;
//        }
//    }
//
//    /**
//     * Checks if the connection is currently open.
//     *
//     * @return true if open; otherwise, false.
//     */
//    public boolean isOpen() {
//        if (connection == null) {
//            return false;
//        }
//
//        try {
//            return !connection.isClosed();
//        } catch (Exception e) {
//            LOGGER.log(Level.SEVERE, "An exception occurred while trying to check if the database connection is open.", e);
//            return false;
//        }
//    }
//
//    /**
//     * Closes the connection.
//     *
//     * @return true if successful; otherwise, false.
//     */
//    public boolean close() {
//        if (!isOpen()) {
//            return true;
//        }
//
//        try {
//            connection.close();
//            connection = null;
//            return true;
//        } catch (Exception e) {
//            LOGGER.log(Level.SEVERE, "An exception occurred while trying to close the database connection.", e);
//            return false;
//        }
//    }
//
//    /**
//     * Executes SQL that does not return any results.
//     *
//     * @param sql The sql to be executed
//     * @return true if successful; otherwise, false.
//     */
//    public boolean execute(String sql) {
//        try {
//            Statement statement = connection.createStatement();
//            return statement.execute(sql);
//        } catch (Exception e) {
//            LOGGER.log(Level.SEVERE, "An exception occurred while trying to execute the statement.", e);
//            return false;
//        }
//    }
//
//    /**
//     * Executes SQL that does not return any results.
//     *
//     * @param sql The sql to be executed
//     * @return true if successful; otherwise, false.
//     */
//    public int[] executeBatch(String... sql) {
//        try {
//            Statement statement = connection.createStatement();
//            for (String sqlLine : sql) {
//                statement.addBatch(sqlLine);
//            }
//            return statement.executeBatch();
//        } catch (Exception e) {
//            LOGGER.log(Level.SEVERE, "An exception occurred while trying to execute the statement.", e);
//            return new int[0];
//        }
//    }
//
//    /**
//     * Executes an SQL query that returns results.
//     *
//     * @param sql The sql to be executed
//     * @return The result set returned by the query.
//     */
//    public ResultSet executeQuery(String sql) {
//        try {
//            Statement statement = connection.createStatement();
//            return statement.executeQuery(sql);
//        } catch (Exception e) {
//            LOGGER.log(Level.SEVERE, "An exception occurred while trying to execute the query.", e);
//            return null;
//        }
//    }
//
//    /**
//     * Creates and executes an SQL string by placing the specified items into
//     * the SQL format string and returns the generated keys.
//     *
//     * @param sqlFormat The SQL string to place the items in.
//     * @param items The items to place into the SQL format string.
//     * @return The generated keys as an integer List.
//     */
//    public List<Integer> executePrepared(String sqlFormat, Object... items) {
//        try {
//            PreparedStatement statement = connection.prepareStatement(sqlFormat, Statement.RETURN_GENERATED_KEYS);
//
//            int itemCount = items.length;
//
//            for (int i = 0; i < itemCount; i++) {
//                statement.setObject(i + 1, items[i]);
//            }
//
//            statement.execute();
//
//            List<Integer> generatedIds = new ArrayList<>();
//
//            ResultSet generatedKeys = statement.getGeneratedKeys();
//            while (generatedKeys.next()) {
//                generatedIds.add(generatedKeys.getInt(1));
//            }
//
//            return generatedIds;
//        } catch (Exception e) {
//            LOGGER.log(Level.SEVERE, "An exception occurred while trying to execute the prepared statement.", e);
//            return new ArrayList<>();
//        }
//    }
//
//    /**
//     * Creates and executes an SQL string for each set of items in the specified
//     * batch by placing the specified items into the SQL format string and
//     * returns the number of affected rows.
//     *
//     * @param sqlFormat The SQL string to place the items in.
//     * @param batch The batch with item collections to place in the SQL string.
//     * @return The number of affected rows as an integer List.
//     */
//    public int[] executePreparedBatch(String sqlFormat, List<Object[]> batch) {
//        try {
//            PreparedStatement statement = connection.prepareStatement(sqlFormat);
//
//            for (Object[] items : batch) {
//                int itemCount = items.length;
//
//                for (int i = 0; i < itemCount; i++) {
//                    statement.setObject(i + 1, items[i]);
//                }
//
//                statement.addBatch();
//            }
//
//            return statement.executeBatch();
//        } catch (Exception e) {
//            LOGGER.log(Level.SEVERE, "An exception occurred while trying to execute the prepared statement batch.", e);
//            return new int[0];
//        }
//    }
//}
//class AppointmentDAO {
//
//    //private DatabaseConnection connection = new DatabaseConnection("jdbc:mysql://127.0.0.1/java_calendar", "root", "root");
//    /**
//     * Gets all appointments of a given date.
//     *
//     * @param date the date the appointments needs to be retrieved from
//     */
//    public ArrayList<Appointment> getAppointments(Date date) {
//        ArrayList<Appointment> appointments = new ArrayList<Appointment>();
////        String dateString = new SimpleDateFormat("yyyy-MM-dd").format(date);
////
////        if (date != null) {
////            // First open a database connnection
////            if (connection.open()) {
////                // If a connection was successfully setup, execute the SELECT statement.
////                ResultSet resultset = connection.executeQuery(
////                        "SELECT * FROM appointment WHERE date = '" + dateString + "' ORDER BY startTime;");
////
////                if (resultset != null) {
////                    try {
////                        while (resultset.next()) {
////                            // get fields
////                            Integer appointmentId = resultset.getInt("id");
////                            String title = resultset.getString("title");
////                            String description = resultset.getString("description");
////                            String location = resultset.getString("location");
////                            Time startTime = resultset.getTime("startTime");
////                            Time endTime = resultset.getTime("endTime");
////
////                            // add appointment to list
////                            Appointment appointment = new Appointment(appointmentId, title, description, location, date, startTime, endTime);
////                            appointments.add(appointment);
////                        }
////                    } catch (SQLException e) {
////                        System.out.println(e);
////                    }
////                }
////
////                // We had a database connection opened. Since we're finished,
////                // we need to close it.
////                connection.close();
////            }
////        }
//
////        try {
////            BufferedReader br = new BufferedReader(new FileReader("appointments.txt"));
////            Scanner s = new Scanner(new File("appointments.txt"));
////
////            String line = "";
////
////            while ((line = br.readLine()) != null) {
////
////                String in = s.nextLine();
////                String[] sArray = in.split(",");
////                String day = sArray[7].toString();
////                SimpleDateFormat parser = new SimpleDateFormat("EEE MMM d HH:mm:ss zzz yyyy");
////                date = parser.parse(day);
////                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
////                String formattedDate = formatter.format(date);
////
////                Date todayDate = new Date();
////                String time1 = sArray[8].toString();
////                SimpleDateFormat format = new SimpleDateFormat("HH:mm"); // 12 hour format
////
////                java.util.Date d1 = (java.util.Date) format.parse(time1);
////
////                java.sql.Time timeValue = new java.sql.Time(d1.getTime());
////
////                System.out.println(formattedDate);
////                String apptID = sArray[0].toString();
////                int apID = Integer.parseInt(apptID);
////
////                System.out.println(apID);
////                System.out.println(sArray[2].toString() + " " + sArray[3].toString());
////                System.out.println(sArray[6].toString() + " / " + sArray[12].toString());
////                System.out.println(date);
////                System.out.println(timeValue);
////
////                //add appointment to list
////                Appointment appointment = new Appointment(apID, sArray[2].toString() + " " + sArray[3].toString(), sArray[13].toString(), sArray[6].toString() + " / " + sArray[12].toString(), date, timeValue, timeValue);
////                //   Appointment appointment = new Appointment(apID, sArray[2].toString() + " " + sArray[3].toString(), sArray[13].toString(), sArray[6].toString() + " / " + sArray[12].toString(), date, timeValue, timeValue);
////                   appointments.add(appointment); 
////                   
////                   
////            }
////
////        } catch (FileNotFoundException d) {
////            JOptionPane.showMessageDialog(null, "User Database Not Found.", "Error", JOptionPane.ERROR_MESSAGE);
////        } catch (IOException exx) {
////            // TODO Auto-generated catch block
////            exx.printStackTrace();
////        } catch (ParseException ex) {
////            Logger.getLogger(AppointmentDAO.class.getName()).log(Level.SEVERE, null, ex);
////        }
//
//        return appointments;
//    }
//
//    /**
//     * Inserts a new appointment in the database.
//     *
//     * @param date the date the appointment needs to be added to
//     * @param title the title of the appointment
//     * @param location the location of the appointment
//     * @param description a description of the appointment
//     * @param startTime the starttime of the appointment
//     * @param endTime the endtime of the appointment
//     */
//    public Boolean addAppointment(Date date, String title, String description, String location, Time startTime, Time endTime) {
//        List<Integer> resultIds = new ArrayList<Integer>();
//
////        if (date != null && title != null && date != null && startTime != null && endTime != null) {
////            // First open a database connnection
////            if (connection.open()) {
////                // If a connection was successfully setup, execute the statement.
////                resultIds = connection.executePrepared("INSERT INTO appointment (title, description, location, date, startTime, endTime) VALUES(?,?,?,?,?,?);",
////                        title, description, location, date, startTime, endTime);
////            }
////
////            // We had a database connection opened. Since we're finished,
////            // we need to close it.
////            connection.close();
////        }
//        return resultIds.isEmpty();
//    }
//
//    /**
//     * Deletes an appointment from the database.
//     *
//     * @param appointmentId the id of the appointment.
//     */
//    public boolean deleteAppointment(Integer appointmentId) {
//        boolean result = false;
////        if (appointmentId != null) {
////            // First open a database connnection
////            if (connection.open()) {
////                // If a connection was successfully setup, execute the statement.
////                result = connection.execute("DELETE FROM appointment WHERE id = '" + appointmentId + "';");
////            }
////
////            // We had a database connection opened. Since we're finished,
////            // we need to close it.
////            connection.close();
////        }
//        return result;
//    }
//}
