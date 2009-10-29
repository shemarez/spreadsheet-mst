// finish (and comment) me!

package snapshop.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

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
  
  //Constructor
  
  public SnapShopGUI()
  {
    super("TCSS 305 SnapShot");
    setupComponents();
  }
  
  private void setupComponents()
  {
    my_master_panel = new JPanel(new BorderLayout());
    my_north_panel = new JPanel(new FlowLayout());
    my_center_panel = new JPanel(new FlowLayout());
    my_south_panel = new JPanel(new FlowLayout());
    
    final JButton edge_detect = new JButton("Edge Detect");
    edge_detect.setEnabled(false);
    final JButton edge_highlight = new JButton("Edge Highlight");
    edge_highlight.setEnabled(false);
    final JButton flip_horizontal = new JButton("Flip Horizontal");
    flip_horizontal.setEnabled(false);
    final JButton flip_vertical = new JButton("Flip Vertical");
    flip_vertical.setEnabled(false);
    final JButton grayscale = new JButton("Grayscale");
    grayscale.setEnabled(false);
    final JButton sharpen = new JButton("Sharpen");
    sharpen.setEnabled(false);
    final JButton soften = new JButton("Soften");
    soften.setEnabled(false);
    
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
          save_as.setEnabled(true);
          pack();
        }
      }
    });
    
    my_north_panel.add(edge_detect);
    my_north_panel.add(edge_highlight);
    my_north_panel.add(flip_horizontal);
    my_north_panel.add(flip_vertical);
    my_north_panel.add(grayscale);
    my_north_panel.add(sharpen);
    my_north_panel.add(soften);
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
