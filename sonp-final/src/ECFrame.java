/*
 * Autumn 2009 TCSS 305 Final Exam
 * Extra Credit - Event Handling
 */

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * The frame class for the extra credit question.
 * 
 * @author Daniel M. Zimmerman
 * @author (your name here)
 * @version Autumn 2009
 */
@SuppressWarnings("serial")
public class ECFrame extends JFrame 
{
  // Static Fields

  /**
   * The alphabet.
   */
  private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

  /**
   * The vowels.
   */
  private static final String VOWELS = "AEIOU"; 
  
  /**
   * The consonants.
   */
  private static final String CONSONANTS = "BCDFGHJKLMNPQRSTVWXYZ";

  /**
   * The frame title.
   */
  private static final String TITLE = "Text Program";
  
  /**
   * The text of the "Vowels" checkbox.
   */
  private static final String VOWELS_TEXT = "Vowels";

  /**
   * The text of the "Consonants" checkbox.
   */
  private static final String CONSONANTS_TEXT = "Consonants";
  
  /**
   * The text of the "Upper" button.
   */
  private static final String UPPER_TEXT = "Upper";
  
  /**
   * The text of the "Lower" button.
   */
  private static final String LOWER_TEXT = "Lower";
  
  // Instance Fields
  
  /**
   * The label for the line of letters.
   */
  private final JLabel my_label = new JLabel(ALPHABET);
  
  /**
   * The "Vowels" checkbox.
   */
  private final JCheckBox my_vowels = new JCheckBox(VOWELS_TEXT);
  
  /**
   * The "Consonants" checkbox.
   */
  private final JCheckBox my_consonants = new JCheckBox(CONSONANTS_TEXT);
  
  /**
   * The "Upper" button.
   */
  private final JButton my_upper = new JButton(UPPER_TEXT);
  
  /**
   * The "Lower" button.
   */
  private final JButton my_lower = new JButton(LOWER_TEXT);
  
  /**
   * Is upper button clicked?
   */
  //private boolean my_is_upper;
  
  // Constructor
  
  /**
   * Constructs and lays out a new Question5Frame. Note that this does
   * not make the frame visible; that shouldn't be done until after the
   * event handlers are set up.
   */
  public ECFrame()
  {
    super(TITLE);
    setResizable(false);
    layoutComponents();
    setDefaultCloseOperation(EXIT_ON_CLOSE);
  }

  /**
   * Lays out the components in the frame.
   */
  private void layoutComponents()
  {
    final JPanel north = new JPanel();
    north.add(my_vowels);
    north.add(my_consonants);
    final JPanel south = new JPanel();
    south.add(my_upper);
    south.add(my_lower);
    add(north, BorderLayout.NORTH);
    add(south, BorderLayout.SOUTH);
    add(my_label, BorderLayout.CENTER);
    pack();
  }
  /*
  private void editLabelString()
  {
    StringBuilder the_sb = new StringBuilder(my_label.getText());
    final String the_vowels = VOWELS + VOWELS.toLowerCase();
    final String the_consonants = CONSONANTS + CONSONANTS.toLowerCase();
    System.err.println(the_sb.toString());
    if (my_vowels.isSelected() && my_consonants.isSelected())
    {
      final String the_string;
      if (my_is_upper)
      {        
        the_string = my_label.getText().toUpperCase();         
      }
      else 
      {
        the_string = my_label.getText().toLowerCase();
      }
      my_label.setText(the_string);
    }
    else if (my_consonants.isSelected())
    {
      final char[] the_array = the_consonants.toCharArray();
      if (my_is_upper)
      {
        for (int i = 0; i < the_array.length; i++)
        {
          the_sb.setCharAt(the_sb.indexOf("\"" + the_array[i] + "\""), 
                           Character.toUpperCase(the_array[i]));
        }
      }
      else
      {
        for (int i = 0; i < the_array.length; i++)
        {
          the_sb.setCharAt(the_sb.indexOf("\"" + the_array[i] + "\""), 
                           Character.toLowerCase(the_array[i]));
        }
      }
      my_label.setText(the_sb.toString());
    }
    else if (my_vowels.isSelected())
    {
      final char[] the_array = the_vowels.toCharArray();
      if (my_is_upper)
      {
        for (int i = 0; i < the_array.length; i++)
        {
          the_sb.setCharAt(the_sb.indexOf("\"" + the_array[i] + "\""), 
                           Character.toUpperCase(the_array[i]));
        }
      }
      else
      {
        for (int i = 0; i < the_array.length; i++)
        {
          the_sb.setCharAt(the_sb.indexOf("\"" + the_array[i] + "\""), 
                           Character.toLowerCase(the_array[i]));
        }
      }
      my_label.setText(the_sb.toString());
    }
    System.err.println(the_sb.toString());
  }*/
  /*
  private class UpperLowerListener implements MouseListener
  {
    private boolean my_inner_is_upper;
    public UpperLowerListener(final boolean the_is_upper)
    {
      my_inner_is_upper = the_is_upper;
    }
    
    public void mouseClicked(final MouseEvent the_event)
    {
      my_is_upper = my_inner_is_upper;
      editLabelString();
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

    @Override
    public void mouseReleased(final MouseEvent the_event)
    {
      // TODO Auto-generated method stub
      
    }
  }*/
  
  /**
   * Sets up the event handlers and displays the frame. 
   */
  public void start()
  {
    // your code goes here
    // you may also add other methods, fields, and inner classes, 
    // as you deem necessary
    /*
    my_upper.addMouseListener(new UpperLowerListener(true));
    my_lower.addMouseListener(new UpperLowerListener(false));
    setVisible(true);*/
  }
  
  /**
   * Creates and displays a new Question5Frame.
   * 
   * @param the_args Command line arguments, ignored.
   */
  public static void main(final String... the_args) 
  {
    final ECFrame frame = new ECFrame();
    frame.start();
  }
}

// end of class ECFrame
