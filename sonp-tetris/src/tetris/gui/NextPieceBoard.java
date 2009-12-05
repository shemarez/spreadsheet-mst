/*
 * NextPieceBoard for Tetris game. Fall 09.
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
import tetris.entities.pieces.Piece;

/**
 * The board draws the next piece in waiting.
 * 
 * @author Son Pham
 * @version 1.0
 */
@SuppressWarnings("serial")
public class NextPieceBoard extends JPanel implements Observer
{
  /**
   * The width of the next piece board.
   */
  private static final int WIDTH = 120;

  /**
   * The width of the cell block.
   */
  private static final double BLOCK = (double) WIDTH / Piece.NUMBER_OF_BLOCKS;
  
  /**
   * The border of the panel.
   */
  private static final int BORDER = 5;

  /**
   * The Tetris game.
   */
  private GameBoard my_game;

  /**
   * The next piece.
   */
  private Piece my_next_piece;

  /**
   * Construct a NextPieceBoard panel and passing to it a Tetris game.
   * 
   * @param the_game The Tetris game.
   */
  public NextPieceBoard(final GameBoard the_game)
  {
    super();
    // setFocusable(true);
    my_game = the_game;
    my_next_piece = my_game.nextPiece();
    my_game.addObserver(this);
    setBackground(Color.WHITE);
    setPreferredSize(new Dimension(WIDTH, WIDTH));
    setBorder(BorderFactory.createLineBorder(Color.BLACK, BORDER));
  }

  /**
   * Paint the next piece.
   * 
   * @param the_graphics The graphics.
   */
  public void paintComponent(final Graphics the_graphics)
  {
    super.paintComponent(the_graphics);
    final Graphics2D g2d = (Graphics2D) the_graphics;
    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

    g2d.setStroke(new BasicStroke(1));
    drawPiece(g2d, my_next_piece);
  }

  /**
   * Paint a single row on the board.
   * 
   * @param the_graphics The graphics.
   * @param the_piece The piece.
   */
  public void drawPiece(final Graphics the_graphics, final Piece the_piece)
  {
    for (int i = 0; i < Piece.NUMBER_OF_BLOCKS; i++)
    {
      drawSquare(the_graphics, 
                 (int) (the_piece.absolutePosition(i).x() * BLOCK),
                 (int) (WIDTH - (the_piece.absolutePosition(i).y() + 1) * BLOCK), 
                 the_piece.color());
    }
  }

  /**
   * Paint a single square on the board at the start point (the_x, the_y) and
   * color is the_color.
   * 
   * @param the_graphics The graphics.
   * @param the_x The x coordinate of the starting point.
   * @param the_y The y coordinate of the starting point.
   * @param the_color The color.
   */
  public void drawSquare(final Graphics the_graphics, final int the_x, final int the_y,
                         final Color the_color)
  {
    final Graphics2D g2d = (Graphics2D) the_graphics;
    g2d.setColor(the_color);

    final Shape inner_square =
        new Rectangle2D.Double(the_x + 1, the_y + 1, BLOCK - 2, BLOCK - 2);
    g2d.fill(inner_square);

    // Draw the border of the square.

    // Draw the top and left borders.
    g2d.setColor(the_color.brighter());
    g2d.drawLine(the_x, the_y, the_x, (int) (the_y + BLOCK - 1));
    g2d.drawLine(the_x, the_y, (int) (the_x + BLOCK - 1), the_y);

    // Draw the right and bottom borders.
    g2d.setColor(the_color.darker());
    g2d.drawLine((int) (the_x + BLOCK - 1), the_y, (int) (the_x + BLOCK - 1),
                 (int) (the_y + BLOCK - 1));
    g2d.drawLine(the_x, (int) (the_y + BLOCK - 1), (int) (the_x + BLOCK - 1),
                 (int) (the_y + BLOCK - 1));
  }

  /**
   * {@inheritDoc}
   */
  public void update(final Observable the_obj, final Object the_arg)
  {
    my_game = (GameBoard) the_arg;
    if (!my_next_piece.equals(my_game.nextPiece()))
    {
      my_next_piece = my_game.nextPiece();
      repaint();
    }
  }
}
