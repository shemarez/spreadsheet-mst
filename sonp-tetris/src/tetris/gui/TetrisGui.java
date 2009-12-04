package tetris.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import tetris.board.GameBoard;

public class TetrisGui extends JFrame
{
  /**
   * The initial delay (in milliseconds) for the move timer.
   */
  public static final int INITIAL_MOVE_DELAY = 20;
  
  public static final int DELAY_CHANGE = 5;
  
  /**
   * The timer that controls the movement of the falling piece.
   */
  private final Timer my_move_timer;
  
  /**
   * The Tetris game.
   */
  private final GameBoard my_game;
  
  public TetrisGui()
  {
    super("A Tetris Clone");
    my_game = new GameBoard();
    my_move_timer = new Timer(INITIAL_MOVE_DELAY, new PBMoveListener());
    addKeyListener(new PBKeyListener());
    setDefaultCloseOperation(EXIT_ON_CLOSE);
  }
  
  public void setup()
  {
    final JPanel master_panel = new JPanel(new BorderLayout());
    final JPanel play_panel = new PlayingBoard(my_game);
    master_panel.add(play_panel, BorderLayout.CENTER);
    add(master_panel);
    pack();
    setVisible(true);
  }
  
  public void start()
  {
    my_move_timer.start();
  }
  
  /**
   * A class that listens for timer events and re-draw the game board
   * when necessary.
   */
  private class PBMoveListener implements ActionListener
  {
    public void actionPerformed(final ActionEvent the_event)
    {
      my_game.update();
    }
  }
  
  private class PBKeyListener extends KeyAdapter
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
