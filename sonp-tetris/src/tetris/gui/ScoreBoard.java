package tetris.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import tetris.board.GameBoard;

public class ScoreBoard extends JPanel implements Observer
{
  private GameBoard my_game;
  
  private int my_score;
  
  public ScoreBoard(final GameBoard the_game)
  {
    my_game = the_game;
    my_game.addObserver(this);
    my_score = the_game.gameScore();
    setPreferredSize(new Dimension(100, 50));
    setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
  }
  
  public void paintComponent(final Graphics the_graphics)
  {
    final Graphics2D g2d = (Graphics2D) the_graphics;
    g2d.drawString("Score: ", 10, 30);
    g2d.drawString(String.valueOf(my_score), 10, 40);
    System.err.println(my_score);
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
