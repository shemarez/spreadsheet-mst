/*
 * TetrisGui for Tetris game. Fall 09.
 */

package tetris.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
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
   * The game move left key.
   */
  private int my_left_key;
  
  /**
   * The game move left right.
   */
  private int my_right_key;
  
  /**
   * The game move left down.
   */
  private int my_down_key;
  
  /**
   * The game rotate clock_wise key.
   */
  private int my_rotate_cw_key;
  
  /**
   * The game rotate counter clock_wise key.
   */
  private int my_rotate_ccw_key;
  
  /**
   * The game move down to bottom key.
   */
  private int my_drop_to_bottom_key;
  
  /**
   * The game pause key.
   */
  private int my_pause_key;
  
  private KeyAdapter my_key_listener;
  
  /*
  //Possibly usefull for later.
  private int[] my_keys_array = new int[] {KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT,
                                           KeyEvent.VK_DOWN, KeyEvent.VK_SPACE,
                                           KeyEvent.VK_ALT, KeyEvent.VK_UP,
                                           KeyEvent.VK_P};
  private int my_left_key = my_keys_array[0];
  private int my_right_key = my_keys_array[1];
  private int my_down_key = my_keys_array[2];
  private int my_rotate_cw_key = my_keys_array[3];
  private int my_rotate_ccw_key = my_keys_array[4];
  private int my_drop_to_bottom_key = my_keys_array[5];
  private int my_pause_key = my_keys_array[6];*/
  
  // Constructor

  /**
   * Construct a GUI panel for Tetris game.
   */
  public TetrisGui()
  {
    super(FRAME_NAME);
    my_game = new GameBoard();
    my_timer = new Timer(INITIAL_MOVE_DELAY, new PBMoveListener());
    my_key_listener = new PBKeyListener();
    addKeyListener(my_key_listener);
    setupKeys();
    setDefaultCloseOperation(EXIT_ON_CLOSE);
  }

  /**
   * Set up the keys to move, rotate and pause for the Tetris game.
   */
  private void setupKeys()
  {
    my_left_key = KeyEvent.VK_LEFT;
    my_right_key = KeyEvent.VK_RIGHT;
    my_down_key = KeyEvent.VK_DOWN;
    my_rotate_cw_key = KeyEvent.VK_SPACE;
    my_rotate_ccw_key = KeyEvent.VK_ALT;
    my_drop_to_bottom_key = KeyEvent.VK_UP;
    my_pause_key = KeyEvent.VK_P;
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
    
    /*
    JMenuBar menu_bar = new JMenuBar();
    JMenu menu = new JMenu("Options");
    menu.setMnemonic('O');
    JMenu sub_menu = new JMenu("Modify Keys");*/
    /*
    class ModifyKey extends AbstractAction
    {
      private int my_key_index;
      private String my_name;
      
      public ModifyKey(final String the_name, final int the_key_index)
      {
        super(the_name);
        my_name = the_name;
        my_key_index = the_key_index;
        setFocusable(true);
        addKeyListener(new MKeyListener());
      }

      public void actionPerformed(final ActionEvent the_event)
      {
        JOptionPane.showInputDialog(null, "Please enter your new key to move left: ");
      }*/
      
      /**
       * Key listener class for ModifyKey action.
       * @author Son Pham
       * @version 1.0
       */
      /*
      class MKeyListener extends KeyAdapter
      {        
        /**
         * {@inheritDoc}
         */
        /*
        public void keyTyped(final KeyEvent the_key_event)
        {
          final int key_code = the_key_event.getKeyCode();
          my_keys_array[my_key_index] = key_code;
          JOptionPane.showMessageDialog(null, "You picked " + the_key_event.getKeyChar() +
                                        " to be your new key for " + my_name.toLowerCase());
        }*/
        
        /**
         * {@inheritDoc}
         */
        /*
        public void keyPressed(final KeyEvent the_key_event)
        {
          final int key_code = the_key_event.getKeyCode();
          my_keys_array[my_key_index] = key_code;
          JOptionPane.showMessageDialog(null, 
                                        "You picked " + 
                                        KeyEvent.getKeyText(key_code) +
                                        " to be your new key for " + my_name);
        }
      }
    }
    
    Action move_left = new ModifyKey("Move left", 0);
    Action move_right = new ModifyKey("Move right", 1);
    Action move_down = new ModifyKey("Move down", 2);
    Action rotate_cw = new ModifyKey("Rotate clockwise", 3);
    Action rotate_ccw = new ModifyKey("Rotate counter-clockwise", 4);
    Action drop_to_bottom = new ModifyKey("Drop to bottom", 5);
    Action pause = new ModifyKey("Pause", 6);
    
    sub_menu.add(new JMenuItem(move_left));
    sub_menu.add(new JMenuItem(move_right));
    sub_menu.add(new JMenuItem(move_down));
    sub_menu.add(new JMenuItem(rotate_cw));
    sub_menu.add(new JMenuItem(rotate_ccw));
    sub_menu.add(new JMenuItem(drop_to_bottom));
    sub_menu.add(new JMenuItem(pause));*/
    /*
    menu.add(sub_menu);
    menu_bar.add(menu);
    setJMenuBar(menu_bar);*/
    
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
    if (!my_is_started || my_game.isGameOver())
    {
      return;
    }

    my_is_paused = my_is_paused ^ true;

    if (my_is_paused)
    {
      my_timer.stop();
      setTitle(FRAME_NAME + " - Paused!");
      removeKeyListener(my_key_listener);
      my_key_listener = new PBPauseKeyListener();
      addKeyListener(my_key_listener);
    }
    else
    {
      my_timer.start();
      setTitle(FRAME_NAME);
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
      if (key_code == my_pause_key)
      {
        my_key_listener = new PBKeyListener();
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
    /*
     * public void keyTyped(final KeyEvent the_event) { final char key =
     * the_event.getKeyChar(); if (key == 'p' || key == 'P') { pause(); return;
     * }
     * 
     * switch (key) { case '.': my_game.rotateClockwise(); break; case '/':
     * my_game.rotateCounterclockwise(); break; case 'a': my_game.moveLeft();
     * break; case 'd': my_game.moveRight(); break; case 's':
     * my_game.moveDown(); break; case ' ': my_game.moveDownToBottom(); break;
     * default: break; } }
     */

    /**
     * {@inheritDoc}
     */
    public void keyPressed(final KeyEvent the_event)
    {
      final int key_code = the_event.getKeyCode();
      if (key_code == my_pause_key)
      {
        pause();
      }
      else if (key_code == my_left_key)
      {
        my_game.moveLeft();
      }
      else if (key_code == my_right_key)
      {
        my_game.moveRight();
      }
      else if (key_code == my_down_key)
      {
        my_game.moveDown();
      }
      else if (key_code == my_left_key)
      {
        my_game.moveLeft();
      }
      else if (key_code == my_drop_to_bottom_key)
      {
        my_game.moveDownToBottom();
      }
      else if (key_code == my_rotate_cw_key)
      {
        my_game.rotateClockwise();
      }
      else if (key_code == my_rotate_ccw_key)
      {
        my_game.rotateCounterclockwise();
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
