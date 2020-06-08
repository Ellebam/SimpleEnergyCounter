package com.SimpleEnergyCounterV1;



import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class CreateCounterFrame extends JFrame {
    //buttons for our frame
    private JButton NextButton = new JButton("Next");
    private JButton BackButton = new JButton("Back");
    //text Areas for User Input
    private JTextArea CounterNameInputArea;
    private JTextArea CounterTypeInputArea;
    private JTextArea SerialNumberInputArea;

    //our frame
    CreateCounterFrame frame;
    //Instantiating a Driver Object to use our predefined methods for managing Counters,CounterReadings, etc.
    public Driver driver = new Driver();

    public CreateCounterFrame(){
        //setting up the general frame
        super("Simple Energy Counter V1");
        frame = this;
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);




        //setting up a Top-JPanel frameLabel for a general information for the user
        JPanel frameLabelPane = new JPanel();
        frameLabelPane.setLayout(new BoxLayout(frameLabelPane, BoxLayout.X_AXIS) );
        frameLabelPane.setPreferredSize(new Dimension(400,200));


        Font frameLabelFont = new Font("Arial", Font.PLAIN,25);
        JLabel frameLabel = new JLabel("Please fill in your counters information");
        frameLabel.setFont(frameLabelFont);


        frameLabelPane.add(Box.createHorizontalGlue());
        frameLabelPane.add(frameLabel);
        frameLabelPane.add(Box.createHorizontalGlue());





        /* setting up the middle JPanel for all Components, which will accept user input
        * for creation of a new Counter*/
        Font CounterInformationLabelsFont = new Font("Arial",Font.PLAIN,18);
        Dimension InputAreasDimensions = new Dimension(150,15);
        /* the InputAreasDimension is used to prevent the TextAreas from resizing when
        * the user hits the Enter Key*/
        JLabel CounterNameLabel = new JLabel("Counter Name:");
        CounterNameLabel.setFont(CounterInformationLabelsFont);
        Action doNothing = new AbstractAction(){
            public void actionPerformed(ActionEvent e){
                /* this Action is used to prevent the Enter key to be used while inside the JTextArea so we only get
                * singular line Data*/
            }
        };

        CounterNameInputArea = new JTextArea(0, 20);
        CounterNameInputArea.setPreferredSize(InputAreasDimensions);
        CounterNameInputArea.getDocument().putProperty("filterNewLines",Boolean.TRUE);
        CounterNameInputArea.getInputMap().put(KeyStroke.getKeyStroke("ENTER"),"doNothing");
        CounterNameInputArea.getActionMap().put("doNothing",doNothing);





        JLabel CounterTypeLabel = new JLabel("Counter Type:");
        CounterTypeLabel.setFont(CounterInformationLabelsFont);

        CounterTypeInputArea = new JTextArea(0,20);
        CounterTypeInputArea.setPreferredSize(InputAreasDimensions);
        CounterTypeInputArea.getDocument().putProperty("filterNewLines",Boolean.TRUE);
        CounterTypeInputArea.getInputMap().put(KeyStroke.getKeyStroke("ENTER"),"doNothing");
        CounterTypeInputArea.getActionMap().put("doNothing",doNothing);

        JLabel SerialNumberLabel = new JLabel("Serial Number:");
        SerialNumberLabel.setFont(CounterInformationLabelsFont);

        SerialNumberInputArea = new JTextArea(0,20);
        SerialNumberInputArea.setPreferredSize(InputAreasDimensions);
        SerialNumberInputArea.getInputMap().put(KeyStroke.getKeyStroke("ENTER"),"doNothing");
        SerialNumberInputArea.getActionMap().put("doNothing",doNothing);




        /* setting up a JPanel CounterInformationInputPane with a GridBagLayout for
        * more effective positioning of the components */
        JPanel CounterInformationInputPane = new JPanel();
        GridBagLayout CounterInformationInputGrid = new GridBagLayout();
        GridBagConstraints GridConstraints = new GridBagConstraints();
        CounterInformationInputPane.setLayout(CounterInformationInputGrid);

        //adding the single components to the CounterInformationInputPane
        GridConstraints.fill=GridConstraints.NONE;

        GridConstraints.ipadx=20;
        GridConstraints.ipady=20;
        GridConstraints.gridx=0;
        GridConstraints.gridy=0;
        CounterInformationInputPane.add(CounterNameLabel, GridConstraints);

        GridConstraints.ipadx=0;
        GridConstraints.ipady=0;
        GridConstraints.gridx=1;
        GridConstraints.gridy=0;
        CounterInformationInputPane.add(CounterNameInputArea, GridConstraints);

        GridConstraints.ipadx=20;
        GridConstraints.ipady=20;
        GridConstraints.gridx=0;
        GridConstraints.gridy=1;
        CounterInformationInputPane.add(CounterTypeLabel, GridConstraints);

        GridConstraints.ipadx=0;
        GridConstraints.ipady=0;
        GridConstraints.gridx=1;
        GridConstraints.gridy=1;
        CounterInformationInputPane.add(CounterTypeInputArea, GridConstraints);

        GridConstraints.ipadx=20;
        GridConstraints.ipady=20;
        GridConstraints.gridx=0;
        GridConstraints.gridy=2;
        CounterInformationInputPane.add(SerialNumberLabel, GridConstraints);

        GridConstraints.ipadx=0;
        GridConstraints.ipady=0;
        GridConstraints.gridx=1;
        GridConstraints.gridy=2;
        CounterInformationInputPane.add(SerialNumberInputArea,GridConstraints);



        //setting up NextButton and BackButton for navigating to previous/next frame
        Font buttonsFont = new Font("Arial",Font.BOLD,14);
        NextButton.setFont(buttonsFont);
        NextButton.addActionListener(new NextButtonListener());


        BackButton.setFont(buttonsFont);
        BackButton.addActionListener(new BackButtonListener());

        JPanel buttonPane =new JPanel();
        buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.LINE_AXIS));
        buttonPane.setPreferredSize(new Dimension(500,50));

        buttonPane.add(Box.createRigidArea(new Dimension(1,0)));
        buttonPane.add(Box.createHorizontalGlue());
        buttonPane.add(NextButton);
        buttonPane.add(Box.createRigidArea(new Dimension(20,0)));
        buttonPane.add(BackButton);
        buttonPane.add(Box.createRigidArea(new Dimension(20,0)));



        //adding all the created JPanels to the frame
        frame.getContentPane().add(BorderLayout.PAGE_START,frameLabelPane);
        frame.getContentPane().add(BorderLayout.CENTER, CounterInformationInputPane);
        frame.getContentPane().add(BorderLayout.PAGE_END,buttonPane);
        frame.setLocationRelativeTo(null);

        frame.setVisible(true);
    }

    public Counter processCounterInformationFromTextAreas (){
        /* this method takes the users input and sets the declared variables as the ones provided, so we can
        * pass those values to the next class (next Frame)*/



       Counter Counter =  driver.createNewCounter(CounterNameInputArea.getText(),
                Integer.parseInt(SerialNumberInputArea.getText()),
                CounterTypeInputArea.getText());
                return Counter;


    }


    public class NextButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            frame.processCounterInformationFromTextAreas();
            new ManageNewCounterFrame(driver.createCounterReadingHistory(
                    frame.processCounterInformationFromTextAreas(),
                    driver.createListOfReadings()));
            frame.setVisible(false);

        }
    }

    public class BackButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            frame.setVisible(false);
            new OpeningFrame();
        }
    }

}
