import java.util.*;
import java.io.*;

public class USACO{

  static int rows,cols,depth,digginginstructions;
  static int[][] grid;
  static int[][] moves = {
    {0,0} , {0,-1}, {0,-2},
    {1,0} , {1,-1}, {1,-2},
    {2,0} , {2,-1}, {2,-2}
  };

  public static int bronze(String filename) throws FileNotFoundException{

    File f = new File(filename);
    Scanner s = new Scanner(f);
    String str = s.nextLine();
    intepret(str);
    fillGrid(rows,s);
    for (int i = 0; i < rows; i++){
      int[] instructions = getarray(s);
      instructions[0]--;
      instructions[1]--;
      dig(instructions[0], instructions[1], instructions[2]);
    }
    fillLake();
    return getVolume();

  }

  public static void fillGrid(int r,Scanner s){

    grid = new int[rows][];
    for (int i = 0; i < rows; i++){
      grid[i] = getarray(s);
    }

  }

  private static int[] getarray(Scanner s){

    String str = s.nextLine();

    String[] strs = str.split(" ");
    int[] ints = new int[strs.length];
    for(int i = 0; i < strs.length; i++){
        ints[i] = Integer.parseInt(strs[i]);
    }
    return ints;
  }

  public static void intepret(String s){

    String[] str = s.split(" ");
    rows = Integer.parseInt(str[0]);
    cols = Integer.parseInt(str[1]);
    depth = Integer.parseInt(str[2]);
    digginginstructions = Integer.parseInt(str[3]);

  }

  public static void fillLake(){
    for (int i = 0; i < rows; i++){
      for (int j = 0; j < grid[0].length; j++){
        int elevation = depth - grid[i][j];
        if (elevation < 0 ) grid[i][j] = 0;
        grid[i][j] = elevation;
      }
    }
  }

  public static int getVolume(){
    int volume = 0;
    for (int i = 0; i < rows; i++){
      for (int j = 0; j < grid[0].length; j++){
        volume += grid[i][j] * 5184;
      }
    }
    return volume;
  }

  public static void dig(int r, int c, int elevation){
    int largestVal = grid[r][c];
    for (int i = 0; i < 10; i++){
      int newR = r + moves[i][0];
      int newC = c + moves[i][1];
      if (grid[newR][newC] > largestVal) largestVal = grid[newR][newC];
    }
    int newElevation = largestVal - elevation;
    for (int j = 0; j < 10; j++){
      int newR = r + moves[j][0];
      int newC = c + moves[j][1];
      if (grid[newR][newC] > newElevation) grid[newR][newC] = newElevation;
    }
  }

  public static void main(String[] args){
    try{
      System.out.println(bronze("makelake.in"));
    }catch(FileNotFoundException e){
      e.printStackTrace();
    }
  }

}
