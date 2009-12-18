/*
 * Autumn 2009 TCSS 305 Final Exam
 * Extra Credit - Event Handling
 */

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
   * Creates and arranges the components in the frame.
   */
  private void layoutComponents()
  {
    // your code to lay out the GUI components goes here.
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
