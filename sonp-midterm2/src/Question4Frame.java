/*
 * Autumn 2009 TCSS 305 Midterm 2
 * Question 4 - Drawing with Java2D
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

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
  // Static Fields
  
  /**
   * The frame title.
   */
  private static final String TITLE = "2D Graphics";
  
  /**
   * The dimensions of the buffer panels.
   */
  private static final int BUFFER_SPACE = 20;
  
  /**
   * The dimensions to add in the window.
   */
  private static final int WINDOW_BUFFER = 60;
  
  /**
   * The width of the graphics panel.
   */
  private static final int WIDTH = 300;
  
  /**
   * The height of the graphics panel.
   */
  private static final int HEIGHT = 150;
  
  // Constructor
  
  /**
   * Constructs a new Question4Frame. 
   */
  public Question4Frame()
  {
    super(TITLE);
    setupPanels();
    setDefaultCloseOperation(EXIT_ON_CLOSE);
  }
  
  // Instance Methods
  
  /**
   * Lays out the components in, and displays, the frame.
   */
  private void setupPanels()
  {
    // buffer panels, so we can see the whole drawing
    
    add(buffer(), BorderLayout.WEST);
    add(buffer(), BorderLayout.NORTH);
    add(new GraphicsPanel(), BorderLayout.CENTER);
    setSize(WIDTH + WINDOW_BUFFER, HEIGHT + WINDOW_BUFFER);
  }
  
  /**
   * @return a buffer panel.
   */
  private JPanel buffer()
  {
    final JPanel result = new JPanel();
    result.setBackground(Color.WHITE);
    result.setPreferredSize(new Dimension(BUFFER_SPACE, BUFFER_SPACE));
    return result;
  }
  
  /**
   * The panel class that actually does the drawing.
   * 
   * @author Daniel M. Zimmerman
   * @author (your name here)
   * @version Winter 2009
   */
  private static class GraphicsPanel extends JPanel
  {
    /**
     * Constructs a new GraphicsPanel.  
     */
    public GraphicsPanel()
    {
      super();
      setBackground(Color.WHITE);
      setPreferredSize(new Dimension(WIDTH, HEIGHT));
    }
    
    // fill in the rest of this inner class to do the 2D drawing
    // you should not need to implement or change any other methods, 
    // fields or classes in the containing Question4Frame class,
    // though you may want to add constants somewhere to avoid
    // magic number warnings if you're going for extra credit.
    // you should only need 3 constants to calculate all the 
    // necessary numbers.
  }
  
  // Main Method
  
  /**
   * Creates and displays a Question4Frame.
   * 
   * @param the_args Command line arguments, ignored.
   */
  public static void main(final String... the_args)
  {
    final Question4Frame frame = new Question4Frame();
    frame.setVisible(true);
  }
}
