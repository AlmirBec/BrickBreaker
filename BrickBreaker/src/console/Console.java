package console;

import java.util.Scanner;

public class Console {

	public static void main(String[] args) {
		GameLogic game = new GameLogic();
	    Scanner sc = new Scanner(System.in);
	    System.out.println("Welcome to BrickBreaker!");
	    game.initialize();
	    while (!game.isGameOver()) {
	      game.printMatrix();
	      System.out.println("Move paddle left (l) or right (r): ");
	      String input = sc.next();
	      if (input.equals("l")) {
	        game.movePaddleLeft();
	      } else if (input.equals("r")) {
	        game.movePaddleRight();
	      }
	      game.moveBall();
	      System.out.println("\n");
	    }
	    System.out.println("Game Over!");
	    sc.close();

	}

}
