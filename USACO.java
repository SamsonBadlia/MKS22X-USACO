import java.util.*;
import java.io.*;

public class USACO{

  static int rows,cols,depth,digginginstructions;
  static int[][] grid;

  public static int bronze(String filename) throws FileNotFoundException{

    File f = new File(filename);
    Scanner s = new Scanner(f);
    int i = 0;
    String str = s.nextLine();
    intepret(str);
    fillGrid(rows,filename);
    return 1;

  }

  public static void fillGrid(int r,String filename) throws FileNotFoundException{

    File f = new File(filename);
    Scanner s = new Scanner(f);
    grid = new int[rows][];
    for (int i = 0; i < r; i++){
      String line = s.nextLine();
      String[] str = line.split(" ");
      int[] ints = new int[str.length];
      for (int j = 0; j < str.length -1; j++){
        ints[i] = Integer.parseInt(str[i]);
      }
      grid[i] = ints;
    }

  }

  public static void intepret(String s){

    String[] str = s.split(" ");
    rows = Integer.parseInt(str[0]);
    cols = Integer.parseInt(str[1]);
    depth = Integer.parseInt(str[2]);
    digginginstructions = Integer.parseInt(str[3]);

  }

}
