package console;

import java.util.List;

public class GameLogic {
  
  private final int ROWS = 10;
  private final int COLUMNS = 10;
  private final int PADDLE_LENGTH = 4;
  private char[][] matrix;
  private int ballX;
  private int ballY;
  private int paddleX;
  private int paddleY;
  private int paddleLeftX;
  private int paddleRightX;
  private boolean isBallGoingUp;
  private boolean isBallGoingLeft;

  public void initialize() {
    matrix = new char[ROWS][COLUMNS];
    for (int i = 0; i < ROWS; i++) {
      for (int j = 0; j < COLUMNS; j++) {
        if (i < 4) {
          matrix[i][j] = 'x';
        } else {
          matrix[i][j] = ' ';
        }
      }
    }
    ballX = 8;
    ballY = 4;
    paddleX = COLUMNS / 2;
    paddleY = ROWS - 1;
    paddleLeftX = paddleX - PADDLE_LENGTH / 2;
    paddleRightX = paddleX + PADDLE_LENGTH / 2;
    isBallGoingUp = true;
    isBallGoingLeft = true;
    matrix[ballX][ballY] = 'o';
    for (int i = paddleLeftX; i <= paddleRightX; i++) {
      matrix[paddleY][i] = '-';
    }
  }

  public void printMatrix() {
    for (int i = 0; i < ROWS; i++) {
      for (int j = 0; j < COLUMNS; j++) {
        System.out.print(matrix[i][j] + " ");
      }
      System.out.println();
    }
  }
  
  public void movePaddleLeft() {
	    if (paddleX > 0) {
	        matrix[paddleY][paddleRightX] = ' ';
	        paddleX--;

	        if (paddleLeftX > 0) {
	            paddleLeftX--;
	            paddleRightX--;
	        }
	            
	        for (int i = paddleLeftX; i <= paddleRightX; i++) {
	            matrix[paddleY][i] = '-';
	        }
	    }
	}
  public void movePaddleRight() {
	    if (paddleX < COLUMNS - 1) {
	        matrix[paddleY][paddleLeftX] = ' ';
	        paddleX++;

	        if (paddleRightX < COLUMNS - 1) {
	            paddleRightX++;
	            paddleLeftX++;
	        }

	        for (int i = paddleLeftX; i <= paddleRightX; i++) {
	            matrix[paddleY][i] = '-';
	        }
	    }
	}

	  public void moveBall() {
	    matrix[ballX][ballY] = ' ';
	    if (isBallGoingUp) {
	      ballX--;
	    } else {
	      ballX++;
	    }
	    if (isBallGoingLeft) {
	      ballY--;
	    } else {
	      ballY++;
	    }
	    checkCollisions();
	    matrix[ballX][ballY] = 'o';
	  }

	  private void checkCollisions() {
		  if (ballX < 0) {
			    ballX = 0;
			  }
		  if(ballY < 0) {
			  ballY = 0;
		  }
		  if(ballY > COLUMNS - 1) {
			  ballY = COLUMNS - 1;
		  }
			
	    if (ballX == 0) {
	      isBallGoingUp = false;
	    } else if (ballX == ROWS - 1) {
	      if (ballY >= paddleLeftX && ballY <= paddleRightX) {
	        isBallGoingUp = true;
	        if (ballY <= paddleX) {
	        	System.out.println(ballY + "," + paddleX);
	          isBallGoingLeft = true;
	        } else {
	        	System.out.println(ballY + "," + paddleX);
	          isBallGoingLeft = false;
	        }
	      } else {
	        gameOver();
	      }
	    }
	    if (ballY == 0) {
	      isBallGoingLeft = false;
	    } else if (ballY == COLUMNS - 1) {
	      isBallGoingLeft = true;
	    }
	    if (matrix[ballX][ballY] == 'x') {
	    	matrix[ballX][ballY] = ' ';
	    	  double randomNumber = Math.random();
	    	  if (randomNumber < 0.5) {
	    	    isBallGoingUp = true;
	    	  } else {
	    	    isBallGoingUp = false;
	    	  }
	    	  if (isBallGoingLeft) {
	    	    isBallGoingLeft = false;
	    	  } else {
	    	    isBallGoingLeft = true;
	    	  }
	    }
	  }

	  private void gameOver() {
	    for (int i = 0; i < ROWS; i++) {
	      for (int j = 0; j < COLUMNS; j++) {
	        matrix[i][j] = ' ';
	      }
	    }
	    System.out.println("Game Over!");
	    System.exit(0);
	  }

	  public boolean isGameOver() {
	    for (int i = 0; i < 4; i++) {
	      for (int j = 0; j < COLUMNS; j++) {
	        if (matrix[i][j] == 'x') {
	          return false;
	        }
	      }
	    }
	    return true;
	  }
	  public int[] getPaddle(){
		  int[] paddle = new int[2];
		  paddle[0] = paddleRightX;
		  paddle[1] = paddleLeftX;
		  return paddle;
	  }
	  public int[] getBall(){
		  int[] ball = new int[2];
		  ball[0] = ballX;
		  ball[1] = ballY;
		  return ball;
	  }
	  /*public void update(){	
		}*/
	}
	

   