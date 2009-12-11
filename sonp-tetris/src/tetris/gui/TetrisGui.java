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
  public static final int DELAY_CHANGE = 30;
  
  /**
   * The name of the frame.
   */
  public static final String FRAME_NAME = "A Tetris Clone";
  
  /**
   * The game move left key.
   */
  public static final int LEFT = 0;
  
  /**
   * The game move left right.
   */
  public static final int RIGHT = 1;
  
  /**
   * The game move left down.
   */
  public static final int DOWN = 2;
  
  /**
   * The game rotate clock_wise key.
   */
  public static final int ROTATE_CW = 3;
  
  /**
   * The game rotate counter clock_wise key.
   */
  public static final int ROTATE_CCW = 4;
  
  /**
   * The game move down to bottom key.
   */
  public static final int DROP_TO_BOTTOM = 5;
  
  /**
   * The game pause key.
   */
  public static final int PAUSE = 6;
  
  /**
   * The size of the string buffer.
   */
  public static final int BUFFER_SIZE = 200;
  
  /**
   * The close bracket char.
   */
  public static final String CLOSE_BRACKET = "]";
  
  //Instance fields
  
  /**
   * The timer that controls the movement of the falling piece.
   */
  private final Timer my_timer;

  /**
   * The Tetris game.
   */
  private GameBoard my_game;

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
  
  /**
   * The level of this game.
   */
  private int my_level;
  
  
  /**
   * The master panel of TetrisGui.
   */
  private JPanel my_panel;
  
  /**
   * The delay (in millisecond) of this game.
   */
  //private int my_delay;

  
  // Constructor

  /**
   * Construct a GUI panel for Tetris game.
   */
  public TetrisGui()
  {
    super(FRAME_NAME);
    //startGame();
    my_game = new GameBoard();
    my_timer = new Timer(INITIAL_MOVE_DELAY, new PBMoveListener(this));
    my_level = 1;
    my_is_started = false;
    my_is_paused = false;
    my_panel = setupMasterPanel();
    //addKeyListener(new PBKeyListener());
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
  
  /**
   * A class to modify control keys.
   * @author Son Pham
   * @version 1.0
   */
  private class ModifyKey extends AbstractAction
  {
    /**
     * The key index in the control key indexes array.
     */
    private final int my_key_index;
    
    /**
     * The name of the key.
     */
    private final String my_name;
    
    /**
     * The frame contains this class.
     */
    private final JFrame my_frame;
    
    //Constructor
    
    /**
     * Construct an action with parameters the_name and the_key_index.
     * @param the_name The name.
     * @param the_key_index The key index.
     * @param the_frame The frame.
     */
    public ModifyKey(final String the_name, final int the_key_index,
                     final JFrame the_frame)
    {
      super(the_name);
      my_name = the_name;
      my_key_index = the_key_index;
      my_frame = the_frame;
    }
    
    /**
     * {@inheritDoc}
     */
    public void actionPerformed(final ActionEvent the_event)
    {
      my_is_paused = false;
      pauseGame();
      JOptionPane.showMessageDialog(my_frame, 
                                    "Press 'OK' and enter your new key for " + 
                                    my_name +
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
          JOptionPane.showMessageDialog(my_frame, "'" + 
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
            JOptionPane.showMessageDialog(my_frame, 
                                          "You picked '" + 
                                          KeyEvent.getKeyText(key_code).toLowerCase() +
                                          "' to be your new key for " + my_name +
                                          ". Press the pause key to resume.");
          }
          my_is_paused = true;
          pauseGame();          
          replaceKeyListener(new PBKeyListener());
        }
      }
    }
  }
  
  /**
   * NewGame class implements the action of new game key in the menu.
   * @author Son Pham
   * @version 1.0
   */
  private class NewGame extends AbstractAction
  {    
    /**
     * The frame that contains this action.
     */
    private final JFrame my_frame;
    
    //Constructor
    
    /**
     * Constructs a NewGame action with the inputs the_name and the_frame.
     * @param the_name The name.
     * @param the_frame The frame.
     */
    public NewGame(final String the_name, final JFrame the_frame)
    {
      super(the_name);
      my_frame = the_frame;
    }
    
    /**
     * {@inheritDoc}
     */
    public void actionPerformed(final ActionEvent the_event)
    {
      if (my_is_started)
      {
        final int answer = 
          JOptionPane.showConfirmDialog(my_frame, 
                                        "Do you want to quit the current game?", 
                                        "Tetris Clone - Confirm", 
                                        JOptionPane.YES_NO_OPTION);
        if (answer == 0)
        {
          endGame();
          startGame();
        }
      }
      else
      {
        startGame();
      }
    }
  }
  
  /**
   * @return The string buffer of the game control keys.
   */
  private StringBuffer controlKeyIndex()
  {
    final StringBuffer sb = new StringBuffer(BUFFER_SIZE);
    sb.append("FUNCTION:  [KEY]");
    sb.append(GameBoard.NEW_LINE);
    sb.append("Move left:  [" + 
              KeyEvent.getKeyText(my_keys_array.get(LEFT)) + 
              CLOSE_BRACKET + GameBoard.NEW_LINE);
    sb.append("Move right:  [" + 
              KeyEvent.getKeyText(my_keys_array.get(RIGHT)) + 
              CLOSE_BRACKET + GameBoard.NEW_LINE);
    sb.append("Move down:  [" + 
              KeyEvent.getKeyText(my_keys_array.get(DOWN)) + 
              CLOSE_BRACKET + GameBoard.NEW_LINE);
    sb.append("Move down to bottom:  [" + 
              KeyEvent.getKeyText(my_keys_array.get(DROP_TO_BOTTOM)) + 
              CLOSE_BRACKET + GameBoard.NEW_LINE);
    sb.append("Rotate clockwise:  [" + 
              KeyEvent.getKeyText(my_keys_array.get(ROTATE_CW)) + 
              CLOSE_BRACKET + GameBoard.NEW_LINE);
    sb.append("Rotate counter clockwise:  [" + 
              KeyEvent.getKeyText(my_keys_array.get(ROTATE_CCW)) + 
              CLOSE_BRACKET + GameBoard.NEW_LINE);          
    sb.append("Pause:  [" + 
              KeyEvent.getKeyText(my_keys_array.get(PAUSE)) + 
              CLOSE_BRACKET + GameBoard.NEW_LINE);
    return sb;
  }
  
  /**
   * Setup the components of the master panel includes play_panel, next_piece_panel,
   * and score_panel.
   * @return The master panel of TetrisGui.
   */
  private JPanel setupMasterPanel()
  {
    final JPanel master_panel = new JPanel(new BorderLayout());
    final JPanel play_panel = new PlayingBoard(my_game);
    final JPanel right_panel = new JPanel(new BorderLayout());
    final JPanel inner_right_panel = new JPanel(new BorderLayout());
    final JPanel next_piece_panel = new NextPieceBoard(my_game);
    right_panel.add(next_piece_panel, BorderLayout.NORTH);
    final JPanel score_panel = new ScoreBoard(my_game);
    inner_right_panel.add(score_panel, BorderLayout.NORTH);
    right_panel.add(inner_right_panel, BorderLayout.CENTER);
    master_panel.add(play_panel, BorderLayout.CENTER);
    master_panel.add(right_panel, BorderLayout.EAST);
    return master_panel;
    /*add(master_panel);
    
    setJMenuBar(setupMenuBar());
    
    pack();
    setVisible(true);*/
  }
  
  // Instance methods.
  
  /**
   * Set up the components for TetrisGui.
   */
  public void setup()
  {
    add(my_panel);
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
    
    menu_bar.add(fileMenu(this));
    menu_bar.add(optionMenu(this));
    menu_bar.add(helpMenu(this));
    
    return menu_bar;
  }
  
  /**
   * @param the_frame The frame.
   * @return The file menu.
   */
  public JMenu fileMenu(final JFrame the_frame)
  {
    final JMenu menu =  new JMenu("File");
    menu.setMnemonic('F');
    final Action new_game = new NewGame("New Game", the_frame);      
    final JMenuItem start_new_game = new JMenuItem(new_game);
    start_new_game.setMnemonic('N');
    menu.add(start_new_game);
    return menu;
  }
  
  /**
   * @return The option menu.
   * @param the_frame The frame.
   */
  public JMenu optionMenu(final JFrame the_frame)
  {
    final JMenu menu = new JMenu("Options");
    menu.setMnemonic('O');
    final JMenu sub_menu = new JMenu("Modify Keys");
    sub_menu.setMnemonic('M');
    
    final JMenuItem move_left = new JMenuItem(new ModifyKey("Move left", LEFT, the_frame));
    move_left.setMnemonic('l');
    final JMenuItem move_right = new JMenuItem(new ModifyKey("Move right", RIGHT, the_frame));
    move_right.setMnemonic('r');
    final JMenuItem move_down = new JMenuItem(new ModifyKey("Move down", DOWN, the_frame));
    move_down.setMnemonic('d');
    final JMenuItem rotate_cw = new JMenuItem(new ModifyKey("Rotate clockwise", ROTATE_CW, 
                                                            the_frame));
    rotate_cw.setMnemonic('c');
    final JMenuItem rotate_ccw = new JMenuItem(new ModifyKey("Rotate counter-clockwise", 
                                                             ROTATE_CCW, 
                                                             the_frame));
    rotate_ccw.setMnemonic('o');
    final JMenuItem drop_to_bottom = new JMenuItem(new ModifyKey("Drop to bottom", 
                                                                 DROP_TO_BOTTOM,
                                                                 the_frame));
    drop_to_bottom.setMnemonic('b');
    final JMenuItem pause = new JMenuItem(new ModifyKey("Pause", PAUSE, the_frame));
    pause.setMnemonic('p');
    
    sub_menu.add(move_left);
    sub_menu.add(move_right);
    sub_menu.add(move_down);
    sub_menu.add(rotate_cw);
    sub_menu.add(rotate_ccw);
    sub_menu.add(drop_to_bottom);
    sub_menu.add(pause);
    
    menu.add(sub_menu);
    return menu;
  }
  
  /**
   * @return The help menu.
   * @param the_frame The frame.
   */
  public JMenu helpMenu(final JFrame the_frame)
  {
    final JMenu help_menu = new JMenu("Help");
    help_menu.setMnemonic('H');
    final Action control_keys = 
      new AbstractAction("Control Keys")
      {
        public void actionPerformed(final ActionEvent the_event)
        {
          final StringBuffer sb = controlKeyIndex();
          JOptionPane.showMessageDialog(the_frame, sb, "Game Control Keys", 1);
        }
      };
    final JMenuItem keys_info = new JMenuItem(control_keys);
    keys_info.setMnemonic('C');
    help_menu.add(keys_info);
    return help_menu;
  }

  /**
   * Start the game. 
   * <li> Re-initialize the level. 
   * <li> Add the key listener to the frame. 
   * <li> Start the timer. 
   * <li> Set up the components of the frame.
   */
  public void startGame()
  {
    my_game.start();
    my_is_started = true;
    my_level = 1;
    addKeyListener(new PBKeyListener());
    my_timer.start();
    setup();
  }
  
  /**
   * End the game. 
   * <li> Re-initialize a new game. 
   * <li> Stop the timer. 
   * <li> Remove the key listener and the old panel of the frame.  
   */
  public void endGame()
  {
    my_game = new GameBoard();
    my_is_started = false;
    my_is_paused = false;
    my_timer.stop();
    removeKeyListener(getKeyListeners()[0]);
    remove(my_panel);
    my_panel = setupMasterPanel();
  }

  /**
   * Pause the game.
   */
  public void pauseGame()
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
      JOptionPane.showMessageDialog(this, "Game Paused!");
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
        pauseGame();
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
     * The frame of PBMoveListener.
     */
    private final JFrame my_frame;
    
    //Constructor
    
    /**
     * Construct a PBMoveListener.
     * @param the_frame The frame.
     */
    public PBMoveListener(final JFrame the_frame)
    {
      //super();
      my_frame = the_frame;
    }
    
    /**
     * {@inheritDoc}
     */
    public void actionPerformed(final ActionEvent the_event)
    {
      if (my_game.isGameOver())
      {
        my_timer.stop();
        setTitle(FRAME_NAME + " - GameOver!");
        removeKeyListener(getKeyListeners()[0]);
        JOptionPane.showMessageDialog(my_frame, "Game Over!");
      }
      else
      {
        my_game.update();
        if (my_game.gameScore() > my_level * GameBoard.NEXT_LEVEL)
        {
          //my_delay = my_delay - DELAY_CHANGE;
          my_level++;
          my_timer.setDelay(Math.max(my_timer.getDelay() - DELAY_CHANGE, 0));
        }
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
        pauseGame();
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
  }
}
