package tetris.gui;

public class TetrisMain
{
  public static void main(final String... the_args)
  {
    final TetrisGui the_frame = new TetrisGui();
    the_frame.setup();
    the_frame.start();
  }
}
