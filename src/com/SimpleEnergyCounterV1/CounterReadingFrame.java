package com.SimpleEnergyCounterV1;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class CounterReadingFrame extends JFrame {
    private CounterTracking CounterGroup;
    private int ListOfCountersIndex;
    private File saveFileLocation;
    private JButton BackButton;
    private JButton EditButton;
    CounterReadingFrame frame;
    Driver driver = new Driver();
    JTextArea NewCounterReadingValueTextArea;
    JDatePickerImpl datePicker;
    Font CounterReadingDisplayFont = new Font("Arial", Font.PLAIN, 16); //used in various UI elements

    /* this class represents the last frame of our program. It shows the single entries of the selceted
     * Counters Readings and has options to add additional readings*/
    public CounterReadingFrame(CounterTracking CounterGroup, int ListOfCountersIndex, File saveFileLocation) {
        //setting up the general frame
        super("Simple Energy Counter V1");
        this.CounterGroup = CounterGroup;
        this.ListOfCountersIndex = ListOfCountersIndex;
        this.saveFileLocation = saveFileLocation;
        frame = this;
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //setting up the top labeling "Counter Readings of: X-Counter"
        JPanel frameLabelPaneTop = new JPanel();
        frameLabelPaneTop.setLayout(new BoxLayout(frameLabelPaneTop, BoxLayout.Y_AXIS));
        frameLabelPaneTop.setPreferredSize(new Dimension(400, 100));

        Font frameLabelFont = new Font("Arial", Font.PLAIN, 25);

        JLabel frameLabelTop1 = new JLabel("Counter Readings of " + CounterGroup.
                getListOfCounters().get(ListOfCountersIndex).getCounter().getName());
        frameLabelTop1.setFont(frameLabelFont);
        frameLabelTop1.setAlignmentX(Component.CENTER_ALIGNMENT);
        frameLabelTop1.setAlignmentY(Component.CENTER_ALIGNMENT);
        frameLabelPaneTop.add(Box.createRigidArea(new Dimension(30, 30)));
        frameLabelPaneTop.add(frameLabelTop1);


        //setting up the JPanel which displays every single reading + its date, also adding a JScroller
        JPanel CounterReadingDisplayPanel = new JPanel();
        CounterReadingDisplayPanel.setLayout(new BoxLayout(CounterReadingDisplayPanel, BoxLayout.Y_AXIS));

        CounterReadingDisplayPanel.setBackground(Color.WHITE);
        Border CounterReadingDisplayPanelBorder = BorderFactory.createLineBorder(Color.BLACK, 2, true);
        //CounterReadingDisplayPanel.setBorder(CounterReadingDisplayPanelBorder);

        //setting up the header Labels for ReadingValue and ReadingDate
        JPanel CounterReadingDisplayPanelHeader = new JPanel();
        CounterReadingDisplayPanelHeader.setLayout(new BoxLayout(CounterReadingDisplayPanelHeader, BoxLayout.X_AXIS));
        CounterReadingDisplayPanelHeader.setBackground(Color.WHITE);


        JLabel ReadingValueLabel = new JLabel(" Reading Value ");
        ReadingValueLabel.setFont(CounterReadingDisplayFont);
        JPanel ReadingValueLabelPanel = new JPanel();
        ReadingValueLabelPanel.setLayout(new BoxLayout(ReadingValueLabelPanel, BoxLayout.Y_AXIS));
        ReadingValueLabelPanel.add(Box.createVerticalGlue());
        ReadingValueLabelPanel.add(ReadingValueLabel);
        ReadingValueLabelPanel.add(Box.createVerticalGlue());
        ReadingValueLabelPanel.setBorder(CounterReadingDisplayPanelBorder);
        ReadingValueLabelPanel.setBackground(Color.LIGHT_GRAY);
        ReadingValueLabelPanel.setSize(new Dimension(40, 25));
        ReadingValueLabelPanel.setMaximumSize(new Dimension(40, 25));


        JLabel ReadingDateLabel = new JLabel("                Reading Date                    ");
        ReadingDateLabel.setFont(CounterReadingDisplayFont);
        JPanel ReadingDateLabelPanel = new JPanel();
        ReadingDateLabelPanel.setLayout(new BoxLayout(ReadingDateLabelPanel, BoxLayout.Y_AXIS));
        ReadingDateLabelPanel.add(Box.createVerticalGlue());
        ReadingDateLabelPanel.add(ReadingDateLabel);
        ReadingDateLabelPanel.add(Box.createVerticalGlue());
        ReadingDateLabelPanel.setBorder(CounterReadingDisplayPanelBorder);
        ReadingDateLabelPanel.setBackground(Color.LIGHT_GRAY);
        ReadingDateLabelPanel.setMinimumSize(new Dimension(100, 25));
        ReadingDateLabelPanel.setMaximumSize(new Dimension(100, 25));


        CounterReadingDisplayPanelHeader.add(Box.createHorizontalGlue());
        CounterReadingDisplayPanelHeader.add(ReadingValueLabelPanel);
        CounterReadingDisplayPanelHeader.add(Box.createHorizontalGlue());
        CounterReadingDisplayPanelHeader.add(ReadingDateLabelPanel);
        CounterReadingDisplayPanelHeader.add(Box.createHorizontalGlue());

        CounterReadingDisplayPanel.add(CounterReadingDisplayPanelHeader);




        /* this for loop iterates through all of the chosen counters Readings and creates a new CounterReadingDisplay
         * JPanel and adds it to the CounterReadingDisplayPanel.*/
        for (int i = 0; i < CounterGroup.getListOfCounters().get(ListOfCountersIndex).getListOfReadings().size(); i++) {
            CounterReadingDisplayPanel.add(Box.createRigidArea(new Dimension(20, 10)));
            CounterReadingDisplayPanel.add(CreateNewCounterReadingDisplay(i));
        }

        JScrollPane CounterReadingDisplayPanelScroller = new JScrollPane(CounterReadingDisplayPanel);
        CounterReadingDisplayPanelScroller.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        CounterReadingDisplayPanelScroller.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        CounterReadingDisplayPanelScroller.setPreferredSize(new Dimension(420,200));
        CounterReadingDisplayPanelScroller.setBorder(CounterReadingDisplayPanelBorder);



        /* the next lines represent the bottom half of the frame. This displays the calculated mean of the given
         CounterReadingValues over full days and a line, where you can add a new CounterReading Object. Also a "back"
          Button for getting back to the OverviewOfCountersFrame*/
        JPanel BottomBoxPanel = new JPanel();
        BottomBoxPanel.setLayout(new BoxLayout(BottomBoxPanel, BoxLayout.Y_AXIS));

       //this section creates a Panel in which the mean of the values for single days is shown to the user
        JPanel MeanValuePerDayPanel = new JPanel();
        MeanValuePerDayPanel.setLayout(new BoxLayout(MeanValuePerDayPanel, BoxLayout.X_AXIS));
        JLabel MeanValuePerDayLabel = new JLabel("Mean Value per day:            " +
                (String.format("%.2f", driver.calculateCounterReadingValueMeanForSingleDays(CounterGroup, ListOfCountersIndex))));

        MeanValuePerDayLabel.setFont(CounterReadingDisplayFont);
        MeanValuePerDayPanel.setBorder(CounterReadingDisplayPanelBorder);
        MeanValuePerDayPanel.setBackground(Color.orange);


        MeanValuePerDayPanel.add(Box.createRigidArea(new Dimension(40, 20)));
        MeanValuePerDayPanel.add(MeanValuePerDayLabel);
        MeanValuePerDayPanel.add(Box.createRigidArea(new Dimension(40, 20)));
        MeanValuePerDayPanel.setPreferredSize(new Dimension(380, 40));



        /* Setup of the Panel which is used for adding new CounterReading Objects. It utilizes a JDatePicker and a
        JTextArea for User Input */
        JPanel AddNewCounterReadingPanel = new JPanel();
        AddNewCounterReadingPanel.setLayout(new BoxLayout(AddNewCounterReadingPanel, BoxLayout.X_AXIS));

        Action doNothing = new AbstractAction(){
            public void actionPerformed(ActionEvent e){
                /* this Action is used to prevent the Enter key to be used while inside the JTextArea so we only get
                 * singular line Data*/
            }
        };



        NewCounterReadingValueTextArea = new JTextArea(0, 1);
        JLabel NewCounterReadingValueTextAreaLabel = new JLabel("Insert Value ");
        NewCounterReadingValueTextArea.setPreferredSize(new Dimension(60, 15));
        JPanel NewCounterReadingValueTextAreaPanel = new JPanel();
        NewCounterReadingValueTextAreaPanel.setLayout(new BoxLayout(NewCounterReadingValueTextAreaPanel, BoxLayout.X_AXIS));
        NewCounterReadingValueTextArea.getInputMap().put(KeyStroke.getKeyStroke("ENTER"),"doNothing");
        NewCounterReadingValueTextArea.getActionMap().put("doNothing",doNothing);
        NewCounterReadingValueTextAreaPanel.add(NewCounterReadingValueTextAreaLabel);
        NewCounterReadingValueTextAreaPanel.add(NewCounterReadingValueTextArea);




        UtilDateModel dateModel = new UtilDateModel();
        JDatePanelImpl datePanel = new JDatePanelImpl(dateModel, new Properties());
        datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
        JLabel datePickerLabel = new JLabel("Insert Date ");


        JButton AddNewCounterReadingButton = new JButton("Add");
        AddNewCounterReadingButton.setPreferredSize(new Dimension(57,20));
        AddNewCounterReadingButton.addActionListener(new AddNewCounterReadingButtonListener());


        AddNewCounterReadingPanel.add(Box.createRigidArea(new Dimension(10, 10)));
        AddNewCounterReadingPanel.add(NewCounterReadingValueTextAreaPanel);
        AddNewCounterReadingPanel.add(Box.createRigidArea(new Dimension(10, 30)));
        AddNewCounterReadingPanel.add(datePickerLabel);
        AddNewCounterReadingPanel.add(datePicker);
        AddNewCounterReadingPanel.add(Box.createRigidArea(new Dimension(8, 20)));
        AddNewCounterReadingPanel.add(AddNewCounterReadingButton);
        AddNewCounterReadingPanel.add(Box.createRigidArea(new Dimension(3, 20)));


        JPanel BackAndEditButtonsPanel = new JPanel();
        BackAndEditButtonsPanel.setLayout(new BoxLayout(BackAndEditButtonsPanel, BoxLayout.X_AXIS));

        BackButton = new JButton("Back");
        BackButton.addActionListener(new BackButtonListener());

        EditButton = new JButton(" Edit ");
        EditButton.addActionListener(new EditButtonListener());

        BackAndEditButtonsPanel.add(Box.createHorizontalGlue());
        BackAndEditButtonsPanel.add(BackButton);
        BackAndEditButtonsPanel.add(Box.createRigidArea(new Dimension(10,20)));
        BackAndEditButtonsPanel.add(EditButton);
        BackAndEditButtonsPanel.add(Box.createRigidArea(new Dimension(3,20)));

        BottomBoxPanel.add(MeanValuePerDayPanel);
        BottomBoxPanel.add(Box.createRigidArea(new Dimension(10, 25)));
        BottomBoxPanel.add(AddNewCounterReadingPanel);
        JPanel BottomBoxPanelHolder = new JPanel();
        BottomBoxPanelHolder.add(BottomBoxPanel);
        BottomBoxPanel.add(Box.createRigidArea(new Dimension(20,15)));
        BottomBoxPanel.add(BackAndEditButtonsPanel);



        frame.getContentPane().add(BorderLayout.PAGE_START, frameLabelPaneTop);
        frame.getContentPane().add(BorderLayout.CENTER, CounterReadingDisplayPanelScroller);
        frame.getContentPane().add(BorderLayout.PAGE_END, BottomBoxPanelHolder);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);


    }

    public JPanel CreateNewCounterReadingDisplay(int ListOfReadingsIndex) {
        /* this method works similarly to the CreatePseudoButtonForCounters() method. It takes
         * the current index and creates a JPanel for the found CounterReadingValue to display on
         * the CounterReadingDisplayPanel*/

        JPanel CounterReadingDisplay = new JPanel();
        CounterReadingDisplay.setLayout(new BoxLayout(CounterReadingDisplay, BoxLayout.X_AXIS));


        JLabel ReadingValue = new JLabel("" + CounterGroup.getListOfCounters().get(ListOfCountersIndex).
                getListOfReadings().get(ListOfReadingsIndex).getCounterReadingValue());
        ReadingValue.setFont(CounterReadingDisplayFont);

        JLabel ReadingDate = new JLabel("" + CounterGroup.getListOfCounters().get(ListOfCountersIndex).
                getListOfReadings().get(ListOfReadingsIndex).getCounterReadingDate());
        ReadingDate.setFont(CounterReadingDisplayFont);

        CounterReadingDisplay.add(Box.createHorizontalGlue());
        CounterReadingDisplay.add(ReadingValue);
        CounterReadingDisplay.add(Box.createHorizontalGlue());
        CounterReadingDisplay.add(ReadingDate);
        CounterReadingDisplay.add(Box.createHorizontalGlue());
        CounterReadingDisplay.setBackground(Color.WHITE);


        return CounterReadingDisplay;
    }

    public class AddNewCounterReadingButtonListener implements ActionListener {
        /* this button uses the addCounterReading() method while also  checking wether the JTextArea has an input.
        * After adding the new CounterReading Object to the current list it will save the whole CounterTracking Object*/
        public void actionPerformed(ActionEvent e) {
            Date date = new Date();
            try {
                date = new SimpleDateFormat("dd.MM.yyyy").parse(datePicker.getJFormattedTextField().getText());
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            if (NewCounterReadingValueTextArea.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please enter a value!");



            } else {
                driver.addCounterReading(CounterGroup.getListOfCounters().get(ListOfCountersIndex),
                        new CounterReading(Double.parseDouble(NewCounterReadingValueTextArea.getText()), date));
                driver.saveCounterGroup(CounterGroup, saveFileLocation);

                frame.setVisible(false);
                new CounterReadingFrame(CounterGroup, ListOfCountersIndex, saveFileLocation);
            }
        }
    }

    public class BackButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            frame.setVisible(false);
            new OverviewOfCountersframe(CounterGroup,saveFileLocation);
        }
    }

    public class EditButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            frame.setVisible(false);
            new EditCounterReadingsFrame(CounterGroup,ListOfCountersIndex,saveFileLocation);
        }
    }
}


