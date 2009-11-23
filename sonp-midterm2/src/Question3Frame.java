/*
 * Autumn 2009 TCSS 305 Midterm 2
 * Question 3 - Layout Managers
 */

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * The frame class for Question 3.
 * 
 * @author Daniel M. Zimmerman
 * @author Son Pham
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
    
    final Question3Frame the_frame = new Question3Frame();
    final JPanel master_panel = new JPanel(new BorderLayout());
    final JPanel north_panel = new JPanel(new GridLayout(0, 1));
    final JPanel inner_north_panel = new JPanel(new BorderLayout());
    final JPanel center_panel = new JPanel(new GridLayout(0, 2));
    final JPanel inner_panel_num_3 = new JPanel(new BorderLayout());
    final JPanel inner_panel_num_4 = new JPanel(new BorderLayout());
    final JPanel inner_panel_num_56 = new JPanel(new FlowLayout());
    final JPanel inner_panel_num_7 = new JPanel(new BorderLayout());
    final JPanel south_panel = new JPanel(new BorderLayout());
    final Iterator<JButton> the_iter = my_buttons.iterator();
    
    inner_north_panel.add((Component) the_iter.next(), BorderLayout.WEST);
    inner_north_panel.add(my_text_field, BorderLayout.CENTER);
    north_panel.add((Component) the_iter.next());
    north_panel.add(inner_north_panel);
    north_panel.add((Component) the_iter.next());
    
    inner_panel_num_3.add((Component) the_iter.next(), BorderLayout.CENTER);
    inner_panel_num_3.setPreferredSize(new Dimension(200, 100));
    inner_panel_num_4.add((Component) the_iter.next(), BorderLayout.SOUTH);
    inner_panel_num_56.add((Component) the_iter.next());
    inner_panel_num_56.add((Component) the_iter.next());
    inner_panel_num_7.add((Component) the_iter.next(), BorderLayout.CENTER);
    inner_panel_num_7.setPreferredSize(new Dimension(200, 100));
    center_panel.add(inner_panel_num_3);
    center_panel.add(inner_panel_num_4);
    center_panel.add(inner_panel_num_56);
    center_panel.add(inner_panel_num_7);
    
    south_panel.add(my_label, BorderLayout.WEST);
    
    master_panel.add(north_panel, BorderLayout.NORTH);
    master_panel.add(center_panel, BorderLayout.CENTER);
    master_panel.add(south_panel, BorderLayout.SOUTH);
    
    the_frame.add(master_panel);
    the_frame.pack();
    the_frame.setVisible(true);
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
