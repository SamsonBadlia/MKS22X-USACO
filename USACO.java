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
public static int silver(String filename){

  String output = "";
  int[][] moves = {{0,1}, {0,-1}, {1,0}, {-1, 0}};
  int rows = 0; int cols= 0;
  int time = 0;
  int r = 0; int c = 0;
  int endr = 0; int endc = 0;

  try{
    File f = new File(filename);
    Scanner scan = new Scanner(f);
    rows = scan.nextInt();
    cols = scan.nextInt();
    time = scan.nextInt();
    scan.nextLine();

    String s = "";
    while(scan.hasNextLine()){
      s = scan.nextLine();
      output += s;
    }

    int index = 0;
    board = new char[rows][cols];
    for(int i = 0; i < rows; i++){
      for(int j = 0; j < cols; j++){
        board[i][j] = output.charAt(index);
        index++;
      }
    }
    String[] nums = s.split(" ");
    r = Integer.parseInt(nums[0]) - 1;
    c = Integer.parseInt(nums[1]) - 1;
    endr = Integer.parseInt(nums[2]) - 1;
    endc = Integer.parseInt(nums[3]) - 1;
  }
  catch(FileNotFoundException e){

  }

  int[][] current = new int[rows][cols];
  int[][] old = new int[rows][cols];
  for(int i = 0; i < rows; i++){
    for(int j = 0; j < cols; j++){
      if(board[i][j] == '*'){
        current[i][j] = -1;
      }
    }
  }
  current[r][c] = 1;

  while(time > 0){
    for(int i = 0; i < rows; i++){
      for(int j = 0; j < cols; j++){
        old[i][j] = current[i][j];
      }
    }

    for(int i = 0; i < rows; i++){
      for(int j = 0; j < cols; j++){
        if(board[i][j] != '*'){
          current[i][j] = 0;
          for(int x = 0; x < moves.length; x++){
            int newR = r + moves[x][0];
            int newC = c + moves[x][1];
            if(newR < rows && newC < cols && newR >= 0 && newC >= 0 && old[newR][newC]!= -1){
              current[i][j] += old[newR][newC];
            }
          }
        }
      }
    }
    time --;
  }
  return current[endr][endc];
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
