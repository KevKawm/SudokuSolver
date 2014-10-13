package com.webs.kevkawmcode.SudokuSolver;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import com.webs.kevkawmcode.SudokuSolver.Solver.Board;

public class Display extends JPanel implements Runnable{
	private static final long serialVersionUID = 1L;

	Thread thread = new Thread(this);
	
	public Frame frame;
	
	Board board;
	
	public Display(Frame frame) {
		this.frame = frame;
		thread.start();
	}

	@Override
	public void run() {
		
		board = new Board(this);
		
		while(true){
			repaint();
		}
	}

	@Override
	public void paintComponent(Graphics g){
		g.clearRect(0,0,frame.getWidth(),frame.getHeight());
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, frame.getWidth(), frame.getHeight());
		board.render(g);
	}
	
}
