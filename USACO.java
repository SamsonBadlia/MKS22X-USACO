import java.util.*;
import java.io.*;

public class USACO{

  public static int bronze(String filename) throws FileNotFoundException{
  int[][] grid;
  int[][] moves;
  int depth;
  File f = new File(filename);
  Scanner s = new Scanner(f);
  int row = s.nextInt();
  int col = s.nextInt();

  grid = new int[row][col];
  depth = s.nextInt();
  int move = s.nextInt();
  moves = new int[move][3];

  fillGrid(grid,row,col,s);
  generateMoves(moves, move, s);

  int count = 0;
  while (count < moves.length) {
    dig(grid, moves[count]);
    count++;
  }
  
  return calculateVolume(grid, row, col, depth);
}

  public static void dig(int[][] grid, int[] moves) {
    int largest = 0;
    for (int i = moves[0]; i < moves[0]+3 && i < grid.length ; i++) {
      for (int j = moves[1]; j < moves[1]+3 && j < grid[0].length; j++) {
        if (grid[i][j] > largest) {
          largest = grid[i][j];
        }
      }
    }
    largest -= moves[2];
    for (int i = moves[0]; i < moves[0]+3 && i < grid.length; i++) {
      for (int j = moves[1]; j < moves[1]+3 && j < grid[0].length; j++) {
        if (grid[i][j] > largest) {
          grid[i][j] = largest;
        }
      }
    }
  }

  public static void fillGrid(int[][] grid, int row, int col, Scanner s){
    for (int i = 0; i < row; i++) {
      for (int j = 0; j < col; j++) {
        grid[i][j] = s.nextInt();
      }
    }
  }

  public static void generateMoves(int[][] moves, int move, Scanner s){
    for (int i = 0; i < move; i++) {
      for (int j = 0; j < 3; j++) {
        moves[i][j] = s.nextInt();
        if (j != 2) {
          moves[i][j] -= 1;
        }
      }
    }
  }

  public static int calculateVolume(int[][] grid, int row, int col, int depth){
    int volume = 0;
    for (int i = 0; i < row; i++) {
      for (int j = 0; j < col; j++) {
        if (grid[i][j] < depth) {
          volume += depth - grid[i][j];
        }
      }
    }
    return volume * 72 * 72;
  }

  public static void main(String[] args){
    try{
      System.out.println(bronze("makelake.in"));
    }catch(FileNotFoundException e){
      e.printStackTrace();
    }
  }

}
