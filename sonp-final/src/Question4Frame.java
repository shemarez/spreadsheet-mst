/*
 * Autumn 2009 TCSS 305 Final Exam
 * Extra Credit - Event Handling
 */

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DecimalFormat;

import javax.swing.AbstractAction;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 * The frame class for Question 4.
 * 
 * @author Daniel M. Zimmerman
 * @author (your name here)
 * @version Autumn 2009
 */
@SuppressWarnings("serial")
public class Question4Frame extends JFrame
{
  /**
   * The frame title.
   */
  public static final String TITLE = "Tip Calculator";  
  
  /**
   * Tip for my_r15.
   */
  private static final double MY_R15_TIP = 0.15;
  
  /**
   * Tip for my_r18.
   */
  private static final double MY_R18_TIP = 0.18;
  
  /**
   * Tip for my_r20.
   */
  private static final double MY_R20_TIP = 0.20;
  
  // controls for you to use
  
  /**
   * The 15% radio button.
   */
  private final JRadioButton my_r15 = new JRadioButton("15%");

  /**
   * The 18% radio button.
   */
  private final JRadioButton my_r18 = new JRadioButton("18%");
  
  /**
   * The 20% radio button.
   */
  private final JRadioButton my_r20 = new JRadioButton("20%");
  
  /**
   * The Compute button.
   */
  private final JButton my_compute = new JButton("Compute");
  
  /**
   * The text field for data entry.
   */
  private final JTextField my_field = new JTextField(10);
  
  /**
   * The label that displays the tip amount.
   */
  private final JLabel my_amount = new JLabel(" ");
  
  /**
   * The percentage for tip.
   */
  private double my_tip_percent;
  
  /**
   * Constructs a new Question4Frame.
   */
  public Question4Frame()
  {
    super(TITLE);
    setResizable(false);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    layoutComponents();
  }
  
  /**
   * A listener for tip radio button.
   * @author Son Pham
   * @verison 1.0
   */
  private class TipAction extends AbstractAction
  {
    /**
     * The percentage of tip.
     */
    private final double my_percent;
    
    /**
     * Constructs a TipAction object with the input the_percent.
     * @param the_percent The percent.
     */
    public TipAction(final double the_percent)
    {
      super();
      my_percent = the_percent;
    }
    
    /**
     * {@inheritDoc}
     */
    public void actionPerformed(final ActionEvent the_event)
    {
      my_tip_percent = my_percent;
    }
  }
  
  /**
   * Compute the bill based on the entered amount.
   */
  private void computeBill()
  {
    final String text = my_field.getText();
    try
    {
      final double amount = Double.parseDouble(text);
      final double tip = amount * my_tip_percent;
      final double bill = amount + tip;
      my_amount.setText("tip = " + roundTo2Decimal(tip) + ", bill = " + 
                        roundTo2Decimal(bill));
    }
    catch (final NumberFormatException nfe)
    {
      my_amount.setText("error");
    }
  }
  
  /**
   * Round a double to 2 decimal places.
   * @param the_double The double.
   * @return A double is rounded to 2 decimal places.
   */
  private double roundTo2Decimal(final double the_double)
  {
    final DecimalFormat twoDecimal = new DecimalFormat("#.##");
    return Double.valueOf(twoDecimal.format(the_double));   
  }
  
  /**
   * A listener for compute button.
   * @author Son Pham
   * @version 1.0
   */
  private class ComputeButton implements MouseListener
  {
    @Override
    public void mouseReleased(final MouseEvent the_event)
    {
      computeBill();
    }

    @Override
    public void mouseClicked(final MouseEvent the_event)
    {
      // TODO Auto-generated method stub
      
    }

    @Override
    public void mouseEntered(final MouseEvent the_event)
    {
      // TODO Auto-generated method stub
      
    }

    @Override
    public void mouseExited(final MouseEvent the_event)
    {
      // TODO Auto-generated method stub
      
    }

    @Override
    public void mousePressed(final MouseEvent the_event)
    {
      // TODO Auto-generated method stub
      
    }
  }
  
  /**
   * A listener class for the text field.
   * @author Son Pham
   * @version 1.0
   */
  private class TextFieldListener implements KeyListener
  {
    @Override
    public void keyReleased(final KeyEvent the_event)
    {
      final int keyCode = the_event.getKeyCode();
      if (keyCode == KeyEvent.VK_ENTER)
      {
        computeBill();
      }
    }

    @Override
    public void keyPressed(final KeyEvent the_event)
    {
      // TODO Auto-generated method stub
      
    }

    @Override
    public void keyTyped(final KeyEvent the_event)
    {
      // TODO Auto-generated method stub
      
    }
  }
  
  /**
   * Creates and arranges the components in the frame.
   */
  private void layoutComponents()
  {
    // your code to lay out the GUI components goes here.
    final JPanel master_panel = new JPanel(new BorderLayout());
    //JPanel north_panel = new JPanel(new BorderLayout());
    final JPanel center_panel = new JPanel(new FlowLayout(FlowLayout.TRAILING));
    master_panel.add(my_field, BorderLayout.NORTH);
    center_panel.add(my_r15);
    center_panel.add(my_r18);
    center_panel.add(my_r20);
    center_panel.add(my_compute);
    master_panel.add(center_panel, BorderLayout.CENTER);
    master_panel.add(my_amount, BorderLayout.SOUTH);
    add(master_panel);
    pack();
  }
  
  /**
   * Creates the event handlers (if necessary) and makes the
   * frame available for use.
   */
  private void start()
  {
    // your code to create event handlers and make the frame 
    // available for use by the user - to the extent that it
    // does not appear in layoutComponents() - goes here.
    
    // you can also add any other methods, inner classes, etc.
    // you need to accomplish the requirements of the question.
    my_r15.addActionListener(new TipAction(MY_R15_TIP));
    my_r18.addActionListener(new TipAction(MY_R18_TIP));
    my_r20.addActionListener(new TipAction(MY_R20_TIP));
    final ButtonGroup the_group = new ButtonGroup();
    the_group.add(my_r15);
    the_group.add(my_r18);
    the_group.add(my_r20);
    my_r15.setSelected(true);
    my_tip_percent = MY_R15_TIP;
    my_compute.addMouseListener(new ComputeButton());
    my_field.addKeyListener(new TextFieldListener());
    setVisible(true);
  }
  
  /**
   * The main() method.
   * 
   * @param the_args Command line arguments, ignored.
   */
  public static void main(final String... the_args)
  {
    final Question4Frame frame = new Question4Frame();
    frame.start();
  }
}

// end of class Question4Frame
