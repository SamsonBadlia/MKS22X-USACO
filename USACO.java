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

  public static char[][] board;
  public static int silver(String filename) throws FileNotFoundException{
  	int[][] old; int[][] neW; char[][] data;

  	int row; int col;
    int num;
    int startR; int startC;
    int endR; int endC;

    File file = new File(filename);
    Scanner s = new Scanner(file);
    row = s.nextInt();
    col = s.nextInt();
    num= s.nextInt();
    board = new char[row][col];
    neW = new int[row][col];
    old = new int[row][col];

    for (int i = 0; i < row; i++){
      String str = s.next();
        for(int j = 0; j < col; j++){
	          board[i][j] = str.charAt(j);
          }
        }

    startR = s.nextInt() - 1;
    startC = s.nextInt() - 1;
    endR = s.nextInt() - 1;
    endC = s.nextInt() - 1;
    old[startR][startC] = 1;

    for(int i = 0; i < num; i++){
	      for(int x = 0; x < row; x++){
  		    for(int j = 0; j < col; j++){
  			       if (board[x][j] != '*'){
      			    int total = 0;
      			    if(isOnBoard(x-1,j,row,col)) total = total + old[x-1][j];
      			    if(isOnBoard(x+1,j,row,col)) total = total + old[x+1][j];
      			    if(isOnBoard(x,j-1,row,col)) total = total + old[x][j-1];
      			    if(isOnBoard(x,j+1,row,col)) total = total + old[x][j+1];
      			    neW[x][j] = total;
      			     }
	         }
	        }

    	old = neW;
    	neW = new int[row][col];
    }
    return old[endR][endC];
  }

  private static boolean isOnBoard(int newR,int newC, int r, int c){
     return newR >= 0 && newC >= 0 && newR < r && newC < c;
  }

  public static void main(String[] args){
    try{
      System.out.println(bronze("makelake.in"));
      System.out.println(silver("test.txt"));
    }catch(FileNotFoundException e){
      e.printStackTrace();
    }
  }

}
