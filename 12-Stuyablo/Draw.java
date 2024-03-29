public class Draw {
  public static void main(String[] args) {
    //clear
    Text.clear();

    //create an 30 by 80 border of color
    final int height = 30;
    final int width = 80;
    final int color = Text.BLUE + Text.BACKGROUND;

    //cursor position
    int row = 0;
    int col = 0;

    //draw border
    drawHorizontal(0, width, color); //top row
    drawHorizontal(height, width, color); //bottom row
    drawVertical(0, height, color); //left col
    drawVertical(width, height, color); //right col

    //make 4 random ints
    int[] ints = new int[4];
    for (int i = 0; i < ints.length; i++) {
      ints[i] = randInt(0, 99);
    }

    //evenly distribute these ints
    for (int i = 0; i < ints.length; i++) {
      int increment = (width / ints.length); //spacing to evenly distribute
      int offset = -(increment / 2); //offset to make it center-aligned
      Text.go(2, (i + 1) * increment + offset); //magic number 2 comes from it being row after border
      System.out.print(ints[i]);
    }

    //place cursor at proper position
    Text.go(height, 0);

    //pl to place cursor on next row
    System.out.println();
  }

  //return random number from lower to upper inclusive
  private static int randInt(int lower, int upper) {
    return (int)((Math.random() * (upper - lower + 1)) + lower);
  }

  private static void drawHorizontal(int row, int length, int color) {
    int col = 0;
    for (col = 1; col <= length; col++) {
      Text.go(row, col);
      System.out.print(Text.colorize(" ", color));
    }
  }

  private static void drawVertical(int col, int length, int color) {
    int row = 0;
    for (row = 1; row <= length; row++) {
      Text.go(row, col);
      System.out.print(Text.colorize(" ", color));
    }
  }
}
