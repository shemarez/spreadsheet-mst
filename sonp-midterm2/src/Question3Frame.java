/*
 * Autumn 2009 TCSS 305 Midterm 2
 * Question 3 - Layout Managers
 */

import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 * The frame class for Question 3.
 * 
 * @author Daniel M. Zimmerman
 * @author (your name here)
 * @version Autumn 2009
 */
@SuppressWarnings("serial")
public class Question3Frame extends JFrame 
{ 
  // Static Fields
  
  /**
   * The frame title.
   */
  private static final String TITLE = "TCSS 305 Layout";
  
  /**
   * The label text.
   */
  private static final String LABEL_TEXT = "Label";
  
  /**
   * The initial text field text.
   */
  private static final String FIELD_TEXT = "Text";
  
  /**
   * The number of buttons.
   */
  private static final int NUM_BUTTONS = 8;
  
  // Instance Fields
  
  /**
   * A list of buttons, with text corresponding to the button's index.
   */
  private final List<JButton> my_buttons;
  
  /**
   * A label.
   */
  private final JLabel my_label;
  
  /**
   * A text field.
   */
  private final JTextField my_text_field;
  
  // Constructor
  
  /**
   * Constructs a new Question3Frame. 
   */
  public Question3Frame()
  {
    super(TITLE);
    my_buttons = new ArrayList<JButton>();
    for (int i = 0; i < NUM_BUTTONS; i++)
    {
      my_buttons.add(new JButton(String.valueOf(i)));
    }
    my_label = new JLabel(LABEL_TEXT);
    my_text_field = new JTextField(FIELD_TEXT);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
  }
  
  // Start Method
  
  /**
   * Lays out the components in, and displays, the frame.
   */
  public void start()
  {
    // your code goes here - you should not have to modify
    // any other method, though you may want to add constants
    // to the class
    
    // to avoid magic numbers, consider using the List
    // of buttons in some way other than directly referencing
    // each element by number...
  }
  
  // Main Method
  
  /**
   * Creates and displays a Question4Frame.
   * 
   * @param the_args Command line arguments, ignored.
   */
  public static void main(final String... the_args)
  {
    final Question3Frame frame = new Question3Frame();
    frame.start();
  }
}
