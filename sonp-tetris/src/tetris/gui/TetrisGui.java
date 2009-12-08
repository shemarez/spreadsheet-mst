/*
 * TetrisGui for Tetris game. Fall 09.
 */

package tetris.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

import tetris.board.GameBoard;

/**
 * The class handle the GUI of this Tetris game.
 * 
 * @author Son Pham.
 * @version 1.0
 */
@SuppressWarnings("serial")
public class TetrisGui extends JFrame
{
  //Static fields
  
  /**
   * The initial delay (in milliseconds) for the move timer.
   */
  public static final int INITIAL_MOVE_DELAY = 300;

  /**
   * The change (in milliseconds) in delay.
   */
  public static final int DELAY_CHANGE = 10;

  /**
   * The name of the frame.
   */
  private static final String FRAME_NAME = "A Tetris Clone";
  
  /**
   * The game move left key.
   */
  private static final int LEFT = 0;
  
  /**
   * The game move left right.
   */
  private static final int RIGHT = 1;
  
  /**
   * The game move left down.
   */
  private static final int DOWN = 2;
  
  /**
   * The game rotate clock_wise key.
   */
  private static final int ROTATE_CW = 3;
  
  /**
   * The game rotate counter clock_wise key.
   */
  private static final int ROTATE_CCW = 4;
  
  /**
   * The game move down to bottom key.
   */
  private static final int DROP_TO_BOTTOM = 5;
  
  /**
   * The game pause key.
   */
  private static final int PAUSE = 6;
  
  /**
   * The size of the string buffer.
   */
  private static final int BUFFER_SIZE = 200;
  
  //Instance fields
  
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
  
  /**
   * List of key_code of game control keys.
   */
  private List<Integer> my_keys_array;
  
  // Constructor

  /**
   * Construct a GUI panel for Tetris game.
   */
  public TetrisGui()
  {
    super(FRAME_NAME);
    my_game = new GameBoard();
    my_timer = new Timer(INITIAL_MOVE_DELAY, new PBMoveListener());
    addKeyListener(new PBKeyListener());
    setupKeys();
    setDefaultCloseOperation(EXIT_ON_CLOSE);
  }

  //Private methods
  
  /**
   * Set up the keys to move, rotate and pause for the Tetris game.
   */
  private void setupKeys()
  {
    final Integer[] the_array = new Integer[] {KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT,
                                               KeyEvent.VK_DOWN, KeyEvent.VK_SPACE,
                                               KeyEvent.VK_ALT, KeyEvent.VK_UP,
                                               KeyEvent.VK_P};
    my_keys_array = Arrays.asList(the_array);
  }
  
  /**
   * Replace the current key listener for this object by the_key_listener.
   * @param the_key_listener The key listener.
   */
  private void replaceKeyListener(final KeyListener the_key_listener)
  {
    removeKeyListener(getKeyListeners()[0]);
    addKeyListener(the_key_listener);
  }
  
  private class ModifyKey extends AbstractAction
  {
    private final int my_key_index;
    private final String my_name;
    
    public ModifyKey(final String the_name, final int the_key_index)
    {
      super(the_name);
      my_name = the_name;
      my_key_index = the_key_index;
    }
    
    /**
     * {@inheritDoc}
     */
    public void actionPerformed(final ActionEvent the_event)
    {
      my_is_paused = false;
      pause();
      JOptionPane.showMessageDialog(null, "Please enter your new key for " + my_name +
                                    ". Press the pause key to quit.");
      replaceKeyListener(new MKeyListener());
    }
    
    /**
     * Key listener class for ModifyKey action.
     * @author Son Pham
     * @version 1.0
     */    
    private class MKeyListener extends KeyAdapter
    {                
      /**
       * {@inheritDoc}
       */
      
      public void keyPressed(final KeyEvent the_key_event)
      {
        final int key_code = the_key_event.getKeyCode();
        if (my_keys_array.contains(key_code) && key_code != my_keys_array.get(PAUSE))
        {
          JOptionPane.showMessageDialog(null, "'" + 
                                        KeyEvent.getKeyText(key_code).toLowerCase() +
                                        "' is already chosen for other function. " +
                                        "Please type another key, or press the " +
                                        "pause key to quit.");
        }
        else
        {
          if (key_code != my_keys_array.get(PAUSE))
          {
            my_keys_array.set(my_key_index, key_code);
            JOptionPane.showMessageDialog(null, 
                                          "You picked '" + 
                                          KeyEvent.getKeyText(key_code).toLowerCase() +
                                          "' to be your new key for " + my_name +
                                          ". Press the pause key to resume.");
          }
          my_is_paused = true;
          pause();          
          replaceKeyListener(new PBKeyListener());
        }
      }
    }
  }
  
  /**
   * @return The string buffer of the game control keys.
   */
  private StringBuffer controlKeyIndex()
  {
    final StringBuffer sb = new StringBuffer(BUFFER_SIZE);
    sb.append("FUNCTION - KEY" + GameBoard.NEW_LINE);
    sb.append("Move left - " + KeyEvent.getKeyText(my_keys_array.get(LEFT)) + 
              GameBoard.NEW_LINE);
    sb.append("Move right - " + KeyEvent.getKeyText(my_keys_array.get(RIGHT)) + 
              GameBoard.NEW_LINE);
    sb.append("Move down - " + KeyEvent.getKeyText(my_keys_array.get(DOWN)) + 
              GameBoard.NEW_LINE);
    sb.append("Move down to bottom - " + 
              KeyEvent.getKeyText(my_keys_array.get(DROP_TO_BOTTOM)) + 
              GameBoard.NEW_LINE);
    sb.append("Rotate clockwise - " + KeyEvent.getKeyText(my_keys_array.get(ROTATE_CW)) + 
              GameBoard.NEW_LINE);
    sb.append("Rotate counter clockwise - " + 
              KeyEvent.getKeyText(my_keys_array.get(ROTATE_CCW)) + 
              GameBoard.NEW_LINE);          
    sb.append("Pause - " + KeyEvent.getKeyText(my_keys_array.get(PAUSE)) + 
              GameBoard.NEW_LINE);
    return sb;
  }
  
  // Instance methods.

  /**
   * Setup the components of the master panel.
   */
  public void setup()
  {
    final JPanel master_panel = new JPanel(new BorderLayout());
    final JPanel center_panel = new JPanel(new BorderLayout());
    final JPanel play_panel = new PlayingBoard(my_game);
    final JPanel right_panel = new JPanel(new BorderLayout());
    final JPanel next_piece_panel = new NextPieceBoard(my_game);
    right_panel.add(next_piece_panel, BorderLayout.NORTH);
    center_panel.add(play_panel, BorderLayout.CENTER);
    center_panel.add(right_panel, BorderLayout.EAST);
    master_panel.add(center_panel, BorderLayout.CENTER);
    add(master_panel);
    
    setJMenuBar(setupMenuBar());
    
    pack();
    setVisible(true);
  }
  
  /**
   * Set up the menu bar for the frame.
   * @return The JMenuBar for the frame.
   */
  public JMenuBar setupMenuBar()
  {
    final JMenuBar menu_bar = new JMenuBar();
    
    menu_bar.add(optionMenu());
    menu_bar.add(helpMenu());
    
    return menu_bar;
  }
  
  /**
   * @return The option menu.
   */
  public JMenu optionMenu()
  {
    final JMenu menu = new JMenu("Options");
    menu.setMnemonic('O');
    final JMenu sub_menu = new JMenu("Modify Keys");
        
    final Action move_left = new ModifyKey("Move left", LEFT);
    final Action move_right = new ModifyKey("Move right", RIGHT);
    final Action move_down = new ModifyKey("Move down", DOWN);
    final Action rotate_cw = new ModifyKey("Rotate clockwise", ROTATE_CW);
    final Action rotate_ccw = new ModifyKey("Rotate counter-clockwise", ROTATE_CCW);
    final Action drop_to_bottom = new ModifyKey("Drop to bottom", DROP_TO_BOTTOM);
    final Action pause = new ModifyKey("Pause", PAUSE);
    
    sub_menu.add(new JMenuItem(move_left));
    sub_menu.add(new JMenuItem(move_right));
    sub_menu.add(new JMenuItem(move_down));
    sub_menu.add(new JMenuItem(rotate_cw));
    sub_menu.add(new JMenuItem(rotate_ccw));
    sub_menu.add(new JMenuItem(drop_to_bottom));
    sub_menu.add(new JMenuItem(pause));
    
    menu.add(sub_menu);
    return menu;
  }
  
  /**
   * @return The help menu.
   */
  public JMenu helpMenu()
  {
    final JMenu help_menu = new JMenu("Help");
    help_menu.setMnemonic('H');
    final Action control_keys = 
      new AbstractAction("Control Keys")
      {
        public void actionPerformed(final ActionEvent the_event)
        {
          final StringBuffer sb = controlKeyIndex();
          JOptionPane.showMessageDialog(null, sb, "Game Control Keys", 1);
        }
      };
    help_menu.add(new JMenuItem(control_keys));
    return help_menu;
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
    if (!my_is_started || my_game.isGameOver())
    {
      return;
    }

    my_is_paused = my_is_paused ^ true;

    if (my_is_paused)
    {
      my_timer.stop();
      setTitle(FRAME_NAME + " - Paused!");
      replaceKeyListener(new PBPauseKeyListener());
    }
    else
    {
      my_timer.start();
      setTitle(FRAME_NAME);
      replaceKeyListener(new PBKeyListener());
    }
  }

  /**
   * A class that listens for the pause key.
   * @author Son Pham
   * @version 1.0
   */
  private class PBPauseKeyListener extends KeyAdapter
  {
    /**
     * {@inheritDoc}
     */
    public void keyPressed(final KeyEvent the_event)
    {
      final int key_code = the_event.getKeyCode();
      if (key_code == my_keys_array.get(PAUSE))
      {
        replaceKeyListener(new PBKeyListener());
        pause();
      }
    }
  }
  
  /**
   * A class that listens for timer events and re-draw the game board when
   * necessary.
   * 
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
   * 
   * @author Son Pham.
   * @version 1.0
   */
  private class PBKeyListener extends KeyAdapter
  {
    /**
     * {@inheritDoc}
     */
    public void keyPressed(final KeyEvent the_event)
    {
      final int key_code = the_event.getKeyCode();
      if (key_code == my_keys_array.get(PAUSE))
      {
        pause();
      }
      else if (key_code == my_keys_array.get(LEFT))
      {
        my_game.moveLeft();
      }
      else if (key_code == my_keys_array.get(RIGHT))
      {
        my_game.moveRight();
      }
      else if (key_code == my_keys_array.get(DOWN))
      {
        my_game.moveDown();
      }
      else if (key_code == my_keys_array.get(ROTATE_CW))
      {
        my_game.rotateClockwise();
      }
      else if (key_code == my_keys_array.get(ROTATE_CCW))
      {
        my_game.rotateCounterclockwise();
      }
      else if (key_code == my_keys_array.get(DROP_TO_BOTTOM))
      {
        my_game.moveDownToBottom();
      }
    }
  }

  /**
   * The main method of TetrisGUI.
   * 
   * @param the_args The command line argument (ignored).
   */
  public static void main(final String... the_args)
  {
    final TetrisGui the_frame = new TetrisGui();
    the_frame.setup();
    the_frame.start();
  }
}
