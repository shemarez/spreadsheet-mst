package powerpaint.gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import drawingtool.DrawingTool;
import drawingtool.Pencil;

public class PaintPanel extends JPanel implements MouseListener, MouseMotionListener
{
  private static final int WIDTH = 400;
  private static final int HEIGHT = 300;
  private static final Color my_bg_color = Color.white;
  private int my_current_stroke;
  private State my_state = State.IDLE;
  private DrawingTool my_tool;
  private Color my_color = Color.BLACK;
  private enum State {IDLE, DRAGGING};
  private Point my_start_point = null;
  private Point my_end_point = null;
  private BufferedImage my_buff_images = null;
  private boolean my_grid_boolean = false;

  
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
  
  public void setColor(Color the_color)
  {
    my_color = the_color;
  }
  
  public void setTool(DrawingTool the_tool)
  {
    my_tool = the_tool;
  }
  
  public void setStroke(int the_stroke)
  {
    my_current_stroke = the_stroke;
    my_tool.setStroke(my_current_stroke);
  }
  
  public void setMyGridBoolean(boolean enable)
  {
    my_grid_boolean = enable;
  }
  
  public Color getColor()
  {
    return my_color;
  }
  
  public int getStroke()
  {
    return my_current_stroke;
  }
  
  public void clearBufferedImage()
  {
    my_buff_images = null;
  }
  
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
  
  
  public void drawCurrentShape(Graphics2D the_graphic)
  {
    the_graphic.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    the_graphic.setColor(my_color);
    the_graphic.setStroke(new BasicStroke(my_tool.getStroke()));
    
    the_graphic.draw(my_tool.drawShape());
  }
  
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
  
  public void mousePressed(MouseEvent the_event)
  {
    my_state = State.DRAGGING;
    
    my_start_point = the_event.getPoint();
    my_end_point = my_start_point;
    my_tool.setStartPoint(my_start_point);
  }
  
  public void mouseDragged(MouseEvent the_event)
  {
    my_state = State.DRAGGING;
    
    my_end_point = the_event.getPoint();
    my_tool.setEndPoint(my_end_point);
    repaint();  
  }
  
  public void mouseReleased(MouseEvent the_event)
  {
    my_end_point = the_event.getPoint();
    
    if(my_state == State.DRAGGING)
    {
      my_state = State.IDLE;
      
      //my_tool.drawShape(my_buff_images.createGraphics(), my_start_point, my_end_point);
      drawCurrentShape(my_buff_images.createGraphics());
      
      repaint();
    }    
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

