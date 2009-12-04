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
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;
import java.util.Observable;
import java.util.Observer;

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
   * The width of the playing board.
   */
  private static final int WIDTH = 400;
  
  /**
   * The height of the playing board.
   */
  private static final int HEIGHT = WIDTH * 2;
  
  /**
   * The width of the cell block.
   */
  private static final double BLOCK = (double) WIDTH / GameBoard.WIDTH;
  
  /**
   * The stroke of the border.
   */
  private static final int BORDER = 5;
  
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
    my_game = the_game;
    my_game.addObserver(this);
    setBackground(Color.WHITE);
    setPreferredSize(new Dimension(WIDTH, HEIGHT));
    addKeyListener(new PBListener());
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
    
    g2d.setStroke(new BasicStroke(BORDER));
    g2d.setColor(Color.RED);
    final Shape container = new Rectangle2D.Double(0, 0, WIDTH, HEIGHT);
    g2d.draw(container);
    
    for (int i = 0; i < GameBoard.VISIBLE_HEIGHT; i++)
    {
      drawRow(g2d, my_game.rows().get(i), i * BLOCK);
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
    final Graphics2D g2d = (Graphics2D) the_graphics;
    for (int i = 0; i < GameBoard.WIDTH; i++)
    {
      final Shape block = new Rectangle2D.Double((GameBoard.WIDTH - i) * BLOCK, 
                                                 the_height, BLOCK, BLOCK);
      g2d.setColor(the_row.getColorIndex(i));
      g2d.fill(block);
    }
  }
  
  public void update(final Observable the_obj, final Object the_arg)
  {
    my_game = (GameBoard) the_arg;
    repaint();
  }
  
  private class PBListener extends KeyAdapter
  {
    public void keyTyped(final KeyEvent the_event)
    {
      if (the_event.getKeyChar() == 'x')
      {
        my_game.rotateClockwise();
      }
      else if (the_event.getKeyChar() == 'z')
      {
        my_game.rotateCounterclockwise();
      }
      else if (the_event.getKeyChar() == KeyEvent.VK_LEFT)
      {
        my_game.moveLeft();
      }
      else if (the_event.getKeyChar() == KeyEvent.VK_RIGHT)
      {
        my_game.moveRight();
      }
      else if (the_event.getKeyChar() == KeyEvent.VK_DOWN)
      {
        my_game.moveDown();
      }
    }
  }
}
