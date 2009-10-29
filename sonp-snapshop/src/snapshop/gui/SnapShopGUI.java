/*
 * Homework 3 - SnapShopGUI
 * Autumn 2009 TCSS 305
 */


package snapshop.gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import snapshop.filters.EdgeDetectFilter;
import snapshop.filters.EdgeHighlightFilter;
import snapshop.filters.Filter;
import snapshop.filters.FlipHorizontalFilter;
import snapshop.filters.FlipVerticalFilter;
import snapshop.filters.GrayscaleFilter;
import snapshop.filters.SharpenFilter;
import snapshop.filters.SoftenFilter;
import snapshop.image.PixelImage;

/**
 * A class that create a window panel for opening, filtering and saving image.
 * 
 * @author Son
 * @version 1.0
 */
public class SnapShopGUI extends JFrame
{
  //Instance fields
  
  /**
   * The master panel.
   */
  private JPanel my_master_panel;
  
  /**
   * The north panel.
   */
  private JPanel my_north_panel;
  
  /**
   * The center panel.
   */
  private JPanel my_center_panel;
  
  /**
   * The south panel.
   */
  private JPanel my_south_panel;
  
  /**
   * The file choose object.
   */
  private final  JFileChooser my_file_chooser = new JFileChooser();
  
  /**
   * The result of opening a showOpenDialog or showSaveDialog.
   */
  private int my_result;
  
  /**
   * The loaded image.
   */
  private PixelImage my_image;
  
  /**
   * The label object.
   */
  private final JLabel my_label =  new JLabel();
  
  /**
   * The map of filter names and objects.
   */
  private final  Map<String, Filter> my_filter_treemap = new TreeMap<String, Filter>();
  
  //Constructor
  
  /**
   * Construct a SnapShopGUI object.
   */
  public SnapShopGUI()
  {
    super("TCSS 305 SnapShot");
    setupComponents();
  }
  
  //Instance methods
  
  /**
   * Create a button that calls a specified filter on the image.
   * 
   * @param the_object The FilterObject needed to create a button.
   * @return the button.
   */
  private JButton createButton(final FilterObject the_object)
  {
    final JButton button = new JButton(the_object.toString());
    button.addActionListener(new ActionListener()
    {
      public void actionPerformed(final ActionEvent the_event)
      {
        the_object.doFilter(my_image);
        my_label.setIcon(new ImageIcon(my_image));
        pack();
      }
    });
    
    return button;
  }
  
  /**
   * Setup the components for the SnapShopGUI object.
   */
  private void setupComponents()
  {
    my_master_panel = new JPanel(new BorderLayout());
    my_north_panel = new JPanel(new FlowLayout());
    my_center_panel = new JPanel(new FlowLayout());
    my_south_panel = new JPanel(new FlowLayout());
    
    my_filter_treemap.put("Edge Detect", new EdgeDetectFilter());
    my_filter_treemap.put("Edge Highlight", new EdgeHighlightFilter());
    my_filter_treemap.put("Flip Horizontal", new FlipHorizontalFilter());
    my_filter_treemap.put("Flip Vertical", new FlipVerticalFilter());
    my_filter_treemap.put("Grayscale", new GrayscaleFilter());
    my_filter_treemap.put("Sharpen", new SharpenFilter());
    my_filter_treemap.put("Soften", new SoftenFilter());
    
    final Iterator<String> the_iterator = my_filter_treemap.keySet().iterator();
    
    while (the_iterator.hasNext())
    {
      final String the_current_string = the_iterator.next();
      final JButton new_button = createButton(new FilterObject(the_current_string, 
                                              my_filter_treemap.get(the_current_string)));
      new_button.setEnabled(false);
      my_north_panel.add(new_button);
    }
     
    final JButton save_as = new JButton("Save as...");
    save_as.addActionListener(new ActionListener()
    {
      public void actionPerformed(final ActionEvent the_event)
      {
        my_result = my_file_chooser.showSaveDialog(null);
        
        if (my_result == JFileChooser.APPROVE_OPTION)
        {
          try
          {
            my_image.save(my_file_chooser.getSelectedFile());
          }
          catch (final IOException the_exception)
          {
            JOptionPane.showMessageDialog(null, "File could not be written!");
          }
        }
      }
    });
    save_as.setEnabled(false);
    
    final JButton open = new JButton("Open...");
    open.addActionListener(new ActionListener()
    {  
      public void actionPerformed(final ActionEvent the_event)
      {
        my_result = my_file_chooser.showOpenDialog(my_master_panel);
        
        if (my_result == JFileChooser.APPROVE_OPTION)
        {
          try
          {
            my_image = PixelImage.load(my_file_chooser.getSelectedFile());
          }
          catch (final IOException the_exception)
          {
            JOptionPane.showMessageDialog(null, "File did not contain a valid image: " +
                                          my_file_chooser.getSelectedFile());
          }
          my_label.setIcon(new ImageIcon(my_image));
          final Component[] component = my_north_panel.getComponents();
          for (int i = 0; i < component.length; i++)
          {
            final JButton button = (JButton) component[i];
            button.setEnabled(true);
          }
          save_as.setEnabled(true);
          pack();
        }
      }
    });
    
    my_center_panel.add(my_label);
    my_south_panel.add(open);
    my_south_panel.add(save_as);
    
    my_master_panel.add(my_north_panel, BorderLayout.NORTH);
    my_master_panel.add(my_center_panel, BorderLayout.CENTER);
    my_master_panel.add(my_south_panel, BorderLayout.SOUTH);
    
    add(my_master_panel);
    pack();
  }
  
  /**
   * Create and show the GUI on the screen.
   */
  public void start()
  {
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);
  }
}
