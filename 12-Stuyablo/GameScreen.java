import java.util.Scanner;

public class GameScreen {
  public static void main(String[] args) {
    fill(1, 1, 30, 30, Text.GREEN);
  }

  public static void _main(String[] args) {
    //create an 30 by 80 border of color
    final int width = 80;
    final int height = 30;
    final int color = Text.BLUE + Text.BACKGROUND;

    int[] ints = randomize(0, 99, 4);
    screen(width, height, color, ints);

    boolean run = true;
    Scanner console = new Scanner(System.in);
    while (run) {
      String input = console.nextLine();
      switch (input) {
        case "":
          //redraw screen
          screen(width, height, color, ints);
          break;
        case "q":
          run = false;
          break;
        case "quit":
          run = false;
          break;
        default:
          //rerandomize numbers and draw screen
          ints = randomize(0, 99, 4);
          screen(width, height, color, ints);
          break;
      }
    }
  }

  public static void screen(int width, int height, int color, int[] ints) {
    //clear
    Text.clear();

    drawBorder(width, height, color);
    distribute(ints, width, height, 2); //2 is row after border

    //pl to place cursor on next row
    System.out.println();

    //draw prompt on bottom
    System.out.print(">");
    Text.showCursor();
  }

  public static void screen(int width, int height, int color) {
    //clear
    Text.clear();

    //fill(1, 1, height, width, Text.BLACK);
    drawBorder(width, height, color);

    //draw bars separating text field from party fields
    drawHorizontal(7, width, color);
    drawHorizontal(height - 6, width, color);
  }

  //returns int[] of n random ints from lower to upper
  public static int[] randomize(int lower, int upper, int n) {
    //make 4 random ints
    int[] ints = new int[n];
    for (int i = 0; i < ints.length; i++) {
      ints[i] = randInt(lower, upper);
    }
    return ints;
  }

  public static void drawBorder(int width, int height, int color) {
    drawHorizontal(0, width, color); //top row
    drawHorizontal(height, width, color); //bottom row
    drawVertical(0, height, color); //left col
    drawVertical(width, height, color); //right col
  }

  public static void distribute(int[] ints, int width, int height, int y) {
    for (int i = 0; i < ints.length; i++) {
      int increment = (width / ints.length); //spacing to evenly distribute
      int offset = -(increment / 2); //offset to make it center-aligned
      Text.go(y, (i + 1) * increment + offset); //y := y position to print at
      System.out.print(ints[i]);
    }
    Text.go(height, 0); //place cursor at proper position
  }

  public static void distribute(String[] words, int width, int height, int y) {
    for (int i = 0; i < words.length; i++) {
      int increment = (width / words.length); //spacing to evenly distribute
      int offset = -(increment / 2); //offset to make it center-aligned
      offset -= words[i].length() / 2; //offset it by half of the word it is aligned by center of word
      Text.go(y, (i + 1) * increment + offset); //y := y position to print at
      System.out.print(words[i]);
    }
    Text.go(height, 0); //place cursor at proper position
  }

  public static void distribute(String[] words, int width, int height, int y, int ESCoffset) {
    for (int i = 0; i < words.length; i++) {
      int increment = (width / words.length); //spacing to evenly distribute
      int offset = -(increment / 2); //offset to make it center-aligned
      offset -= (words[i].length() - ESCoffset) / 2; //offset it by half of the word it is aligned by center of word
      Text.go(y, (i + 1) * increment + offset); //y := y position to print at
      System.out.print(words[i]);
    }
    Text.go(height, 0); //place cursor at proper position
  }

  //return random number from lower to upper inclusive
  public static int randInt(int lower, int upper) {
    return (int)((Math.random() * (upper - lower + 1)) + lower);
  }

  public static void drawHorizontal(int row, int length, int color) {
    int col = 0;
    for (col = 1; col <= length; col++) {
      Text.go(row, col);
      System.out.print(Text.colorize(" ", color));
    }
  }

  public static void drawVertical(int col, int length, int color) {
    int row = 0;
    for (row = 1; row <= length; row++) {
      Text.go(row, col);
      System.out.print(Text.colorize(" ", color));
    }
  }

  public static void fill(int startRow, int startCol, int endRow, int endCol, int color) {
    for (int row = startRow; row < endRow; row++) {
      for (int col = startRow; col < endCol; col++) {
        Text.go(row, col);
        System.out.print(Text.colorize(" ", color + Text.BACKGROUND));
      }
    }
  }
}
