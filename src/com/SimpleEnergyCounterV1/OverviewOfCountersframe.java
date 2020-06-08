package com.SimpleEnergyCounterV1;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.text.DateFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.Properties;

public class OverviewOfCountersframe extends JFrame {
    private CounterTracking CounterGroup;
    File saveFileLocation;
    OverviewOfCountersframe frame;
    JButton BackButton;
    JButton EditButton;
/* this class represents the frame, in which the Counter Overview is shown. It either gets its CounterTracking Object
* from a newly created Counter or from a loaded one. */
    public OverviewOfCountersframe(CounterTracking CounterGroup, File saveFileLocation){
//seting up the general frame
        super("Simple Energy Counter V1");
        this.CounterGroup = CounterGroup;
        this.saveFileLocation =saveFileLocation;
        frame = this;
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


//setting up the top labeling "CounterGroup Name" and "List of Counters"
        JPanel frameLabelPaneTop = new JPanel();
        frameLabelPaneTop.setLayout(new BoxLayout(frameLabelPaneTop, BoxLayout.Y_AXIS) );
        frameLabelPaneTop.setPreferredSize(new Dimension(400,100));

        Font frameLabelFont = new Font("Arial", Font.PLAIN,25);

        JLabel frameLabelTop1 = new JLabel("Group of Counters: "+ CounterGroup.getName());
        frameLabelTop1.setFont(frameLabelFont);
        frameLabelTop1.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel frameLabelTop2 = new JLabel("Choose from the list of counters:");
        frameLabelTop2.setFont(frameLabelFont);
        frameLabelTop2.setAlignmentX(Component.CENTER_ALIGNMENT);


        frameLabelPaneTop.add(frameLabelTop1);
        frameLabelPaneTop.add(Box.createRigidArea(new Dimension(30,20)));
        frameLabelPaneTop.add(frameLabelTop2);

        /* setting up the Middle JPanel for the List of actual counters. This will feature single JPanels that are
        clickable that represent the actual CounterReadingHistory Objects saved in the Countertrackings listOfCOunters */

        JPanel ListOfCountersPane = new JPanel();
        //ListOfCountersPane.setPreferredSize(new Dimension(380,600));
        ListOfCountersPane.setLayout(new BoxLayout(ListOfCountersPane, BoxLayout.Y_AXIS));

        /* the next for loop iterates through all the entries of the provided CounterGroup and adds "PseudoButtons" to
        * the middle JPanel with all of the single Counters Informations*/
        for(int i = 0; i<CounterGroup.getListOfCounters().size();i++){
            ListOfCountersPane.add(Box.createRigidArea(new Dimension(320,20)));
            ListOfCountersPane.add(CreatePseudoButtonForCounters(i));
        }
        ListOfCountersPane.add(Box.createVerticalGlue());
        JScrollPane ListOfCountersScroller = new JScrollPane(ListOfCountersPane);
        ListOfCountersScroller.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        ListOfCountersScroller.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);




//setting up the bottom labeling "Choose a counter" + a Button to get back to the OpeningFrame
        JPanel frameLabelPaneBottom = new JPanel();
        frameLabelPaneBottom.setLayout(new BoxLayout(frameLabelPaneBottom, BoxLayout.X_AXIS));

        BackButton = new JButton("Back");
        BackButton.addActionListener(new BackButtonListener());

        EditButton = new JButton((" Edit "));
        EditButton.addActionListener(new EditButtonListener());



        frameLabelPaneBottom.add(Box.createHorizontalGlue());
        frameLabelPaneBottom.add(BackButton);
        frameLabelPaneBottom.add(Box.createRigidArea(new Dimension(10,20)));
        frameLabelPaneBottom.add(EditButton);
        frameLabelPaneBottom.add(Box.createRigidArea(new Dimension(3,20)));










        frame.getContentPane().add(BorderLayout.PAGE_START, frameLabelPaneTop);
        frame.getContentPane().add(BorderLayout.PAGE_END, frameLabelPaneBottom);
        frame.getContentPane().add(BorderLayout.CENTER, ListOfCountersScroller);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }


    public JPanel CreatePseudoButtonForCounters(int ListOfCountersIndex){
        /* this method createds the "PseudButtons". It takes the informations of the provided CounterGroup and puts them
        * together into a JPanel that is clickable and starts a new CounterReadingFrame object*/

        JPanel PseudoButton = new JPanel();
        PseudoButton.setBackground(Color.LIGHT_GRAY);

        PseudoButton.setMaximumSize(new Dimension(320, 100));
        PseudoButton.setLayout(new BoxLayout(PseudoButton,BoxLayout.Y_AXIS));

        JLabel CounterNameLabel  = new JLabel(CounterGroup.getListOfCounters().
                get(ListOfCountersIndex).getCounter().getName());
        CounterNameLabel.setFont(new Font("Arial",Font.PLAIN,24));
        CounterNameLabel.setPreferredSize(new Dimension(100,50));
        CounterNameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel CounterTypeAndSerialnrPanel =new JPanel();
        CounterTypeAndSerialnrPanel.setBackground((Color.LIGHT_GRAY));
        Font CounterTypeAndSerialnrLabelFont = new Font("Arial",Font.PLAIN,18);

        JLabel CounterTyeLabel = new JLabel("Type: " + CounterGroup.getListOfCounters().
                get(ListOfCountersIndex).getCounter().getType());
        CounterTyeLabel.setFont(CounterTypeAndSerialnrLabelFont);

        JLabel SerialnrLabel = new JLabel(("Serial-Nr: " + CounterGroup.getListOfCounters().
                get(ListOfCountersIndex).getCounter().getSerialNumber()));
        SerialnrLabel.setFont(CounterTypeAndSerialnrLabelFont);

        CounterTypeAndSerialnrPanel.setLayout(new BoxLayout(CounterTypeAndSerialnrPanel, BoxLayout.X_AXIS));
        CounterTypeAndSerialnrPanel.add(Box.createHorizontalGlue());
        CounterTypeAndSerialnrPanel.add(CounterTyeLabel);
        CounterTypeAndSerialnrPanel.add(Box.createRigidArea(new Dimension(25,30)));
        CounterTypeAndSerialnrPanel.add(SerialnrLabel);
        CounterTypeAndSerialnrPanel.add(Box.createHorizontalGlue());
        CounterTypeAndSerialnrPanel.setAlignmentX(Component.CENTER_ALIGNMENT);


        Border PseudoButtonBorder = BorderFactory.createLineBorder(Color.BLACK,2,true);


        PseudoButton.add(Box.createRigidArea(new Dimension(10,12)));
        PseudoButton.add(CounterNameLabel);
        PseudoButton.add(Box.createRigidArea(new Dimension(20,16)));
        PseudoButton.add(CounterTypeAndSerialnrPanel);
        PseudoButton.add(Box.createRigidArea(new Dimension(10,12)));
        PseudoButton.setBorder(PseudoButtonBorder);

        PseudoButton.addMouseListener((new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                frame.setVisible(false);
                new CounterReadingFrame(CounterGroup, ListOfCountersIndex, saveFileLocation);
            }
        }));
        return PseudoButton;

    }

    public class BackButtonListener implements ActionListener{
        public void actionPerformed (ActionEvent ev){
            frame.setVisible(false);
            new OpeningFrame();
        }

    }

    public class EditButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            frame.setVisible(false);
            new EditCountersFrame(CounterGroup,saveFileLocation);
        }
    }
}
