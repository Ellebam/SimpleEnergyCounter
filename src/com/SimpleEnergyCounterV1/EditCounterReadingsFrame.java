package com.SimpleEnergyCounterV1;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

public class EditCounterReadingsFrame extends JFrame {
    /* this class represents the frame to edit single Counter Readings. It has the same code as the
    "Reading Display"-Portion of the CounterReadingFrame Class but with added checkboxes for editing single readings */
    private CounterTracking CounterGroup;
    private int ListOfCountersIndex;
    private File saveFileLocation;
    private JButton BackButton;
    Font CounterReadingDisplayFont = new Font("Arial", Font.PLAIN, 16); //used in various UI elements
    EditCounterReadingsFrame frame;

    Driver driver = new Driver();

    public EditCounterReadingsFrame(CounterTracking CounterGroup, int ListOfCountersIndex, File saveFileLocation){
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


        JLabel frameLabelTop2 = new JLabel("Click on a reading to edit:");
        frameLabelTop2.setFont(frameLabelFont);
        frameLabelTop2.setAlignmentX(Component.CENTER_ALIGNMENT);

        frameLabelPaneTop.add(Box.createRigidArea(new Dimension(30, 10)));
        frameLabelPaneTop.add(frameLabelTop1);
        frameLabelPaneTop.add(Box.createRigidArea(new Dimension(30,10)));
        frameLabelPaneTop.add(frameLabelTop2);


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
        CounterReadingDisplayPanelScroller.setPreferredSize(new Dimension(420,320));
        CounterReadingDisplayPanelScroller.setBorder(CounterReadingDisplayPanelBorder);



        //setting up the BackButtonPanel for the Page_End
        JPanel BackButtonPanel = new JPanel();
        BackButtonPanel.setLayout(new BoxLayout(BackButtonPanel, BoxLayout.X_AXIS));

        BackButton = new JButton("Back");
        BackButton.addActionListener(new BackButtonListener());
        BackButtonPanel.add(Box.createHorizontalGlue());
        BackButtonPanel.add(BackButton);
        BackButtonPanel.add(Box.createRigidArea(new Dimension(3,38)));

        frame.getContentPane().add(BorderLayout.PAGE_START, frameLabelPaneTop);
        frame.getContentPane().add(BorderLayout.CENTER, CounterReadingDisplayPanelScroller);
        frame.getContentPane().add(BorderLayout.PAGE_END,BackButtonPanel);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }




    public JPanel CreateNewCounterReadingDisplay(int ListOfReadingsIndex) {
        /* this method works similarly to the CreatePseudoButtonForCounters() method. It takes
         * the current index and creates a JPanel for the found CounterReadingValue to display on
         * the CounterReadingDisplayPanel. In this frame, the single JPanels are also Pseudobuttons themselves.
         * Once clicked, an OptionDialog is displayed to the user for a decision how the single reading should be edited */

        Object[] options= {"Edit value","Delete reading"};
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

        CounterReadingDisplay.addMouseListener((new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);


              int  userDecision = JOptionPane.showOptionDialog(frame,
                        "Would you like to edit or delete the chosen entry?",
                        "Counter Reading of "+CounterGroup.getListOfCounters().get(ListOfCountersIndex).
                getListOfReadings().get(ListOfReadingsIndex).getCounterReadingDate(),
                      JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
                        null, options, null);
              if (userDecision == 1){
                  driver.removeCounterReading(CounterGroup.getListOfCounters().get(ListOfCountersIndex),
                          ListOfReadingsIndex);
                  driver.saveCounterGroup(CounterGroup,saveFileLocation);
                  driver.loadCounterGroup(saveFileLocation);
                  frame.setVisible(false);
                  new EditCounterReadingsFrame(CounterGroup,ListOfCountersIndex,saveFileLocation);
              }else if (userDecision ==0){
                    driver.editCounterReading(CounterGroup.getListOfCounters().get(ListOfCountersIndex),
                            ListOfReadingsIndex);
                  driver.saveCounterGroup(CounterGroup,saveFileLocation);
                  driver.loadCounterGroup(saveFileLocation);
                  frame.setVisible(false);
                  new EditCounterReadingsFrame(CounterGroup,ListOfCountersIndex,saveFileLocation);
              }



            }
        }));
        return CounterReadingDisplay;
    }

    public class BackButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e){
            frame.setVisible(false);
            new CounterReadingFrame(CounterGroup,ListOfCountersIndex,saveFileLocation);
        }
    }



}
