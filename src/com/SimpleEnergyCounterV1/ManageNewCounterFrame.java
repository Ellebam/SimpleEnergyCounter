package com.SimpleEnergyCounterV1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManageNewCounterFrame extends JFrame {
    private JButton CreateNewCounterGroupButton = new JButton("Create new  Group");
    private JButton AddToExistingCounterGroupButton = new JButton ("Add to existing  Group");
    /* these variables are coming from the last frame, so we can save them into an existing Counter Group or create a
    * new one*/
    private CounterReadingHistory CounterReadingHistory;
    public Driver driver = new Driver();


    ManageNewCounterFrame frame;

    public ManageNewCounterFrame(CounterReadingHistory CounterReadingHistory){
        //setting up the general frame
        super("Simple Energy Counter V1");
        this.CounterReadingHistory = CounterReadingHistory;
        frame = this;
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel frameLabelPane = new JPanel();
        frameLabelPane.setLayout(new BoxLayout(frameLabelPane, BoxLayout.X_AXIS) );
        frameLabelPane.setPreferredSize(new Dimension(400,200));

        Font frameLabelFont = new Font("Arial", Font.PLAIN,25);
        JLabel frameLabel = new JLabel("Where should "+ CounterReadingHistory.getCounter().getName()+" go?");
        frameLabel.setFont(frameLabelFont);



        frameLabelPane.add(Box.createHorizontalGlue());
        frameLabelPane.add(frameLabel);
        frameLabelPane.add(Box.createHorizontalGlue());




        //Setting up the frames buttons and the JPanel buttonpane  to hold them
        Dimension buttonsDimension = new Dimension(200,200);
        Font buttonsFont = new Font("Arial",Font.BOLD,15);

        CreateNewCounterGroupButton.setMinimumSize(buttonsDimension);
        CreateNewCounterGroupButton.setPreferredSize(buttonsDimension);
        CreateNewCounterGroupButton.setFont(buttonsFont);
        CreateNewCounterGroupButton.addActionListener(new CreateNewCounterGroupButtonListener());

        AddToExistingCounterGroupButton.setMinimumSize(buttonsDimension);
        AddToExistingCounterGroupButton.setPreferredSize(buttonsDimension);
        AddToExistingCounterGroupButton.setFont(buttonsFont);
        AddToExistingCounterGroupButton.addActionListener(new AddToExistingCounterGroupButtonListener());

        JPanel buttonpane = new JPanel();
        buttonpane.setLayout(new BoxLayout(buttonpane, BoxLayout.LINE_AXIS));
        buttonpane.setPreferredSize(new Dimension(400,150));
        buttonpane.add(Box.createHorizontalGlue());
        buttonpane.add(Box.createRigidArea(new Dimension(10,0)));
        buttonpane.add(CreateNewCounterGroupButton);

        buttonpane.add(Box.createRigidArea(new Dimension(20,0)));
        buttonpane.add(AddToExistingCounterGroupButton);
        buttonpane.add(Box.createRigidArea(new Dimension(10,0)));
        buttonpane.add(Box.createHorizontalGlue());


        frame.getContentPane().add(BorderLayout.PAGE_END, buttonpane);
        frame.getContentPane().add(BorderLayout.CENTER, frameLabelPane);
        frame.setLocationRelativeTo(null);
        frame.setVisible((true));




    }

    public class CreateNewCounterGroupButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent ev){

            JFileChooser fileSave = new JFileChooser();
            CounterTracking CounterGroup = driver.createCounterTracking(driver.createListOfCounters(),
                    JOptionPane.showInputDialog("Please enter a name for the new Group of Counters"));
            driver.addCountersToListOfCounters(CounterGroup, CounterReadingHistory);
            fileSave.showSaveDialog(null);
            driver.saveCounterGroup(CounterGroup, fileSave.getSelectedFile());
            frame.setVisible(false);
            new OverviewOfCountersframe(driver.loadCounterGroup(fileSave.getSelectedFile()), fileSave.getSelectedFile());
            System.out.println(CounterGroup);
        }
    }

    public class AddToExistingCounterGroupButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent ev){


            JFileChooser fileLoad = new JFileChooser();
            fileLoad.showOpenDialog(null);
            System.out.println(driver.loadCounterGroup(fileLoad.getSelectedFile()));

            driver.saveCounterGroup(driver.addCountersToListOfCounters(driver.loadCounterGroup(fileLoad.getSelectedFile()),CounterReadingHistory),
                    fileLoad.getSelectedFile());

            frame.setVisible(false);
            new OverviewOfCountersframe(driver.loadCounterGroup(fileLoad.getSelectedFile()), fileLoad.getSelectedFile());
            System.out.println(driver.loadCounterGroup(fileLoad.getSelectedFile()));


        }
    }







}
