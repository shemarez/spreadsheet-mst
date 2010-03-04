package gui;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import spreadsheet.Spreadsheet;

/**
 * Spreadsheet GUI.
 * 
 * @author Son Pham
 * @version 1.0
 */
@SuppressWarnings("serial")
public class SpreadSheetGui extends JFrame {
	/**
	 * Name of this spreadsheet GUI frame.
	 */
	public static final String FRAME_NAME = "TCSS-342 Simple SpreadSheet";

	public static final int NUM_COLS = 12;

	public static final int NUM_ROWS = 10;

	/**
	 * The main method of SpreadSheetGui.
	 * 
	 * @param the_args
	 *            The command line argument (ignored).
	 */
	public static void main(final String... the_args) {
		final SpreadSheetGui the_frame = new SpreadSheetGui(new Spreadsheet(
				NUM_ROWS, NUM_COLS));
		the_frame.setUp();
	}

	// Constructor

	/**
	 * The cell table in the spreadsheet GUI.
	 */
	private SpreadSheetBoard my_board;

	/**
	 * Construct a SpreadSheetGui.
	 * 
	 * @param the_spreadsheet
	 *            The spreadsheet.
	 */
	public SpreadSheetGui(final Spreadsheet the_spreadsheet) {
		super(FRAME_NAME);
		my_board = new SpreadSheetBoard(the_spreadsheet);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	@SuppressWarnings("unused")
	private JMenu editMenu() {
		final JMenu edit_menu = new JMenu("Edit");
		edit_menu.setMnemonic('E');
		return edit_menu;
	}

	private JMenu fileMenu() {
		final JMenu file_menu = new JMenu("File");
		file_menu.setMnemonic('F');
		final JMenuItem reset = new JMenuItem(new AbstractAction("New") {
			@Override
			public void actionPerformed(final ActionEvent the_event) {
				my_board.reset();
				my_board.update();
			}
		});
		reset.setMnemonic('N');
		file_menu.add(reset);
		final JMenuItem open = new JMenuItem(new AbstractAction("Open") {
			@Override
			public void actionPerformed(final ActionEvent the_event) {
				JFileChooser fc = new JFileChooser();
				int returnVal = fc.showOpenDialog(SpreadSheetGui.this);
				if (returnVal == JFileChooser.APPROVE_OPTION)
					try {
						my_board.load(fc.getSelectedFile().getCanonicalPath());
					} catch (Exception e) {
					}
				my_board.update();
			}
		});
		open.setMnemonic('O');
		file_menu.add(open);
		final JMenuItem save = new JMenuItem(new AbstractAction("Save") {
			@Override
			public void actionPerformed(final ActionEvent the_event) {
				JFileChooser fc = new JFileChooser();
				int returnVal = fc.showSaveDialog(SpreadSheetGui.this);
				if (returnVal == JFileChooser.APPROVE_OPTION)
					try {
						my_board.save(fc.getSelectedFile().getCanonicalPath());
					} catch (Exception e) { }
				my_board.update();
			}
		});
		save.setMnemonic('S');
		file_menu.add(save);
		file_menu.addSeparator();
		final Action close = new AbstractAction("Quit") {
			public void actionPerformed(final ActionEvent the_event) {
				dispose();
			}
		};
		final JMenuItem quit = new JMenuItem(close);
		quit.setMnemonic('Q');
		file_menu.add(quit);
		return file_menu;
	}

	private JMenu helpMenu() {
		final JMenu help_menu = new JMenu("Help");
		help_menu.setMnemonic('H');
		final StringBuffer sb = new StringBuffer();
		sb.append("Authors: \n");
		sb.append("Miles Raymond - Teddy Doll - Son Pham\n");
		sb
				.append("Documentation: http://code.google.com/p/spreadsheet-mst/wiki/HowToUseTheSpreadsheet");
		final Action about = new AbstractAction("About...") {
			public void actionPerformed(final ActionEvent the_event) {
				JOptionPane.showMessageDialog(null, sb,
						"About Simple Spreadsheet", 1);
			}
		};
		JMenuItem menu_item = new JMenuItem(about);
		menu_item.setMnemonic('A');
		help_menu.add(menu_item);
		return help_menu;
	}

	/**
	 * @return The menu bar.
	 */
	private JMenuBar menuBar() {
		final JMenuBar menu_bar = new JMenuBar();
		menu_bar.add(fileMenu());
		// menu_bar.add(editMenu());
		menu_bar.add(helpMenu());
		return menu_bar;
	}

	/**
	 * Setup the GUI.
	 */
	public void setUp() {
		add(my_board);
		setJMenuBar(menuBar());
		pack();
		setVisible(true);
	}
}
