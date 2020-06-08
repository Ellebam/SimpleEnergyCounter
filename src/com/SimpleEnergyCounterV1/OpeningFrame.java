package com.SimpleEnergyCounterV1;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/* This class represents the first Frame of our GUI. When instantiated, it creates a frame with a simple JLabel to
* welcome the user. It also creates two JButtons, which are linked to the next Frames (LoadCounterFrame and
* CreateCounterFrame) */
public class OpeningFrame extends JFrame {
    private JButton CreateCounterButton = new JButton("Create Counter");
    private JButton LoadCOunterButton = new JButton("Load Counter");
    OpeningFrame frame;
    Driver driver = new Driver();

    public OpeningFrame(){
        //setting up the general frame
        super("Simple Energy Counter V1");
        frame = this;
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Setting up the frames buttons and the JPanel buttonpane  to hold them
        Dimension buttonsDimension = new Dimension(200,200);
        Font buttonsFont = new Font("Arial",Font.BOLD,18);

        CreateCounterButton.setMinimumSize(buttonsDimension);
        CreateCounterButton.setPreferredSize(buttonsDimension);
        CreateCounterButton.setFont(buttonsFont);

        LoadCOunterButton.setMinimumSize(buttonsDimension);
        LoadCOunterButton.setPreferredSize(buttonsDimension);
        LoadCOunterButton.setFont(buttonsFont);

        JPanel buttonpane = new JPanel();
        buttonpane.setLayout(new BoxLayout(buttonpane, BoxLayout.LINE_AXIS));
        buttonpane.setPreferredSize(new Dimension(400,150));
        buttonpane.add(Box.createHorizontalGlue());
        buttonpane.add(Box.createRigidArea(new Dimension(10,0)));
        buttonpane.add(CreateCounterButton);

        buttonpane.add(Box.createRigidArea(new Dimension(20,0)));
        buttonpane.add(LoadCOunterButton);
        buttonpane.add(Box.createRigidArea(new Dimension(10,0)));
        buttonpane.add(Box.createHorizontalGlue());

        CreateCounterButton.addActionListener(new CreateCounterListener());
        LoadCOunterButton.addActionListener(new LoadCounterListener());


        //setting up a JLabel to welcome the user and the JPanel to hold that JLabel
        JPanel labelPane = new JPanel();
        labelPane.setLayout(new BoxLayout(labelPane,BoxLayout.X_AXIS));
        labelPane.setPreferredSize(new Dimension(400,600));
        Font WelcomeMessageFont = new Font("Arial",Font.PLAIN,25);
        JLabel WelcomeMessage = new JLabel();
        WelcomeMessage.setFont(WelcomeMessageFont);
        WelcomeMessage.setText("<html><body>Welcome to Simple Energy Counter" +
                "<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
                "Please choose an option:</body></html>");
        WelcomeMessage.setHorizontalAlignment(JLabel.CENTER);
        WelcomeMessage.setVerticalTextPosition(JLabel.BOTTOM);

        labelPane.add(WelcomeMessage);
        labelPane.add(Box.createHorizontalGlue());


        //adding labelPane and buttonpane to the general frame
        frame.getContentPane().add(BorderLayout.CENTER, labelPane);
        frame.getContentPane().add(BorderLayout.PAGE_END, buttonpane);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);


    }


    public class CreateCounterListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            frame.setVisible(false);
            new CreateCounterFrame();
            //creation of new frame
        }
    }

    public class LoadCounterListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser fileLoad = new JFileChooser();
            fileLoad.showOpenDialog((null));
            CounterTracking CounterGroup = driver.loadCounterGroup(fileLoad.getSelectedFile());
            new OverviewOfCountersframe(CounterGroup, fileLoad.getSelectedFile());
            frame.setVisible(false);
            //creation of new frame
        }
    }

}
