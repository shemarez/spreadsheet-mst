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

import javax.swing.JPanel;

import tetris.board.GameBoard;
import tetris.board.Row;

@SuppressWarnings("serial")
public class TetrisDrawingBoard extends JPanel implements Observer
{    
  /**
   * The width of the playing board.
   */
  private int my_width;
  
  /**
   * The height of the playing board.
   */
  private int my_height;
  
  /**
   * The width of the cell block.
   */
  private double my_block_width;
  
  /**
   * The height of the cell block.
   */
  private double my_block_height;
  
  /**
   * The Tetris game.
   */
  private GameBoard my_game;
  
  /**
   * The background color.
   */
  private Color my_bg_color;
  
  //Constructor
  
  /**
   * Construct a TetrisDrawingBoard panel and passing to it a Tetris game, background
   * color, the_width and the_height.
   * @param the_game The Tetris game.
   * @param the_color The color.
   * @param the_width The width.
   * @param the_height The height.
   */
  public TetrisDrawingBoard(final GameBoard the_game, final Color the_color,
                            final int the_width, final int the_height)
  {
    my_game = the_game;
    my_game.addObserver(this);
    setPreferredSize(new Dimension(the_width, the_height));
    my_bg_color = the_color;
  }
  
  /**
   * Setup my_width, my_height, my_block_width, my_block_height.
   * @param the_width The width.
   * @param the_height The height.
   */
  private void setupDimensions(final int the_width, final int the_height)
  {
    my_width = the_width;
    my_height = the_height;
    my_block_width = (double) the_width / GameBoard.WIDTH;
    my_block_height = (double) the_height / GameBoard.VISIBLE_HEIGHT;
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
    setupDimensions(getSize().width, getSize().height);
    g2d.setStroke(new BasicStroke(1));
    
    for (int i = 0; i < GameBoard.VISIBLE_HEIGHT; i++)
    {
      drawRow(g2d, my_game.rows().get(i), my_height - (i + 1) * my_block_height);
    }
    
    //final Shape s = new Rectangle2D.Double(0, 0, getSize().width, getSize().height);
    //g2d.setColor(my_bg_color);
    //g2d.fill(s);    
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
      if (!the_row.getColorIndex(i).equals(Row.EMPTY_COLOR))
      {
        drawSquare(the_graphics, (int) (i * my_block_width), 
                  (int) the_height, the_row.getColorIndex(i));
      }
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
    //g2d.setColor(the_color);
    if (the_color.equals(Row.EMPTY_COLOR))
    {
      g2d.setColor(my_bg_color);
      final Shape empty_square = 
        new Rectangle2D.Double(the_x, the_y, my_block_width, my_block_height);
      g2d.fill(empty_square);
    }
    else
    {
      g2d.setColor(the_color);
      final Shape inner_square = 
        new Rectangle2D.Double(the_x + 1, the_y + 1,                            
                               my_block_width - 2, my_block_height - 2);
      g2d.fill(inner_square);
      
      //Draw the border of the square.
      
      //Draw the top and left borders.
      g2d.setColor(the_color.brighter());
      g2d.drawLine(the_x, the_y, the_x, (int) (the_y + my_block_height - 1));
      g2d.drawLine(the_x, the_y, (int) (the_x + my_block_width - 1), the_y);
      
      //Draw the right and bottom borders.
      g2d.setColor(the_color.darker());
      g2d.drawLine((int) (the_x + my_block_width - 1), 
                   the_y, 
                   (int) (the_x + my_block_width - 1), 
                   (int) (the_y + my_block_height - 1));
      g2d.drawLine(the_x, 
                   (int) (the_y + my_block_height - 1), 
                   (int) (the_x + my_block_width - 1), 
                   (int) (the_y + my_block_height - 1));    
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
