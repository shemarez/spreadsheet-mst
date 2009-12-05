/*
 * TetrisGui for Tetris game.
 * Fall 09.
 */
package tetris.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

import tetris.board.GameBoard;

/**
 * The class handle the GUI of this Tetris game.
 * @author Son Pham.
 * @version 1.0
 */
@SuppressWarnings("serial")
public class TetrisGui extends JFrame
{
  /**
   * The initial delay (in milliseconds) for the move timer.
   */
  public static final int INITIAL_MOVE_DELAY = 200;
  
  /**
   * The change (in milliseconds) in delay.
   */
  public static final int DELAY_CHANGE = 10;
  
  /**
   * The name of the frame.
   */
  private static final String FRAME_NAME = "A Tetris Clone";
  /**
   * The timer that controls the movement of the falling piece.
   */
  private final Timer my_timer;
  
  /**
   * The Tetris game.
   */
  private final GameBoard my_game;
  
  /**
   * Has the game been started.
   */
  private boolean my_is_started;
  
  /**
   * Has the game been paused.
   */
  private boolean my_is_paused;
  
  private char my_left_key;
  private char my_right_key;
  private char my_down_key;
  private char my_rotate_cw_key;
  private char my_rotate_ccw_key;
  private char my_drop_to_bottom_key;
  
  //Constructor
  
  /**
   * Construct a GUI panel for Tetris game.
   */
  public TetrisGui()
  {
    super(FRAME_NAME);
    my_game = new GameBoard();
    my_timer = new Timer(INITIAL_MOVE_DELAY, new PBMoveListener());
    addKeyListener(new PBKeyListener());
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    my_is_started = false;
    my_is_paused = false;
  }
  
  //Instance methods.
  
  /**
   * Setup the components of the master panel.
   */
  public void setup()
  {
    final JPanel master_panel = new JPanel(new BorderLayout());
    final JPanel play_panel = new PlayingBoard(my_game);
    final JPanel right_panel = new JPanel(new BorderLayout());
    final JPanel next_piece_panel = new NextPieceBoard(my_game);
    right_panel.add(next_piece_panel, BorderLayout.NORTH);
    master_panel.add(play_panel, BorderLayout.CENTER);
    master_panel.add(right_panel, BorderLayout.EAST);
    add(master_panel);
    
    JMenuBar menu_bar = new JMenuBar();
    JMenu menu = new JMenu("Options");
    menu.setMnemonic('O');
    JMenu sub_menu = new JMenu("Keys Editing");
    
    Action keyLeft = 
      new AbstractAction("Move left")
      {
        public void actionPerformed(final ActionEvent the_event)
        {
          JOptionPane.showInputDialog(null, "Please enter your new key: ", my_left_key);
        }
      };
    
    pack();
    setVisible(true);
  }
  
  /**
   * Start the game.
   */
  public void start()
  {
    my_is_started = true;
    my_timer.start();
  }
  
  /**
   * Pause the game.
   */
  public void pause()
  {
    if (!my_is_started)
    {
      return;
    }
    
    my_is_paused = my_is_paused ^ true;
    
    if (my_is_paused)
    {
      my_timer.stop();
      setTitle(FRAME_NAME + " - Paused!");
    }
    else
    {
      my_timer.start();
      setTitle(FRAME_NAME);
    }
  }
  
  /**
   * A class that listens for timer events and re-draw the game board
   * when necessary.
   * @author Son Pham.
   * @version 1.0
   */
  private class PBMoveListener implements ActionListener
  {
    /**
     * {@inheritDoc}
     */
    public void actionPerformed(final ActionEvent the_event)
    {
      if (my_game.isGameOver())
      {
        my_timer.stop();
        setTitle(FRAME_NAME + " - GameOver!");
      }
      else
      {
        my_game.update();
      }
    }
  }
  
  /**
   * A class that listener to key typed.
   * @author Son Pham.
   * @version 1.0
   */
  private class PBKeyListener extends KeyAdapter
  {
    /**
     * {@inheritDoc}
     */
    public void keyTyped(final KeyEvent the_event)
    {
      final char key = the_event.getKeyChar();
      if (key == 'p' || key == 'P')
      {
        pause();
        return;
      }
      
      switch (key)
      {
        case '.':
          my_game.rotateClockwise();
          break;
        case '/':
          my_game.rotateCounterclockwise();
          break;
        case 'a':
          my_game.moveLeft();
          break;
        case 'd':
          my_game.moveRight();
          break;
        case 's':
          my_game.moveDown();
          break;
        case ' ':
          my_game.moveDownToBottom();
          break;
        default:
          break;
      }
    }
  }
  
  /**
   * The main method of TetrisGUI.
   * @param the_args The command line argument (ignored).
   */
  public static void main(final String... the_args)
  {
    final TetrisGui the_frame = new TetrisGui();
    the_frame.setup();
    the_frame.start();
  }
}
