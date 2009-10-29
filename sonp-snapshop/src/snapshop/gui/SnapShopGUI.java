// finish (and comment) me!

package snapshop.gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

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

public class SnapShopGUI extends JFrame
{
  private JPanel my_master_panel;
  private JPanel my_north_panel;
  private JPanel my_center_panel;
  private JPanel my_south_panel;
  private JFileChooser my_file_chooser = new JFileChooser();
  private int result;
  private PixelImage image;
  private JLabel label =  new JLabel();
  private Map<String, Filter> my_filter_hashmap = new HashMap<String, Filter>();
  private JButton[] my_jbuttons;
  
  //Constructor
  
  public SnapShopGUI()
  {
    super("TCSS 305 SnapShot");
    setupComponents();
  }
  
  public JButton createButton(final FilterObject the_object)
  {
    final JButton button = new JButton(the_object.toString());
    button.addActionListener(new ActionListener()
    {
      public void actionPerformed(final ActionEvent the_event)
      {
        the_object.doFilter(image);
        label.setIcon(new ImageIcon(image));
        pack();
      }
    });
    
    return button;
  }
  
  private void setupComponents()
  {
    my_master_panel = new JPanel(new BorderLayout());
    my_north_panel = new JPanel(new FlowLayout());
    my_center_panel = new JPanel(new FlowLayout());
    my_south_panel = new JPanel(new FlowLayout());
    
    my_filter_hashmap.put("Edge Detect", new EdgeDetectFilter());
    my_filter_hashmap.put("Edge Highlight", new EdgeHighlightFilter());
    my_filter_hashmap.put("Flip Horizontal", new FlipHorizontalFilter());
    my_filter_hashmap.put("Flip Vertical", new FlipVerticalFilter());
    my_filter_hashmap.put("Grayscale", new GrayscaleFilter());
    my_filter_hashmap.put("Sharpen", new SharpenFilter());
    my_filter_hashmap.put("Soften", new SoftenFilter());
    
    final Iterator<String> the_iterator = my_filter_hashmap.keySet().iterator();
    
    while (the_iterator.hasNext())
    {
      final String the_current_string = the_iterator.next();
      JButton new_button = createButton(new FilterObject(the_current_string, 
                                                         my_filter_hashmap.get(the_current_string)));
      new_button.setEnabled(false);
      my_north_panel.add(new_button);
    }
     
    final JButton save_as = new JButton("Save as...");
    save_as.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent the_event)
      {
        result = my_file_chooser.showSaveDialog(null);
        
        if (result == JFileChooser.APPROVE_OPTION)
        {
          try
          {
            image.save(my_file_chooser.getSelectedFile());
          }
          catch (IOException the_exception)
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
      public void actionPerformed(ActionEvent the_event)
      {
        result = my_file_chooser.showOpenDialog(my_master_panel);
        
        if (result == JFileChooser.APPROVE_OPTION)
        {
          try
          {
            image = PixelImage.load(my_file_chooser.getSelectedFile());
          }
          catch (IOException the_exception)
          {
            JOptionPane.showMessageDialog(null, "File did not contain a valid image: " +
                                          my_file_chooser.getSelectedFile());
          }
          label.setIcon(new ImageIcon(image));
          Component[] component = my_north_panel.getComponents();
          for (int i = 0; i < component.length; i++)
          {
            JButton button = (JButton) component[i];
            button.setEnabled(true);
          }
          save_as.setEnabled(true);
          pack();
        }
      }
    });
    
    my_center_panel.add(label);
    my_south_panel.add(open);
    my_south_panel.add(save_as);
    
    my_master_panel.add(my_north_panel, BorderLayout.NORTH);
    my_master_panel.add(my_center_panel, BorderLayout.CENTER);
    my_master_panel.add(my_south_panel, BorderLayout.SOUTH);
    
    add(my_master_panel);
    pack();
  }
  
  public void start()
  {
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);
    //javax.swing.JOptionPane.showMessageDialog(null, "SnapShop placeholder");
  }
}
