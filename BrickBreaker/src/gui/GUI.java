package gui;

import console.GameLogic;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GUI implements ActionListener {
  private JFrame frame;
  private GameLogic gameLogic;
  private Timer timer;

  public GUI() {
    gameLogic = new GameLogic();
    frame = new JFrame();
    frame.setSize(new Dimension(1000, 1000));
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setResizable(false);
    frame.setTitle("BrickBreaker");
    frame.add(new DrawingBoard());
    frame.setVisible(true);
    timer = new Timer(1000 / 60, this);
    timer.start();
  }

  class DrawingBoard extends JPanel {
    public void paintComponent(Graphics g) {
      super.paintComponent(g);
      g.setColor(Color.WHITE);
      g.fillRect(0, 0, getWidth(), getHeight());
      
      //Bricks
      for (int i = 0; i < 4; i++) {
    	  for(int j = 0; j < 10; j++) {
    		  g.fillRect(i, j, 100, 50);
    		  g.setColor(Color.BLUE);
    	  }
      }
      g.setColor(Color.RED);
      int[] paddle = gameLogic.getPaddle();
      g.fillRect(paddle[0], paddle[1], 30, 30);
      g.setColor(Color.YELLOW);
      int[] ball = gameLogic.getBall();
      g.fillOval(ball[0], ball[1], 20, 20);
    }
  }

  public static void main(String[] args) {
    new GUI();
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    //gameLogic.update();
    frame.repaint();
  }
}