/*
 * PlayingBoard for Tetris game.
 * Fall 09.
 */
package tetris.gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import tetris.board.GameBoard;
import tetris.board.Row;

/**
 * A panel that draws the visible part of the game board.
 * @author Son Pham
 * @version 1.0
 */
@SuppressWarnings("serial")
public class PlayingBoard extends JPanel implements Observer
{
  /**
   * The stroke of the border.
   */
  private static final int BORDER = 5;
  
  /**
   * The width of the playing board.
   */
  private static final int WIDTH = 300;
  
  /**
   * The height of the playing board.
   */
  private static final int HEIGHT = WIDTH * 2;
  
  /**
   * The width of the cell block.
   */
  private static final double BLOCK = (double) WIDTH / GameBoard.WIDTH;
  
  /**
   * The Tetris game.
   */
  private GameBoard my_game;
  
  /**
   * Construct a PlayingBoard panel and passing to it a Tetris game.
   * @param the_game The Tetris game.
   */
  public PlayingBoard(final GameBoard the_game)
  {
    super();
    //setFocusable(true);
    my_game = the_game;
    my_game.addObserver(this);
    setBackground(Color.WHITE);
    setPreferredSize(new Dimension(WIDTH, HEIGHT));
    setBorder(BorderFactory.createLineBorder(Color.RED, BORDER));
  }
  
  /**
   * Paint the visible game board.
   * @param the_graphics The graphics.
   */
  public void paintComponent(final Graphics the_graphics)
  {
    super.paintComponent(the_graphics);
    final Graphics2D g2d = (Graphics2D) the_graphics;
    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    
    //g2d.setStroke(new BasicStroke(BORDER));
    //g2d.setColor(Color.RED);
    //final Shape container = new Rectangle2D.Double(0, 0, 100, 200);
    //g2d.draw(container);
    g2d.setStroke(new BasicStroke(1));
    
    for (int i = 0; i < GameBoard.VISIBLE_HEIGHT; i++)
    {
      drawRow(g2d, my_game.rows().get(i), HEIGHT - ((i + 1) * BLOCK));
    }
  }
  
  /**
   * Paint a single row on the board.
   * @param the_graphics The graphics.
   * @param the_row The row.
   * @param the_height The height of this row.
   */
  public void drawRow(final Graphics the_graphics, final Row the_row, final double the_height)
  {
    for (int i = 0; i < GameBoard.WIDTH; i++)
    {
      drawSquare(the_graphics, (int) (i * BLOCK), 
                 (int) the_height, the_row.getColorIndex(i));
    }
  }
  
  /**
   * Paint a single square on the board at the start point (the_x, the_y) and color is
   * the_color.
   * @param the_graphics The graphics.
   * @param the_x The x coordinate of the starting point.
   * @param the_y The y coordinate of the starting point.
   * @param the_color The color.
   */
  public void drawSquare(final Graphics the_graphics, final int the_x, 
                         final int the_y, final Color the_color)
  {
    final Graphics2D g2d = (Graphics2D) the_graphics;
    g2d.setColor(the_color);
    if (the_color.equals(Color.WHITE))
    {
      final Shape empty_square = new Rectangle2D.Double(the_x, the_y, BLOCK, BLOCK);
      g2d.fill(empty_square);
    }
    else
    {
      final Shape inner_square = new Rectangle2D.Double(the_x + 1, the_y + 1, 
                                                        BLOCK - 2, BLOCK - 2);
      g2d.fill(inner_square);
      
      //Draw the border of the square.
      
      //Draw the top and left borders.
      g2d.setColor(the_color.brighter());
      g2d.drawLine(the_x, the_y, the_x, (int) (the_y + BLOCK - 1));
      g2d.drawLine(the_x, the_y, (int) (the_x + BLOCK - 1), the_y);
      
      //Draw the right and bottom borders.
      g2d.setColor(the_color.darker());
      g2d.drawLine((int) (the_x + BLOCK - 1), the_y, (int) (the_x + BLOCK - 1), 
                   (int) (the_y + BLOCK - 1));
      g2d.drawLine(the_x, (int) (the_y + BLOCK - 1), (int) (the_x + BLOCK - 1), 
                   (int) (the_y + BLOCK - 1));    
    }
  }
  
  /**
   * {@inheritDoc}
   */
  public void update(final Observable the_obj, final Object the_arg)
  {
    my_game = (GameBoard) the_arg;
    repaint();
  }
}
