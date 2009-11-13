/*
 * PaintPanel.
 * Fall 09.
 */
package powerpaint.gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import drawingtool.DrawingTool;
import drawingtool.Pencil;

/**
 * PaintPanel
 * @author Son
 * @version 1.0
 */
public class PaintPanel extends JPanel implements MouseListener, MouseMotionListener
{
  //Static fields
  
  /**
   * The width of the drawing panel.
   */
  private static final int WIDTH = 400;
  
  /**
   * The height of the drawing panel.
   */
  private static final int HEIGHT = 300;
  
  /**
   * The background color of the drawing panel.
   */
  private static final Color my_bg_color = Color.white;
  
  //Instance fields
  
  /**
   * My current stroke size.
   */
  private int my_current_stroke;
  
  /**
   * My mouse action state.
   */
  private State my_state = State.IDLE;
  
  /**
   * My drawing tool.
   */
  private DrawingTool my_tool;
  
  /**
   * My drawing color.
   */
  private Color my_color = Color.BLACK;
  
  /**
   * My enumeration state.
   * @author Son
   * @version 1.0
   */
  private enum State {IDLE, DRAGGING};
  
  /**
   * My starting point.
   */
  private Point my_start_point = null;
  
  /**
   * My ending point.
   */
  private Point my_end_point = null;
  
  /**
   * My buffered image.
   */
  private BufferedImage my_buff_images = null;
  
  /**
   * My grid boolean value.
   */
  private boolean my_grid_boolean = false;

  //Constructor
  
  /**
   * Construct a PaintPanel object.
   */
  public PaintPanel()
  {
    super();
    setPreferredSize(new Dimension(WIDTH, HEIGHT));
    setBackground(my_bg_color);
    
    //my_shape_collection = new ArrayList<DrawingShape>();
    my_tool = new Pencil();
    this.addMouseListener(this);
    this.addMouseMotionListener(this);
  }
  
  //Instance methods
  
  /**
   * Set the drawing color.
   * @param the_color The color.
   */
  public void setColor(Color the_color)
  {
    my_color = the_color;
  }
  
  /**
   * Set the drawing tool.
   * @param the_tool The drawing tool.
   */
  public void setTool(DrawingTool the_tool)
  {
    my_tool = the_tool;
  }
  
  /**
   * Set the stroke size.
   * @param the_stroke The stroke size.
   */
  public void setStroke(int the_stroke)
  {
    my_current_stroke = the_stroke;
    my_tool.setStroke(my_current_stroke);
  }
  
  /**
   * Set the value of my grid boolean.
   * @param isEnabled The boolean value.
   */
  public void setMyGridBoolean(boolean isEnabled)
  {
    my_grid_boolean = isEnabled;
  }
  
  /**
   * Return the drawing color. 
   * @return The drawing color.
   */
  public Color getColor()
  {
    return my_color;
  }
  
  /**
   * Return the stroke size. 
   * @return The stroke size.
   */
  public int getStroke()
  {
    return my_current_stroke;
  }
  
  /**
   * Clear the buffered image.
   */
  public void clearBufferedImage()
  {
    my_buff_images = null;
  }
  
  /**
   * Paints the buffered image (and the grid). 
   * @param the_graphics The graphics context to use for painting.
   */
  public void paintComponent(Graphics the_graphic)
  {
    super.paintComponent(the_graphic);
    Graphics2D graphic_2d = (Graphics2D) the_graphic;
    
    //Initialize my_buff_images the first time
    
    if (my_buff_images == null)
    {
      int w = this.getWidth();
      int h = this.getHeight();
      my_buff_images = (BufferedImage) this.createImage(w, h);
      Graphics2D gc = my_buff_images.createGraphics();
      gc.setColor(my_bg_color);
      gc.fillRect(0, 0, w, h);
    }
    
    //Display the buffered image.
    
    graphic_2d.drawImage(my_buff_images, null, 0, 0);
    
    if (my_state == State.DRAGGING)
    {
      //my_tool.drawShape(graphic_2d, my_start_point, my_end_point);
      drawCurrentShape(graphic_2d);
    }
    
    if (my_grid_boolean == true)
    {
      drawGrid(graphic_2d, 10);
    }
  }
  
  /**
   * Draw the current shape onto the graphic.
   * @param the_graphic The graphic.
   */
  public void drawCurrentShape(Graphics2D the_graphic)
  {
    the_graphic.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    the_graphic.setColor(my_color);
    the_graphic.setStroke(new BasicStroke(my_tool.getStroke()));
    
    the_graphic.draw(my_tool.drawShape());
  }
  
  /**
   * Draw the grid on the paint panel.
   * @param the_graphic The graphic.
   * @param the_spacing The spacing.
   */
  public void drawGrid(Graphics2D the_graphic, int the_spacing)
  {
    the_graphic.setColor(Color.BLACK);
    the_graphic.setStroke(new BasicStroke(1));
    
    for (int i = 1; i < (WIDTH / the_spacing); i++)
    {
      Point start = new Point(i * the_spacing, 0);
      Point end = new Point(i * the_spacing, HEIGHT);
      the_graphic.drawLine(start.x, start.y, end.x, end.y);
    }
    
    for (int i = 1; i < (HEIGHT / the_spacing); i++)
    {
      Point start = new Point(0, i * the_spacing);
      Point end = new Point(WIDTH, i * the_spacing);
      the_graphic.drawLine(start.x, start.y, end.x, end.y);      
    }
  }
  
  /**
   * Set the state to dragging. Set the value of start point and end point.
   * Set the start point for the drawing tool.
   * 
   * @param the_event The event, ignored.
   */
  public void mousePressed(final MouseEvent the_event)
  {
    my_state = State.DRAGGING;
    
    my_start_point = the_event.getPoint();
    my_end_point = my_start_point;
    my_tool.setStartPoint(my_start_point);
    my_tool.setEndPoint(my_end_point);
  }
  
  /**
   * Set the state to dragging. Set the value of the end point.
   * Set the end point for the drawing tool. Then repaint the panel.
   * 
   * @param the_event The event, ignored.
   */
  public void mouseDragged(final MouseEvent the_event)
  {
    my_state = State.DRAGGING;
    
    my_end_point = the_event.getPoint();
    my_tool.setEndPoint(my_end_point);
    repaint();  
  }
  
  /**
   * Set the state to dragging/idle. Set the value of the end point.
   * Set the end point for the drawing tool. Then repaint the panel.
   * 
   * @param the_event The event, ignored.
   */
  public void mouseReleased(final MouseEvent the_event)
  {
    my_end_point = the_event.getPoint();
    
    if (my_state == State.DRAGGING)
    {
      my_state = State.IDLE;      
      drawCurrentShape(my_buff_images.createGraphics());      
      //repaint();
    }   
    repaint();
  }

  @Override
  public void mouseClicked(MouseEvent the_event){}

  @Override
  public void mouseEntered(MouseEvent the_event){}

  @Override
  public void mouseExited(MouseEvent the_event){}

  @Override
  public void mouseMoved(MouseEvent the_event){}
}

