package tetris.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import tetris.board.GameBoard;

/**
 * ScoreBoard panel for Tetris game.
 * @author Son
 * @version 1.0
 */
@SuppressWarnings("serial")
public class ScoreBoard extends JPanel implements Observer
{  
  //Static field
  
  /**
   * The border.
   */
  private static final int BORDER = 5;
  
  /**
   * The background color.
   */
  private static final Color BG_COLOR = Color.RED;
  
  //Instance fields
  
  /**
   * Starting point of the first line.
   */
  private final Point my_first_line = new Point(10, 20);
  
  /**
   * Starting point of the second line.
   */
  private final Point my_second_line = new Point(10, 40);
  
  /**
   * My dimension.
   */
  private final Dimension my_dimension = new Dimension(100, 50);
  
  /**
   * The game Tetris.
   */
  private GameBoard my_game;
  
  /**
   * The score of my_game.
   */
  private int my_score;
  
  //Constructor
  
  /**
   * Construct a ScoreBoard object.
   * @param the_game The Tetris game.
   */
  public ScoreBoard(final GameBoard the_game)
  {
    super();
    my_game = the_game;
    my_game.addObserver(this);
    my_score = the_game.gameScore();
    setPreferredSize(my_dimension);
    setBorder(BorderFactory.createLineBorder(Color.BLACK, BORDER));
    //setBackground(Color.ORANGE);
  }
  
  //Instance methods
  
  /**
   * {@inheritDoc}
   */
  public void paintComponent(final Graphics the_graphics)
  {
    final Graphics2D g2d = (Graphics2D) the_graphics;
    g2d.setColor(BG_COLOR);
    final Shape s = new Rectangle2D.Double(0, 0, getSize().width, getSize().height);
    g2d.fill(s);
    g2d.setColor(Color.BLACK);
    g2d.drawString("POINTS: " + my_score, 
                   my_first_line.x,
                   my_first_line.y);
    g2d.drawString("LEVEL: " + (my_score / GameBoard.NEXT_LEVEL + 1), 
                   my_second_line.x,
                   my_second_line.y);
  }
  
  /**
   * {@inheritDoc}
   */
  public void update(final Observable the_obj, final Object the_arg)
  {
    my_game = (GameBoard) the_arg;
    if (my_score != my_game.gameScore())
    {
      my_score = my_game.gameScore();
      repaint();
    }
  }
}
