package powerpaint.gui;

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
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import drawingtool.DrawingTool;
import drawingtool.Pencil;

public class PaintPanel extends JPanel implements MouseListener, MouseMotionListener
{
  private static final int WIDTH = 400;
  private static final int HEIGHT = 300;
  private static final Color my_bg_color = Color.white;
  private State my_state = State.IDLE;
  private DrawingTool my_tool;
  private Shape my_shape = Shape.LINE;
  private Color my_color = Color.BLACK;
  private enum State {IDLE, DRAGGING};
  private Point my_start_point = null;
  private Point my_end_point = null;
  private List<DrawingShape> my_shape_collection;
  private BufferedImage my_buff_images = null;

  
  public PaintPanel()
  {
    super();
    setPreferredSize(new Dimension(WIDTH, HEIGHT));
    setBackground(my_bg_color);
    
    my_shape_collection = new ArrayList<DrawingShape>();
    my_tool = new Pencil();
    this.addMouseListener(this);
    this.addMouseMotionListener(this);
  }
  
  public void setShape(Shape the_shape)
  {
    my_shape = the_shape;
  }
  
  public void setColor(Color the_color)
  {
    my_color = the_color;
  }
  
  public void setTool(DrawingTool the_tool)
  {
    my_tool = the_tool;
  }
  
  public void paintComponent(Graphics the_graphic)
  {
    super.paintComponent(the_graphic);
    Graphics2D graphic_2d = (Graphics2D) the_graphic;
    
    if (my_buff_images == null)
    {
      int w = this.getWidth();
      int h = this.getHeight();
      my_buff_images = (BufferedImage) this.createImage(w, h);
      Graphics2D gc = my_buff_images.createGraphics();
      gc.setColor(my_bg_color);
      gc.fillRect(0, 0, w, h);      
    }
    
    graphic_2d.drawImage(my_buff_images, null, 0, 0);
    
    if (my_state == State.DRAGGING)
    {
      my_tool.drawShape(graphic_2d, my_start_point, my_end_point);
      //drawCurrentShape(graphic_2d);
    }
  }
  
  /*
  public void drawCurrentShape(Graphics2D the_graphic)
  {
    the_graphic.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    the_graphic.setColor(my_color);
    
    the_graphic.drawLine(my_start_point.x, my_start_point.y, my_end_point.x, my_end_point.y);
    /*switch(my_shape)
    {
      case: 
    }
  }*/
  
  public void mousePressed(MouseEvent the_event)
  {
    my_state = State.DRAGGING;
    
    my_start_point = the_event.getPoint();
    my_end_point = my_start_point;
  }
  
  public void mouseDragged(MouseEvent the_event)
  {
    my_state = State.DRAGGING;
    
    my_end_point = the_event.getPoint();
    repaint();
  }
  
  public void mouseReleased(MouseEvent the_event)
  {
    my_end_point = the_event.getPoint();
    
    if(my_state == State.DRAGGING)
    {
      my_state = State.IDLE;
      
      my_tool.drawShape(my_buff_images.createGraphics(), my_start_point, my_end_point);
      //drawCurrentShape(my_buff_images.createGraphics());
      
      repaint();
    }    
  }
  
  public void mouseMoved(MouseEvent the_event){};
  public void mouseEntered(MouseEvent the_event){};
  public void mouseExited(MouseEvent the_event){};
  public void mouseClicked(MouseEvent the_event){};
}

