import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.Scanner;

/*
 * Kevin Raskell
 * CSCD 350 Assignment 2
 * MinesweeperClass
 * 
 * This class file will be the main tester for this assignment. There will be only this class for this
 * assignment. It will take input and produce a minesweeper related output file.
 * 
 * Example Input:
 * 4 4
 * *...
 * ....
 * .*..
 * ....
 * 
 * Example Output:
 * Field #1
 * *100
 * 2210
 * 1*10
 * 1110
 */
public class MinesweeperTester {
	/* Global variables */
	public static File fileName;
	public static int rows, cols;
	public static char space=' ';
	public static char mine='*';
	public static char emptySpace='.';
	public static char[][] mines; //used to store the input
	public static String[][] output; //used to store the output
	public static Scanner fin; //used to read input
	public static PrintWriter out; //used to write output
	/*------------------*/

	public static void main(String[] args) throws FileNotFoundException {
//		fin=new Scanner(System.in);
		fileName=new File("input.txt");
		fin=new Scanner(fileName);
		out=new PrintWriter("output.txt");
		fillMines(fin);
		out.close();
		fin.close();
	}

	private static void fillMines(Scanner fin) {
		int fieldNumber=0;
		while(fin.hasNext()){
			rows=fin.nextInt();
			cols=fin.nextInt();
			fieldNumber++;
			output=new String[rows][cols];
			if(rows==0 || cols==0){
				return;
			}
			mines=new char[rows][cols];
			String temp="";

			for(int i=0; i<rows; i++){
				temp=fin.next();
				for(int j=0; j<cols; j++){
					if(temp.charAt(j)!=mine && temp.charAt(j)!=emptySpace){
						throw new InputMismatchException(temp.charAt(j) + " is the incorrect format; A mine must be " + mine + " and empty space must be " + emptySpace);
					}
					mines[i][j]=temp.charAt(j);
				}
			}

			for(int i=0; i<rows; i++){
				for(int j=0; j<cols; j++){
					System.out.print(mines[i][j]);
				}
				System.out.println();
			}

			for(int i=0; i<rows; i++){
				for(int j=0; j<cols; j++){
					if(mines[i][j]==mine){
						output[i][j]=Character.toString(mine);
					}
					else{
						output[i][j]=Integer.toString(searchForMines(i, j));
					}
				}
			}
			for(int i=0; i<rows; i++){
				for(int j=0; j<cols; j++){
					System.out.print(output[i][j]);
				}
				System.out.println();
			}
			System.out.println();
			
			output(out, output, fieldNumber);
		}
	}

	private static int searchForMines(int x, int y){
		int numMines=0;
		int top=x-1;
		int bottom=x+1;
		int left=y-1;
		int right=y+1;

		for(int q=x-1; q<=x+1; q++){
			for(int w=y-1; w<=y+1; w++){
				while(true){
					if(q<0 || w<0 || q>=rows || w>=cols)
						break;
					if(mines[q][w]==mine)
						numMines++;
					break;
				}
			}
		}

		return numMines;

	}

	private static void output(PrintWriter out, String[][] output, int fieldNumber){
		out.println("Field #" + fieldNumber);
		for(int i=0; i<rows; i++){
			for(int j=0; j<cols; j++){
				out.print(output[i][j]);
			}
			out.println();
		}
		out.println();
	}




}
