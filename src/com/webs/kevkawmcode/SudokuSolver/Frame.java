package com.webs.kevkawmcode.SudokuSolver;

import javax.swing.JFrame;

public class Frame extends JFrame {
	private static final long serialVersionUID = 1L;
	
	int width = 800, height = 600;
	
	public static void main(String[] args) {
		new Frame();
	}

	public Frame(){
		new JFrame();
		
		Display display = new Display(this);
		this.add(display);
		
		this.setSize(width,height);
		this.setTitle("Sudoku Solver");
		this.setVisible(true);
		this.setResizable(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.addKeyListener(display);
	}

}
